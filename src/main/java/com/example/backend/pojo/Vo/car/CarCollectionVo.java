package com.example.backend.pojo.Vo.car;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarCollectionVo {
    private Long id;

    private String picturePath;

    private String name;

    private double price;

    private String time;

    private double mileage;

    private String source;
}
