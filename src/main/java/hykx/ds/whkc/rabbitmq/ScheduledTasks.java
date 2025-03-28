package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.MiddleService;
import hykx.ds.whkc.entity.*;
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
//            ddhz.setUserName("HNYZT");
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
//            String routeKey = "topic.MIDOrder";
//
//            String exchange = "topicExchange";
//
//            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;
//
//            System.out.println("sendMIDOrder : " + context);
//
//            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
//        }
//    }

    @Scheduled(fixedDelay = 60*1000)
    public void reportCurrentTimeGY()throws Exception {
        List<ysbddhz> listysbddhz = khzlService.getysbddhzs();
        for (int i = 0; i < listysbddhz.size(); i++) {
            ysbddhz ddhz = listysbddhz.get(i);
            ddhz.setUserName("HNYZT");
            List<ysbddmx> listDDMX = khzlService.getysbddmxbydjbh(ddhz.getDjbh());
            ysbdd dd = new ysbdd();
            if(listDDMX.size()>0)
            {
                dd.setYsbddhz(ddhz);
                dd.setYsbddmxes(listDDMX);
            }
            else {
                khzlService.updateysbddhz(ddhz.getDjbh());
                continue;
            }
            khzlService.updateysbddhz(ddhz.getDjbh());//更新订单汇总状态
            //MiddleService.saveOrder2GY(dd);
            //System.out.println("saveOrder2GY,Data:" + dd.toString());
            //            JSONObject data = JSONObject.fromObject(dd);

            JSONObject data = JSONObject.fromObject(dd);

            System.out.println("GetDD,Name:" + data.toString());

            String context = data.toString();

            String routeKey = "topic.YZTERPOrder";

            String exchange = "topicExchange";

            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

            System.out.println("sendYZTERPOrder : " + context);

            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        }
    }

    @Scheduled(fixedDelay = 30*60*1000)
    public void reportCurrentTimeERPSP()throws Exception {
        List<erpsp> erpsps = khzlService.getERPSP();
        for (int i = 0; i < erpsps.size(); i++) {
            erpsp sp = erpsps.get(i);

            JSONObject data = JSONObject.fromObject(sp);

            System.out.println("GetYZTERPGoods,Data:" + data.toString());

            String context = data.toString();

            String routeKey = "topic.YZTERPGoods";

            String exchange = "topicExchange";

            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

            System.out.println("sendYZTERPGoods : " + context);

            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        }
    }
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

    @Scheduled(fixedDelay = 60*60*1000)
    public void reportCurrentTimeCommodityXYY()throws Exception {
        try {
            System.out.println("取药帮忙数据:开始");
            List<MyGoodsEntity> list = MiddleService.GetYBMG2MGEByUser("HNYZT");
            for (MyGoodsEntity myGoodsEntity : list) {
                if(myGoodsEntity.getJx()==null)
                    myGoodsEntity.setJx("");
                khzlService.insertYZYGOODS(myGoodsEntity);
            }
            System.out.println("取药帮忙数据:结束");
        }
        catch (Exception e)
        {
            System.out.println("reportCurrentTimeCommodityXYY:"+e.toString());
        }
    }

    @Scheduled(fixedDelay = 30*60*1000)
    public void reportCurrentTimeCommodityHYGY()throws Exception {
        System.out.println("取中台华源工业公司数据:开始");
        List<YZYGOODS> list = MiddleService.GetHYGYGoods("HNYZT");

        System.out.println("取中台华源工业公司数据:"+list.size()+"行");
//        if(list.size()>10000)
//            khzlService.unOnSale();
        for(YZYGOODS yzygoods:list)
        {
            if(yzygoods.getCDDM()==null) yzygoods.setCDDM("");
            if(yzygoods.getPCH()==null) yzygoods.setPCH("");
            if(yzygoods.getOtc()==null) yzygoods.setOtc("");
            if(yzygoods.getYpbh()==null) yzygoods.setYpbh("");
            khzlService.insertYZYGOODSGY(yzygoods);
        }
        System.out.println("取中台华源工业公司数据:结束");
    }

    @Scheduled(fixedDelay = 60*60*1000)
    public void reportCurrentTimeCommodityPGBY()throws Exception {
        System.out.println("取批购包邮数据:开始");
        List<YZYGOODS> list = MiddleService.GetPGBY("HNYZT");
        int list_size = list.size();
        if(list_size>0) khzlService.deleteYZYGOODSP();
        list.forEach(yzygoods -> {
            System.out.println("批购包邮数据:" + yzygoods);
            khzlService.insertYZYGOODSP(yzygoods);
            khzlService.insertYZYGOODSPGBY(yzygoods);
        });
        System.out.println("取批购包邮数据:结束");
    }

}
