package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

import lombok.Builder;

@Builder
public record WineOriginDTO(

        String country,
        String region,
        String harvest,
        String guardTime,
        String maturation,
        String harmonization

) {
}
