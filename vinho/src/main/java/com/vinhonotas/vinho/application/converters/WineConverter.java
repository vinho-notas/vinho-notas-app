package com.vinhonotas.vinho.application.converters;

import com.vinhonotas.vinho.domain.entities.WineEntity;
import com.vinhonotas.vinho.interfaces.dtos.inputs.WineInputDTO;
import org.springframework.stereotype.Component;

@Component
public class WineConverter {

    public WineEntity toEntity(WineInputDTO wineInputDTO) {
        return WineEntity.builder()
                .name(wineInputDTO.getName())
                .price(wineInputDTO.getPrice())
                .purchaseLocation(wineInputDTO.getPurchaseLocation())
                .purchaseDate(wineInputDTO.getPurchaseDate())
                .wineType(wineInputDTO.getWineType())
                .wineClassification(wineInputDTO.getWineClassification())
                .alcoholContent(wineInputDTO.getAlcoholContent())
                .volumeMl(wineInputDTO.getVolumeMl())
                .grape(wineInputDTO.getGrape())
                .winery(wineInputDTO.getWinery())
                .serviceTemperature(wineInputDTO.getServiceTemperature())
                .harvest(wineInputDTO.getHarvest())
                .country(wineInputDTO.getCountry())
                .guardTime(wineInputDTO.getGuardTime())
                .region(wineInputDTO.getRegion())
                .maturation(wineInputDTO.getMaturation())
                .harmonization(wineInputDTO.getHarmonization())
                .build();
    }
}
