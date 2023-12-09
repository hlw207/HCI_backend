package com.example.backend.Service;

import com.example.backend.pojo.Vo.car.CarCollectionVo;
import com.example.backend.pojo.Vo.car.CarInfoVo;
import com.example.backend.pojo.Vo.car.CarRequest;
import com.example.backend.pojo.Vo.car.CarVo;

import java.util.List;

public interface CarService {

    public List<String> getBrands();

    public List<String> getBrands(int id);

    public List<String> getCarTypes(String brand);

    public List<CarVo> getCars(CarRequest carRequest);

    public CarInfoVo getCarInfo(Long id);

}
