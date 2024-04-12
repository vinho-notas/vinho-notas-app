package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TastingCardInputDTO {

    private LocalDate tastingData;
    private String wineTasted;
    private String harvest;
    private String grapes;
    private String country;
    private String region;
//    private VisualInspectionInputDTO visualInspection;
//    private OlfactoryInspectionInputDTO olfactoryInspection;
//    private GustatoryInspectionInputDTO gustatoryInspection;
    private String opinion;
    private String pointScale;
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;

}
