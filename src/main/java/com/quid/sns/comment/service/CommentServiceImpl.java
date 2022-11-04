package com.quid.sns.comment.service;

import com.quid.sns.comment.model.CommentCreateRequest;
import com.quid.sns.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void createComment(CommentCreateRequest request, String name, Pageable pageable) {

    }

}
