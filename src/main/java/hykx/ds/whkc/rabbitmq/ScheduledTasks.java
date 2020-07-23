package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.*;
import hykx.ds.whkc.tools.JSONChange;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
    public class ScheduledTasks {
        @Autowired
        private AmqpTemplate rabbitTemplate;
        @Autowired
        private KhzlService khzlService;
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        @Scheduled(fixedDelay = 600*1000)
        public void reportCurrentTime()throws Exception {

            List<Khzl> listsKH = khzlService.getKH();
            for (int i = 0; i < listsKH.size(); i++) {
                Khzl khzl = listsKH.get(i);

                System.out.println("GetKH,Name:" + JSONChange.objToJson(khzl));

                String context = JSONChange.objToJson(khzl);

                String routeKey = "topic.whkhcx";

                String exchange = "topicExchange";

                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

                System.out.println("sendKHTest : " + context);

                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
            }
        }
        @Scheduled(fixedDelay = 600*1000) //20190605 whstruts ERP订单状态同步
        public void reportCurrentTime4()throws Exception {

            List<Ddzt> listsDD = khzlService.getDD();
            for (int i = 0; i < listsDD.size(); i++) {
                Ddzt ddzt = listsDD.get(i);

                System.out.println("GetDD,Name:" + JSONChange.objToJson(ddzt));

                String context = JSONChange.objToJson(ddzt);

                String routeKey = "topic.whds";

                String exchange = "topicExchange";

                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

                System.out.println("sendKHTest : " + context);

                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
            }
        }

    @Scheduled(fixedDelay = 3600*1000) //20190826 whstruts ERP电子发票同步 每小时同步一次七天内的数据
    public void reportCurrentTime5()throws Exception {

        List<DZFP> listsDZFP = khzlService.getdzfp();
        for (int i = 0; i < listsDZFP.size(); i++) {
            DZFP dzfp = listsDZFP.get(i);

            System.out.println("GetDZFP,Name:" + JSONChange.objToJson(dzfp));

            String context = JSONChange.objToJson(dzfp);

            String routeKey = "topic.whdzfp";

            String exchange = "topicExchange";

            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

            System.out.println("sendDZFP : " + context);

            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        }
    }

        @Scheduled(fixedDelay = 600*1000)
        public void reportCurrentTime1()throws Exception {

            List<SpKC> listsKC = khzlService.getKC();
            for (int i = 0; i < listsKC.size(); i++) {
                SpKC spkc = listsKC.get(i);

                System.out.println("GetKC,Name:" + JSONChange.objToJson(spkc));

                String context = JSONChange.objToJson(spkc);

                String routeKey = "topic.whkcx";

                String exchange = "topicExchange";

                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

                System.out.println("sendKCTest : " + context);

                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
            }
        }

        @Scheduled(fixedDelay = 600*1000)
        public void reportCurrentTime2()throws Exception {
            Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
            int hour = c.get(Calendar.HOUR_OF_DAY);
            List<Spzl> listSP = khzlService.getSP();
            for (int i = 0; i < listSP.size(); i++) {
                Spzl spzl = listSP.get(i);

                System.out.println("GetSP,Name:" + JSONChange.objToJson(spzl));

                String context = JSONChange.objToJson(spzl);

                String routeKey = "topic.whkspx";

                String exchange = "topicExchange";

                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

                System.out.println("sendSPTest : " + context);

                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
            }
            System.out.println("The time is now " + dateFormat.format(new Date()));
        }

    @Scheduled(fixedDelay = 60*1000)
    public void reportCurrentTime3()throws Exception {
//        System.out.println("开始处理订单 : " + dateFormat.format(new Date()));
//        List<ERPddhz> listsddHZ = khzlService.getERPDD();
//        String cgjhbh = "";
//        String xsjhbh = "";
//        for (int i = 0; i < listsddHZ.size(); i++) {
//             ERPddhz ddhz = listsddHZ.get(i);
//             khzlService.DoERPDD(ddhz.getKpbh(),cgjhbh,xsjhbh);
//             khzlService.updateERPDD(ddhz.getKpbh());
//            System.out.println("订单已经处理 : " + ddhz.getKpbh());
//        }
    }
}
