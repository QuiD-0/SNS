package com.quid.sns.alarm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AlarmType {
    NEW_COMMENT_ON_POST("new comment on post"),
    NEW_LIKE_ON_POST("new like on post"),
    ;

    private final String alarmText;

}
