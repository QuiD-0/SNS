package com.quid.sns.post.service;

import com.quid.sns.post.model.PostModifyRequest;
import org.springframework.security.core.Authentication;

public interface PostService {

    void create(String title, String body, String userName);

    void modify(Long id, PostModifyRequest request, String userName);
}
