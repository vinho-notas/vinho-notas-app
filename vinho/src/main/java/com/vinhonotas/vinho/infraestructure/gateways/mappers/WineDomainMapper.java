package com.vinhonotas.vinho.infraestructure.gateways.mappers;

import com.vinhonotas.vinho.domain.entities.wine.PurchaseInfo;
import com.vinhonotas.vinho.domain.entities.wine.WineDetails;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.domain.entities.wine.WineOrigin;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.PurchaseInfoDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineDetailsDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineOriginDTO;
import org.springframework.stereotype.Component;

@Component
public class WineDomainMapper {

    public WineDomain toWineDomain(WineInputDTO input) {
        return WineDomain.builder()
                .sku(input.name(), input.wineDetailsDTO().wineType(), input.wineDetailsDTO().wineClassification(), input.wineOriginDTO().harvest(), input.wineOriginDTO().country())
                .name(input.name())
                .wineDetails(toWineDetails(input.wineDetailsDTO()))
                .purchaseInfo(toPurchaseInfo(input.purchaseInfoDTO()))
                .wineOrigin(toWineOrigin(input.wineOriginDTO()))
                .build();
    }

    private WineOrigin toWineOrigin(WineOriginDTO wineOriginDTO) {
        return WineOrigin.builder()
                .country(wineOriginDTO.country())
                .region(wineOriginDTO.region())
                .harvest(wineOriginDTO.harvest())
                .guardTime(wineOriginDTO.guardTime())
                .maturation(wineOriginDTO.maturation())
                .harmonization(wineOriginDTO.harmonization())
                .build();
    }

    private PurchaseInfo toPurchaseInfo(PurchaseInfoDTO purchaseInfoDTO) {
        return PurchaseInfo.builder()
                .price(purchaseInfoDTO.price())
                .purchaseLocation(purchaseInfoDTO.purchaseLocation())
                .purchaseDate(purchaseInfoDTO.purchaseDate())
                .build();
    }

    private WineDetails toWineDetails(WineDetailsDTO wineDetailsDTO) {
        return WineDetails.builder()
                .wineType(wineDetailsDTO.wineType())
                .wineClassification(wineDetailsDTO.wineClassification())
                .alcoholContent(wineDetailsDTO.alcoholContent())
                .volumeMl(wineDetailsDTO.volumeMl())
                .grape(wineDetailsDTO.grape())
                .winery(wineDetailsDTO.winery())
                .serviceTemperature(wineDetailsDTO.serviceTemperature())
                .build();
    }

}
