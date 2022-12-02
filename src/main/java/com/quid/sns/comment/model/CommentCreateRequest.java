package com.quid.sns.comment.model;

import lombok.Builder;

@Builder
public record CommentCreateRequest(
    String content,
    Long postId) {

}
