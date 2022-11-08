package com.quid.sns.alarm.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
public class AlarmDto {

    private Long id;

    private String userName;

    private AlarmType type;

    private AlarmArgs args;

    private LocalDateTime registeredAt;

    private LocalDateTime updatedAt;

    @Builder
    public AlarmDto(Long id, String userName, AlarmType type, AlarmArgs args,
        LocalDateTime registeredAt,
        LocalDateTime updatedAt) {
        this.id = id;
        this.userName = userName;
        this.type = type;
        this.args = args;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
    }
}
