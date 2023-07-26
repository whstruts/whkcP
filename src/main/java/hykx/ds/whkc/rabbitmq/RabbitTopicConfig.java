package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String JXTYGoods = "topic.JXTYGoods"; //20230312 whstruts 湖北东明 商品数据


    final static String JXTYOrder = "topic.JXTYOrder"; //20230312 湖北东明 药师帮订单数据

    final static String JXTYGoodsList = "topic.JXTYGoodsList"; //20230328 whstruts 湖北东明 全量商品数据

    final static String JXTYGoodsListPP = "topic.JXTYGoodsListPP"; //20230726 whstruts 江西天尧 云仓数据


    @Bean
    public Queue queueJXTYOrder() {
        return new Queue(RabbitTopicConfig.JXTYOrder);
    }

    @Bean
    public Queue queueJXTYGoods() {
        return new Queue(RabbitTopicConfig.JXTYGoods);
    }

    @Bean
    public Queue queueJXTYGoodsList() {
        return new Queue(RabbitTopicConfig.JXTYGoodsList);
    }

    @Bean
    public Queue queueJXTYGoodsListPP() {
        return new Queue(RabbitTopicConfig.JXTYGoodsListPP);
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
    Binding bindingExchangeJXTYGoods(Queue queueJXTYGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJXTYGoods).to(topicExchange).with("topic.JXTYGoods");
    }

    @Bean
    Binding bindingExchangeJXTYGoodsList(Queue queueJXTYGoodsList, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJXTYGoodsList).to(topicExchange).with("topic.JXTYGoodsList");
    }

    @Bean
    Binding bindingExchangeJXTYGoodsListPP(Queue queueJXTYGoodsListPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJXTYGoodsListPP).to(topicExchange).with("topic.JXTYGoodsListPP");
    }


    @Bean
    Binding bindingExchangeYSBDDDM(Queue queueJXTYOrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJXTYOrder).to(topicExchange).with("topic.JXTYOrder");
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
