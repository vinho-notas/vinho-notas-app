package com.vinhonotas.vinho.infraestructure.gateways.mappers;

import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import org.springframework.stereotype.Component;

@Component
public class WineEntityMapper {

    public WineEntity toWineEntity(WineDomain wineDomain) {
        return WineEntity.builder()
        .sku(wineDomain.getSku())
        .name(wineDomain.getName())
        .price(wineDomain.getPurchaseInfo().getPrice())
        .purchaseLocation(wineDomain.getPurchaseInfo().getPurchaseLocation())
        .purchaseDate(wineDomain.getPurchaseInfo().getPurchaseDate())
        .wineType(wineDomain.getWineDetails().getWineType())
        .wineClassification(wineDomain.getWineDetails().getWineClassification())
        .alcoholContent(wineDomain.getWineDetails().getAlcoholContent())
        .volumeMl(wineDomain.getWineDetails().getVolumeMl())
        .grape(wineDomain.getWineDetails().getGrape())
        .winery(wineDomain.getWineDetails().getWinery())
        .serviceTemperature(wineDomain.getWineDetails().getServiceTemperature())
        .harvest(wineDomain.getWineOrigin().getHarvest())
        .country(wineDomain.getWineOrigin().getCountry())
        .guardTime(wineDomain.getWineOrigin().getGuardTime())
        .region(wineDomain.getWineOrigin().getRegion())
        .maturation(wineDomain.getWineOrigin().getMaturation())
        .harmonization(wineDomain.getWineOrigin().getHarmonization())
        .build();
    }

}
