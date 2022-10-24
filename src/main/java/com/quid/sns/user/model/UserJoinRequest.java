package com.quid.sns.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserJoinRequest {

    private String username;

    private String password;

    @Builder
    public UserJoinRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
