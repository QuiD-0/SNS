package com.quid.sns.user.repository;

import com.quid.sns.user.User;

public interface UserRepository {

    User findByUserNameOrThrow(String userName);

    void save(User user);

    void delete(User user);

    void checkUserExist(String userName);

    User findById(Long id);
}
