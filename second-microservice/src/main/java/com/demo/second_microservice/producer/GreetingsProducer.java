package com.demo.second_microservice.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class GreetingsProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public GreetingsProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendGreetings(String message) {
        kafkaTemplate.send("greetings", message);
    }
}
