package com.quid.sns.like.repository;

import com.quid.sns.like.Likes;
import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByUserAndPost(User user, Post post);
}
