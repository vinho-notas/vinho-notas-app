package com.vinhonotas.bff.interfaces.dtos.inputs.degustacao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OlfactoryInspectionInputDTO {

    private LocalDate tastingData;
    private String wineTasted;
    private String intensity;
    private String persistence;
    private String quality;
    private AromasInputDTO aromas;
    private String classification;

}
