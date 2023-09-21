package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.entity.ysbdd;
import hykx.ds.whkc.entity.ysbddhz;
import hykx.ds.whkc.entity.ysbddmx;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
    public class ScheduledTasks {
        @Autowired
        private AmqpTemplate rabbitTemplate;
        @Autowired
        private KhzlService khzlService;
//    @Scheduled(fixedDelay = 60*1000)
//    public void reportCurrentTime()throws Exception {
//        List<ysbddhz> listysbddhz = khzlService.getysbddhzs();
//        for (int i = 0; i < listysbddhz.size(); i++) {
//            ysbddhz ddhz = listysbddhz.get(i);
//            List<ysbddmx> listDDMX = khzlService.getysbddmxbydjbh(ddhz.getDjbh());
//            ysbdd dd = new ysbdd();
//            if(listDDMX.size()>0)
//            {
//                dd.setYsbddhz(ddhz);
//                dd.setYsbddmxes(listDDMX);
//            }
//            else
//                return;
//            khzlService.updateysbddhz(ddhz.getDjbh());//更新订单汇总状态
//
//            JSONObject data = JSONObject.fromObject(dd);
//
//            System.out.println("GetDD,Name:" + data.toString());
//
//            String context = data.toString();
//
//            String routeKey = "topic.HNWHOrder";
//
//            String exchange = "topicExchange";
//
//            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//            System.out.println("sendHNWHOrder : " + context);
//
//            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//        }
//    }
    @Scheduled(cron="0 0 1 * * ?")
    private void DownDrug(){
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            khzlService.unOnSale();
            System.out.println(df.format(new Date()));
        }catch (Exception e) {
            log.error("全部华源商品下架", e);
        }
    }
}
