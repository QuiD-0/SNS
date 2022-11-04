package com.quid.sns.comment.model;

import lombok.Builder;
import lombok.Data;

@Data
public class CommentCreateRequest {

    private String content;
    private Long postId;

    @Builder
    public CommentCreateRequest(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }
}
