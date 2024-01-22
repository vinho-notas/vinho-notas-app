package com.vinhonotas.vinho.application.converters;

import com.vinhonotas.vinho.domain.entities.WineEntity;
import com.vinhonotas.vinho.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.vinho.interfaces.dtos.outputs.WineOutputDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    public WineEntity toEntityUpdate(WineEntity wineSaved, UUID id, WineInputDTO wineInputDTO) {
        return WineEntity.builder()
                .id(id)
                .name(wineInputDTO.getName() != null ? wineInputDTO.getName() : wineSaved.getName())
                .price(wineInputDTO.getPrice() != null ? wineInputDTO.getPrice() : wineSaved.getPrice())
                .purchaseLocation(wineInputDTO.getPurchaseLocation() != null ? wineInputDTO.getPurchaseLocation() : wineSaved.getPurchaseLocation())
                .purchaseDate(wineInputDTO.getPurchaseDate() != null ? wineInputDTO.getPurchaseDate() : wineSaved.getPurchaseDate())
                .wineType(wineInputDTO.getWineType() != null ? wineInputDTO.getWineType() : wineSaved.getWineType())
                .wineClassification(wineInputDTO.getWineClassification() != null ? wineInputDTO.getWineClassification() : wineSaved.getWineClassification())
                .alcoholContent(wineInputDTO.getAlcoholContent() != 0 ? wineInputDTO.getAlcoholContent() : wineSaved.getAlcoholContent())
                .volumeMl(wineInputDTO.getVolumeMl() != 0 ? wineInputDTO.getVolumeMl() : wineSaved.getVolumeMl())
                .grape(wineInputDTO.getGrape() != null ? wineInputDTO.getGrape() : wineSaved.getGrape())
                .winery(wineInputDTO.getWinery() != null ? wineInputDTO.getWinery() : wineSaved.getWinery())
                .serviceTemperature(wineInputDTO.getServiceTemperature() != 0 ? wineInputDTO.getServiceTemperature() : wineSaved.getServiceTemperature())
                .harvest(wineInputDTO.getHarvest() != 0 ? wineInputDTO.getHarvest() : wineSaved.getHarvest())
                .country(wineInputDTO.getCountry() != null ? wineInputDTO.getCountry() : wineSaved.getCountry())
                .guardTime(wineInputDTO.getGuardTime() != null ? wineInputDTO.getGuardTime() : wineSaved.getGuardTime())
                .region(wineInputDTO.getRegion() != null ? wineInputDTO.getRegion() : wineSaved.getRegion())
                .maturation(wineInputDTO.getMaturation() != null ? wineInputDTO.getMaturation() : wineSaved.getMaturation())
                .harmonization(wineInputDTO.getHarmonization() != null ? wineInputDTO.getHarmonization() : wineSaved.getHarmonization())
                .build();
    }

    public WineOutputDTO toOutputDTO(WineEntity wineEntity) {
        return WineOutputDTO.builder()
                .id(wineEntity.getId())
                .name(wineEntity.getName())
                .price(wineEntity.getPrice())
                .purchaseLocation(wineEntity.getPurchaseLocation())
                .purchaseDate(wineEntity.getPurchaseDate())
                .wineType(wineEntity.getWineType())
                .wineClassification(wineEntity.getWineClassification())
                .alcoholContent(wineEntity.getAlcoholContent())
                .volumeMl(wineEntity.getVolumeMl())
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
