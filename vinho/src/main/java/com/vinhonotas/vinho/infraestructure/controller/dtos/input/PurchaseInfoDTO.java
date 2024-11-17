package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PurchaseInfoDTO(

        BigDecimal price,
        String purchaseLocation,
        LocalDate purchaseDate

) {
}
