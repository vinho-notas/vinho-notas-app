package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

public record WineInputDTO(

    String name,
    WineDetailsDTO wineDetails,
    PurchaseInfoDTO purchaseInfo,
    WineOriginDTO wineOrigin

)
{}
