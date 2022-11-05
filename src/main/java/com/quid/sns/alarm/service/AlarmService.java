package com.quid.sns.alarm.service;

import com.quid.sns.alarm.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlarmService {

    Page<Alarm> getAlarmList(String name, Pageable pageable);
}
