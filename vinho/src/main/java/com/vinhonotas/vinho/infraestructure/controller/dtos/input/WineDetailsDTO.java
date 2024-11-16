package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

import lombok.Builder;

@Builder
public record WineDetailsDTO(

        String wineType,
        String wineClassification,
        String alcoholContent,
        String volumeMl,
        String grape,
        String winery,
        String serviceTemperature

) {
}
