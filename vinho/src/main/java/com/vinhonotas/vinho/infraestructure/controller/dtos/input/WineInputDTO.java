package com.vinhonotas.vinho.infraestructure.controller.dtos.input;

public record WineInputDTO(

    String name,
    WineDetailsDTO wineDetailsDTO,
    PurchaseInfoDTO purchaseInfoDTO,
    WineOriginDTO wineOriginDTO

)
{}
