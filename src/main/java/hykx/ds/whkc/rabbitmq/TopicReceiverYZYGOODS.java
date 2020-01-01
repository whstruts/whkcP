package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.YZYGOODS;
import hykx.ds.whkc.tools.JSONChange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "topic.yzygoods")
public class TopicReceiverYZYGOODS {
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
            System.out.println("接收者 TopicReceiverYZYGOODS," + s_json);
        }
    }
}
