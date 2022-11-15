package com.quid.sns.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post not founded"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password"),
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "Duplicated user name"),
    ALREADY_LIKED_POST(HttpStatus.CONFLICT, "uUser already like the post"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "User has invalid permission"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurs"),
    NOTIFICATION_CONNECT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
        "Connect to notification occurs error"),
    USER_NOT_MATCHED(HttpStatus.CONFLICT, "User not matched"),
    ALREADY_LIKED(HttpStatus.CONFLICT, "Already liked"),
    ALREADY_UNLIKED(HttpStatus.CONFLICT, "Already unLike"),
    ILLEGAL_STATE(HttpStatus.CONFLICT, "Illegal state"),
    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "User already exist"),
    ALARM_CONNECT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Alarm Connect Error"),
    ;

    private final HttpStatus status;
    private final String message;
}
