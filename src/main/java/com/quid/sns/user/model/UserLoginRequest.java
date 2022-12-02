package com.quid.sns.user.model;

import lombok.Builder;

@Builder
public record UserLoginRequest(
    String name,
    String password
) {

}
