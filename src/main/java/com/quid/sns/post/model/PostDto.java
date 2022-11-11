package com.quid.sns.post.model;

import com.quid.sns.common.BaseEntity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDto extends BaseEntity {

    private Long id;

    private String title;

    private String body;

    private String userName;

    @Builder
    public PostDto(Long id, String title, String body, String userName, LocalDateTime registeredAt,
        LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(registeredAt, updatedAt, deletedAt);
        this.id = id;
        this.title = title;
        this.body = body;
        this.userName = userName;
    }

}
