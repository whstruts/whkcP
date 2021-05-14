package hykx.ds.whkc.rabbitmq;


import hykx.ds.whkc.bean.ERPDD;
import hykx.ds.whkc.bean.ERPddmx;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@RabbitListener(queues = "topic.erpdd")
public class TopicReceiverDD {
    @Autowired
    private KhzlService khzlService;
    @RabbitHandler
    public void process(String message) throws Exception
    {
        int i_pos;
        String s_json;
        i_pos = message.indexOf("{");
        s_json = message.substring(i_pos);
        JSONObject jsonObject = JSONObject.fromObject(s_json);
        ERPDD dd = (ERPDD) JSONObject.toBean(jsonObject,ERPDD.class);
        khzlService.ItoDDHZs(dd.getErpddhz());
        String aa = jsonObject.get("erpddmxs").toString();
        JSONArray array = JSONArray.fromObject(aa);
        for(int i=0;i<array.size();i++)
        {
              ERPddmx mx = (ERPddmx) JSONObject.toBean(JSONObject.fromObject(array.get(i)),ERPddmx.class);
              khzlService.ItoDDMXs(mx);
        }
        //khzlService.DoERPDD(dd.getErpddhz().getKpbh(),"","");
        //khzlService.updateERPDD(dd.getErpddhz().getKpbh());
        System.out.println("接收者 TopicReceiverDD,"+s_json);
        Thread.sleep(10000);
    }
}
