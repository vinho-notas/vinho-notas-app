package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

public record WineOriginDTO(
        String country,
        String region,
        String harvest,
        String guardTime,
        String maturation,
        String harmonization
) {
}
