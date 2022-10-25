package com.quid.sns.user.service;

import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.user.model.UserJoinRequest;
import com.quid.sns.user.model.UserLoginRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserDtoServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void join_when_user_not_exist() {
        UserJoinRequest request = UserJoinRequest.builder()
            .userName("name").password("password").build();

        Assertions.assertDoesNotThrow(() -> {
            userService.join(request);
        });
    }

    @Test
    public void join_when_user_exist() {
        UserJoinRequest request = UserJoinRequest.builder()
            .userName("quid").password("password").build();

        Assertions.assertThrows(SnsApplicationException.class, () -> userService.join(request));
    }

    @Test
    public void login_when_user_exist_and_authorized() {
        UserLoginRequest request = UserLoginRequest.builder()
            .userName("hanpass").password("hanpass1!").build();

        Assertions.assertDoesNotThrow(() -> {
            userService.login(request);
        });
    }

    @Test
    public void login_when_username_not_exist() {
        UserLoginRequest request = UserLoginRequest.builder()
            .password("password").build();

        Assertions.assertThrows(SnsApplicationException.class, () -> userService.login(request));
    }

    @Test
    public void login_when_password_not_exist() {
        UserLoginRequest request = UserLoginRequest.builder()
            .userName("name").build();

        Assertions.assertThrows(SnsApplicationException.class, () -> userService.login(request));
    }
}
