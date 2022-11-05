package com.quid.sns.comment.controller;

import com.quid.sns.comment.Comment;
import com.quid.sns.comment.model.CommentCreateRequest;
import com.quid.sns.comment.model.CommentUpdateRequest;
import com.quid.sns.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
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
        commentService.createComment(request, authentication.getName(), pageable);
    }

    @PutMapping("/{postId}")
    public void updateComment(@RequestBody CommentUpdateRequest request, @PathVariable Long postId,
        Authentication authentication) {
        commentService.updateComment(request, authentication.getName(), postId);
    }

    @GetMapping("/user/{userName}")
    public Page<Comment> getCommentByUser(@PathVariable String userName, Pageable pageable) {
        return commentService.getCommentByUser(userName, pageable);
    }


}
