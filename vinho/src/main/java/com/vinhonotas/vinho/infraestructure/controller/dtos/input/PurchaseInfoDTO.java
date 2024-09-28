package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseInfoDTO(
        BigDecimal price,
        String purchaseLocation,
        LocalDate purchaseDate
) {
}
