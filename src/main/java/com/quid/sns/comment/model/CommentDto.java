package com.quid.sns.comment.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private String content;
    private String userName;
    private Long postId;
    private LocalDateTime registeredAt;

    @Builder
    public CommentDto(Long id, String content, String userName, Long postId,
        LocalDateTime registeredAt) {
        this.id = id;
        this.content = content;
        this.userName = userName;
        this.postId = postId;
        this.registeredAt = registeredAt;
    }

}
