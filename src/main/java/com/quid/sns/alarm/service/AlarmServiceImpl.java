package com.quid.sns.alarm.service;

import com.quid.sns.alarm.Alarm;
import com.quid.sns.alarm.repository.AlarmRepository;
import com.quid.sns.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;

    private final UserRepository userRepository;

    @Override
    public Page<Alarm> getAlarmList(Long id, Pageable pageable) {

        return alarmRepository.getAlarmList(id, pageable);
    }

}
