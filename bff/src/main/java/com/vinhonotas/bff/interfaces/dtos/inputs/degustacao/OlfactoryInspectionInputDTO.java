package com.vinhonotas.bff.interfaces.dtos.inputs.degustacao;

import com.vinhonotas.bff.domain.enums.EnumClassificationType;
import com.vinhonotas.bff.domain.enums.EnumIntensityType;
import com.vinhonotas.bff.domain.enums.EnumPersistenceType;
import com.vinhonotas.bff.domain.enums.EnumQualityType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OlfactoryInspectionInputDTO {

    private LocalDate tastingData;
    private String wineTasted;
    private EnumIntensityType intensity;
    private EnumPersistenceType persistence;
    private EnumQualityType quality;
    private AromasInputDTO aromas;
    private EnumClassificationType classification;

}
