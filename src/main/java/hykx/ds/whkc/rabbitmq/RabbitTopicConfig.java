package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String WHKZGoods = "topic.WHKZGoods"; //20230114 whstruts 康庄医药 商品数据


    final static String WHKZOrder = "topic.WHKZOrder"; //20230114 康庄医药 药师帮订单数据

    final static String WHKZGoodsList = "topic.WHKZGoodsList"; //20230410 whstruts 康庄医药 商品数据List

    final static String WHKZGoodsListPP = "topic.WHKZGoodsListPP"; //20230721 whstruts 康庄医药 爬虫数据List
    @Bean
    public Queue queueWHKZOrder() {
        return new Queue(RabbitTopicConfig.WHKZOrder);
    }

    @Bean
    public Queue queueWHKZGoods() {
        return new Queue(RabbitTopicConfig.WHKZGoods);
    }

    @Bean
    public Queue queueWHKZGoodsList() {
        return new Queue(RabbitTopicConfig.WHKZGoodsList);
    }

    @Bean
    public Queue queueWHKZGoodsListPP() {
        return new Queue(RabbitTopicConfig.WHKZGoodsListPP);
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
    Binding bindingExchangeWHKZGoods(Queue queueWHKZGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWHKZGoods).to(topicExchange).with("topic.WHKZGoods");
    }


    @Bean
    Binding bindingExchangeYSBDDST(Queue queueWHKZOrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWHKZOrder).to(topicExchange).with("topic.WHKZOrder");
    }

    @Bean
    Binding bindingExchangeWHKZGoodsList(Queue queueWHKZGoodsList, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWHKZGoodsList).to(topicExchange).with("topic.WHKZGoodsList");
    }

    @Bean
    Binding bindingExchangeWHKZGoodsListPP(Queue queueWHKZGoodsListPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWHKZGoodsListPP).to(topicExchange).with("topic.WHKZGoodsListPP");
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
