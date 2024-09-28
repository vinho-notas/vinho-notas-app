package com.vinhonotas.vinho.infraestructure.controller.dtos.output;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class WineOutputDTO {

    private UUID id;
    private String sku;
    private String name;
    private BigDecimal price;
    private String purchaseLocation;
    private LocalDate purchaseDate;
    private String wineType;
    private String wineClassification;
    private String alcoholContent;
    private String volumeMl;
    private String grape;
    private String winery;
    private String serviceTemperature;
    private String harvest;
    private String country;
    private String guardTime;
    private String region;
    private String maturation;
    private String harmonization;

}
