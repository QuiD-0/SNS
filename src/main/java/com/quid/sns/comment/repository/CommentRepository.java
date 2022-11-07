package com.quid.sns.comment.repository;

import com.quid.sns.comment.Comment;
import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepository {

    void save(Comment comment);

    Comment findByUserAndPostOrThrow(User user, Post post);

    Page<Comment> findAllByUser(User user, Pageable pageable);

    void delete(Comment comment);

    void saveById(Long id, Long postId, String content);
}
