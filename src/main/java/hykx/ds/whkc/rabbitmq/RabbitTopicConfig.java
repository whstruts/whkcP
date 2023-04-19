package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String HNYJGoods = "topic.HNYJGoods"; //20221125 whstruts 意家医药 商品数据


    final static String HNYJOrder = "topic.HNYJOrder"; //20221125 意家医药 药师帮订单数据

    final static String HNYJGoodsList = "topic.HNYJGoodsList"; //20230419 whstruts 意家医药 商品数据


    @Bean
    public Queue queueHNYJOrder() {
        return new Queue(RabbitTopicConfig.HNYJOrder);
    }

    @Bean
    public Queue queueHNYJGoods() {
        return new Queue(RabbitTopicConfig.HNYJGoods);
    }

    @Bean
    public Queue queueHNYJGoodsList() {
        return new Queue(RabbitTopicConfig.HNYJGoodsList);
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
    Binding bindingExchangeHNYJGoods(Queue queueHNYJGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNYJGoods).to(topicExchange).with("topic.HNYJGoods");
    }

    @Bean
    Binding bindingExchangeHNYJGoodsList(Queue queueHNYJGoodsList, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNYJGoodsList).to(topicExchange).with("topic.HNYJGoodsList");
    }


    @Bean
    Binding bindingExchangeHNYJOrder(Queue queueHNYJOrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNYJOrder).to(topicExchange).with("topic.HNYJOrder");
    }


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }


}
