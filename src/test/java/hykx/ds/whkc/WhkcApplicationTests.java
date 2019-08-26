package hykx.ds.whkc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhkcApplicationTests {

   @Autowired
    private AmqpTemplate rabbitTemplate;
   @Test
    public void sendKCTest()
   {
      for(int i = 0;i<5;i++)
      {
          String context = "此消息在，配置转发消息模式队列下，有 TopicReceiver3 可以收到" +i;

          String routeKey = "topic.whkc";

          String exchange = "topicExchange";

          context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

          System.out.println("sendKCTest : " + context);

          this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
      }
   }

}
