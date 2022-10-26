package com.quid.sns.post.model;

import lombok.Data;

@Data
public class PostCreateRequest {

    private String title;

    private String body;

    private String userName;
}
