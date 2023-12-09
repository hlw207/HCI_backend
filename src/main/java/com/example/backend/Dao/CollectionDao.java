package com.example.backend.Dao;

import com.example.backend.pojo.Entity.CollectionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionDao extends CrudRepository<CollectionEntity, Long> {

    public CollectionEntity findByUserIdAndCarId(Long userId, Long carId);

    public List<CollectionEntity> findByUserId(Long userId);
}
