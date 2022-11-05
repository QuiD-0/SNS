package com.quid.sns.alarm.repository;

import com.quid.sns.alarm.Alarm;
import com.quid.sns.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AlarmRepositoryImpl implements AlarmRepository {

    private final AlarmJpaRepository alarmJpaRepository;

    @Override
    public Page<Alarm> getAlarmList(User user, Pageable pageable) {
        return alarmJpaRepository.findAllByUser(user, pageable);
    }

    @Override
    public void save(Alarm alarm) {
        alarmJpaRepository.save(alarm);
    }
}
