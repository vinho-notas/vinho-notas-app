package com.vinhonotas.bff.interfaces.dtos.outputs.avaliacao;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PointScaleOutputDTO {

    private UUID id;
    private String whatTasted;
    private String whenTasted;
    private String whatSaw;
    private String whatAromas;
    private String whatFlavors;
    private String whatOpinion;
    private String pointScale;

}
