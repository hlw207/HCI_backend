package com.example.backend.Service;

import com.example.backend.pojo.Entity.UserEntity;

public interface UserService {

    public void login(String username, String password);

    public void register(String username, String password);

    public void logout(String username);

    public boolean session(String username);

    public UserEntity getUser(String username);
}
