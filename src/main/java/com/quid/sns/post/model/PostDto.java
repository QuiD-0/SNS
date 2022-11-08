package com.quid.sns.post.model;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDto {

    private Long id;

    private String title;

    private String body;

    private String userName;
    private LocalDateTime registeredAt;
    private LocalDateTime updatedAt;

    @Builder
    public PostDto(Long id, String title, String body, String userName, LocalDateTime registeredAt,
        LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userName = userName;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
    }

}
