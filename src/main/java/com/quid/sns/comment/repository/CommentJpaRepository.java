package com.quid.sns.comment.repository;

import com.quid.sns.comment.Comment;
import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByUserAndPost(User user, Post post);

    Page<Comment> findAllByUser(User user, Pageable pageable);
}
