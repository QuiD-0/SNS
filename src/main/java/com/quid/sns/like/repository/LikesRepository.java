package com.quid.sns.like.repository;

import com.quid.sns.like.Likes;
import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import java.util.Optional;

public interface LikesRepository {

    int countAllByPostId(Long postId);

    void delete(Likes like);

    void save(Likes like);

    Optional<Likes> findByUserAndPost(User user, Post post);

    void saveOrThrow(User user, Post post);

    void deleteOrThrow(User user, Post post);
}
