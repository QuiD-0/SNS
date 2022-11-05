package com.quid.sns.alarm.controller;

import com.quid.sns.alarm.Alarm;
import com.quid.sns.alarm.service.AlarmService;
import com.quid.sns.common.Response;
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
    public Response<Page<Alarm>> getAlarmList(Authentication authentication, Pageable pageable) {
        return Response.success(alarmService.getAlarmList(authentication.getName(), pageable));
    }

}
