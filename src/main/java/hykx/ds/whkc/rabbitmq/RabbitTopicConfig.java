package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


//    final static String HNWHGoods = "topic.HNWHGoods"; //20230607 whstruts 湖北朗志 商品数据
//
//
//    final static String HNWHOrder = "topic.HNWHOrder"; //20230607 湖北朗志 药师帮订单数据
//
//    final static String HNWHOrderBack = "topic.HNWHOrderBack"; //20230607 湖北朗志 药师帮订单数据回写
//
//    final static String HNWHGoodsAll = "topic.HNWHGoodsAll"; //20230607 whstruts 湖北朗志 全量商品数据

    final static String HNWHGoodsAllPP = "topic.HNWHGoodsAllPP"; //20230805 whstruts 湖北朗志 全量爬虫商品数据


//    @Bean
//    public Queue queueHNWHOrder() {
//        return new Queue(RabbitTopicConfig.HNWHOrder);
//    }
//
//    @Bean
//    public Queue queueHNWHOrderBack() {
//        return new Queue(RabbitTopicConfig.HNWHOrderBack);
//    }
//
//    @Bean
//    public Queue queueHNWHGoods() {
//        return new Queue(RabbitTopicConfig.HNWHGoods);
//    }
//
//    @Bean
//    public Queue queueHNWHGoodsAll() {
//        return new Queue(RabbitTopicConfig.HNWHGoodsAll);
//    }

    @Bean
    public Queue queueHNWHGoodsAllPP() {
        return new Queue(RabbitTopicConfig.HNWHGoodsAllPP);
    }



    /**
     * 交换机(Exchange) 描述：接收消息并且转发到绑定的队列，交换机不存储消息
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }
    //綁定队列 queueYmq() 到 topicExchange 交换机,路由键只要是以 topic 开头的队列接受者可以收到消息


//    @Bean
//    Binding bindingExchangeHNWHGoods(Queue queueHNWHGoods, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queueHNWHGoods).to(topicExchange).with("topic.HNWHGoods");
//    }
//
//    @Bean
//    Binding bindingExchangeHNWHGoodsAll(Queue queueHNWHGoodsAll, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queueHNWHGoodsAll).to(topicExchange).with("topic.HNWHGoodsAll");
//    }

    @Bean
    Binding bindingExchangeHNWHGoodsAllPP(Queue queueHNWHGoodsAllPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHNWHGoodsAllPP).to(topicExchange).with("topic.HNWHGoodsAllPP");
    }


//    @Bean
//    Binding bindingExchangeYSBDDLZ(Queue queueHNWHOrder, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queueHNWHOrder).to(topicExchange).with("topic.HNWHOrder");
//    }
//
//
//    @Bean
//    Binding bindingExchangeYSBDDBACK(Queue queueHNWHOrderBack, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queueHNWHOrderBack).to(topicExchange).with("topic.HNWHOrderBack");
//    }


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
