package com.demo.third_microservice.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class GreetingsConsumer {

    private static final Logger log = LoggerFactory.getLogger(GreetingsConsumer.class);

    @KafkaListener(topics = "greetings", groupId = "third-microservice")
    public void handleGreetings(ConsumerRecord<String, String> message) {
        log.info("Message received in topic {} - key: {}, value: {}, offset: {}, partition: {}",
                message.topic(), message.key(), message.value(), message.offset(), message.partition());
        Headers headers = message.headers();
        for (Header header : headers) {
            log.info("Header - key: {}, value: {}", header.key(), new String(header.value()));
        }
    }
}
