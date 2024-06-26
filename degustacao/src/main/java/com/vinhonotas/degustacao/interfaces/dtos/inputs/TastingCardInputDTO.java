package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TastingCardInputDTO {

    private String wineTasted;
    private LocalDate tastingData;
    private String harvest;
    private String grapes;
    private String country;
    private String region;
    private String tastingType;
    private String clarity;
    private String brightness;
    private String viscosity;
    private String colorRed;
    private String colorWhite;
    private String colorRose;
    private String visualInspectionClassification;
    private String intensity;
    private String olfactoryInspectionPersistence;
    private String quality;
    private String fruity;
    private String dryFruits;
    private String florals;
    private String vegetablesAndHerbs;
    private String minerals;
    private String spices;
    private String animals;
    private String empireumatics;
    private String wood;
    private String chemicals;
    private String lacteal;
    private String sweets;
    private String olfactoryInspectionClassification;
    private String body;
    private String sweetness;
    private String tannin;
    private String acidity;
    private String alcohol;
    private String gustatoryInspectionPersistence;
    private String maturity;
    private String typicality;
    private String gustatoryInspectionClassification;
    private String opinion;
    private String pointScale;
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;
}
