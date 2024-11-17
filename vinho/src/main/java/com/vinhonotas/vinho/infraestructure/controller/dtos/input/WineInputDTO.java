package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

import lombok.Builder;

@Builder
public record WineInputDTO(

    String name,
    WineDetailsDTO wineDetails,
    PurchaseInfoDTO purchaseInfo,
    WineOriginDTO wineOrigin

)
{}
