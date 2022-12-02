package com.quid.sns.alarm.model;

import lombok.Builder;

@Builder
public record AlarmArgs(
    Long fromUserId,
    Long targetId) {

}
