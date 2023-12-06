package com.example.backend.Service;

import com.example.backend.pojo.Entity.UserEntity;

public interface UserService {

    public String login(String info, String password);

    public void login(String phone);

    public void register(String phone,String username, String password);

    public void logout(String phone);

    public boolean session(String phone);

    public UserEntity getUser(String info);

    public void updateProfile(String phone, String path);

    public void updateInfo(String phone, String username, String autoGraph);

    public void passwordCheck(String phone, String password);

    public void updatePassword(String phone, String password);

    public void updatePhone(String phone, String newPhone);

    public boolean checkName(String username);

    public boolean hasPassword(String phone);
}
