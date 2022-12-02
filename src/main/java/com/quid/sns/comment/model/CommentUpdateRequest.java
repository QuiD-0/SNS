package com.quid.sns.comment.model;

import lombok.Builder;

@Builder
public record CommentUpdateRequest(
    String content
) {

}
