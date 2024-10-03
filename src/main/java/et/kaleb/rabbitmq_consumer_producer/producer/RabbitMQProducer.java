package et.kaleb.rabbitmq_consumer_producer.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQProducer {

    @Value("${rabbitmq.queue.exchange.name}")
    private String exchangeName;


    @Value("${rabbitmq.queue.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {

        log.info("Sending message: {}", message);

        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
