package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.entity.YZYGOODS;
import hykx.ds.whkc.tools.JSONChange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RabbitListener(queues = "topic.HBLZGoodsAllPP")
public class TopicReceiverYZYGOODSALLPP1 {
    @Autowired
    private KhzlService khzlService;
    @RabbitHandler
    public void process(String message) throws IOException
    {
        int i_pos;
        int i_pos2;
        String s_json;
        YZYGOODS yzygoods = new YZYGOODS();
        i_pos = message.indexOf("DELETEYZYGOODS");
        i_pos2 = message.indexOf("UPDATEYZYGOODS");
        if(i_pos>0){
            khzlService.deleteYZYGOODS();
            System.out.println("接收者 message," + message);
        }else if(i_pos2>0){
            khzlService.updateYZYGOODS();
            System.out.println("接收者 message," + message);
        }
        else {
            i_pos = message.indexOf("{");
            s_json = message.substring(i_pos);
            yzygoods = (YZYGOODS) JSONChange.jsonToObj(yzygoods, s_json);
            khzlService.insertYZYGOODS(yzygoods);
            System.out.println("接收者 TopicReceiverYZYGOODSALLPP," + s_json);

            List<YZYGOODS>  yzygoods_fix_list = khzlService.getYZYGOODS_FIX(yzygoods.getGoods_sn());

            if(yzygoods_fix_list.size()==0)
            {
                if(yzygoods.getCDDM().isEmpty())
                    yzygoods.setCDDM("***");
                khzlService.insertYZYGOODS_FIX(yzygoods);
            }
        }
    }
}
