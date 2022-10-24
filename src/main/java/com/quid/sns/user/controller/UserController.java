package com.quid.sns.user.controller;

import static com.quid.sns.common.Response.success;

import com.quid.sns.common.Response;
import com.quid.sns.user.model.UserJoinRequest;
import com.quid.sns.user.model.UserLoginRequest;
import com.quid.sns.user.model.UserLoginResponse;
import com.quid.sns.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public void join(@RequestBody UserJoinRequest userJoinRequest) {
        userService.join(userJoinRequest);
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest)
        throws NotFoundException {
        return success(userService.login(userLoginRequest));
    }

}
