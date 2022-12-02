package com.quid.sns.kafka.consumer;

import com.quid.sns.kafka.event.AlarmEvent;
import com.quid.sns.sse.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlarmConsumer {

    private final SseService sseService;

    @KafkaListener(topics = "alarm")
    public void consumeAlarmEvent(AlarmEvent event, Acknowledgment ack) {
        log.info("AlarmEvent consumed from kafka topic: {}", event);
        sseService.send(event.type(), event.args(), event.receiveUserId());
        ack.acknowledge();
    }
}
