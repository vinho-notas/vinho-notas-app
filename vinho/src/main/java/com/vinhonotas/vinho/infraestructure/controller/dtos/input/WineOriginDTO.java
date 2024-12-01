package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record WineOriginDTO(

        @Schema(description = "País de origem", example = "Brasil")
        String country,
        @Schema(description = "Região de origem", example = "Vale dos Vinhedos")
        String region,
        @Schema(description = "Safra", example = "2019")
        String harvest,
        @Schema(description = "Guarda", example = "5 anos")
        String guardTime,
        @Schema(description = "Maturação", example = "12 meses")
        String maturation,
        @Schema(description = "Harmonização", example = "Carnes vermelhas")
        String harmonization

) {
}
