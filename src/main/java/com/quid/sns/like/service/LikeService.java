package com.quid.sns.like.service;

public interface LikeService {

    void likePost(Long postId, String userName);

    void unlikePost(Long postId, String userName);
}
