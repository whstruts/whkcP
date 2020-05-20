package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.YZYGOODS;
import hykx.ds.whkc.tools.JSONChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Listener {
    @Autowired
    private KhzlService khzlService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "${rabbitmq.fanout.queue.b}")
    public void handleQueue(String content) throws IOException {
        int i_pos;
        int i_pos2;
        String s_json;
        YZYGOODS yzygoods = new YZYGOODS();
        i_pos = content.indexOf("DELETEYZYGOODS");
        i_pos2 = content.indexOf("UPDATEYZYGOODS");
        if(i_pos>0){
            khzlService.deleteYZYGOODS();
            System.out.println("接收者 message," + content);
        }else if(i_pos2>0){
            khzlService.updateYZYGOODS();
            System.out.println("接收者 message," + content);
        }
        else {
            i_pos = content.indexOf("{");
            s_json = content.substring(i_pos);
            yzygoods = (YZYGOODS) JSONChange.jsonToObj(yzygoods, s_json);
            khzlService.insertYZYGOODS(yzygoods);
            System.out.println("接收者 TopicReceiverYZYGOODS," + s_json);

            List<YZYGOODS> yzygoods_fix_list = khzlService.getYZYGOODS_FIX(yzygoods.getGoods_sn());

            if(yzygoods_fix_list.size()==0)
            {
                khzlService.insertYZYGOODS_FIX(yzygoods);
            }
        }
    }
}
