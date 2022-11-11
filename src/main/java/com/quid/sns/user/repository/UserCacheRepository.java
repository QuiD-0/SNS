package com.quid.sns.user.repository;

import static com.quid.sns.common.ClassUtils.castInstance;

import com.quid.sns.user.User;
import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserCacheRepository {

    private final static Duration USER_CACHE_TTL = Duration.ofDays(1);
    private final RedisTemplate<String, Object> redisTemplate;

    private static String getKey(String username) {
        return "user:" + username;
    }

    public void setUser(User user) {
        String key = getKey(user.getUserName());
        redisTemplate.opsForValue().set(key, user, USER_CACHE_TTL);
    }

    public Optional<User> getUser(String username) {
        String key = getKey(username);
        log.info("getUser: {}", key);
        return Optional.ofNullable(castInstance(redisTemplate.opsForValue().get(key),
            User.class));
    }

}
