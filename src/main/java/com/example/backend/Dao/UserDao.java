package com.example.backend.Dao;

import com.example.backend.pojo.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    UserEntity findByPhone(String phone);

    List<UserEntity> findAll();
}
