package com.quid.sns.like.service;

import com.quid.sns.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LikeService {

    void likePost(Long postId, String userName);

    void unlikePost(Long postId, String userName);

    int countLikes(Long postId);

    Page<Post> getLikedPosts(String name, Pageable pageable);
}
