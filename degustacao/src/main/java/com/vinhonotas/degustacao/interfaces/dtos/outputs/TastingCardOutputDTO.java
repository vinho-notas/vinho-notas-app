package com.vinhonotas.degustacao.interfaces.dtos.outputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class TastingCardOutputDTO {

    private UUID id;
    private LocalDate tastingData;
    private String wineTasted;
    private String harvest;
    private String grapes;
    private String country;
    private String region;
//    private VisualInspectionOutputDTO visualInspection;
//    private OlfactoryInspectionOutputDTO olfactoryInspection;
//    private GustatoryInspectionOutputDTO gustatoryInspection;
    private String opinion;
    private String pointScale;
//    private TastingOutputDTO tasting;

}
