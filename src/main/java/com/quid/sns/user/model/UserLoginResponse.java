package com.quid.sns.user.model;

import lombok.Builder;

@Builder
public record UserLoginResponse(String token) {

}
