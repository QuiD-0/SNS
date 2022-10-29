package com.quid.sns.post.service;

import com.quid.sns.post.Post;
import com.quid.sns.post.model.PostModifyRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    void create(String title, String body, String userName);

    void modify(Long id, PostModifyRequest request, String userName);

    void delete(Long id, String name);

    Page<Post> list(Pageable pageable, String name);
}
