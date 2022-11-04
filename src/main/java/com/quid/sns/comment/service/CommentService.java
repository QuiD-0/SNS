package com.quid.sns.comment.service;

import com.quid.sns.comment.model.CommentCreateRequest;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    void createComment(CommentCreateRequest request, String name, Pageable pageable);
}
