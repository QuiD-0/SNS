package com.quid.sns.comment.repository;

import com.quid.sns.comment.Comment;
import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public void save(Comment comment) {
        commentJpaRepository.save(comment);
    }

    @Override
    public Comment findByUserAndPostOrThrow(User user, Post post) {
        return commentJpaRepository.findByUserAndPost(user, post)
            .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }
}
