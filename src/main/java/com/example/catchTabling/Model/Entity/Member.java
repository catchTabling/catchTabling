package com.example.catchTabling.Model.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "test")
public class Member {

    @Id
    private Long id;
    private String name;
}
