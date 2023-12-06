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

    public void login(String phone, String password){
        redisTemplate.opsForValue().set(phone, password);
    }

    public void logOut(String phone){
        redisTemplate.delete(phone);
    }

    public boolean session(String phone){
        return Boolean.TRUE.equals(redisTemplate.hasKey(phone));
    }
}
