package com.example.backend.Service.impl;

import com.example.backend.Dao.UserDao;
import com.example.backend.Service.UserService;
import com.example.backend.pojo.Entity.UserEntity;
import com.example.backend.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
@Slf4j
public class userServiceImpl implements UserService {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserDao userDao;

    @Override
    public void login(String username, String password) {
        UserEntity user = userDao.findByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(user == null || !bCryptPasswordEncoder.matches(password, user.getPassword())){
            log.warn("用户不存在");
            throw new RuntimeException();
        }
        sessionUtil.login(username, new BCryptPasswordEncoder().encode(password));
    }

    @Override
    public void register(String username, String password) {
        UserEntity user = UserEntity.builder().username(username).password(password).nickname("").phone("").profile("").autoGraph("").build();
        userDao.save(user);
        sessionUtil.login(username, new BCryptPasswordEncoder().encode(password));
    }

    @Override
    public void logout(String username) {
        sessionUtil.logOut(username);
    }

    @Override
    public boolean session(String username) {
        return sessionUtil.session(username);
    }

    @Override
    public UserEntity getUser(String username) {
        if(userDao.findByUsername(username) == null)
            throw new RuntimeException();
        return userDao.findByUsername(username);
    }
}
