package com.example.backend.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SessionUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void login(String username, String password){
        redisTemplate.opsForValue().set(username, password);
    }

    public void logOut(String username){
        redisTemplate.delete(username);
    }

    public boolean session(String username){
        return Boolean.TRUE.equals(redisTemplate.hasKey(username));
    }
}
