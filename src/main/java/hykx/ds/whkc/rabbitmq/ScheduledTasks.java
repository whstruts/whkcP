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
        private KhzlService khzlService;
    @Scheduled(fixedDelay = 60*1000)
    public void reportCurrentTimeGY()throws Exception {
        List<ysbddhz> listysbddhz = khzlService.getysbddhzs();
        for (int i = 0; i < listysbddhz.size(); i++) {
            ysbddhz ddhz = listysbddhz.get(i);
            ddhz.setUserName("YYKR");
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
            System.out.println(dd.toString());
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
    //@Scheduled(fixedDelay = 1000)
    public void reportCurrentTimeCommodityYBM()throws Exception {
        System.out.println("取中台数据:开始");
        List<YZYGOODS> list = MiddleService.GetYZYGOODSByUser("18692180722");
        System.out.println("取到中台数据:"+list.size()+"行");
        for(YZYGOODS yzygoods:list)
        {
            khzlService.insertYZYGOODS(yzygoods);
        }
        System.out.println("取中台数据:结束");
    }

    @Scheduled(fixedDelay = 60*60*1000)
    //@Scheduled(fixedDelay = 1000)
    public void reportCurrentTimeCommodityHYGY()throws Exception {
        System.out.println("取中台华源工业公司数据:开始");
        List<YZYGOODS> list = MiddleService.GetHYGYGoods("YYKR");

        System.out.println("取中台华源工业公司数据:"+list.size()+"行");
        for(YZYGOODS yzygoods:list)
        {
            khzlService.insertYZYGOODS(yzygoods);
        }
        System.out.println("取中台华源工业公司数据:结束");
    }

    @Scheduled(fixedDelay = 60*60*1000)
    public void reportCurrentTimeCommodityPGBY()throws Exception {
        System.out.println("取批购包邮数据:开始");
        List<YZYGOODS> list = MiddleService.GetPGBY("YYKR");
        List<YZYGOODS> list_x = MiddleService.GetPGBY_X("YYKR");
        list.addAll(list_x);
        khzlService.deleteYZYGOODSP();
        list.forEach(yzygoods -> {
            System.out.println("批购包邮数据:" + yzygoods);
            try {
                khzlService.insertYZYGOODSP(yzygoods);
            }
            catch (Exception e)
            {
                System.out.println("写入批购包邮数据异常:" + e.toString());
            }
        });
        System.out.println("取批购包邮数据:结束");
    }

}
