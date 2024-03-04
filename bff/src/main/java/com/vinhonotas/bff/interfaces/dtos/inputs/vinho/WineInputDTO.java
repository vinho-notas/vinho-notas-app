package com.vinhonotas.bff.interfaces.dtos.inputs.vinho;

import com.vinhonotas.bff.client.vinho.EnumWineClassification;
import com.vinhonotas.bff.domain.enums.EnumWineType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class WineInputDTO {

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
