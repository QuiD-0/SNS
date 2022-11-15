package com.quid.sns.kafka.model;

import com.quid.sns.alarm.model.AlarmArgs;
import com.quid.sns.alarm.model.AlarmType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlarmEvent {

    private Long receiveUserId;

    private AlarmType type;

    private AlarmArgs args;

    @Builder
    public AlarmEvent(Long receiveUserId, AlarmType type, AlarmArgs args) {
        this.receiveUserId = receiveUserId;
        this.type = type;
        this.args = args;
    }
}
