package com.example.backend.Service.impl;

import com.example.backend.Dao.BrandDao;
import com.example.backend.Service.CarService;
import com.example.backend.pojo.Entity.BrandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public List<String> getBrands() {
        List<BrandEntity> brandEntities = brandDao.findAll();
        List<String> brands = new ArrayList<>();
        for(BrandEntity brandEntity: brandEntities){
            brands.add(brandEntity.getBrand());
        }
        return brands;
    }

    @Override
    public List<String> getCarTypes(String brand) {
        BrandEntity brandEntity = brandDao.findByBrand(brand);
        return brandEntity.getCarType();
    }
}
