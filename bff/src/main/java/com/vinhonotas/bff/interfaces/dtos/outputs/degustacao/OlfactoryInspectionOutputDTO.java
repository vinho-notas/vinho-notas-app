package com.vinhonotas.bff.interfaces.dtos.outputs.degustacao;

import com.vinhonotas.bff.domain.enums.EnumClassificationType;
import com.vinhonotas.bff.domain.enums.EnumIntensityType;
import com.vinhonotas.bff.domain.enums.EnumPersistenceType;
import com.vinhonotas.bff.domain.enums.EnumQualityType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class OlfactoryInspectionOutputDTO {

    private UUID id;
    private LocalDate tastingData;
    private String wineTasted;
    private EnumIntensityType intensity;
    private EnumPersistenceType persistence;
    private EnumQualityType quality;
    private AromasOutputDTO aromas;
    private EnumClassificationType classification;
    private TastingCardOutputDTO tastingCard;

}
