package com.vinhonotas.vinho.domain.entities.wine;

import com.vinhonotas.vinho.v1.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WineDomain {

    private String name;
    private BigDecimal price;
    private String purchaseLocation;
    private LocalDate purchaseDate;
    private EnumWineType wineType;
    private EnumWineClassification wineClassification;
    private String alcoholContent;
    private int volumeMl;
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
