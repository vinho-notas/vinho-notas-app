package com.vinhonotas.degustacao.interfaces.dtos.outputs;

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
    private String intensity;
    private String persistence;
    private String quality;
    private AromasOutputDTO aromas;
    private String classification;
    private TastingCardOutputDTO tastingCard;

}
