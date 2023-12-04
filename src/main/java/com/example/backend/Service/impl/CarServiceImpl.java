package com.example.backend.Service.impl;

import com.example.backend.Dao.BrandDao;
import com.example.backend.Service.CarService;
import com.example.backend.pojo.Entity.BrandEntity;
import net.sourceforge.pinyin4j.PinyinHelper;
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
    public List<String> getBrands(int id) {
        List<BrandEntity> brandEntities = brandDao.findAll();
        List<String> brands = new ArrayList<>();
        for(BrandEntity brandEntity: brandEntities){
            String brand = brandEntity.getBrand();
            char first = brand.charAt(0);
            char convert;
            if(first == '长')
                convert = 'c';
            else {
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(first);
                if (pinyinArray != null) {
                    convert = pinyinArray[0].charAt(0);
                } else {
                    convert = first;
                }
            }
            if((convert == 65 + id || convert == 97 + id) && brandEntity.getCarType().size() >= 2)
                brands.add(brand);
        }
        return brands;
    }

    @Override
    public List<String> getCarTypes(String brand) {
        BrandEntity brandEntity = brandDao.findByBrand(brand);
        return brandEntity.getCarType();
    }
}
