package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.EnumClassificationType;
import com.vinhonotas.degustacao.domain.enums.EnumIntensityType;
import com.vinhonotas.degustacao.domain.enums.EnumPersistenceType;
import com.vinhonotas.degustacao.domain.enums.EnumQualityType;
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
    private AromasEntity aromas;
    private EnumClassificationType classification;

}
