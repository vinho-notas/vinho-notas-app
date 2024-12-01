package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record WineDetailsDTO(

        @Schema(description = "Tipo de vinho", example = "Vinho Tinto")
        String wineType,
        @Schema(description = "Classificação do vinho", example = "Vinho Seco")
        String wineClassification,
        @Schema(description = "Teor alcoólico", example = "13.5")
        String alcoholContent,
        @Schema(description = "Volume em ml", example = "750")
        String volumeMl,
        @Schema(description = "Uva", example = "Cabernet Sauvignon")
        String grape,
        @Schema(description = "Produtor", example = "Miolo")
        String winery,
        @Schema(description = "Temperatura de serviço", example = "18.0")
        String serviceTemperature

) {
}
