package com.quid.sns.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserJoinRequest {

    private String name;

    private String password;

    @Builder
    public UserJoinRequest(String userName, String password) {
        this.name = userName;
        this.password = password;
    }
}
