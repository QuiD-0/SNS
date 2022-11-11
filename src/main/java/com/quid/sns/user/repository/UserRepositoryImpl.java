package com.quid.sns.user.repository;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    private final UserCacheRepository userCacheRepository;

    @Override
    public User findByUserNameOrThrow(String userName) {
        return userCacheRepository.getUser(userName)
            .orElseGet(() -> {
                log.info("cache miss: {}", userName);
                User user = userJpaRepository.findByUserName(userName)
                    .orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND));
                userCacheRepository.setUser(user);
                return user;
            });
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(user);
    }

    @Override
    public User findByIdOrThrow(Long userId) {
        return userJpaRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public void checkUserExist(String userName) {
        userCacheRepository.getUser(userName).ifPresentOrElse(
            user -> {
                throw new SnsApplicationException(ErrorCode.USER_ALREADY_EXIST);
            },
            () -> userJpaRepository.findByUserName(userName).ifPresent(
                (e) -> new SnsApplicationException(ErrorCode.USER_ALREADY_EXIST))
        );

    }
}

