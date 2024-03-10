package com.vinhonotas.bff.interfaces.dtos.inputs.degustacao;

import com.vinhonotas.bff.domain.enums.EnumPointScale;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TastingCardInputDTO {

    private LocalDate tastingData;
    private String wineTasted;
    private String harvest;
    private String grapes;
    private String country;
    private String region;
    private VisualInspectionInputDTO visualInspection;
    private OlfactoryInspectionInputDTO olfactoryInspection;
    private GustatoryInspectionInputDTO gustatoryInspection;
    private String opinion;
    private EnumPointScale pointScale;

}
