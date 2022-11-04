package com.quid.sns.like.service;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.like.Likes;
import com.quid.sns.like.repository.LikeJpaRepository;
import com.quid.sns.post.Post;
import com.quid.sns.post.repository.PostRepository;
import com.quid.sns.user.User;
import com.quid.sns.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeJpaRepository likeJpaRepository;

    @Override
    @Transactional
    public void likePost(Long postId, String userName) {
        User user = userRepository.findByUserNameOrThrow(userName);
        Post post = postRepository.findByIdOrThrow(postId);

        likeJpaRepository.findByUserAndPost(user, post)
            .ifPresentOrElse(like -> {
                throw new SnsApplicationException(ErrorCode.ALREADY_LIKED);
            }, () -> likeJpaRepository.save(Likes.builder()
                .user(user)
                .post(post)
                .build()));
    }

    @Override
    @Transactional
    public void unlikePost(Long postId, String userName) {
        User user = userRepository.findByUserNameOrThrow(userName);
        Post post = postRepository.findByIdOrThrow(postId);

        likeJpaRepository.findByUserAndPost(user, post)
            .ifPresentOrElse(like -> likeJpaRepository.delete(like), () -> {
                throw new SnsApplicationException(ErrorCode.ALREADY_UNLIKED);
            });
    }

    @Override
    @Transactional(readOnly = true)
    public int countLikes(Long postId) {
        return likeJpaRepository.countAllByPostId(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> getLikedPosts(String userName, Pageable pageable) {
        User user = userRepository.findByUserNameOrThrow(userName);

        return postRepository.findByUser(user, pageable);
    }

}
