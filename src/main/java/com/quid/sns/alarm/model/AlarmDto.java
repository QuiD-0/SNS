package com.quid.sns.alarm.model;

import com.quid.sns.common.BaseEntity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
public class AlarmDto extends BaseEntity {

    private Long id;

    private String userName;

    private AlarmType type;

    private AlarmArgs args;

    @Builder
    public AlarmDto(Long id, String userName, AlarmType type, AlarmArgs args,
        LocalDateTime registeredAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(registeredAt, updatedAt, deletedAt);
        this.id = id;
        this.userName = userName;
        this.type = type;
        this.args = args;
    }
}
