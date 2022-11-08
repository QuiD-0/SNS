package com.quid.sns.comment.service;

import com.quid.sns.comment.model.CommentCreateRequest;
import com.quid.sns.comment.model.CommentDto;
import com.quid.sns.comment.model.CommentUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    void createComment(CommentCreateRequest request, Long id, Pageable pageable);

    void updateComment(CommentUpdateRequest request, String name, Long commentId);

    Page<CommentDto> getCommentByUser(String userName, Pageable pageable);

    void deleteComment(Long commentId);
}
