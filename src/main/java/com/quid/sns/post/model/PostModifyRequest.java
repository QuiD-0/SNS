package com.quid.sns.post.model;

import lombok.Builder;

@Builder
public record PostModifyRequest(
    String title,
    String body
) {

}
