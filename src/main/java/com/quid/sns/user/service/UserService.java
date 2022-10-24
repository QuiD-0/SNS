package com.quid.sns.user.service;

import com.quid.sns.user.model.UserDto;
import com.quid.sns.user.model.UserJoinRequest;
import com.quid.sns.user.model.UserLoginRequest;
import com.quid.sns.user.model.UserLoginResponse;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface UserService {

    UserDto join(UserJoinRequest request);

    UserLoginResponse login(UserLoginRequest userLoginRequest) throws NotFoundException;
}
