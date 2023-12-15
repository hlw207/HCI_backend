package com.example.backend.pojo.Vo.car;

import lombok.Data;

@Data
public class CarRequest {
    private String search;

    private String brand;

    private String carType;

    private String price;

    private String carLevel;

    private String carDetailType;

    private String carAge;

    private String carPollution;

    private String carDistance;

    private String carColor;

    private String carGear;
}
