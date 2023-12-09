package com.example.backend.Controller;

import com.example.backend.Service.CarService;
import com.example.backend.Service.CollectionService;
import com.example.backend.pojo.Vo.car.CarCollectionVo;
import com.example.backend.pojo.Vo.car.CarInfoVo;
import com.example.backend.pojo.Vo.car.CarRequest;
import com.example.backend.pojo.Vo.car.CarVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@RestController
@RequestMapping("/v1")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/brands")
    public List<String> getBrands(){
        return carService.getBrands();
    }

    @GetMapping("/brands/{id}")
    public List<String> getBrands(@Valid @PathVariable int id){
        return carService.getBrands(id);
    }

    @GetMapping("/carTypes")
    public List<String> getCarTypes(@RequestParam String brand){
        return carService.getCarTypes(brand);
    }

    @PostMapping("/cars")
    public List<CarVo> getCars(@RequestBody CarRequest carRequest){
        System.out.println(carRequest);
        return carService.getCars(carRequest);
    }

    @GetMapping("/cars/collection")
    public Boolean isCollection(@RequestParam Long userId, @RequestParam Long carId){
        return collectionService.isCollection(userId, carId);
    }

    @PostMapping("/cars/collection")
    public void addCollection(@RequestParam Long userId, @RequestParam Long carId){
        collectionService.addCollection(userId, carId);
    }

    @DeleteMapping("/cars/collection")
    public void deleteCollection(@RequestParam Long userId, @RequestParam Long carId){
        collectionService.deleteCollection(userId, carId);
    }

    @GetMapping("/cars/{id}")
    public CarInfoVo getCarInfo(@Valid @PathVariable Long id){
        return carService.getCarInfo(id);
    }

    @GetMapping("/collection")
    public List<CarCollectionVo> getCollections(@RequestParam Long userId){
        return collectionService.getCollections(userId);
    }
}
