package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
import com.vinhonotas.degustacao.domain.enums.EnumPointScale;
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
    private VisualInspectionEntity visualInspection;
    private OlfactoryInspectionEntity olfactoryInspection;
    private GustatoryInspectionEntity gustatoryInspection;
    private String opinion;
    private EnumPointScale pointScale;

}
