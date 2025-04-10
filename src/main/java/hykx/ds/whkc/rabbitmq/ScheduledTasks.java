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
    public void reportCurrentTime()throws Exception {
        List<ysbddhz> listysbddhz = khzlService.getysbddhzs();
        for (int i = 0; i < listysbddhz.size(); i++) {
            ysbddhz ddhz = listysbddhz.get(i);
            ddhz.setUserName("JSDFYY");
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

            String routeKey = "topic.MIDOrder";

            String exchange = "topicExchange";

            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

            System.out.println("sendMIDOrder : " + context);

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
    public void reportCurrentTimeCommodityYBM()throws Exception {
        System.out.println("取中台数据:开始");
        List<YZYGOODS> list = MiddleService.GetYZYGOODSByUser("JSDFYY");
        System.out.println("取到中台数据:"+list.size()+"行");
        for(YZYGOODS yzygoods:list)
        {
            if(yzygoods.getPCH()==null) yzygoods.setPCH("");
            if(yzygoods.getTXM()==null) yzygoods.setTXM("");
            if(yzygoods.getOtc()==null) yzygoods.setOtc("");
            if(yzygoods.getYPDM()==null) yzygoods.setYPDM("");
            if(yzygoods.getJX()==null) yzygoods.setJX("");
            if(yzygoods.getPZWH()==null) yzygoods.setPZWH("");
            if(yzygoods.getCDDM()==null) yzygoods.setCDDM("");
            if(yzygoods.getPH()==null) yzygoods.setPH("");
            if(yzygoods.getYXQ()==null) yzygoods.setYXQ("");
            if(yzygoods.getSCRQ()==null) yzygoods.setSCRQ("");
            if(yzygoods.getGG()==null) yzygoods.setGG("");
            khzlService.insertYZYGOODS(yzygoods);
        }
        System.out.println("取中台数据:结束");
    }
    @Scheduled(fixedDelay = 60*60*1000)
    public void reportCurrentTimeCommodityPGBY()throws Exception {
        System.out.println("取中台裂变数据:开始");
        List<YZYGOODS> list = MiddleService.GetPGBYByUser("JSDFYY");
        System.out.println("取中台裂变数据:"+list.size()+"行");
        khzlService.deleteYZYGOODSP();
        list.forEach(yzygoods -> {
            System.out.println("取中台裂变数据:" + yzygoods);
            if(yzygoods.getPCH()==null) yzygoods.setPCH("");
            if(yzygoods.getTXM()==null) yzygoods.setTXM("");
            if(yzygoods.getOtc()==null) yzygoods.setOtc("");
            if(yzygoods.getYPDM()==null) yzygoods.setYPDM("");
            if(yzygoods.getJX()==null) yzygoods.setJX("");
            if(yzygoods.getPZWH()==null) yzygoods.setPZWH("");
            if(yzygoods.getCDDM()==null) yzygoods.setCDDM("");
            if(yzygoods.getPH()==null) yzygoods.setPH("");
            if(yzygoods.getYXQ()==null) yzygoods.setYXQ("");
            if(yzygoods.getSCRQ()==null) yzygoods.setSCRQ("");
            if(yzygoods.getGG()==null) yzygoods.setGG("");
            khzlService.insertYZYGOODSP(yzygoods);
        });
        System.out.println("取中台裂变数据:结束");
    }
}
