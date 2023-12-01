package com.example.backend.Service;

import java.util.List;

public interface CarService {

    public List<String> getBrands();

    public List<String> getCarTypes(String brand);
}
