package com.quid.sns.post.repository;

import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    Page<Post> findByUser(User user, Pageable pageable);

}
