package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.MiddleService;
import hykx.ds.whkc.entity.YZYGOODS;
import hykx.ds.whkc.entity.ysbdd;
import hykx.ds.whkc.entity.ysbddhz;
import hykx.ds.whkc.entity.ysbddmx;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
    public class ScheduledTasks {
        @Autowired
        private AmqpTemplate rabbitTemplate;
        @Autowired
        private KhzlService khzlService;
        // 批量插入批次大小（根据数据库配置调整，默认 500 条/批）
        private static final int BATCH_SIZE = 800;
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

            String routeKey = "topic.HBLZOrderX";

            String exchange = "topicExchange";

            context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

            this.rabbitTemplate.convertAndSend(exchange, routeKey, context);

            System.out.println("sendHBLZOrder : " + context);
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
    public void reportCurrentTimeCommodityPGBY()throws Exception {
        try {
            System.out.println("取批购包邮数据:开始");
            List<YZYGOODS> list = MiddleService.GetPGBY("HBLZ");
            khzlService.deleteYZYGOODSP();
            list.forEach(yzygoods -> {
                System.out.println("批购包邮数据:" + yzygoods);
                khzlService.insertYZYGOODSP(yzygoods);
                khzlService.insertYZYGOODSPGBY(yzygoods);
            });
            System.out.println("取批购包邮数据:结束");
        }
        catch (Exception e) {
            log.error("取批购包邮数据", e);
        }
        //syncPGBYData();
    }

    /**
     * 同步批购包邮数据（核心方法）
     * 事务注解：确保删除和插入原子性，异常时回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void syncPGBYData() {
        log.info("取批购包邮数据:开始");
        long startTime = System.currentTimeMillis();

        try {
            // 1. 获取两类数据并合并
            List<YZYGOODS> list = MiddleService.GetPGBY("HBLZ");
            List<YZYGOODS> allData = new ArrayList<>();

            // 避免空集合 addAll 报错
            if (CollectionUtils.isNotEmpty(list)) {
                allData.addAll(list);
            }
            // 2. 数据校验：无数据直接返回
            if (CollectionUtils.isEmpty(allData)) {
                log.warn("批购包邮数据为空，无需同步");
                return;
            }

            log.info("批购包邮数据合并去重后共 {} 条", allData.size());

            // 4. 清空旧数据（注意：delete 操作需谨慎，建议先备份或加条件）
            khzlService.deleteYZYGOODSP();
            log.info("旧批购包邮数据已清空");

            // 5. 分批批量插入（避免 SQL 超长报错）
            for (int i = 0; i < allData.size(); i += BATCH_SIZE) {
                int endIndex = Math.min(i + BATCH_SIZE, allData.size());
                List<YZYGOODS> batchData = allData.subList(i, endIndex);

                try {
                    khzlService.batchInsertYZYGOODSP(batchData);  // 调用批量插入方法
                    log.info("第 {} 批插入成功，条数：{}", (i / BATCH_SIZE + 1), batchData.size());
                } catch (Exception e) {
                    throw new RuntimeException("批量插入批购包邮数据失败", e);
                }
            }

            long costTime = System.currentTimeMillis() - startTime;
            log.info("取批购包邮数据:结束，总耗时 {} ms，共插入 {} 条数据", costTime, allData.size());

        } catch (Exception e) {
            // 捕获整体异常，事务会自动回滚
            log.error("同步批购包邮数据异常", e);
            // 若需要向上抛出异常，让调用方处理，可保留 throw；否则注释掉
            throw new RuntimeException("同步批购包邮数据失败", e);
        }
    }
}
