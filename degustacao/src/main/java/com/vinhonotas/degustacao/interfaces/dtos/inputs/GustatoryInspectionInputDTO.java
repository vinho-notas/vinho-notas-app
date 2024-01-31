package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GustatoryInspectionInputDTO {

    private LocalDate tastingData;
    private String wineTasted;
    private EnumBodyType body;
    private EnumSweetnessType sweetness;
    private EnumTanninType tannin;
    private EnumClassificationType classification;
    EnumAcidityType acidity;
    EnumAlcoholType alcohol;
    EnumPersistenceType persistence;
    EnumMaturityType maturity;
    EnumTypicalityType typicality;
    private TastingCardEntity tastingCard;

}
