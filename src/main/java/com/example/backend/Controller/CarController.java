package com.example.backend.Controller;

import com.example.backend.Service.CarService;
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

    @GetMapping("/carTypes")
    public List<String> getCarTypes(@RequestParam String brand){
        return carService.getCarTypes(brand);
    }
}
