package com.vinhonotas.vinho.infraestructure.gateways.mappers;

import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.controller.dtos.output.WineOutputDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.utils.EnumConverter;
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

    public WineOutputDTO toWineOutputDTO(WineEntity wineEntity) {
        return WineOutputDTO.builder()
                .id(wineEntity.getId())
                .sku(wineEntity.getSku())
                .name(wineEntity.getName())
                .price(wineEntity.getPrice())
                .purchaseLocation(wineEntity.getPurchaseLocation())
                .purchaseDate(wineEntity.getPurchaseDate())
                .wineType(EnumConverter.toString(wineEntity.getWineType()))
                .wineClassification(EnumConverter.toString(wineEntity.getWineClassification()))
                .alcoholContent(wineEntity.getAlcoholContent())
                .volumeMl(String.valueOf(wineEntity.getVolumeMl()))
                .grape(wineEntity.getGrape())
                .winery(wineEntity.getWinery())
                .serviceTemperature(wineEntity.getServiceTemperature())
                .harvest(wineEntity.getHarvest())
                .country(wineEntity.getCountry())
                .guardTime(wineEntity.getGuardTime())
                .region(wineEntity.getRegion())
                .maturation(wineEntity.getMaturation())
                .harmonization(wineEntity.getHarmonization())
                .build();
    }

}
