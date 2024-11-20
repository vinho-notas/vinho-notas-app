package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PurchaseInfoDTO(

        @Schema(description = "Preço da compra", example = "100.00")
        BigDecimal price,
        @Schema(description = "Local da compra", example = "Supermercado")
        String purchaseLocation,
        @Schema(description = "Data da compra", example = "2021-10-10")
        LocalDate purchaseDate

) {
}
