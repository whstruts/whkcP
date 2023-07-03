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
@RabbitListener(queues = "topic.HBLZOrderBack")
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
        //khzlService.ItoDDHZs(dd.getYsbddhz());
        String aa = jsonObject.get("ysbddmxes").toString();
        JSONArray array = JSONArray.fromObject(aa);
        for(int i=0;i<array.size();i++)
        {
              ERPddmx mx = (ERPddmx) JSONObject.toBean(JSONObject.fromObject(array.get(i)),ERPddmx.class);
              if(mx.getBeizhu().length()>0)
                  mx.setStatus(0);
              else
                  mx.setStatus(1);
              khzlService.updateysbddmx(mx);
        }
        //khzlService.DoERPDD(dd.getYsbddhz().getDjbh(),"","");
        //khzlService.updateERPDD(dd.getYsbddhz().getDjbh());
        System.out.println("接收者 TopicReceiverDD,"+s_json);
        Thread.sleep(10000);
    }
}
