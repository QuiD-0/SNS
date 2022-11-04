package com.quid.sns.post.service;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.post.Post;
import com.quid.sns.post.model.PostModifyRequest;
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
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void create(String title, String body, String userName) {
        User user = userRepository.findByUserNameOrThrow(userName);
        Post post = Post.builder().title(title).body(body).user(user).build();

        postRepository.save(post);
    }

    @Override
    @Transactional
    public void modify(Long id, PostModifyRequest request, String userName) {
        User user = userRepository.findByUserNameOrThrow(userName);
        Post post = postRepository.findByIdOrThrow(id);

        if (!user.equals(post.getUser())) {
            throw new SnsApplicationException(ErrorCode.USER_NOT_MATCHED);
        }
        post.updatePost(request.getTitle(), request.getBody());
    }

    @Override
    @Transactional
    public void delete(Long id, String userName) {
        User user = userRepository.findByUserNameOrThrow(userName);
        Post post = postRepository.findByIdOrThrow(id);

        if (!user.equals(post.getUser())) {
            throw new SnsApplicationException(ErrorCode.USER_NOT_MATCHED);
        }

        postRepository.delete(post);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> list(Pageable pageable, String userName) {
        userRepository.checkUserExist(userName);
        return postRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> myFeed(Pageable pageable, String userName) {
        User user = userRepository.findByUserNameOrThrow(userName);
        return postRepository.findByUser(user, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> search(Pageable pageable, String keyword) {
        return postRepository.searchPost(keyword, keyword, pageable);
    }
}
