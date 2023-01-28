package hykx.ds.whkc.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
    public class ScheduledTasks {
        @Autowired
        private KhzlService khzlService;
        @Scheduled(fixedDelay = 5*60*1000)
        public void reportCurrentTime()throws Exception {
             try{
                 Date now = new Date();
                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 System.out.println(dateFormat.format(now)+":ysb_kh2erp : BEGIN");
                 khzlService.ysb_kh2erp();
                 System.out.println(dateFormat.format(now)+":ysb_kh2erp : END");
                 System.out.println(dateFormat.format(now)+":ysb_kh2erp_x : BEGIN");
                 khzlService.ysb_kh2erp_x();
                 System.out.println(dateFormat.format(now)+":ysb_kh2erp_x : END");
             }
             catch (Exception e)
             {
                 System.out.println("Exception : " + e.toString());
             }
        }
}
