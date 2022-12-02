package com.quid.sns.user.model;

import lombok.Builder;

@Builder
public record UserJoinRequest(
    String name,
    String password
) {

}
