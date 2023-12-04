package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String HNZHGoods = "topic.HNZHGoods"; //20230114 whstruts 康庄医药 商品数据


    final static String HNZHOrder = "topic.HNZHOrder"; //20230114 康庄医药 药师帮订单数据

    final static String HNZHGoodsList = "topic.HNZHGoodsList"; //20230410 whstruts 康庄医药 商品数据List

    final static String HNZHGoodsListPP = "topic.HNZHGoodsListPP"; //20230721 whstruts 康庄医药 爬虫数据List
    @Bean
    public Queue queueHNZHOrder() {
        return new Queue(RabbitTopicConfig.HNZHOrder);
    }

    @Bean
    public Queue queueHNZHGoods() {
        return new Queue(RabbitTopicConfig.HNZHGoods);
    }

    @Bean
    public Queue queueHNZHGoodsList() {
        return new Queue(RabbitTopicConfig.HNZHGoodsList);
    }

    @Bean
    public Queue queueHNZHGoodsListPP() {
        return new Queue(RabbitTopicConfig.HNZHGoodsListPP);
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
    Binding bindingExchangeHNZHGoods(Queue queueHNZHGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNZHGoods).to(topicExchange).with("topic.HNZHGoods");
    }


    @Bean
    Binding bindingExchangeYSBDDST(Queue queueHNZHOrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNZHOrder).to(topicExchange).with("topic.HNZHOrder");
    }

    @Bean
    Binding bindingExchangeHNZHGoodsList(Queue queueHNZHGoodsList, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNZHGoodsList).to(topicExchange).with("topic.HNZHGoodsList");
    }

    @Bean
    Binding bindingExchangeHNZHGoodsListPP(Queue queueHNZHGoodsListPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNZHGoodsListPP).to(topicExchange).with("topic.HNZHGoodsListPP");
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
