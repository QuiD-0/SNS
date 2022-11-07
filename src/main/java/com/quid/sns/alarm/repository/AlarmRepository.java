package com.quid.sns.alarm.repository;

import com.quid.sns.alarm.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlarmRepository {

    Page<Alarm> getAlarmList(Long id, Pageable pageable);

    void save(Alarm build);
}
