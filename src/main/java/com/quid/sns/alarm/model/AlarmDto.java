package com.quid.sns.alarm.model;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record AlarmDto(
    Long id,
    String userName,
    AlarmType type,
    AlarmArgs args,
    LocalDateTime registeredAt,
    LocalDateTime updatedAt,
    LocalDateTime removedAt
) {

}