package com.quid.sns.user.service;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.user.entity.User;
import com.quid.sns.user.model.UserDto;
import com.quid.sns.user.model.UserJoinRequest;
import com.quid.sns.user.model.UserLoginRequest;
import com.quid.sns.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
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
        userJpaRepository.findByUsername(request.getName())
            .ifPresent((e) -> {
                throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME,
                    String.format("userName is %s", request.getName()));
            });

        User user = User.builder()
            .username(request.getName())
            .password(encoder.encode(request.getPassword()))
            .build();
        userJpaRepository.save(user);

        return user.toUserDto();
    }

    @Override
    @Transactional(readOnly = true)
    public String login(UserLoginRequest request) {
        User user = userJpaRepository.findByUsername(request.getName())
            .orElseThrow(IllegalStateException::new);

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new SnsApplicationException(ErrorCode.INVALID_PASSWORD);
        }

        return "";
    }
}
