package com.quid.sns.comment.model;

import com.quid.sns.common.BaseEntity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
public class CommentDto extends BaseEntity {

    private Long id;
    private String content;
    private String userName;
    private Long postId;

    @Builder
    public CommentDto(Long id, String content, String userName, Long postId,
        LocalDateTime registeredAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(registeredAt, updatedAt, deletedAt);
        this.id = id;
        this.content = content;
        this.userName = userName;
        this.postId = postId;
    }

}
