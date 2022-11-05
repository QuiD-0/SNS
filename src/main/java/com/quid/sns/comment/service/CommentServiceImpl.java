package com.quid.sns.comment.service;

import com.quid.sns.alarm.Alarm;
import com.quid.sns.alarm.model.AlarmArgs;
import com.quid.sns.alarm.model.AlarmType;
import com.quid.sns.alarm.repository.AlarmRepository;
import com.quid.sns.comment.Comment;
import com.quid.sns.comment.model.CommentCreateRequest;
import com.quid.sns.comment.model.CommentUpdateRequest;
import com.quid.sns.comment.repository.CommentRepository;
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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final AlarmRepository alarmRepository;

    @Override
    @Transactional
    public void createComment(CommentCreateRequest request, String name, Pageable pageable) {
        User user = userRepository.findByUserNameOrThrow(name);
        Post post = postRepository.findByIdOrThrow(request.getPostId());

        commentRepository.save(
            Comment.builder().content(request.getContent()).user(user).post(post).build());

        alarmRepository.save(
            Alarm.builder().user(post.getUser()).type(AlarmType.NEW_COMMENT_ON_POST).args(
                    AlarmArgs.builder().fromUserId(user.getId()).targetId(post.getId()).build())
                .build());
    }

    @Override
    @Transactional
    public void updateComment(CommentUpdateRequest request, String name, Long postId) {
        User user = userRepository.findByUserNameOrThrow(name);
        Post post = postRepository.findByIdOrThrow(postId);
        Comment comment = commentRepository.findByUserAndPostOrThrow(user, post);

        comment.updateComment(request.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Comment> getCommentByUser(String userName, Pageable pageable) {
        User user = userRepository.findByUserNameOrThrow(userName);
        return commentRepository.findAllByUser(user, pageable);
    }

    @Override
    @Transactional
    public void deleteComment(Long postId, String name) {
        User user = userRepository.findByUserNameOrThrow(name);
        Post post = postRepository.findByIdOrThrow(postId);

        Comment comment = commentRepository.findByUserAndPostOrThrow(user, post);
        commentRepository.delete(comment);
    }

}
