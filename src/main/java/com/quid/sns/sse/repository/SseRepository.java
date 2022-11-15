package com.quid.sns.sse.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Repository
public class SseRepository {

    private Map<String, SseEmitter> emitterMap = new HashMap<>();

    public SseEmitter save(Long id, SseEmitter emitter) {
        final String key = getKey(id);
        emitterMap.put(key, emitter);
        log.info("EmitterRepository save : {}", key);
        return emitter;
    }

    public Optional<SseEmitter> get(Long id) {
        final String key = getKey(id);
        log.info("EmitterRepository get : {}", key);
        return Optional.ofNullable(emitterMap.get(key));
    }

    public String getKey(Long id) {
        return "Emitter:UID" + id.toString();
    }

    public void remove(Long id) {
        final String key = getKey(id);
        log.info("EmitterRepository remove : {}", key);
        emitterMap.remove(key);
    }


}
