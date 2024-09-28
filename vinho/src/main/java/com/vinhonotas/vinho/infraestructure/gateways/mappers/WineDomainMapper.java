package com.vinhonotas.vinho.infraestructure.gateways.mappers;

import com.vinhonotas.vinho.domain.entities.wine.PurchaseInfo;
import com.vinhonotas.vinho.domain.entities.wine.WineDetails;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.domain.entities.wine.WineOrigin;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.PurchaseInfoDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineDetailsDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineOriginDTO;
import com.vinhonotas.vinho.utils.EnumConverter;
import org.springframework.stereotype.Component;

@Component
public class WineDomainMapper {

    public WineDomain toWineDomain(WineInputDTO input) {
        return WineDomain.builder()
                .sku(input.name(), EnumConverter.fromString(input.wineDetails().wineType(), EnumWineType.class),
                        EnumConverter.fromString(input.wineDetails().wineClassification(), EnumWineClassification.class),
                        input.wineOrigin().harvest(), input.wineOrigin().country())
                .name(input.name())
                .wineDetails(toWineDetails(input.wineDetails()))
                .purchaseInfo(toPurchaseInfo(input.purchaseInfo()))
                .wineOrigin(toWineOrigin(input.wineOrigin()))
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
                .wineType(EnumConverter.fromString(wineDetailsDTO.wineType(), EnumWineType.class))
                .wineClassification(EnumConverter.fromString(wineDetailsDTO.wineClassification(), EnumWineClassification.class))
                .alcoholContent(wineDetailsDTO.alcoholContent())
                .volumeMl(Integer.parseInt(wineDetailsDTO.volumeMl()))
                .grape(wineDetailsDTO.grape())
                .winery(wineDetailsDTO.winery())
                .serviceTemperature(wineDetailsDTO.serviceTemperature())
                .build();
    }

}
