package com.quid.sns.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserJoinRequest {

    private String userName;

    private String password;

    @Builder
    public UserJoinRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
