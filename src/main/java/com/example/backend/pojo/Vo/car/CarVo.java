package com.example.backend.pojo.Vo.car;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarVo {
    private Long id;

    private String name;

    private double price;

    private String color;

    private String image;

    private String time;

    private double mileage;

    private String source;
}
