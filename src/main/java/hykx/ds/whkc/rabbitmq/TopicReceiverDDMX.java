package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.ERPddmx;
import hykx.ds.whkc.tools.JSONChange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
//@RabbitListener(queues = "topic.erpddmx")
//public class TopicReceiverDDMX {
//    @Autowired
//    private KhzlService khzlService;
//    @RabbitHandler
//    public void process(String message) throws IOException
//    {
//        int i_pos;
//        String s_json;
//        ERPddmx ddmx = new ERPddmx();
//        i_pos = message.indexOf("{");
//        s_json = message.substring(i_pos);
//        ddmx = (ERPddmx) JSONChange.jsonToObj(ddmx,s_json);
//     //   khzlService.ItoDDMXs(ddmx);
//        System.out.println("接收者 TopicReceiverDDMX,"+s_json);
//    }
//}
