package io.ctsa.careertrendservice.prediction.storage;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class SmoothingParamsRepository {

    private static final String KEY = "smoothingParameters";

    private final HashOperations<String, String, SmoothingParams> hashOps;

    public SmoothingParamsRepository(RedisTemplate<String, SmoothingParams> redisTemplate) {
        this.hashOps = redisTemplate.opsForHash();
    }

    public void addParam(String trendSubject, SmoothingParams params) {
        hashOps.putIfAbsent(KEY, trendSubject, params);
    }

    public void updateParam(String trendSubject, SmoothingParams params) {
        hashOps.put(KEY, trendSubject, params);
    }

    public SmoothingParams getParam(String trendSubject) {
        return hashOps.get(KEY, trendSubject);
    }

    public Map<String, SmoothingParams> getAllParams() {
        return hashOps.entries(KEY);
    }
}
