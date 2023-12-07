package com.example.backend.Service.impl;

import com.example.backend.Dao.BrandDao;
import com.example.backend.Dao.CarDao;
import com.example.backend.Service.CarService;
import com.example.backend.pojo.Entity.BrandEntity;
import com.example.backend.pojo.Entity.CarEntity;
import com.example.backend.pojo.Vo.car.CarRequest;
import com.example.backend.pojo.Vo.car.CarVo;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CarDao carDao;

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

    @Override
    public List<CarVo> getCars(CarRequest carRequest) {
        List<CarEntity> carEntities = carDao.findAll();
        List<CarVo> carVoList = new ArrayList<>();
        for (CarEntity carEntity: carEntities){
            if(!carEntity.getBrand().equals(carRequest.getBrand()) && !carRequest.getBrand().equals("不限"))
                continue;
            if(!carEntity.getCarType().equals(carRequest.getCarType()) && !carRequest.getCarType().equals("不限"))
                continue;
            if(!carRequest.getPrice().equals("不限")){
                String[] price = carRequest.getPrice().split("-");
                double start = Double.parseDouble(price[0]);
                double end = Double.parseDouble(price[1]);
                if(carEntity.getPrice() > end || carEntity.getPrice() < start){
                    continue;
                }
            }
            CarVo carVo = CarVo.builder().id(carEntity.getId()).name(carEntity.getBrand() + "-" + carEntity.getCarType())
                    .price(carEntity.getPrice()).color(carEntity.getCarColor()).image(carEntity.getPicture())
                    .time(carEntity.getCarAge()).mileage(carEntity.getCarDistance()).source(carEntity.getPosition()).build();
            carVoList.add(carVo);
        }
        return carVoList;
    }
}
