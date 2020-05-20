package hykx.ds.whkc.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "${rabbitmq.fanout.queue.b}")
    public void handleQueue(String content) {
        logger.info("handleQueue:{} {}", content, Thread.currentThread().getId());
    }
}
