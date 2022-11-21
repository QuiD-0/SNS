package com.quid.sns.user.repository;

import com.quid.sns.common.RedisBase;
import com.quid.sns.user.User;
import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRedisRepository {

    private final RedisBase redisBase;

    private static final Duration TTL = Duration.ofMinutes(5);

    public void save(String key, User value) {
        redisBase.save(getKey(key), value, TTL);
    }

    public Optional<User> getData(String key) {
        return redisBase.getData(getKey(key), User.class);
    }

    public void delete(String key) {
        redisBase.delete(getKey(key));
    }

    public String getKey(String userName) {
        return "user:" + userName;
    }
}
