package hykx.ds.whkc.rabbitmq;


import hykx.ds.whkc.bean.STGoods;
import hykx.ds.whkc.bean.ysbdd;
import hykx.ds.whkc.bean.ysbddhz;
import hykx.ds.whkc.bean.ysbddmx;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
    public class ScheduledTasks {
        @Autowired
        private AmqpTemplate rabbitTemplate;
        @Autowired
        private KhzlService khzlService;
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        @Scheduled(fixedDelay = 60*1000)
        public void reportCurrentTime()throws Exception {
        List<ysbddhz> listysbddhz = khzlService.getysbddhzs();
        for (int i = 0; i < listysbddhz.size(); i++) {
             ysbddhz ddhz = listysbddhz.get(i);
             List<ysbddmx> listDDMX = khzlService.getysbddmxbydjbh(ddhz.getDjbh());
             ysbdd dd = new ysbdd();
            if(listDDMX.size()>0)
            {
                dd.setYsbddhz(ddhz);
                dd.setYsbddmxes(listDDMX);
            }
            else
                return;
            khzlService.updateysbddhz(ddhz.getDjbh());//更新订单汇总状态

            JSONObject data = JSONObject.fromObject(dd);

            System.out.println("GetDD,Name:" + data.toString());

            String context = data.toString();

            String routeKey = "topic.ysbdd";

            String exchange = "topicExchange";

            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

            System.out.println("sendDDTest : " + context);

            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        }
    }
        @Scheduled(fixedDelay = 20*60*1000)
        public void reportCurrentTime1()throws Exception {
        List<STGoods> listGoods = khzlService.getSTGoods();
        for (int i = 0; i < listGoods.size(); i++) {

            JSONObject data = JSONObject.fromObject(listGoods.get(i));

            System.out.println("GetSTGoods,Value:" + data.toString());

            String routeKey = "topic.STGoods";

            String exchange = "topicExchange";

            String context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + data.toString();

            System.out.println("sendSTGoods : " + context);

            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        }
    }

}
