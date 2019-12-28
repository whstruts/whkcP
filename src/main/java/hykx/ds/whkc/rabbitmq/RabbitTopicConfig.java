package hykx.ds.whkc.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {

    final static String WHKC = "topic.whkcx";
    final static String WHKH = "topic.whkhcx";
    final static String WHSP = "topic.whkspx";
    final static String WHDD = "topic.whds";
    final static String WHDZFP = "topic.whdzfp";

    final static String BBSPZL = "topic.bbspzl"; //20191205 whstruts 兵兵 MSSQL 商品资料
    final static String BBSPJG = "topic.bbspjg"; //20191205 whstruts 兵兵 MSSQL 商品价格
    final static String BBSPKC = "topic.bbspkc"; //20191205 whstruts 兵兵 MSSQL 商品库存
    final static String BBSPPH = "topic.bbspph"; //20191205 whstruts 兵兵 MSSQL 商品批号

    final static String YZYGOODS = "topic.yzygoods"; //20191226 whstruts 兵兵 MSSQL 商品库存

    final static String YSBDD = "topic.ysbdd"; //20191229 whstruts 兵兵 时空药师帮订单

    @Bean
    public Queue queueYSBDD() {
        return new Queue(RabbitTopicConfig.YSBDD);
    }

    @Bean
    public Queue queueYZYGOODS() {
        return new Queue(RabbitTopicConfig.YZYGOODS);
    }

    @Bean
    public Queue queueBBSPZL() {
        return new Queue(RabbitTopicConfig.BBSPZL);
    }
    @Bean
    public Queue queueBBSPJG() {
        return new Queue(RabbitTopicConfig.BBSPJG);
    }
    @Bean
    public Queue queueBBSPKC() {
        return new Queue(RabbitTopicConfig.BBSPKC);
    }
    @Bean
    public Queue queueBBSPPH() {
        return new Queue(RabbitTopicConfig.BBSPPH);
    }
    @Bean
    public Queue queueWhkc() {
        return new Queue(RabbitTopicConfig.WHKC);
    }

    @Bean
    public Queue queueWhkh() {
        return new Queue(RabbitTopicConfig.WHKH);
    }

    @Bean
    public Queue queueWhsp() {
        return new Queue(RabbitTopicConfig.WHSP);
    }

    @Bean
    public Queue queueWhdd() {
        return new Queue(RabbitTopicConfig.WHDD);
    }

    @Bean
    public Queue queueWhdzfp() {
        return new Queue(RabbitTopicConfig.WHDZFP);
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
    Binding bindingExchangeBBSPZL(Queue queueBBSPZL, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueBBSPZL).to(topicExchange).with("topic.bbspzl");
    }

    @Bean
    Binding bindingExchangeBBSPJG(Queue queueBBSPJG, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueBBSPJG).to(topicExchange).with("topic.bbspjg");
    }

    @Bean
    Binding bindingExchangeBBSPKC(Queue queueBBSPKC, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueBBSPKC).to(topicExchange).with("topic.bbspkc");
    }

    @Bean
    Binding bindingExchangeBBSPPH(Queue queueBBSPPH, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueBBSPPH).to(topicExchange).with("topic.bbspph");
    }

    @Bean
    Binding bindingExchangeWhkc(Queue queueWhkc, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWhkc).to(topicExchange).with("topic.whkcx");
    }

    @Bean
    Binding bindingExchangeWhkh(Queue queueWhkh, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWhkh).to(topicExchange).with("topic.whkhcx");
    }

    @Bean
    Binding bindingExchangeWhsp(Queue queueWhsp, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWhsp).to(topicExchange).with("topic.whkspx");
    }

    @Bean
    Binding bindingExchangeWhdd(Queue queueWhdd, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWhdd).to(topicExchange).with("topic.whds");
    }

    @Bean
    Binding bindingExchangeWhdzfp(Queue queueWhdzfp, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueWhdzfp).to(topicExchange).with("topic.whdzfp");
    }

    @Bean
    Binding bindingExchangeYZYGOODS(Queue queueYZYGOODS, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueYZYGOODS).to(topicExchange).with("topic.yzygoods");
    }

    @Bean
    Binding bindingExchangeYSBDD(Queue queueYSBDD, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueYSBDD).to(topicExchange).with("topic.ysbdd");
    }
}
