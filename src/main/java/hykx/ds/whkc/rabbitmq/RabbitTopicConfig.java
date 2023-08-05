package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String HBLZGoods = "topic.HBLZGoods"; //20230607 whstruts 湖北朗志 商品数据


    final static String HBLZOrder = "topic.HBLZOrder"; //20230607 湖北朗志 药师帮订单数据

    final static String HBLZOrderBack = "topic.HBLZOrderBack"; //20230607 湖北朗志 药师帮订单数据回写

    final static String HBLZGoodsAll = "topic.HBLZGoodsAll"; //20230607 whstruts 湖北朗志 全量商品数据

    final static String HBLZGoodsAllPP = "topic.HBLZGoodsAllPP"; //20230805 whstruts 湖北朗志 全量爬虫商品数据


    @Bean
    public Queue queueHBLZOrder() {
        return new Queue(RabbitTopicConfig.HBLZOrder);
    }

    @Bean
    public Queue queueHBLZOrderBack() {
        return new Queue(RabbitTopicConfig.HBLZOrderBack);
    }

    @Bean
    public Queue queueHBLZGoods() {
        return new Queue(RabbitTopicConfig.HBLZGoods);
    }

    @Bean
    public Queue queueHBLZGoodsAll() {
        return new Queue(RabbitTopicConfig.HBLZGoodsAll);
    }

    @Bean
    public Queue queueHBLZGoodsAllPP() {
        return new Queue(RabbitTopicConfig.HBLZGoodsAllPP);
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
    Binding bindingExchangeHBLZGoods(Queue queueHBLZGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBLZGoods).to(topicExchange).with("topic.HBLZGoods");
    }

    @Bean
    Binding bindingExchangeHBLZGoodsAll(Queue queueHBLZGoodsAll, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBLZGoodsAll).to(topicExchange).with("topic.HBLZGoodsAll");
    }

    @Bean
    Binding bindingExchangeHBLZGoodsAllPP(Queue queueHBLZGoodsAllPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBLZGoodsAllPP).to(topicExchange).with("topic.HBLZGoodsAllPP");
    }


    @Bean
    Binding bindingExchangeYSBDDLZ(Queue queueHBLZOrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBLZOrder).to(topicExchange).with("topic.HBLZOrder");
    }


    @Bean
    Binding bindingExchangeYSBDDBACK(Queue queueHBLZOrderBack, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBLZOrderBack).to(topicExchange).with("topic.HBLZOrderBack");
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
