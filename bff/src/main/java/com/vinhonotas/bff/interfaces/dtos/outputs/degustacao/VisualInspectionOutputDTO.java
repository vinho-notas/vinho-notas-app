package com.vinhonotas.bff.interfaces.dtos.outputs.degustacao;

import com.vinhonotas.bff.domain.enums.*;
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
    private EnumClarityType clarity;
    private EnumBrightnessType brightness;
    private EnumViscosityType viscosity;
    private EnumRedColorType colorRed;
    private EnumWhiteColorType colorWhite;
    private EnumRoseColorType colorRose;
    private EnumClassificationType classification;
    private TastingCardOutputDTO tastingCard;

}
