package com.quid.sns.user.service;

import com.quid.sns.user.entity.User;
import com.quid.sns.user.model.UserDto;
import com.quid.sns.user.model.UserJoinRequest;
import com.quid.sns.user.model.UserLoginRequest;
import com.quid.sns.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDto join(UserJoinRequest request) {
        userJpaRepository.findByUsername(request.getUsername())
            .ifPresent((e) -> new IllegalStateException());

        User user = User.builder().build();
        userJpaRepository.save(user);

        return user.toUserDto();
    }

    @Override
    public String login(UserLoginRequest request) throws NotFoundException {
        User user = userJpaRepository.findByUsername(request.getUsername())
            .orElseThrow(IllegalStateException::new);

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException();
        }
        return "";
    }
}
