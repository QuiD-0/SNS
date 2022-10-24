package com.quid.sns.user.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    private String username;


    @Builder
    public UserDto(String username) {
        this.username = username;
    }
}
