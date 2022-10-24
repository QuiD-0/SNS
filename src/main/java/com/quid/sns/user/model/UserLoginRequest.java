package com.quid.sns.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequest {

    private String name;

    private String password;

    @Builder
    public UserLoginRequest(String userName, String password) {
        this.name = userName;
        this.password = password;
    }

}
