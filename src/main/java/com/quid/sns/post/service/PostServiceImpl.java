package com.quid.sns.post.service;

import com.quid.sns.common.RestPage;
import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.post.Post;
import com.quid.sns.post.model.PostDto;
import com.quid.sns.post.model.PostModifyRequest;
import com.quid.sns.post.repository.PostRepository;
import com.quid.sns.user.User;
import com.quid.sns.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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
    public void delete(Long id, Long userId) {
        Post post = postRepository.findByIdOrThrow(id);

        if (post.getUser().getId() != userId) {
            throw new SnsApplicationException(ErrorCode.USER_NOT_MATCHED);
        }

        postRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "postList", key = "{#userName, #pageable.pageNumber, #pageable.pageSize}")
    @Transactional(readOnly = true)
    public RestPage<PostDto> list(Pageable pageable, String userName) {
        userRepository.findByUserNameOrThrow(userName);
        return new RestPage(postRepository.findAll(pageable).map(Post::toDto));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostDto> myFeed(Pageable pageable, String userName) {
        User user = userRepository.findByUserNameOrThrow(userName);
        return postRepository.findByUser(user, pageable).map(Post::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostDto> search(Pageable pageable, String keyword) {
        return postRepository.searchPost(keyword, keyword, pageable).map(Post::toDto);
    }
}
