package com.quid.sns.alarm.controller;

import com.quid.sns.alarm.model.AlarmDto;
import com.quid.sns.alarm.service.AlarmService;
import com.quid.sns.common.ClassUtils;
import com.quid.sns.common.Response;
import com.quid.sns.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarms")
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping
    public Response<Page<AlarmDto>> getAlarmList(Authentication authentication, Pageable pageable) {
        UserDto userDto = ClassUtils.castInstance(authentication.getPrincipal(), UserDto.class);
        return Response.success(alarmService.getAlarmList(userDto.id(), pageable));
    }

}
