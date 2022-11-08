package com.quid.sns.post.repository;

import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    Page<Post> findByUser(User user, Pageable pageable);

    Page<Post> findByTitleContainingOrBodyContaining(String keyword1, String keyword2,
        Pageable pageable);

    @Modifying
    @Query("delete from Post p where p.id = :id")
    void deleteById(@Param("id") Long id);
}
