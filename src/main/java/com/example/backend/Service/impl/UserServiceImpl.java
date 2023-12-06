package com.example.backend.Service.impl;

import com.example.backend.Dao.UserDao;
import com.example.backend.Service.UserService;
import com.example.backend.pojo.Entity.UserEntity;
import com.example.backend.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.regex.Pattern;
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserDao userDao;

    @Override
    public String login(String info, String password) {
        // 定义手机号的正则表达式
        String phonePattern = "^1\\d{10}$";
        UserEntity user;
        if(Pattern.matches(phonePattern, info)) {
            user = userDao.findByPhone(info);
        }
        else
            user = userDao.findByUsername(info);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(user == null || !bCryptPasswordEncoder.matches(password, user.getPassword())){
            log.warn("用户不存在");
            throw new RuntimeException();
        }
        sessionUtil.login(user.getPhone(), new BCryptPasswordEncoder().encode(password));
        return user.getPhone();
    }

    @Override
    public void login(String phone) {
        System.out.println(phone);
        UserEntity user = userDao.findByPhone(phone);
        if(user == null){
            user = UserEntity.builder().username("").password("").phone(phone).profile("profile/1.jpg").autoGraph("").build();
            userDao.save(user);
            sessionUtil.login(phone, "");
        }else {
            sessionUtil.login(user.getPhone(), user.getPassword());
        }
    }

    @Override
    public void register(String phone, String username, String password) {
        UserEntity user = userDao.findByPhone(phone);
        user.setUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userDao.save(user);
    }

    @Override
    public void logout(String phone) {
        sessionUtil.logOut(phone);
    }

    @Override
    public boolean session(String phone) {
        return sessionUtil.session(phone);
    }

    @Override
    public UserEntity getUser(String phone) {
        String phonePattern = "^1\\d{10}$";
        UserEntity user = userDao.findByPhone(phone);
        if(user == null)
            throw new RuntimeException();
        return user;
    }

    @Override
    public void updateProfile(String phone, String path) {
        UserEntity user = userDao.findByPhone(phone);
        user.setProfile(path);
        userDao.save(user);
    }

    @Override
    public void updateInfo(String phone, String username, String autoGraph) {
        UserEntity user = userDao.findByUsername(username);
        if(user != null && !user.getPhone().equals(phone)) {
            log.warn("用户名已存在");
            throw new RuntimeException();
        }else {
            user = userDao.findByPhone(phone);
            user.setUsername(username);
            user.setAutoGraph(autoGraph);
            userDao.save(user);
        }
    }

    @Override
    public void passwordCheck(String phone, String password) {
        UserEntity user = userDao.findByPhone(phone);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException();
        }
    }

    @Override
    public void updatePassword(String phone, String password) {
        UserEntity user = userDao.findByPhone(phone);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userDao.save(user);
    }

    @Override
    public void updatePhone(String phone, String newPhone) {
        UserEntity user = userDao.findByPhone(newPhone);
        if(user != null) {
            System.out.println(123);
            throw new RuntimeException();
        }
        user = userDao.findByPhone(phone);
        user.setPhone(newPhone);
        userDao.save(user);
        sessionUtil.logOut(phone);
        sessionUtil.login(newPhone, user.getPassword());
    }

    @Override
    public boolean checkName(String username) {
        UserEntity user = userDao.findByUsername(username);
        return user != null;
    }

    @Override
    public boolean hasPassword(String phone) {
        return !userDao.findByPhone(phone).getPassword().isEmpty();
    }

}
