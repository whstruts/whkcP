package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.YZYGOODS;
import hykx.ds.whkc.bean.mateCode;
import hykx.ds.whkc.bean.ysbdd;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RabbitListener(queues = "topic.HBDMMateGoods")
public class TopicReceiverMateGoods {
    @Autowired
    private KhzlService khzlService;
    @RabbitHandler
    public void process(String message) throws IOException
    {
        int i_pos;
        String s_json;
        try {
            i_pos = message.indexOf("{");
            s_json = message.substring(i_pos);
            JSONObject jsonObject = JSONObject.fromObject(s_json);
            mateCode mateCode = (mateCode) JSONObject.toBean(jsonObject, mateCode.class);
            khzlService.UpdateMateGoods(mateCode);
            System.out.println("UpdateMateGoods,"+s_json);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            log.error("UpdateMateGoods", e);
        }
    }
}
