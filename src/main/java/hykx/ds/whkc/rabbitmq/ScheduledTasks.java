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
    @Scheduled(fixedDelay = 60*60*1000)
    //@Scheduled(fixedDelay = 1000)
    public void reportCurrentTimeCommodityYBM()throws Exception {
        System.out.println("取中台数据:开始");
        List<MyGoodsEntity> list = MiddleService.GetMyGoodsEntityByUse("13017319628");
        YZYGOODS yzygoods = new YZYGOODS();
        for(MyGoodsEntity myGoodsEntity:list)
        {
            yzygoods.setBZ(Integer.valueOf(myGoodsEntity.getBz()));
            yzygoods.setGG(myGoodsEntity.getGg());
            yzygoods.setDW(myGoodsEntity.getDw());
            yzygoods.setCDMC(myGoodsEntity.getCdmc());
            yzygoods.setJX(myGoodsEntity.getJx());
            yzygoods.setPH(myGoodsEntity.getPh());
            yzygoods.setPCH(String.valueOf(myGoodsEntity.getPch()));
            yzygoods.setGoods_id_s(myGoodsEntity.getId());
            yzygoods.setGoods_name(myGoodsEntity.getYpmc());
            yzygoods.setGoods_number(myGoodsEntity.getSl());
            yzygoods.setIs_on_sale(1);
            yzygoods.setISRETAIL(myGoodsEntity.getIsretail());
            yzygoods.setGoods_sn(myGoodsEntity.getId());
            yzygoods.setSCRQ(myGoodsEntity.getScrq());
            yzygoods.setYXQ(myGoodsEntity.getYxq());
            yzygoods.setShop_price(myGoodsEntity.getDj());
            yzygoods.setZBZ(Integer.valueOf(myGoodsEntity.getZbz()));
            yzygoods.setTXM(myGoodsEntity.getTm());
            yzygoods.setPZWH(myGoodsEntity.getPzwh());
            khzlService.insertYZYGOODS(yzygoods);
        }
        System.out.println("取中台数据:结束");
    }
}
