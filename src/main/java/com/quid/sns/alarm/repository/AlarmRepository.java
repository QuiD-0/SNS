package com.quid.sns.alarm.repository;

import com.quid.sns.alarm.Alarm;
import com.quid.sns.alarm.model.AlarmDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlarmRepository {

    Page<AlarmDto> getAlarmList(Long id, Pageable pageable);

    void save(Alarm alarm);
}
