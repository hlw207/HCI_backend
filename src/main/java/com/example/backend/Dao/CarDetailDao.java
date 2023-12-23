package com.example.backend.Dao;

import com.example.backend.pojo.Entity.CarDetailEntity;
import com.example.backend.pojo.Entity.CollectionEntity;
import org.springframework.data.repository.CrudRepository;

public interface CarDetailDao extends CrudRepository<CarDetailEntity, Long> {

    public CarDetailEntity findAllById(Long id);
}
