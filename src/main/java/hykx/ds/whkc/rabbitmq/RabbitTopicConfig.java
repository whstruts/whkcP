//package hykx.ds.whkc.rabbitmq;
//
//
//import org.springframework.amqp.core.*;
//
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.MappingJackson2MessageConverter;
//
//
//@Configuration
//public class RabbitTopicConfig {
//
//
//    final static String HNYZTGoods = "topic.HNYZTGoods"; //20230607 whstruts 湖北朗志 商品数据
//
//
//    final static String HNYZTOrder = "topic.HNYZTOrder"; //20230607 湖北朗志 药师帮订单数据
//
//    final static String HNYZTOrderBack = "topic.HNYZTOrderBack"; //20230607 湖北朗志 药师帮订单数据回写
//
//    final static String HNYZTGoodsAll = "topic.HNYZTGoodsAll"; //20230607 whstruts 湖北朗志 全量商品数据
//
//    final static String HNYZTGoodsAllPP = "topic.HNYZTGoodsAllPP"; //20230805 whstruts 湖北朗志 全量爬虫商品数据
//
//
//    @Bean
//    public Queue queueHNYZTOrder() {
//        return new Queue(RabbitTopicConfig.HNYZTOrder);
//    }
//
//    @Bean
//    public Queue queueHNYZTOrderBack() {
//        return new Queue(RabbitTopicConfig.HNYZTOrderBack);
//    }
//
//    @Bean
//    public Queue queueHNYZTGoods() {
//        return new Queue(RabbitTopicConfig.HNYZTGoods);
//    }
//
//    @Bean
//    public Queue queueHNYZTGoodsAll() {
//        return new Queue(RabbitTopicConfig.HNYZTGoodsAll);
//    }
//
//    @Bean
//    public Queue queueHNYZTGoodsAllPP() {
//        return new Queue(RabbitTopicConfig.HNYZTGoodsAllPP);
//    }
//
//
//
//    /**
//     * 交换机(Exchange) 描述：接收消息并且转发到绑定的队列，交换机不存储消息
//     */
//    @Bean
//    TopicExchange topicExchange() {
//        return new TopicExchange("topicExchange");
//    }
//    //綁定队列 queueYmq() 到 topicExchange 交换机,路由键只要是以 topic 开头的队列接受者可以收到消息
//
//
//    @Bean
//    Binding bindingExchangeHNYZTGoods(Queue queueHNYZTGoods, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queueHNYZTGoods).to(topicExchange).with("topic.HNYZTGoods");
//    }
//
//    @Bean
//    Binding bindingExchangeHNYZTGoodsAll(Queue queueHNYZTGoodsAll, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queueHNYZTGoodsAll).to(topicExchange).with("topic.HNYZTGoodsAll");
//    }
//
//    @Bean
//    Binding bindingExchangeHNYZTGoodsAllPP(Queue queueHNYZTGoodsAllPP, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queueHNYZTGoodsAllPP).to(topicExchange).with("topic.HNYZTGoodsAllPP");
//    }
//
//
//    @Bean
//    Binding bindingExchangeYSBDDLZ(Queue queueHNYZTOrder, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queueHNYZTOrder).to(topicExchange).with("topic.HNYZTOrder");
//    }
//
//
//    @Bean
//    Binding bindingExchangeYSBDDBACK(Queue queueHNYZTOrderBack, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queueHNYZTOrderBack).to(topicExchange).with("topic.HNYZTOrderBack");
//    }
//
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    @Bean
//    public MappingJackson2MessageConverter jackson2Converter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        return converter;
//    }
//
//
//}
