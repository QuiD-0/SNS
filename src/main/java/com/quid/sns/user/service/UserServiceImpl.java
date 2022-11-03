package com.quid.sns.user.service;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.token.JwtToken;
import com.quid.sns.user.User;
import com.quid.sns.user.model.UserDto;
import com.quid.sns.user.model.UserJoinRequest;
import com.quid.sns.user.model.UserLoginRequest;
import com.quid.sns.user.model.UserLoginResponse;
import com.quid.sns.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    @Transactional
    public UserDto join(UserJoinRequest request) {
        userJpaRepository.findByUserName(request.getName())
            .ifPresent((e) -> {
                throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME);
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
    public UserLoginResponse login(UserLoginRequest request) {
        User user = userJpaRepository.findByUserName(request.getName())
            .orElseThrow(() -> {
                throw new SnsApplicationException(ErrorCode.USER_NOT_FOUND);
            });

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new SnsApplicationException(ErrorCode.INVALID_PASSWORD);
        }

        return UserLoginResponse.builder()
            .token(JwtToken.generateToken(user.getUserName(), secretKey, 259200000)).build();
    }

    @Override
    public UserDto findUserDtoByUsername(String userName) {
        return userJpaRepository.findByUserName(userName).map(UserDto::fromEntity).orElseThrow(() ->
            new SnsApplicationException(ErrorCode.USER_NOT_FOUND)
        );
    }

    @Override
    public User findUserByName(String userName) {
        return userJpaRepository.findByUserName(userName).orElseThrow(() ->
            new SnsApplicationException(ErrorCode.USER_NOT_FOUND)
        );
    }

}
