package com.vinhonotas.bff.interfaces.dtos.inputs.degustacao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class GustatoryInspectionInputDTO {

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
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;

}
