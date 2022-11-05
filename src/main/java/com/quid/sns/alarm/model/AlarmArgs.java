package com.quid.sns.alarm.model;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class AlarmArgs {

    private Long fromUserId;

    private Long targetId;
}
