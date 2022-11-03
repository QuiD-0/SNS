package com.quid.sns.like.controller;

import com.quid.sns.common.Response;
import com.quid.sns.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/like/{postId}")
    public Response likePost(@PathVariable(name = "postId") Long postId,
        Authentication authentication) {
        likeService.likePost(postId, authentication.getName());
        return Response.success();
    }

    @GetMapping("/unlike/{postId}")
    public Response unlikePost(@PathVariable(name = "postId") Long postId,
        Authentication authentication) {
        likeService.unlikePost(postId, authentication.getName());
        return Response.success();
    }
}
