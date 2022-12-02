package com.quid.sns.kafka.event;

import com.quid.sns.alarm.model.AlarmArgs;
import com.quid.sns.alarm.model.AlarmType;
import lombok.Builder;

@Builder
public record AlarmEvent(
    Long receiveUserId,
    AlarmType type,
    AlarmArgs args
) {

}
