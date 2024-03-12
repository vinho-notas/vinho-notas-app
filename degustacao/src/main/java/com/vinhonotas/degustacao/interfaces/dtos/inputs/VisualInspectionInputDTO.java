package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VisualInspectionInputDTO {

    private LocalDate tastingData;
    private String wineTasted;
    private String clarity;
    private String brightness;
    private String viscosity;
    private String colorRed;
    private String colorWhite;
    private String colorRose;
    private String classification;

}
