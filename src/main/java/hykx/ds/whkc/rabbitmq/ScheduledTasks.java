package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.ThirdCommodity;
import hykx.ds.whkc.tools.JSONChange;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
    public class ScheduledTasks {
        @Autowired
        private AmqpTemplate rabbitTemplate;
        @Autowired
        private ThirdService thirdService;
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        @Scheduled(fixedDelay = 600*1000)
        public void reportCurrentTimeCommodity()throws Exception {

            List<ThirdCommodity> list = thirdService.getCommodity();
            for (int i = 0; i < list.size(); i++) {
                ThirdCommodity thirdCommodity = list.get(i);

                System.out.println("GetCommodity,Name:" + JSONChange.objToJson(thirdCommodity));

                String context = JSONChange.objToJson(thirdCommodity);

                String routeKey = "topic.thirdCommodity";

                String exchange = "topicExchange";

                context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

                System.out.println("sendCommodity : " + context);

                this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
            }
        }
}
