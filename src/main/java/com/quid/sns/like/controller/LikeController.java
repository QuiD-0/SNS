package com.quid.sns.like.controller;

import com.quid.sns.common.Response;
import com.quid.sns.like.service.LikeService;
import com.quid.sns.post.model.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/like")
    public Response likePost(@PathVariable(name = "postId") Long postId,
        Authentication authentication) {
        likeService.likePost(postId, authentication.getName());
        return Response.success();
    }

    @PostMapping("/{postId}/unlike")
    public Response unlikePost(@PathVariable(name = "postId") Long postId,
        Authentication authentication) {
        likeService.unlikePost(postId, authentication.getName());
        return Response.success();
    }

    @GetMapping("/{postId}")
    public Response<Integer> getLikeCount(@PathVariable(name = "postId") Long postId) {
        return Response.success(likeService.countLikes(postId));
    }

    @GetMapping()
    public Response<Page<PostDto>> getLikedPosts(Authentication authentication, Pageable pageable) {
        return Response.success(likeService.getLikedPosts(authentication.getName(), pageable));
    }

}
