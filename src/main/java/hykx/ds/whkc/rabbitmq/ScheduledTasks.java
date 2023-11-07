package hykx.ds.whkc.rabbitmq;


import hykx.ds.whkc.bean.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Slf4j
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

            String routeKey = "topic.JXTYOrder";

            String exchange = "topicExchange";

            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

            System.out.println("sendJXTYOrder : " + context);

            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        }
    }

    @Scheduled(fixedDelay = 60*1000)
    public void reportCurrentTimeX()throws Exception {
        List<xyyddhz> listxyyddhz = khzlService.getxyyddhzs();
        for (int i = 0; i < listxyyddhz.size(); i++) {
            xyyddhz ddhz = listxyyddhz.get(i);
            List<xyyddmx> listDDMX = khzlService.getxyyddmxbyOrderNo(ddhz.getOrder_no());
            xyydd dd = new xyydd();
            if(listDDMX.size()>0)
            {
                dd.setXyyddhz(ddhz);
                dd.setXyyddmxes(listDDMX);
            }
            else
                return;
            khzlService.updatexyyddhz(ddhz.getOrder_no());//更新订单汇总状态

            JSONObject data = JSONObject.fromObject(dd);

            System.out.println("GetDD,Name:" + data.toString());

            String context = data.toString();

            String routeKey = "topic.JXTYOrderX";

            String exchange = "topicExchange";

            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

            System.out.println("sendJXTYOrder : " + context);

            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        }
    }
        @Scheduled(fixedDelay = 5*60*1000)
        private void DownDrug(){
            try{
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                khzlService.unOnSale();
                System.out.println(df.format(new Date()));
            }catch (Exception e) {
                log.error("24小时以外商品下架", e);
            }
        }
}
