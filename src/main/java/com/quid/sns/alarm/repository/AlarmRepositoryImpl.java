package com.quid.sns.alarm.repository;

import com.quid.sns.alarm.Alarm;
import com.quid.sns.alarm.model.AlarmDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AlarmRepositoryImpl implements AlarmRepository {

    private final AlarmJpaRepository alarmJpaRepository;

    @Override
    public Page<AlarmDto> getAlarmList(Long id, Pageable pageable) {
        return alarmJpaRepository.findAllByUserId(id, pageable).map(Alarm::toDto);
    }

    @Override
    public void save(Alarm alarm) {
        alarmJpaRepository.save(alarm);
    }
}
