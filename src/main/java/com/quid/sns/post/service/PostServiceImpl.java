package com.quid.sns.post.service;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.post.Post;
import com.quid.sns.post.model.PostModifyRequest;
import com.quid.sns.post.repository.PostJpaRepository;
import com.quid.sns.user.User;
import com.quid.sns.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    @Override
    @Transactional
    public void modify(Long id, PostModifyRequest request, String userName) {
        User user = userJpaRepository.findByUserName(userName)
            .orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND));

        Post post = postRepository.findById(id)
            .orElseThrow(() -> new SnsApplicationException(ErrorCode.POST_NOT_FOUND));

        if(!user.equals(post.getUser())){
            throw new SnsApplicationException(ErrorCode.USER_NOT_MATCHED);
        }
        post.updatePost(request.getTitle(), request.getBody());
    }
}
