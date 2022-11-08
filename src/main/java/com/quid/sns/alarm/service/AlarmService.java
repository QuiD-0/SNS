package com.quid.sns.alarm.service;

import com.quid.sns.alarm.model.AlarmDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlarmService {

    Page<AlarmDto> getAlarmList(Long id, Pageable pageable);
}
