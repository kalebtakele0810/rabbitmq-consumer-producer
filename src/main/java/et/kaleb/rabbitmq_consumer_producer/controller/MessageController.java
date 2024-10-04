package et.kaleb.rabbitmq_consumer_producer.controller;

import et.kaleb.rabbitmq_consumer_producer.producer.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {

    private final RabbitMQProducer rabbitMQProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("user") String message) {
        rabbitMQProducer.send(message);
        return ResponseEntity.ok("Message sent to RabbitMQ Broker ...");
    }
}
