package com.quid.sns.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequest {

    private String userName;

    private String password;

    @Builder
    public UserLoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
