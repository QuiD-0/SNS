package com.quid.sns.comment.repository;

import com.quid.sns.comment.Comment;
import com.quid.sns.post.Post;
import com.quid.sns.user.User;

public interface CommentRepository {

    void save(Comment comment);

    Comment findByUserAndPostOrThrow(User user, Post post);
}
