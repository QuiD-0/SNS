package com.quid.sns.comment.repository;

import com.quid.sns.comment.Comment;
import com.quid.sns.comment.model.CommentDto;
import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment findByUserAndPostOrThrow(User user, Post post) {
        return commentJpaRepository.findByUserAndPost(user, post)
            .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    @Override
    public Page<CommentDto> findAllByUser(User user, Pageable pageable) {
        return commentJpaRepository.findAllByUser(user, pageable).map(Comment::toDto);
    }

    @Override
    public void delete(Long commentId) {
        commentJpaRepository.deleteById(commentId);
    }

    @Override
    public void saveById(Long postId, Long userId, String content) {
        commentJpaRepository.saveById(postId, userId, content);
    }

    @Override
    public Comment findByIdOrThrow(Long commentId) {
        return commentJpaRepository.findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }
}
