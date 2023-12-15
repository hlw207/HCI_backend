package com.example.backend.Service.impl;

import com.example.backend.Dao.BrandDao;
import com.example.backend.Dao.CarDao;
import com.example.backend.Service.CarService;
import com.example.backend.pojo.Entity.BrandEntity;
import com.example.backend.pojo.Entity.CarEntity;
import com.example.backend.pojo.Entity.CollectionEntity;
import com.example.backend.pojo.Vo.car.CarCollectionVo;
import com.example.backend.pojo.Vo.car.CarInfoVo;
import com.example.backend.pojo.Vo.car.CarRequest;
import com.example.backend.pojo.Vo.car.CarVo;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            if(carRequest.getSearch().isEmpty()) {
                if (!carEntity.getBrand().equals(carRequest.getBrand()) && !carRequest.getBrand().equals("不限"))
                    continue;
                if (!carEntity.getCarType().equals(carRequest.getCarType()) && !carRequest.getCarType().equals("不限"))
                    continue;
            }else {
                String s = carEntity.getBrand() + carEntity.getCarType();
                if(!s.contains(carRequest.getSearch()))
                    continue;
            }
            if(!carRequest.getPrice().equals("不限")){
                String[] price = carRequest.getPrice().split("-");
                double start = Double.parseDouble(price[0]);
                double end = Double.parseDouble(price[1]);
                if(carEntity.getPrice() > end || carEntity.getPrice() < start){
                    continue;
                }
            }
            if(!canSatisfy(carEntity.getCarLevel(),carRequest.getCarLevel()))
                continue;
            if(!canSatisfy(carEntity.getDetailType(),carRequest.getCarDetailType()))
                continue;
            if(!canSatisfy(carEntity.getCarColor(), carRequest.getCarColor()))
                continue;
            if(!canSatisfy(carEntity.getCarGear(), carRequest.getCarGear()))
                continue;
            if(!canSatisfy(carEntity.getCarDistance(),carRequest.getCarDistance()))
                continue;
            if(!canSatisfy(carEntity.getCarPollution(),carRequest.getCarPollution()))
                continue;
            if(!canAge(carEntity.getCarAge(), carRequest.getCarAge()))
                continue;
            CarVo carVo = CarVo.builder().id(carEntity.getId()).name(carEntity.getBrand() + "-" + carEntity.getCarType())
                    .price(carEntity.getPrice()).color(carEntity.getCarColor()).image(carEntity.getPicture())
                    .time(carEntity.getCarAge()).mileage(carEntity.getCarDistance()).source(carEntity.getPosition()).build();
            carVoList.add(carVo);
        }
        return carVoList;
    }

    @Override
    public CarInfoVo getCarInfo(Long id) {
        Optional<CarEntity> carEntity = carDao.findById(id);
        CarEntity car = carEntity.get();
        CarInfoVo carInfoVo = CarInfoVo.builder().name(car.getBrand() + '-' + car.getCarType()).price(car.getPrice())
                .carDistance(car.getCarDistance()).carGear(car.getCarGear()).carAge(car.getCarAge())
                .carPosition(car.getPosition()).carTime(car.getCarTime()).build();
        return carInfoVo;
    }

    private boolean canSatisfy(String carPart, String request){
        if(request.isEmpty())
            return true;
        String[] parts = request.split("/");
        boolean can = false;
        for (String part: parts){
            if(part.equals(carPart)){
                return true;
            }
        }
        return false;
    }

    private boolean canAge(String carPart, String request){
        if(request.isEmpty())
            return true;
        String[] parts = request.split("/");
        boolean can = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate givenDate = LocalDate.parse(carPart, formatter);
        // 当前日期
        LocalDate currentDate = LocalDate.now();
        // 计算距离当前日期的年数
        Period period = Period.between(givenDate, currentDate);
        int years = period.getYears();
        for (String part: parts){
            int firstYear = 0;
            int endYear = Integer.MAX_VALUE;
            if(part.endsWith("以下")){
                endYear = Integer.parseInt(part.replaceAll("[\\u4e00-\\u9fa5]+", ""));
            }else if(part.endsWith("以上")){
                firstYear = Integer.parseInt(part.replaceAll("[\\u4e00-\\u9fa5]+", ""));
            }else {
                part = part.replaceAll("[\\u4e00-\\u9fa5]+", "");
                firstYear = Integer.parseInt(part.split("-")[0]);
                endYear = Integer.parseInt(part.split("-")[1]);
            }
            if(years >= firstYear && years < endYear)
                return true;
        }
        return false;
    }

    private boolean canSatisfy(Double carPart, String request){
        if(request.isEmpty())
            return true;
        String[] parts = request.split("/");
        boolean can = false;
        for (String part: parts){
            double start = 0;
            double end = Double.MAX_VALUE;
            if(part.endsWith("以下")){
                end = Double.parseDouble(part.replaceAll("[\\u4e00-\\u9fa5]+", "").replace("L",""));
            }else if(part.endsWith("以上")){
                start = Double.parseDouble(part.replaceAll("[\\u4e00-\\u9fa5]+", "").replace("L",""));
            }else {
                part = part.replaceAll("[\\u4e00-\\u9fa5]+", "").replace("L","");
                start = Double.parseDouble(part.split("-")[0]);
                end = Double.parseDouble(part.split("-")[1]);
            }
            if(carPart >= start && carPart <= end)
                return true;
        }
        return false;
    }
}
