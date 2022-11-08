package com.quid.sns.alarm.service;

import com.quid.sns.alarm.model.AlarmDto;
import com.quid.sns.alarm.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;

    @Override
    public Page<AlarmDto> getAlarmList(Long id, Pageable pageable) {

        return alarmRepository.getAlarmList(id, pageable);
    }

}
