package com.quid.sns.post.service;

import com.quid.sns.post.model.PostDto;
import com.quid.sns.post.model.PostModifyRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    void create(String title, String body, String userName);

    void modify(Long id, PostModifyRequest request, String userName);

    void delete(Long id, String name);

    Page<PostDto> list(Pageable pageable, String name);

    Page<PostDto> myFeed(Pageable pageable, String name);

    Page<PostDto> search(Pageable pageable, String keyword);

}
