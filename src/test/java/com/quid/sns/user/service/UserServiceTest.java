package com.quid.sns.user.service;

import com.quid.sns.user.model.UserJoinRequest;
import com.quid.sns.user.model.UserLoginRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void join_when_user_not_exist() {
        UserJoinRequest request = UserJoinRequest.builder()
            .username("name").password("password").build();

        Assertions.assertDoesNotThrow(() -> {
            userService.join(request);
        });
    }

    @Test
    public void join_when_user_exist() {
        UserJoinRequest request = UserJoinRequest.builder()
            .username("name").password("password").build();

        Assertions.assertThrows(IllegalStateException.class, () -> userService.join(request));
    }

    @Test
    public void login_when_user_exist_and_authorized() {
        UserLoginRequest request = UserLoginRequest.builder()
            .username("name").password("password").build();

        Assertions.assertDoesNotThrow(() -> {
            userService.login(request);
        });
    }

    @Test
    public void login_when_username_not_exist() {
        UserLoginRequest request = UserLoginRequest.builder()
            .password("password").build();

        Assertions.assertThrows(IllegalStateException.class, () -> userService.login(request));
    }

    @Test
    public void login_when_password_not_exist() {
        UserLoginRequest request = UserLoginRequest.builder()
            .username("name").build();

        Assertions.assertThrows(IllegalStateException.class, () -> userService.login(request));
    }
}
