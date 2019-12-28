package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.*;
import hykx.ds.whkc.tools.JSONChange;
import net.sf.json.JSONObject;
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
//        @Scheduled(fixedDelay = 600*1000)
//        public void reportCurrentTimeBBSPZL()throws Exception {
//
//            List<BBSPZL> listsBBSPZL = khzlService.getBBSPZL();
//            for (int i = 0; i < listsBBSPZL.size(); i++) {
//                BBSPZL bbspzl = listsBBSPZL.get(i);
//
//                System.out.println("GetBBSPZL,Name:" + JSONChange.objToJson(bbspzl));
//
//                String context = JSONChange.objToJson(bbspzl);
//
//                String routeKey = "topic.bbspzl";
//
//                String exchange = "topicExchange";
//
//                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//                System.out.println("sendBBSPZL : " + context);
//
//                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//            }
//        }

//        @Scheduled(fixedDelay = 600*1000)
//        public void reportCurrentTimeBBSPKC()throws Exception {
//
//        List<BBSPKC> listsBBSPKC = khzlService.getBBSPKC();
//            for (int i = 0; i < listsBBSPKC.size(); i++) {
//                BBSPKC bbspkc = listsBBSPKC.get(i);
//
//                System.out.println("GetBBSPKC,Name:" + JSONChange.objToJson(bbspkc));
//
//                String context = JSONChange.objToJson(bbspkc);
//
//                String routeKey = "topic.bbspkc";
//
//                String exchange = "topicExchange";
//
//                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//                System.out.println("sendBBSPKC : " + context);
//
//                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//            }
//        }

//        @Scheduled(fixedDelay = 600*1000)
//        public void reportCurrentTimeBBSPJG()throws Exception {
//
//            List<BBSPJG> listsBBSPJG = khzlService.getBBSPJG();
//            for (int i = 0; i < listsBBSPJG.size(); i++) {
//                BBSPJG bbspjg = listsBBSPJG.get(i);
//
//                System.out.println("GetBBSPJG,Name:" + JSONChange.objToJson(bbspjg));
//
//                String context = JSONChange.objToJson(bbspjg);
//
//                String routeKey = "topic.bbspjg";
//
//                String exchange = "topicExchange";
//
//                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//                System.out.println("sendBBSPJG : " + context);
//
//                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//            }
//        }

//        @Scheduled(fixedDelay = 600*1000)
//        public void reportCurrentTimeBBSPPH()throws Exception {
//
//            List<BBSPPH> listsBBSPPH = khzlService.getBBSPPH();
//            for (int i = 0; i < listsBBSPPH.size(); i++) {
//                BBSPPH bbspph = listsBBSPPH.get(i);
//
//                System.out.println("GetBBSPJG,Name:" + JSONChange.objToJson(bbspph));
//
//                String context = JSONChange.objToJson(bbspph);
//
//                String routeKey = "topic.bbspph";
//
//                String exchange = "topicExchange";
//
//                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//                System.out.println("sendBBSPPH : " + context);
//
//                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//            }
//        }
//        public void reportCurrentTime()throws Exception {
//
//            List<Khzl> listsKH = khzlService.getKH();
//            for (int i = 0; i < listsKH.size(); i++) {
//                Khzl khzl = listsKH.get(i);
//
//                System.out.println("GetKH,Name:" + JSONChange.objToJson(khzl));
//
//                String context = JSONChange.objToJson(khzl);
//
//                String routeKey = "topic.whkhcx";
//
//                String exchange = "topicExchange";
//
//                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//                System.out.println("sendKH : " + context);
//
//                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//            }
//        }
//        @Scheduled(fixedDelay = 600*1000) //20190605 whstruts ERP订单状态同步
//        public void reportCurrentTime4()throws Exception {
//
//            List<Ddzt> listsDD = khzlService.getDD();
//            for (int i = 0; i < listsDD.size(); i++) {
//                Ddzt ddzt = listsDD.get(i);
//
//                System.out.println("GetDD,Name:" + JSONChange.objToJson(ddzt));
//
//                String context = JSONChange.objToJson(ddzt);
//
//                String routeKey = "topic.whds";
//
//                String exchange = "topicExchange";
//
//                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//                System.out.println("sendDD : " + context);
//
//                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//            }
//        }
//
//        @Scheduled(fixedDelay = 3600*1000) //20190826 whstruts ERP电子发票同步 每小时同步一次七天内的数据
//        public void reportCurrentTime5()throws Exception {
//
//        List<DZFP> listsDZFP = khzlService.getdzfp();
//        for (int i = 0; i < listsDZFP.size(); i++) {
//            DZFP dzfp = listsDZFP.get(i);
//
//            System.out.println("GetDZFP,Name:" + JSONChange.objToJson(dzfp));
//
//            String context = JSONChange.objToJson(dzfp);
//
//            String routeKey = "topic.whdzfp";
//
//            String exchange = "topicExchange";
//
//            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//            System.out.println("sendDZFP : " + context);
//
//            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//        }
//    }
//
//        @Scheduled(fixedDelay = 600*1000)
//        public void reportCurrentTime1()throws Exception {
//
//            List<SpKC> listsKC = khzlService.getKC();
//            for (int i = 0; i < listsKC.size(); i++) {
//                SpKC spkc = listsKC.get(i);
//
//                System.out.println("GetKC,Name:" + JSONChange.objToJson(spkc));
//
//                String context = JSONChange.objToJson(spkc);
//
//                String routeKey = "topic.whkcx";
//
//                String exchange = "topicExchange";
//
//                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//                System.out.println("sendKCTest : " + context);
//
//                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//            }
//        }
//
//        @Scheduled(fixedDelay = 600*1000)
//        public void reportCurrentTime2()throws Exception {
//            Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
//            int hour = c.get(Calendar.HOUR_OF_DAY);
//            List<Spzl> listSP = khzlService.getSP();
//            for (int i = 0; i < listSP.size(); i++) {
//                Spzl spzl = listSP.get(i);
//
//                System.out.println("GetSP,Name:" + JSONChange.objToJson(spzl));
//
//                String context = JSONChange.objToJson(spzl);
//
//                String routeKey = "topic.whkspx";
//
//                String exchange = "topicExchange";
//
//                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//                System.out.println("sendSPTest : " + context);
//
//                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//            }
//            System.out.println("The time is now " + dateFormat.format(new Date()));
//        }
//
//        @Scheduled(fixedDelay = 60*1000)
//        public void reportCurrentTime3()throws Exception {
//        System.out.println("开始处理订单 : " + dateFormat.format(new Date()));
//        List<ERPddhz> listsddHZ = khzlService.getERPDD();
//        String cgjhbh = "";
//        String xsjhbh = "";
//        for (int i = 0; i < listsddHZ.size(); i++) {
//             ERPddhz ddhz = listsddHZ.get(i);
//             khzlService.DoERPDD(ddhz.getKpbh(),cgjhbh,xsjhbh);
//            // khzlService.updateERPDD(ddhz.getKpbh());
//            System.out.println("订单已经处理 : " + ddhz.getKpbh());
//        }
//    }

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
//            for(int j = 0;j<listDDMX.size();j++)
//            {
//                khzlService.UpdateDDMX(listDDMX.get(j));  //更新订单明细状态
//            }
//
            JSONObject data = JSONObject.fromObject(dd);
//
            System.out.println("GetDD,Name:" + data.toString());
//
//
            String context = data.toString();
//
            String routeKey = "topic.erpdd";

            String exchange = "topicExchange";

            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

            System.out.println("sendDDTest : " + context);

            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        }
    }
}
