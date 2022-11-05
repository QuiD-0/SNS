package com.quid.sns.alarm.repository;

import com.quid.sns.alarm.Alarm;
import com.quid.sns.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlarmRepository {

    Page<Alarm> getAlarmList(User user, Pageable pageable);
}
