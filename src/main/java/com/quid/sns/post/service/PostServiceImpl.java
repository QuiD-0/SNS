package com.quid.sns.post.service;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.post.Post;
import com.quid.sns.post.repository.PostJpaRepository;
import com.quid.sns.user.User;
import com.quid.sns.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostJpaRepository postRepository;

    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional
    public void create(String title, String body, String userName) {
        User user = userJpaRepository.findByUserName(userName)
            .orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND));

        Post post = Post.builder().title(title).body(body).user(user).build();

        postRepository.save(post);
    }
}
