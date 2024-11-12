package com.example.catchTabling.Controller;


import com.example.catchTabling.Service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RedisController {

    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/redis/test")
    public ResponseEntity<String> testRedis() {
        try {
            String key = "test:key";
            String value = "Hello Redis!" + LocalDateTime.now();

            log.info("Attempting to set value in Redis");
            redisTemplate.opsForValue().set(key, value);

            log.info("Attempting to get value from Redis");
            String retrieved = redisTemplate.opsForValue().get(key);

            log.info("Retrieved value: {}", retrieved);

            return ResponseEntity.ok("Redis Test Success! Stored and retrieved value: " + retrieved);
        } catch (Exception e) {
            log.error("Redis operation failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Redis Test Failed: " + e.getMessage());
        }
    }

    @GetMapping("/redis/info")
    public ResponseEntity<Map<String, String>> getRedisInfo() {
        try {
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            Properties info = connection.info();
            Map<String, String> redisInfo = new HashMap<>();

            info.forEach((k, v) -> redisInfo.put(k.toString(), v.toString()));

            return ResponseEntity.ok(redisInfo);
        } catch (Exception e) {
            log.error("Failed to get Redis info", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}



