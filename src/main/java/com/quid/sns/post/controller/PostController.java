package com.quid.sns.post.controller;

import com.quid.sns.common.Response;
import com.quid.sns.post.model.PostCreateRequest;
import com.quid.sns.post.model.PostModifyRequest;
import com.quid.sns.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Void> create(@RequestBody PostCreateRequest request,
        Authentication authentication) {
        postService.create(request.getTitle(), request.getBody(), authentication.getName());
        return Response.success();
    }

    @PutMapping("/{pk}")
    public Response<Void> modify(@PathVariable(name = "pk") Long id,
        @RequestBody PostModifyRequest request, Authentication authentication) {
        postService.modify(id, request, authentication.getName());
        return Response.success();
    }

}
