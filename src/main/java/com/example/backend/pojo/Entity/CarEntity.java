package com.example.backend.pojo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String carAge;

    @NotNull
    private Double carDistance;

    @NotNull
    private Double carPollution;

    @NotNull
    private String carGear;

    @NotNull
    private String carColor;

    @NotNull
    private String picture;

    @NotNull
    private String position;
}
