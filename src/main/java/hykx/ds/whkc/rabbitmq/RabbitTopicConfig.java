package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {


    final static String thirdCommodity = "topic.thirdCommodity"; //20211009  安童生商品数据
    final static String thirdCustomer = "topic.thirdCustomer"; //20211014  安童生客户数据

    @Bean
    public Queue queueThirdCommodity() {
        return new Queue(RabbitTopicConfig.thirdCommodity);
    }

    @Bean
    public Queue queueThirdCustomer() {
        return new Queue(RabbitTopicConfig.thirdCustomer);
    }

    /**
     * 交换机(Exchange) 描述：接收消息并且转发到绑定的队列，交换机不存储消息
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

}
