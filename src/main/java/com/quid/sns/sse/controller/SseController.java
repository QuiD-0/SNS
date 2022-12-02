package com.quid.sns.sse.controller;

import com.quid.sns.common.ClassUtils;
import com.quid.sns.sse.service.SseService;
import com.quid.sns.user.model.UserDto;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarms")
public class SseController {

    private final SseService sseService;

    @GetMapping("/subscribe")
    public SseEmitter subscribe(Authentication authentication) throws IOException {
        UserDto userDto = ClassUtils.castInstance(authentication.getPrincipal(), UserDto.class);
        return sseService.connectNotification(userDto.id());
    }

}
