package com.example.backend.Service;

import java.util.List;

public interface CarService {

    public List<String> getBrands();

    public List<String> getBrands(int id);

    public List<String> getCarTypes(String brand);
}
