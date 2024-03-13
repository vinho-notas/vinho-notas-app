package com.vinhonotas.bff.interfaces.dtos.outputs.degustacao;

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
    private String body;
    private String sweetness;
    private String tannin;
    private String classification;
    private String acidity;
    private String alcohol;
    private String persistence;
    private String maturity;
    private String typicality;
    private TastingCardOutputDTO tastingCard;

}
