package com.quid.sns.user.repository;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;
import com.quid.sns.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Cacheable(value = "user", key = "#userName")
    public User findByUserNameOrThrow(String userName) {
        return userJpaRepository.findByUserName(userName)
            .orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }

    @Override
    @CacheEvict(value = "user", key = "#userName")
    public void delete(User user) {
        userJpaRepository.delete(user);
    }

    @Override
    public void checkUserExist(String userName) {
        userJpaRepository.findByUserName(userName).ifPresent(user -> {
            throw new SnsApplicationException(ErrorCode.USER_ALREADY_EXIST);
        });
    }

    @Override
    public User findById(Long id) {
        return userJpaRepository.getById(id);
    }
}

