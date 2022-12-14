package com.quid.sns.comment.controller;

import com.quid.sns.comment.model.CommentCreateRequest;
import com.quid.sns.comment.model.CommentDto;
import com.quid.sns.comment.model.CommentUpdateRequest;
import com.quid.sns.comment.service.CommentService;
import com.quid.sns.common.ClassUtils;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public void createComment(@RequestBody CommentCreateRequest request,
        Authentication authentication, Pageable pageable) {
        UserDto userDto = ClassUtils.castInstance(authentication.getPrincipal(), UserDto.class);
        commentService.createComment(request, userDto.id(), pageable);
    }

    @PutMapping("/{commentId}")
    public void updateComment(@RequestBody CommentUpdateRequest request,
        @PathVariable Long commentId, Authentication authentication) {
        commentService.updateComment(request, authentication.getName(), commentId);
    }

    @GetMapping("/user/{userName}")
    public Page<CommentDto> getCommentByUser(@PathVariable String userName, Pageable pageable) {
        return commentService.getCommentByUser(userName, pageable);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }


    @GetMapping("/post/{postId}")
    public Page<CommentDto> getCommentByPost(@PathVariable Long postId, Pageable pageable) {
        return commentService.getCommentByPost(postId, pageable);
    }

}
