package hykx.ds.whkc.rabbitmq;


import hykx.ds.whkc.bean.YZYGOODS;
import hykx.ds.whkc.bean.ysbdd;
import hykx.ds.whkc.bean.ysbddhz;
import hykx.ds.whkc.bean.ysbddmx;
import hykx.ds.whkc.service.HYService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
        private static final int pageSize = 50;
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 30*60*1000)
    private void GetHYGoods(){
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            khzlService.deleteGoodsTmp();
            List<YZYGOODS> goodsList = HYService.GetHYGoods();
            int listSize = goodsList.size();
            while (goodsList.size() > 0) {
                log.info("直取华源数据剩余数据{}条，总数{}", goodsList.size(), listSize);
                if (goodsList.size() >= pageSize) {
                    try {
                        khzlService.batchInsert(goodsList.subList(0, pageSize));
                    } catch (Exception e) {
                        log.info("直取华源数据批量写入库存异常",
                                String.format("直取华源数据剩余数据%d条，总数%d条\n", goodsList.size(), listSize) + ExceptionUtils.getStackTrace(e));
                    }
                    goodsList.subList(0, pageSize).clear();
                } else {
                    try {
                        khzlService.batchInsert(goodsList);
                    } catch (Exception e) {
                        log.info("直取华源数据批量写入库存异常",
                                String.format("直取华源数据剩余数据%d条，总数%d条\n", goodsList.size(), listSize) + ExceptionUtils.getStackTrace(e));
                    }
                    goodsList.clear();
                    break;
                }
            }
            khzlService.insertTMP2YZYGOODS();
            khzlService.insertTMP2FIX();
            khzlService.updateTMP2YZYGOODS();
            System.out.println(df.format(new Date()));
        }catch (Exception e) {
            log.error("直接取华源数据:", e);
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

            String routeKey = "topic.JMTOrder";

            String exchange = "topicExchange";

            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

            System.out.println("sendJMTOrder : " + context);

            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        }
    }
}
