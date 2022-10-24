package com.quid.sns.user.model;

import lombok.Builder;
import lombok.Data;

@Data
public class UserLoginResponse {

    private String token;

    @Builder
    public UserLoginResponse(String token) {
        this.token = token;
    }
}
