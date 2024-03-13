package com.vinhonotas.avaliacao.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PointScaleInputDTO {

    private String whatTasted;
    private String whenTasted;
    private String whatSaw;
    private String whatAromas;
    private String whatFlavors;
    private String whatOpinion;
    private String pointScale;
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;

}
