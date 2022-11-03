package com.quid.sns.like.service;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.like.Likes;
import com.quid.sns.like.repository.LikeJpaRepository;
import com.quid.sns.post.Post;
import com.quid.sns.post.repository.PostJpaRepository;
import com.quid.sns.user.User;
import com.quid.sns.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final LikeJpaRepository likeJpaRepository;

    @Override
    public void likePost(Long postId, String userName) {
        User user = userJpaRepository.findByUserName(userName)
            .orElseThrow(() -> {
                throw new SnsApplicationException(ErrorCode.USER_NOT_FOUND);
            });
        Post post = postJpaRepository.findById(postId)
            .orElseThrow(() -> {
                throw new SnsApplicationException(ErrorCode.POST_NOT_FOUND);
            });

        likeJpaRepository.findByUserAndPost(user, post)
            .ifPresentOrElse(like -> {
                throw new SnsApplicationException(ErrorCode.ALREADY_LIKED);
            }, () -> likeJpaRepository.save(Likes.builder()
                .user(user)
                .post(post)
                .build()));
    }

    @Override
    public void unlikePost(Long postId, String userName) {
        User user = userJpaRepository.findByUserName(userName)
            .orElseThrow(() -> {
                throw new SnsApplicationException(ErrorCode.USER_NOT_FOUND);
            });
        Post post = postJpaRepository.findById(postId)
            .orElseThrow(() -> {
                throw new SnsApplicationException(ErrorCode.POST_NOT_FOUND);
            });

        likeJpaRepository.findByUserAndPost(user, post)
            .ifPresentOrElse(like -> {
                likeJpaRepository.delete(like);
            }, () -> {
                throw new SnsApplicationException(ErrorCode.ALREADY_UNLIKED);
            });
    }

}
