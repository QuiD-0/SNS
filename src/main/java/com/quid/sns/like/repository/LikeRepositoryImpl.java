package com.quid.sns.like.repository;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.like.Likes;
import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikesRepository {

    private final LikeJpaRepository likeJpaRepository;

    @Override
    public int countAllByPostId(Long postId) {
        return likeJpaRepository.countAllByPostId(postId);
    }

    @Override
    public void delete(Likes like) {
        likeJpaRepository.delete(like);
    }

    @Override
    public void save(Likes like) {
        likeJpaRepository.save(like);
    }

    @Override
    public Optional<Likes> findByUserAndPost(User user, Post post) {
        return likeJpaRepository.findByUserAndPost(user, post);
    }

    @Override
    public void saveOrThrow(User user, Post post) {
        likeJpaRepository.findByUserAndPost(user, post).ifPresentOrElse(like -> {
            throw new SnsApplicationException(ErrorCode.ALREADY_LIKED);
        }, () -> likeJpaRepository.save(Likes.builder().user(user).post(post).build()));
    }

    @Override
    public void deleteOrThrow(User user, Post post) {
        likeJpaRepository.findByUserAndPost(user, post)
            .ifPresentOrElse(like -> likeJpaRepository.delete(like), () -> {
                throw new SnsApplicationException(ErrorCode.ALREADY_UNLIKED);
            });
    }


}
