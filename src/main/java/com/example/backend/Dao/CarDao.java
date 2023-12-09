package com.example.backend.Dao;

import com.example.backend.pojo.Entity.CarEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarDao extends CrudRepository<CarEntity, Long> {

    public List<CarEntity> findAll();

}
