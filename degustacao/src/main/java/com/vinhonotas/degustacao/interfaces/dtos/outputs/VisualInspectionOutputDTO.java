package com.vinhonotas.degustacao.interfaces.dtos.outputs;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.*;
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
    private TastingCardEntity tastingCard;

}
