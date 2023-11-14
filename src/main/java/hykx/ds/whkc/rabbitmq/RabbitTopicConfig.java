package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String JXJZGoods = "topic.JXJZGoods"; //20230114 whstruts 康庄医药 商品数据


    final static String JXJZOrder = "topic.JXJZOrder"; //20230114 康庄医药 药师帮订单数据

    final static String JXJZGoodsList = "topic.JXJZGoodsList"; //20230410 whstruts 康庄医药 商品数据List

    final static String JXJZGoodsListPP = "topic.JXJZGoodsListPP"; //20230721 whstruts 康庄医药 爬虫数据List
    @Bean
    public Queue queueJXJZOrder() {
        return new Queue(RabbitTopicConfig.JXJZOrder);
    }

    @Bean
    public Queue queueJXJZGoods() {
        return new Queue(RabbitTopicConfig.JXJZGoods);
    }

    @Bean
    public Queue queueJXJZGoodsList() {
        return new Queue(RabbitTopicConfig.JXJZGoodsList);
    }

    @Bean
    public Queue queueJXJZGoodsListPP() {
        return new Queue(RabbitTopicConfig.JXJZGoodsListPP);
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
    Binding bindingExchangeJXJZGoods(Queue queueJXJZGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJXJZGoods).to(topicExchange).with("topic.JXJZGoods");
    }


    @Bean
    Binding bindingExchangeYSBDDST(Queue queueJXJZOrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJXJZOrder).to(topicExchange).with("topic.JXJZOrder");
    }

    @Bean
    Binding bindingExchangeJXJZGoodsList(Queue queueJXJZGoodsList, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJXJZGoodsList).to(topicExchange).with("topic.JXJZGoodsList");
    }

    @Bean
    Binding bindingExchangeJXJZGoodsListPP(Queue queueJXJZGoodsListPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJXJZGoodsListPP).to(topicExchange).with("topic.JXJZGoodsListPP");
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
