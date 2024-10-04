package et.kaleb.rabbitmq_consumer_producer.controller;

import et.kaleb.rabbitmq_consumer_producer.dto.User;
import et.kaleb.rabbitmq_consumer_producer.producer.RabbitMQJsonProducer;
import et.kaleb.rabbitmq_consumer_producer.producer.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageJsonController {
    private final RabbitMQJsonProducer rabbitMQProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody User user) {
        rabbitMQProducer.send(user);
        return ResponseEntity.ok("Json message sent to RabbitMQ Broker ...");
    }
}
