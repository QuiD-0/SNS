package com.quid.sns.like.service;

import com.quid.sns.alarm.Alarm;
import com.quid.sns.alarm.model.AlarmArgs;
import com.quid.sns.alarm.model.AlarmType;
import com.quid.sns.alarm.repository.AlarmRepository;
import com.quid.sns.like.repository.LikesRepository;
import com.quid.sns.post.Post;
import com.quid.sns.post.model.PostDto;
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

    private final LikesRepository likeRepository;

    private final AlarmRepository alarmRepository;

    @Override
    @Transactional
    public void likePost(Long postId, String userName) {
        User user = userRepository.findByUserNameOrThrow(userName);
        Post post = postRepository.findByIdOrThrow(postId);

        likeRepository.saveOrThrow(user, post);
        alarmRepository.save(
            Alarm.builder().user(post.getUser()).type(AlarmType.NEW_LIKE_ON_POST).args(
                    AlarmArgs.builder().fromUserId(user.getId()).targetId(post.getId()).build())
                .build());
    }

    @Override
    @Transactional
    public void unlikePost(Long postId, String userName) {
        User user = userRepository.findByUserNameOrThrow(userName);
        Post post = postRepository.findByIdOrThrow(postId);

        likeRepository.deleteOrThrow(user, post);
    }

    @Override
    @Transactional(readOnly = true)
    public int countLikes(Long postId) {
        return likeRepository.countAllByPostId(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostDto> getLikedPosts(String userName, Pageable pageable) {
        User user = userRepository.findByUserNameOrThrow(userName);

        return postRepository.findByUser(user, pageable).map(Post::toDto);
    }

}
