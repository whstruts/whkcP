package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String HBDMGoods = "topic.HBDMGoods"; //20230312 whstruts 湖北东明 商品数据


    final static String HBDMOrder = "topic.HBDMOrder"; //20230312 湖北东明 药师帮订单数据

    final static String HBDMGoodsAll = "topic.HBDMGoodsAll"; //20230328 whstruts 湖北东明 全量商品数据

    final static String HBDMGoodsAllPP = "topic.HBDMGoodsAllPP"; //20230711 whstruts 湖北东明 爬虫数据同步


    @Bean
    public Queue queueHBDMOrder() {
        return new Queue(RabbitTopicConfig.HBDMOrder);
    }

    @Bean
    public Queue queueHBDMGoods() {
        return new Queue(RabbitTopicConfig.HBDMGoods);
    }

    @Bean
    public Queue queueHBDMGoodsAll() {
        return new Queue(RabbitTopicConfig.HBDMGoodsAll);
    }

    @Bean
    public Queue queueHBDMGoodsAllPP() {
        return new Queue(RabbitTopicConfig.HBDMGoodsAllPP);
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
    Binding bindingExchangeHBDMGoods(Queue queueHBDMGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBDMGoods).to(topicExchange).with("topic.HBDMGoods");
    }

    @Bean
    Binding bindingExchangeHBDMGoodsAll(Queue queueHBDMGoodsAll, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBDMGoodsAll).to(topicExchange).with("topic.HBDMGoodsAll");
    }

    @Bean
    Binding bindingExchangeHBDMGoodsAllPP(Queue queueHBDMGoodsAllPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBDMGoodsAllPP).to(topicExchange).with("topic.HBDMGoodsAllPP");
    }


    @Bean
    Binding bindingExchangeYSBDDDM(Queue queueHBDMOrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHBDMOrder).to(topicExchange).with("topic.HBDMOrder");
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
