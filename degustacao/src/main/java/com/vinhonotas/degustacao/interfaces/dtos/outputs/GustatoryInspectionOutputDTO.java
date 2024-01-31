package com.vinhonotas.degustacao.interfaces.dtos.outputs;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class GustatoryInspectionOutputDTO {

    private UUID id;
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
