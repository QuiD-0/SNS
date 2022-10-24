package com.quid.sns.user.service;

import com.quid.sns.user.entity.User;
import com.quid.sns.user.model.UserDto;
import com.quid.sns.user.model.UserJoinRequest;
import com.quid.sns.user.model.UserLoginRequest;
import com.quid.sns.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public UserDto join(UserJoinRequest request) {
        userJpaRepository.findByUsername(request.getUserName())
            .ifPresent((e) -> new IllegalStateException());

        User user = User.builder()
            .username(request.getUserName())
            .password(encoder.encode(request.getPassword()))
            .build();
        userJpaRepository.save(user);

        return user.toUserDto();
    }

    @Override
    @Transactional(readOnly = true)
    public String login(UserLoginRequest request) throws NotFoundException {
        User user = userJpaRepository.findByUsername(request.getUserName())
            .orElseThrow(IllegalStateException::new);

        if (!user.getPassword().equals(encoder.encode(request.getPassword()))) {
            throw new IllegalStateException();
        }
        return "";
    }
}
