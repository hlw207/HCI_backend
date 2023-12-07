package com.example.backend.Controller;

import com.example.backend.Service.CarService;
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
}
