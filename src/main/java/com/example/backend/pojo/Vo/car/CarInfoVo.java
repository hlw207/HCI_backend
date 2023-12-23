package com.example.backend.pojo.Vo.car;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarInfoVo {
    private String name;

    private double price;

    private double carDistance;

    private String carAge;

    private String carPosition;

    private String carGear;

    private String carTime;

    private Double carPollution;

    private String picture;
}
