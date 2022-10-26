package com.quid.sns.post.controller;

import com.quid.sns.common.Response;
import com.quid.sns.post.model.PostCreateRequest;
import com.quid.sns.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Void> create(@RequestBody PostCreateRequest request) {
        postService.create(request.getTitle(), request.getBody(), request.getUserName());
        return Response.success();
    }

}
