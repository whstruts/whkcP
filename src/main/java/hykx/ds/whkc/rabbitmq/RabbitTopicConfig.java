package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String JSSYGoods = "topic.JSSYGoods"; //20230607 whstruts 湖北朗志 商品数据


    final static String JSSYOrder = "topic.JSSYOrder"; //20230607 湖北朗志 药师帮订单数据

    final static String JSSYOrderBack = "topic.JSSYOrderBack"; //20230607 湖北朗志 药师帮订单数据回写

    final static String JSSYGoodsAll = "topic.JSSYGoodsAll"; //20230607 whstruts 湖北朗志 全量商品数据

    final static String JSSYGoodsAllPP = "topic.JSSYGoodsAllPP"; //20230805 whstruts 湖北朗志 全量爬虫商品数据


    @Bean
    public Queue queueJSSYOrder() {
        return new Queue(RabbitTopicConfig.JSSYOrder);
    }

    @Bean
    public Queue queueJSSYOrderBack() {
        return new Queue(RabbitTopicConfig.JSSYOrderBack);
    }

    @Bean
    public Queue queueJSSYGoods() {
        return new Queue(RabbitTopicConfig.JSSYGoods);
    }

    @Bean
    public Queue queueJSSYGoodsAll() {
        return new Queue(RabbitTopicConfig.JSSYGoodsAll);
    }

    @Bean
    public Queue queueJSSYGoodsAllPP() {
        return new Queue(RabbitTopicConfig.JSSYGoodsAllPP);
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
    Binding bindingExchangeJSSYGoods(Queue queueJSSYGoods, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJSSYGoods).to(topicExchange).with("topic.JSSYGoods");
    }

    @Bean
    Binding bindingExchangeJSSYGoodsAll(Queue queueJSSYGoodsAll, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJSSYGoodsAll).to(topicExchange).with("topic.JSSYGoodsAll");
    }

    @Bean
    Binding bindingExchangeJSSYGoodsAllPP(Queue queueJSSYGoodsAllPP, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJSSYGoodsAllPP).to(topicExchange).with("topic.JSSYGoodsAllPP");
    }


    @Bean
    Binding bindingExchangeYSBDDLZ(Queue queueJSSYOrder, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJSSYOrder).to(topicExchange).with("topic.JSSYOrder");
    }


    @Bean
    Binding bindingExchangeYSBDDBACK(Queue queueJSSYOrderBack, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueJSSYOrderBack).to(topicExchange).with("topic.JSSYOrderBack");
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
