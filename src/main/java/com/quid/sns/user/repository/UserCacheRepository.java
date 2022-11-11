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
        Object user = redisTemplate.opsForValue().get(key);
        if (user == null) {
            log.info("cache miss: {}", username);
            return Optional.empty();
        }
        log.info("cache hit: {}", username);
        return Optional.ofNullable(castInstance(redisTemplate.opsForValue().get(key),
            User.class));
    }

    public void deleteUser(String username) {
        String key = getKey(username);
        redisTemplate.delete(key);
    }
}
