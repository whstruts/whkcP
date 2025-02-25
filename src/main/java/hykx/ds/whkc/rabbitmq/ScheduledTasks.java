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
    @Scheduled(fixedDelay = 60*1000)
    public void reportCurrentTimeGY()throws Exception {
        List<ysbddhz> listysbddhz = khzlService.getysbddhzs();
        for (int i = 0; i < listysbddhz.size(); i++) {
            ysbddhz ddhz = listysbddhz.get(i);
            ddhz.setUserName("HBNAT");
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
            MiddleService.saveOrder2GY(dd);
        }
    }
//    @Scheduled(cron="0 0 1 * * ?")
//    private void DownDrug(){
//        try{
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            khzlService.unOnSale();
//            System.out.println(df.format(new Date()));
//        }catch (Exception e) {
//            log.error("全部华源商品下架", e);
//        }
//    }
    @Scheduled(fixedDelay = 60*60*1000)
    public void reportCurrentTimeCommodityHYGY()throws Exception {
        try{
            System.out.println("取中台华源工业公司数据:开始");
            List<YZYGOODS> list = MiddleService.GetHYGYGoods("HBNAT");

            System.out.println("取中台华源工业公司数据:"+list.size()+"行");
            if(list.size()>10000)
                khzlService.unOnSale();
            for(YZYGOODS yzygoods:list)
            {
                khzlService.insertYZYGOODS(yzygoods);
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df.format(new Date()));
            System.out.println("取中台华源工业公司数据:结束");

            System.out.println("取爬虫数据:开始");

            List<YZYGOODS> list1 = MiddleService.GetPPGoods("HBNAT");

            System.out.println("取爬虫数据:"+list1.size()+"行");

            for(YZYGOODS yzygoods:list1)
            {
                khzlService.insertYZYGOODS(yzygoods);
            }
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df1.format(new Date()));
            System.out.println("取爬虫数据:结束");

        }catch (Exception e) {
            log.error("reportCurrentTimeCommodityHYGY", e);
        }
    }

//    @Scheduled(fixedDelay = 60*60*1000)
//    public void reportCurrentTimeCommodityPP()throws Exception {
//        try{
//            System.out.println("取爬虫数据:开始");
//            List<YZYGOODS> list = MiddleService.GetPPGoods("HBNAT");
//
//            System.out.println("取爬虫数据:"+list.size()+"行");
//
//            for(YZYGOODS yzygoods:list)
//            {
//                khzlService.insertYZYGOODS(yzygoods);
//            }
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println(df.format(new Date()));
//            System.out.println("取爬虫数据:结束");
//        }catch (Exception e) {
//            log.error("reportCurrentTimeCommodityPP", e);
//        }
//    }

}
