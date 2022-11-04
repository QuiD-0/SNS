package com.quid.sns.post.repository;

import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository {

    Page<Post> findByUser(User user, Pageable pageable);

    Page<Post> searchPost(String keyword1, String keyword2, Pageable pageable);

    Post findByIdOrThrow(Long postId);

    void delete(Post post);

    Page<Post> findAll(Pageable pageable);

    void save(Post post);
}
