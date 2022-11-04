package com.quid.sns.user.repository;

import com.quid.sns.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByUserNameOrThrow(String userName) {
        return userJpaRepository.findByUserName(userName)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
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
        userJpaRepository.findByUserName(userName)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}

