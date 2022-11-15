package com.quid.sns.sse.service;

import com.quid.sns.alarm.Alarm;
import com.quid.sns.alarm.model.AlarmArgs;
import com.quid.sns.alarm.model.AlarmNoti;
import com.quid.sns.alarm.model.AlarmType;
import com.quid.sns.alarm.repository.AlarmRepository;
import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.sse.repository.SseRepository;
import com.quid.sns.user.User;
import com.quid.sns.user.repository.UserRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseService {

    private final static String ALARM_NAME = "alarm";

    private final AlarmRepository alarmRepository;
    private final SseRepository sseRepository;
    private final UserRepository userRepository;
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    public void send(AlarmType type, AlarmArgs args, Long receiverId) {
        User user = userRepository.findById(receiverId);
        Alarm entity = new Alarm(user, type, args);
        alarmRepository.save(entity);
        sseRepository.get(receiverId).ifPresentOrElse(it -> {
                try {
                    it.send(SseEmitter.event()
                        .id(entity.getId().toString())
                        .name(ALARM_NAME)
                        .data(new AlarmNoti()));
                } catch (IOException exception) {
                    sseRepository.remove(receiverId);
                    throw new SnsApplicationException(ErrorCode.NOTIFICATION_CONNECT_ERROR);
                }
            },
            () -> log.info("No emitter founded")
        );
    }


    public SseEmitter connectNotification(Long userId) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        sseRepository.save(userId, emitter);
        emitter.onCompletion(() -> sseRepository.remove(userId));
        emitter.onTimeout(() -> sseRepository.remove(userId));

        try {
            log.info("send");
            emitter.send(SseEmitter.event()
                .id("id")
                .name(ALARM_NAME)
                .data("connect completed"));
        } catch (IOException exception) {
            throw new SnsApplicationException(ErrorCode.NOTIFICATION_CONNECT_ERROR);
        }
        return emitter;
    }

}
