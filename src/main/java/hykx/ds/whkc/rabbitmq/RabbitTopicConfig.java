package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String HNJRGoods = "topic.HNJRGoods"; //20230114 whstruts 康庄医药 商品数据


    final static String HNJROrder = "topic.HNJROrder"; //20230114 康庄医药 药师帮订单数据

    final static String HNJRGoodsList = "topic.HNJRGoodsList"; //20230410 whstruts 康庄医药 商品数据List

    final static String HNJRGoodsListPP = "topic.HNJRGoodsListPP"; //20230721 whstruts 康庄医药 爬虫数据List
    @Bean
    public Queue queueHNJROrder() {
        return new Queue(RabbitTopicConfig.HNJROrder);
    }

    @Bean
    public Queue queueHNJRGoods() {
        return new Queue(RabbitTopicConfig.HNJRGoods);
    }

    @Bean
    public Queue queueHNJRGoodsList() {
        return new Queue(RabbitTopicConfig.HNJRGoodsList);
    }

    @Bean
    public Queue queueHNJRGoodsListPP() {
        return new Queue(RabbitTopicConfig.HNJRGoodsListPP);
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
    Binding bindingExchangeHNJRGoods(Queue queueHNJRGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNJRGoods).to(topicExchange).with("topic.HNJRGoods");
    }


    @Bean
    Binding bindingExchangeYSBDDST(Queue queueHNJROrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNJROrder).to(topicExchange).with("topic.HNJROrder");
    }

    @Bean
    Binding bindingExchangeHNJRGoodsList(Queue queueHNJRGoodsList, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNJRGoodsList).to(topicExchange).with("topic.HNJRGoodsList");
    }

    @Bean
    Binding bindingExchangeHNJRGoodsListPP(Queue queueHNJRGoodsListPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNJRGoodsListPP).to(topicExchange).with("topic.HNJRGoodsListPP");
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
