package com.vinhonotas.bff.interfaces.dtos.inputs.degustacao;

import com.vinhonotas.bff.domain.enums.*;
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
    private EnumAcidityType acidity;
    private EnumAlcoholType alcohol;
    private EnumPersistenceType persistence;
    private EnumMaturityType maturity;
    private EnumTypicalityType typicality;

}