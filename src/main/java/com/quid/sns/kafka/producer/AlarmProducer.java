package com.quid.sns.kafka.producer;

import com.quid.sns.kafka.event.AlarmEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlarmProducer {

    private final KafkaTemplate<Long, AlarmEvent> kafkaTemplate;

    private String topic = "alarm";

    public void send(AlarmEvent event) {
        kafkaTemplate.send(topic, event.receiveUserId(), event);
        log.info("AlarmEvent sent to kafka topic: {}", event);
    }

}
