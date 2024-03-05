package com.vinhonotas.bff.interfaces.dtos.inputs.avaliacao;

import com.vinhonotas.bff.domain.enums.EnumPointScale;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointScaleInputDTO {

    private String whatTasted;
    private String whenTasted;
    private String whatSaw;
    private String whatAromas;
    private String whatFlavors;
    private String whatOpinion;
    private EnumPointScale pointScale;

}
