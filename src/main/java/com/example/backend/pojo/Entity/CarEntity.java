package com.example.backend.pojo.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String brand;

    @NotNull
    private String carType;

    @NotNull
    private Double price;

    @NotNull
    private String carLevel;

    @NotNull
    private String detailType;

    @NotNull
    private Date carAge;

    @NotNull
    private Double carDistance;

    @NotNull
    private Double carPollution;

}
