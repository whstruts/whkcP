package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.ysbkh;
import hykx.ds.whkc.tools.JSONChange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "topic.ysbkh")
public class TopicReceiverYSBKH {
    @Autowired
    private KhzlService khzlService;
    @RabbitHandler
    public void process(String message) throws IOException
    {
        int i_pos;
        String s_json;
        ysbkh ysbkh = new ysbkh();
        i_pos = message.indexOf("{");
        s_json = message.substring(i_pos);
        ysbkh = (ysbkh) JSONChange.jsonToObj(ysbkh,s_json);
        khzlService.ItoYSBKH(ysbkh);
        System.out.println("接收者 TopicReceiverYSBKH,"+s_json);
    }
}
