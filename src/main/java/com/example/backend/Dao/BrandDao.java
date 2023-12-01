package com.example.backend.Dao;


import com.example.backend.pojo.Entity.BrandEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandDao extends CrudRepository<BrandEntity, Long> {

    public List<BrandEntity> findAll();

    public BrandEntity findByBrand(String brand);
}
