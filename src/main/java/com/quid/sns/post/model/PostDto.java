package com.quid.sns.post.model;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PostDto(
    Long id,
    String title,
    String body,

    String userName,

    LocalDateTime registeredAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {

}
