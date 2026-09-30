package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.MiddleService;
import hykx.ds.whkc.entity.*;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledTasks {
    private final AmqpTemplate rabbitTemplate;
    private final KhzlService khzlService;

    public ScheduledTasks(AmqpTemplate rabbitTemplate, KhzlService khzlService) {
        this.rabbitTemplate = rabbitTemplate;
        this.khzlService = khzlService;
    }

    private static final int BATCH_SIZE = 50;

    @Scheduled(fixedDelay = 60*1000)
    public void reportCurrentTimeGY()throws Exception {
        List<ysbddhz> listysbddhz = khzlService.getysbddhzs();
        log.info("处理订单数量: {}", listysbddhz.size());
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
            khzlService.updateysbddhz(ddhz.getDjbh());

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
    public void reportCurrentTimeERPSP(){
        try {
            List<erpsp> erpsps = khzlService.getERPSP();
            log.info("处理ERP商品数量: {}", erpsps.size());
            int count = 0;
            for (int i = 0; i < erpsps.size(); i++) {
                erpsp sp = erpsps.get(i);

                JSONObject data = JSONObject.fromObject(sp);

                String context = data.toString();

                String routeKey = "topic.YZTERPGoods";

                String exchange = "topicExchange";

                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
                
                count++;
                if (count % BATCH_SIZE == 0) {
                    log.info("已处理ERP商品: {}/{}", count, erpsps.size());
                }
            }
            log.info("ERP商品处理完成: {}", count);
        }catch (Exception e)
        {
            log.error("reportCurrentTimeERPSP:", e);
        }
    }
    
    @Scheduled(cron="0 0 1 * * ?")
    private void DownDrug(){
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            khzlService.unOnSale();
            log.info("全部华源商品下架完成: {}", df.format(new Date()));
        }catch (Exception e) {
            log.error("全部华源商品下架", e);
        }
    }

    @Scheduled(fixedDelay = 60*60*1000)
    public void reportCurrentTimeCommodityXYY()throws Exception {
        try {
            log.info("取药帮忙数据:开始");
            long startTime = System.currentTimeMillis();
            
            List<MyGoodsEntity> list = MiddleService.GetYBMG2MGEByUser("HNYZT");
            log.info("取到药帮忙数据: {} 条, 耗时: {}ms", list.size(), System.currentTimeMillis() - startTime);
            
            if (list.isEmpty()) {
                log.info("药帮忙数据为空，跳过处理");
                return;
            }
            
            int count = 0;
            int total = list.size();
            for (MyGoodsEntity myGoodsEntity : list) {
                if(myGoodsEntity.getJx()==null)
                    myGoodsEntity.setJx("");
                
                try {
                    long dbStartTime = System.currentTimeMillis();
                    khzlService.insertYZYGOODS(myGoodsEntity);
                    long dbEndTime = System.currentTimeMillis();
                    
                    log.debug("写入药帮忙数据: {}, 耗时: {}ms", myGoodsEntity.getGoods_sn(), dbEndTime - dbStartTime);
                } catch (Exception e) {
                    log.error("写入药帮忙数据失败: {}, 错误: {}", myGoodsEntity.getGoods_sn(), e.getMessage());
                }
                
                count++;
                if (count % BATCH_SIZE == 0 || count == total) {
                    log.info("写入药帮忙数据进度: {}/{}, 累计耗时: {}ms", count, total, System.currentTimeMillis() - startTime);
                }
            }
            log.info("取药帮忙数据:结束, 总耗时: {}ms", System.currentTimeMillis() - startTime);
        }
        catch (Exception e)
        {
            log.error("reportCurrentTimeCommodityXYY:", e);
        }
    }

    @Scheduled(fixedDelay = 60*60*1000)
    public void reportCurrentTimeCommodityPGBY()throws Exception {
        try {
            log.info("取批购包邮数据:开始");
            long startTime = System.currentTimeMillis();
            
            List<YZYGOODS> list = MiddleService.GetPGBY("HNYZT");
            log.info("取到批购包邮数据: {} 条, 耗时: {}ms", list.size(), System.currentTimeMillis() - startTime);
            
            khzlService.deleteYZYGOODSP();
            
            int count = 0;
            int total = list.size();
            for (YZYGOODS yzygoods : list) {
                if(yzygoods.getYPDM()==null) yzygoods.setYPDM("");
                if(yzygoods.getCDDM()==null) yzygoods.setCDDM("");
                if(yzygoods.getPH()==null) yzygoods.setPH("");
                if(yzygoods.getPCH()==null) yzygoods.setPCH("");
                if(yzygoods.getOtc()==null) yzygoods.setOtc("");
                if(yzygoods.getYpbh()==null) yzygoods.setYpbh("");
                if(yzygoods.getTXM()==null) yzygoods.setTXM("");
                
                try {
                    khzlService.insertYZYGOODSP(yzygoods);
                    khzlService.insertYZYGOODSPGBY(yzygoods);
                } catch (Exception e) {
                    log.error("写入批购包邮数据失败: {}, 错误: {}", yzygoods.getGoods_sn(), e.getMessage());
                }
                
                count++;
                if (count % BATCH_SIZE == 0 || count == total) {
                    log.info("写入批购包邮数据进度: {}/{}, 累计耗时: {}ms", count, total, System.currentTimeMillis() - startTime);
                }
            }
            log.info("取批购包邮数据:结束, 总耗时: {}ms", System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("reportCurrentTimeCommodityPGBY:", e);
        }
    }

}