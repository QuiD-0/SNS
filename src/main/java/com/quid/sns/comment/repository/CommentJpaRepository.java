package com.quid.sns.comment.repository;

import com.quid.sns.comment.Comment;
import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByUserAndPost(User user, Post post);

    Page<Comment> findAllByUser(User user, Pageable pageable);

    @Modifying
    @Query(value = "insert into Comment (post_id, user_id, content, registered_at) values (:postId, :userId, :content, NOW())", nativeQuery = true)
    int saveById(@Param("postId") Long postId, @Param("userId") Long userid,
        @Param("content") String content);

    @Modifying
    @Query("delete from Comment c where c.id = :commentId")
    void deleteById(@Param("commentId") Long commentId);
}
