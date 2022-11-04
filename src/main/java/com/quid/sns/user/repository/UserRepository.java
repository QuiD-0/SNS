package com.quid.sns.user.repository;

import com.quid.sns.user.User;

public interface UserRepository {

    User findByUserNameOrThrow(String userName);

    void save(User user);

    void delete(User user);

    User findByIdOrThrow(Long userId);

    void checkUserExist(String userName);
}
