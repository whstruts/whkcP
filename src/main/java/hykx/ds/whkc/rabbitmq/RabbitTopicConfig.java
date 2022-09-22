package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitTopicConfig {


    final static String YZYGOODS = "topic.yzygoods"; //20191226 whstruts 兵兵 MSSQL 商品库存

    final static String YSBDD = "topic.ysbdd"; //20191229 whstruts 兵兵 时空药师帮订单

    final static String STGOODS = "topic.STGoods"; //20220513

    final static String MCHK = "topic.mchk"; //20220513

    final static String YSBDDST = "topic.ysbddst"; //20191229 whstruts 兵兵 时空药师帮订单

    @Bean
    public Queue queueYSBDD() {
        return new Queue(RabbitTopicConfig.YSBDD);
    }

    @Bean
    public Queue queueYSBDDST() {
        return new Queue(RabbitTopicConfig.YSBDDST);
    }

    @Bean
    public Queue queueYZYGOODS() {
        return new Queue(RabbitTopicConfig.YZYGOODS);
    }

    @Bean
    public Queue queueSTGOODS() {
        return new Queue(RabbitTopicConfig.STGOODS);
    }

    @Bean
    public Queue queueMCHK() {
        return new Queue(RabbitTopicConfig.MCHK);
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
//    TopicExchange LMExchange() {
//        return new TopicExchange("LMExchange");
//    }
//    //綁定队列 queueYmq() 到 topicExchange 交换机,路由键只要是以 topic 开头的队列接受者可以收到消息


    @Bean
    Binding bindingExchangeYZYGOODS(Queue queueYZYGOODS, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueYZYGOODS).to(topicExchange).with("topic.yzygoods");
    }

    @Bean
    Binding bindingExchangeYSBDD(Queue queueYSBDD, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueYSBDD).to(topicExchange).with("topic.ysbdd");
    }

    @Bean
    Binding bindingExchangeYSBDDST(Queue queueYSBDDST, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueYSBDDST).to(topicExchange).with("topic.ysbddst");
    }

    @Bean
    Binding bindingExchangeSTGOODS(Queue queueSTGOODS, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueSTGOODS).to(topicExchange).with("topic.STGoods");
    }

    @Bean
    Binding bindingExchangeMCHK(Queue queueMCHK, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMCHK).to(topicExchange).with("topic.mchk");
    }

    @Value("${rabbitmq.fanout.exchange}")
    private String fanoutExchangeName;

//    @Value("${rabbitmq.fanout.queue.a}")
//    private String queueA;
//
//    @Value("${rabbitmq.fanout.queue.b}")
//    private String queueB;
//
//    @Value("${rabbitmq.fanout.queue.c}")
//    private String queueC;

    @Value("${rabbitmq.fanout.queue.d}")
    private String queueD;

    private static Binding bindingFanoutExchange4Queue(Queue queue, FanoutExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queue).to(exchange);
        rabbitAdmin.declareBinding(binding);
        return binding;
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

    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange(RabbitAdmin rabbitAdmin) {
        FanoutExchange fanoutExchange = new FanoutExchange(fanoutExchangeName, true, false);
        rabbitAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }

//    @Bean("queueA")
//    public Queue queueA(RabbitAdmin rabbitAdmin) {
//        Queue queue = new Queue(queueA, true);
//        rabbitAdmin.declareQueue(queue);
//        return queue;
//    }
//
//    @Bean("queueB")
//    public Queue queueB(RabbitAdmin rabbitAdmin) {
//        Queue queue = new Queue(queueB, true);
//        rabbitAdmin.declareQueue(queue);
//        return queue;
//    }
//
//    @Bean("queueC")
//    public Queue queueC(RabbitAdmin rabbitAdmin) {
//        Queue queue = new Queue(queueC, true);
//        rabbitAdmin.declareQueue(queue);
//        return queue;
//    }

    @Bean("queueD")
    public Queue queueD(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueD, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

//    @Bean
//    public Binding bindingFanoutExchange4QueueA(@Qualifier("queueA") Queue queueA,
//                                                @Qualifier("fanoutExchange") FanoutExchange exchange,
//                                                RabbitAdmin rabbitAdmin) {
//        return bindingFanoutExchange4Queue(queueA, exchange, rabbitAdmin);
//    }
//
//    @Bean
//    public Binding bindingFanoutExchange4QueueB(@Qualifier("queueB") Queue queueB,
//                                                @Qualifier("fanoutExchange") FanoutExchange exchange,
//                                                RabbitAdmin rabbitAdmin) {
//        return bindingFanoutExchange4Queue(queueB, exchange, rabbitAdmin);
//    }
//
//    @Bean
//    public Binding bindingFanoutExchange4QueueC(@Qualifier("queueC") Queue queueC,
//                                                @Qualifier("fanoutExchange") FanoutExchange exchange,
//                                                RabbitAdmin rabbitAdmin) {
//        return bindingFanoutExchange4Queue(queueC, exchange, rabbitAdmin);
//    }

    @Bean
    public Binding bindingFanoutExchange4QueueD(@Qualifier("queueD") Queue queueD,
                                                @Qualifier("fanoutExchange") FanoutExchange exchange,
                                                RabbitAdmin rabbitAdmin) {
        return bindingFanoutExchange4Queue(queueD, exchange, rabbitAdmin);
    }
}
