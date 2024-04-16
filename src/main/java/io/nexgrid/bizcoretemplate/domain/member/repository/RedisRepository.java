package io.nexgrid.bizcoretemplate.domain.member.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;


    // 레디스에서 리프레시 토큰을 찾는다.
    public Optional<String> findAllSession() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String session = (String) valueOperations.get("");

        if (Objects.isNull(session)) {
            return Optional.empty();
        }

        return Optional.of(session);
    }


}
