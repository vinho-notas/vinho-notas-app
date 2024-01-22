package com.vinhonotas.vinho.interfaces.dtos.outputs;

import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class WineOutputDTO {

    private UUID id;
    private String name;
    private BigDecimal price;
    private String purchaseLocation;
    private LocalDate purchaseDate;
    private EnumWineType wineType;
    private EnumWineClassification wineClassification;
    private double alcoholContent;
    private int volumeMl;
    private String grape;
    private String winery;
    private double serviceTemperature;
    private int harvest;
    private String country;
    private String guardTime;
    private String region;
    private String maturation;
    private String harmonization;
}
