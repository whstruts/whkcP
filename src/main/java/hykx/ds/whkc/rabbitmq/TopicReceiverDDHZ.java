package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.ERPddhz;
import hykx.ds.whkc.tools.JSONChange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
//@RabbitListener(queues = "topic.erpddhz")
//public class TopicReceiverDDHZ {
//    @Autowired
//    private KhzlService khzlService;
//    @RabbitHandler
//    public void process(String message) throws IOException
//    {
//        int i_pos;
//        String s_json;
//        ERPddhz ddhz = new ERPddhz();
//        i_pos = message.indexOf("{");
//        s_json = message.substring(i_pos);
//        ddhz = (ERPddhz) JSONChange.jsonToObj(ddhz,s_json);
//       // khzlService.ItoDDHZs(ddhz);
//        System.out.println("接收者 TopicReceiverDDHZ,"+s_json);
//    }
//}
