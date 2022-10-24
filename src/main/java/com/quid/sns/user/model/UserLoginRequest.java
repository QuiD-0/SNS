package com.quid.sns.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequest {

    private String username;

    private String password;

    @Builder
    public UserLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
