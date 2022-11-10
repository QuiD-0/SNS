package com.quid.sns.user.cache;

import static com.quid.sns.common.ClassUtils.castInstance;

import com.quid.sns.user.model.UserDto;
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

    private final static Duration USER_CACHE_TTL = Duration.ofDays(3);
    private final RedisTemplate<String, Object> redisTemplate;

    private static String getKey(String username) {
        return "user:" + username;
    }

    public void setUser(UserDto user) {
        String key = getKey(user.getUsername());
        redisTemplate.opsForValue().set(key, user, USER_CACHE_TTL);
    }

    public Optional<UserDto> getUser(String username) {
        String key = getKey(username);
        log.info("getUser: {}", key);
        return Optional.ofNullable(castInstance(redisTemplate.opsForValue().get(key),
            UserDto.class));
    }

}
