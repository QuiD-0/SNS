package com.quid.sns.comment.controller;

import com.quid.sns.comment.model.CommentCreateRequest;
import com.quid.sns.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
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


}
