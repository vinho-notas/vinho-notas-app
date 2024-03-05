package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VisualInspectionInputDTO {

    private LocalDate tastingData;
    private String wineTasted;
    private EnumClarityType clarity;
    private EnumBrightnessType brightness;
    private EnumViscosityType viscosity;
    private EnumRedColorType colorRed;
    private EnumWhiteColorType colorWhite;
    private EnumRoseColorType colorRose;
    private EnumClassificationType classification;

}
