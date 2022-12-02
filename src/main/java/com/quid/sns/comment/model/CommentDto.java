package com.quid.sns.comment.model;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CommentDto(
    Long id,
    String content,
    String userName,
    Long postId,
    LocalDateTime registeredAt,
    LocalDateTime updatedAt,
    LocalDateTime removedAt
) {

}
