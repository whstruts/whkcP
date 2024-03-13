package hykx.ds.whkc.rabbitmq;


import hykx.ds.whkc.bean.ysbdd;
import hykx.ds.whkc.bean.ysbddmx;

import hykx.ds.whkc.mapper.KhzlMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RabbitListener(queues = "topic.JZOrderBack")
public class TopicReceiverHYDD {
    @Autowired
    KhzlMapper khzlMapper;
    @RabbitHandler
    public void process(String message) throws IOException
    {
        int i_pos;
        double total = 0.00;
        String s_json;
        i_pos = message.indexOf("{");
        s_json = message.substring(i_pos);
        JSONObject jsonObject = JSONObject.fromObject(s_json);
        ysbdd dd = (ysbdd) JSONObject.toBean(jsonObject, ysbdd.class);
        String aa = jsonObject.get("ysbddmxes").toString();
        JSONArray array = JSONArray.fromObject(aa);
        for(int i=0;i<array.size();i++)
        {
            ysbddmx mx = (ysbddmx) JSONObject.toBean(JSONObject.fromObject(array.get(i)), ysbddmx.class);
            mx.setCgdj(mx.getCgdj());
            mx.setCgje(mx.getCgje());
            total = total + mx.getCgje();
            khzlMapper.updateddmx(mx);
        }
        khzlMapper.updateddhz(dd.getYsbddhz());
        System.out.println("接收者 TopicReceiverHYDD,"+s_json);
    }
}
