package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String HBQJGoods = "topic.HBQJGoods"; //20230607 whstruts 湖北朗志 商品数据


    final static String HBQJOrder = "topic.HBQJOrder"; //20230607 湖北朗志 药师帮订单数据

    final static String HBQJOrderBack = "topic.HBQJOrderBack"; //20230607 湖北朗志 药师帮订单数据回写

    final static String HBQJGoodsAll = "topic.HBQJGoodsAll"; //20230607 whstruts 湖北朗志 全量商品数据

    final static String HBQJGoodsAllPP = "topic.HBQJGoodsAllPP"; //20230805 whstruts 湖北朗志 全量爬虫商品数据


    @Bean
    public Queue queueHBQJOrder() {
        return new Queue(RabbitTopicConfig.HBQJOrder);
    }

    @Bean
    public Queue queueHBQJOrderBack() {
        return new Queue(RabbitTopicConfig.HBQJOrderBack);
    }

    @Bean
    public Queue queueHBQJGoods() {
        return new Queue(RabbitTopicConfig.HBQJGoods);
    }

    @Bean
    public Queue queueHBQJGoodsAll() {
        return new Queue(RabbitTopicConfig.HBQJGoodsAll);
    }

    @Bean
    public Queue queueHBQJGoodsAllPP() {
        return new Queue(RabbitTopicConfig.HBQJGoodsAllPP);
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
    Binding bindingExchangeHBQJGoods(Queue queueHBQJGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBQJGoods).to(topicExchange).with("topic.HBQJGoods");
    }

    @Bean
    Binding bindingExchangeHBQJGoodsAll(Queue queueHBQJGoodsAll, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBQJGoodsAll).to(topicExchange).with("topic.HBQJGoodsAll");
    }

    @Bean
    Binding bindingExchangeHBQJGoodsAllPP(Queue queueHBQJGoodsAllPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBQJGoodsAllPP).to(topicExchange).with("topic.HBQJGoodsAllPP");
    }


    @Bean
    Binding bindingExchangeYSBDDLZ(Queue queueHBQJOrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBQJOrder).to(topicExchange).with("topic.HBQJOrder");
    }


    @Bean
    Binding bindingExchangeYSBDDBACK(Queue queueHBQJOrderBack, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBQJOrderBack).to(topicExchange).with("topic.HBQJOrderBack");
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
