package com.quid.sns.comment.service;

import com.quid.sns.alarm.model.AlarmArgs;
import com.quid.sns.alarm.model.AlarmType;
import com.quid.sns.comment.Comment;
import com.quid.sns.comment.model.CommentCreateRequest;
import com.quid.sns.comment.model.CommentDto;
import com.quid.sns.comment.model.CommentUpdateRequest;
import com.quid.sns.comment.repository.CommentRepository;
import com.quid.sns.common.RestPage;
import com.quid.sns.kafka.event.AlarmEvent;
import com.quid.sns.kafka.producer.AlarmProducer;
import com.quid.sns.post.Post;
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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final AlarmProducer alarmProducer;

    @Override
    @Transactional
    public void createComment(CommentCreateRequest request, Long userId, Pageable pageable) {
        Post post = postRepository.findByIdOrThrow(request.postId());

        commentRepository.saveById(request.postId(), userId, request.content());

        AlarmEvent alarmEvent = AlarmEvent.builder().receiveUserId(post.getUser().getId())
            .type(AlarmType.NEW_COMMENT_ON_POST).args(
                AlarmArgs.builder().fromUserId(userId).targetId(post.getId()).build())
            .build();
        alarmProducer.send(alarmEvent);

    }

    @Override
    @Transactional
    public void updateComment(CommentUpdateRequest request, String name, Long commentId) {
        Comment comment = commentRepository.findByIdOrThrow(commentId);

        comment.updateComment(request.content());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentDto> getCommentByUser(String userName, Pageable pageable) {
        User user = userRepository.findByUserNameOrThrow(userName);
        return commentRepository.findAllByUser(user, pageable).map(Comment::toDto);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.delete(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "PostCommentList", key = "#postId")
    public Page<CommentDto> getCommentByPost(Long postId, Pageable pageable) {
        return new RestPage(
            commentRepository.findAllByPostId(postId, pageable).map(Comment::toDto));
    }

}
