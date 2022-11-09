package com.quid.sns.post.controller;

import com.quid.sns.common.ClassUtils;
import com.quid.sns.common.Response;
import com.quid.sns.post.model.PostCreateRequest;
import com.quid.sns.post.model.PostDto;
import com.quid.sns.post.model.PostModifyRequest;
import com.quid.sns.post.service.PostService;
import com.quid.sns.user.model.UserDto;
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

    private static UserDto getUserDto(Authentication authentication) {
        return ClassUtils.castInstance(authentication.getPrincipal(), UserDto.class);
    }

    @GetMapping
    public Response<Page<PostDto>> list(Pageable pageable, Authentication authentication) {
        Page<PostDto> list = postService.list(pageable, authentication.getName());
        return Response.success(list);
    }

    @GetMapping("/my")
    public Response<Page<PostDto>> myList(Pageable pageable, Authentication authentication) {
        Page<PostDto> list = postService.myFeed(pageable, authentication.getName());
        return Response.success(list);
    }

    @GetMapping("/search")
    public Response<Page<PostDto>> search(Pageable pageable, String keyword) {
        Page<PostDto> list = postService.search(pageable, keyword);
        return Response.success(list);
    }

    @DeleteMapping("/{pk}")
    public Response<Void> delete(@PathVariable(name = "pk") Long id,
        Authentication authentication) {
        UserDto userDto = getUserDto(authentication);
        postService.delete(id, userDto.getId());
        return Response.success();
    }
}
