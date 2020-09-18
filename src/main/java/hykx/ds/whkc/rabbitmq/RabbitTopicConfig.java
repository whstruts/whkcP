package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {

    final static String WHKC = "topic.whkcx";
    final static String WHKH = "topic.whkhcx";
    final static String WHSP = "topic.whkspx";
    final static String WHDD = "topic.whds";

    @Bean
    public Queue queueWhkc() {
        return new Queue(RabbitTopicConfig.WHKC);
    }

    @Bean
    public Queue queueWhkh() {
        return new Queue(RabbitTopicConfig.WHKH);
    }

    @Bean
    public Queue queueWhsp() {
        return new Queue(RabbitTopicConfig.WHSP);
    }

    @Bean
    public Queue queueWhdd() {
        return new Queue(RabbitTopicConfig.WHDD);
    }

    /**
     * 交换机(Exchange) 描述：接收消息并且转发到绑定的队列，交换机不存储消息
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }
    //綁定队列 queueYmq() 到 topicExchange 交换机,路由键只要是以 topic 开头的队列接受者可以收到消息
    @Bean
    Binding bindingExchangeWhkc(Queue queueWhkc, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWhkc).to(topicExchange).with("topic.whkcx");
    }

    @Bean
    Binding bindingExchangeWhkh(Queue queueWhkh, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWhkh).to(topicExchange).with("topic.whkhcx");
    }

    @Bean
    Binding bindingExchangeWhsp(Queue queueWhsp, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWhsp).to(topicExchange).with("topic.whkspx");
    }

    @Bean
    Binding bindingExchangeWhdd(Queue queueWhdd, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWhdd).to(topicExchange).with("topic.whds");
    }
}
