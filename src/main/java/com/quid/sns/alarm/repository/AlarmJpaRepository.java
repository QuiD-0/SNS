package com.quid.sns.alarm.repository;

import com.quid.sns.alarm.Alarm;
import com.quid.sns.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmJpaRepository extends JpaRepository<Alarm, Long> {

    Page<Alarm> findAllByUser(User user, Pageable pageable);
}
