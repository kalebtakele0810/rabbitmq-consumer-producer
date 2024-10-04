package et.kaleb.rabbitmq_consumer_producer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import et.kaleb.rabbitmq_consumer_producer.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.queue.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.routing.json.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(User user) {

        try {
            log.info("Sending json user: {}", new ObjectMapper().writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        rabbitTemplate.convertAndSend(exchangeName, routingKey, user);
    }
}
