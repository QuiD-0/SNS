package com.quid.sns.sse.service;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.sse.repository.EmitterRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class SseService {

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
    private final EmitterRepository emitterRepository;

    public SseEmitter connectAlarm(Long id) throws IOException {
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);
        sseEmitter.onCompletion(() -> {
            emitterRepository.remove(id);
        });
        sseEmitter.onTimeout(() -> {
            emitterRepository.remove(id);
        });
        sseEmitter.send(SseEmitter.event().id("id").name("alarm").data("Connect Complete"));
        return sseEmitter;
    }

    public void send(Long id) {
        emitterRepository.get(id).ifPresent(sseEmitter -> {
            try {
                sseEmitter.send(SseEmitter.event().id("id").name("alarm").data("Alarm"));
            } catch (IOException e) {
                emitterRepository.remove(id);
                throw new SnsApplicationException(ErrorCode.ALARM_CONNECT_ERROR);
            }
        });
    }

}
