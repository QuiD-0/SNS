package com.quid.sns.post.controller;

import com.quid.sns.common.Response;
import com.quid.sns.post.Post;
import com.quid.sns.post.model.PostCreateRequest;
import com.quid.sns.post.model.PostModifyRequest;
import com.quid.sns.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @DeleteMapping("/{pk}")
    public Response<Void> delete(@PathVariable(name = "pk") Long id,
        Authentication authentication) {
        postService.delete(id, authentication.getName());
        return Response.success();
    }

    @GetMapping
    public Response<Page<Post>> list(Pageable pageable, Authentication authentication){
        Page<Post> list = postService.list(pageable, authentication.getName());
        return Response.success(list);
    }

}
