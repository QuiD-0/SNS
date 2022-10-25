package com.quid.sns.post.service;

import com.quid.sns.exception.SnsApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    public void createPost_when_user_exist() {
        Assertions.assertThrows(SnsApplicationException.class, () -> {
            postService.create("test", "body", "quid");
        });
    }

    @Test
    public void createPost_when_user_not_exist() {
        Assertions.assertThrows(SnsApplicationException.class, () -> {
            postService.create("test", "body", "user");
        });
    }
}
