package com.quid.sns.comment.service;

import com.quid.sns.comment.Comment;
import com.quid.sns.comment.model.CommentCreateRequest;
import com.quid.sns.comment.model.CommentUpdateRequest;
import com.quid.sns.comment.repository.CommentRepository;
import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.post.Post;
import com.quid.sns.post.repository.PostRepository;
import com.quid.sns.user.User;
import com.quid.sns.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    @Override
    public void createComment(CommentCreateRequest request, String name, Pageable pageable) {
        User user = userRepository.findByUserNameOrThrow(name);
        Post post = postRepository.findByIdOrThrow(request.getPostId());

        commentRepository.save(
            Comment.builder().content(request.getContent()).user(user).post(post).build());
    }

    @Override
    public void updateComment(CommentUpdateRequest request, String name, Long postId) {
        User user = userRepository.findByUserNameOrThrow(name);
        Post post = postRepository.findByIdOrThrow(postId);
        Comment comment = commentRepository.findByUserAndPostOrThrow(user, post);

        comment.updateComment(request.getContent());
    }

    @Override
    public Page<Comment> getCommentByUser(String userName, Pageable pageable) {
        User user = userRepository.findByUserNameOrThrow(userName);
        return commentRepository.findAllByUser(user, pageable);
    }

    @Override
    public void deleteComment(Long postId, String name) {
        User user = userRepository.findByUserNameOrThrow(name);
        Post post = postRepository.findByIdOrThrow(postId);

        Comment comment = commentRepository.findByUserAndPostOrThrow(user, post);
        commentRepository.delete(comment);
    }

}
