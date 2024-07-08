package hykx.ds.whkc.rabbitmq;


import hykx.ds.whkc.service.HYService;
import hykx.ds.whkc.bean.*;
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
}
