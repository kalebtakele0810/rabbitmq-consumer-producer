package et.kaleb.rabbitmq_consumer_producer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import et.kaleb.rabbitmq_consumer_producer.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void receive(User user) {
        try {
            log.info("Received user: {}", new ObjectMapper().writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
