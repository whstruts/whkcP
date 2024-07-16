package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String HYNXGoods = "topic.HYNXGoods"; //20230607 whstruts 湖北朗志 商品数据


    final static String HYNXOrder = "topic.HYNXOrder"; //20230607 湖北朗志 药师帮订单数据

    final static String HYNXOrderBack = "topic.HYNXOrderBack"; //20230607 湖北朗志 药师帮订单数据回写

    final static String HYNXGoodsAll = "topic.HYNXGoodsAll"; //20230607 whstruts 湖北朗志 全量商品数据

    final static String HYNXGoodsAllPP = "topic.HYNXGoodsAllPP"; //20230805 whstruts 湖北朗志 全量爬虫商品数据


    @Bean
    public Queue queueHYNXOrder() {
        return new Queue(RabbitTopicConfig.HYNXOrder);
    }

    @Bean
    public Queue queueHYNXOrderBack() {
        return new Queue(RabbitTopicConfig.HYNXOrderBack);
    }

    @Bean
    public Queue queueHYNXGoods() {
        return new Queue(RabbitTopicConfig.HYNXGoods);
    }

    @Bean
    public Queue queueHYNXGoodsAll() {
        return new Queue(RabbitTopicConfig.HYNXGoodsAll);
    }

    @Bean
    public Queue queueHYNXGoodsAllPP() {
        return new Queue(RabbitTopicConfig.HYNXGoodsAllPP);
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
    Binding bindingExchangeHYNXGoods(Queue queueHYNXGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHYNXGoods).to(topicExchange).with("topic.HYNXGoods");
    }

    @Bean
    Binding bindingExchangeHYNXGoodsAll(Queue queueHYNXGoodsAll, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHYNXGoodsAll).to(topicExchange).with("topic.HYNXGoodsAll");
    }

    @Bean
    Binding bindingExchangeHYNXGoodsAllPP(Queue queueHYNXGoodsAllPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHYNXGoodsAllPP).to(topicExchange).with("topic.HYNXGoodsAllPP");
    }


    @Bean
    Binding bindingExchangeYSBDDLZ(Queue queueHYNXOrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHYNXOrder).to(topicExchange).with("topic.HYNXOrder");
    }


    @Bean
    Binding bindingExchangeYSBDDBACK(Queue queueHYNXOrderBack, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHYNXOrderBack).to(topicExchange).with("topic.HYNXOrderBack");
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
