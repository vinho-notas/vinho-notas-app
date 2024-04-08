package com.vinhonotas.bff.interfaces.dtos.inputs.degustacao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;

}
