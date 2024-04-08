package com.vinhonotas.degustacao.interfaces.dtos.outputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class VisualInspectionOutputDTO {

    private UUID id;
    private LocalDate tastingData;
    private String wineTasted;
    private String clarity;
    private String brightness;
    private String viscosity;
    private String colorRed;
    private String colorWhite;
    private String colorRose;
    private String classification;
    private TastingCardOutputDTO tastingCard;

}
