package com.quid.sns.user.service;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.token.JwtToken;
import com.quid.sns.user.User;
import com.quid.sns.user.model.UserDto;
import com.quid.sns.user.model.UserJoinRequest;
import com.quid.sns.user.model.UserLoginRequest;
import com.quid.sns.user.model.UserLoginResponse;
import com.quid.sns.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    @Transactional
    public UserDto join(UserJoinRequest request) {
        userRepository.checkUserExist(request.name());

        User user = User.builder().username(request.name())
            .password(encoder.encode(request.password())).build();
        userRepository.save(user);

        return user.toUserDto();
    }

    @Override
    @Transactional(readOnly = true)
    public UserLoginResponse login(UserLoginRequest request) {
        User user = userRepository.findByUserNameOrThrow(request.name());

        if (!encoder.matches(request.password(), user.getPassword())) {
            throw new SnsApplicationException(ErrorCode.INVALID_PASSWORD);
        }
        return UserLoginResponse.builder()
            .token(JwtToken.generateToken(user.getUserName(), secretKey, 259200000)).build();
    }


}
