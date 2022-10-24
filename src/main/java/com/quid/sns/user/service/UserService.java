package com.quid.sns.user.service;

import com.quid.sns.user.model.UserDto;
import com.quid.sns.user.model.UserJoinRequest;
import com.quid.sns.user.model.UserLoginRequest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface UserService {

    UserDto join(UserJoinRequest request);

    String login(UserLoginRequest userLoginRequest) throws NotFoundException;
}
