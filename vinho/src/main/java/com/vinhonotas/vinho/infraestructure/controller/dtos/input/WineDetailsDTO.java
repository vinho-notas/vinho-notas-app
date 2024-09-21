package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

import com.vinhonotas.vinho.v1.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineType;

public record WineDetailsDTO(
        EnumWineType wineType,
        EnumWineClassification wineClassification,
        String alcoholContent,
        int volumeMl,
        String grape,
        String winery,
        String serviceTemperature
) {
}
