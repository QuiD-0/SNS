package com.quid.sns.post.model;

import lombok.Builder;
import lombok.Data;

@Data
public class PostModifyRequest {

    private String title;

    private String body;

    @Builder
    public PostModifyRequest(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
