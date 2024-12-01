package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Builder;

@Builder
public record WineInputDTO(

    @Schema(description = "Nome do vinho", example = "Miolo Reserva Cabernet Sauvignon")
    String name,

    @Schema(description = "Detalhes do vinho")
    WineDetailsDTO wineDetails,

    @Schema(description = "Informações de compra")
    @Valid
    PurchaseInfoDTO purchaseInfo,

    @Schema(description = "Origem do vinho")
    WineOriginDTO wineOrigin

)
{}
