package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record TastingCardInputDTO (
    String wineTasted,
    LocalDate tastingData,
    String harvest,
    String grapes,
    String country,
    String region,
    String clarity,
    String brightness,
    String viscosity,
    String colorRed,
    String colorWhite,
    String colorRose,
    String visualInspectionClassification,
    String intensity,
    String olfactoryInspectionPersistence,
    String quality,
    String fruity,
    String dryFruits,
    String florals,
    String vegetablesAndHerbs,
    String minerals,
    String spices,
    String animals,
    String empireumatics,
    String wood,
    String chemicals,
    String lacteal,
    String sweets,
    String olfactoryInspectionClassification,
    String body,
    String sweetness,
    String tannin,
    String acidity,
    String alcohol,
    String gustatoryInspectionPersistence,
    String maturity,
    String typicality,
    String gustatoryInspectionClassification,
    String opinion,
    String pointScale,
    LocalDateTime dthreg,
    String userreg,
    LocalDateTime dthalt,
    String useralt
    ){
}
