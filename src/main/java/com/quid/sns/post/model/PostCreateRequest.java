package com.quid.sns.post.model;

import lombok.Builder;

@Builder
public record PostCreateRequest(
    String body,
    String title
) {

}