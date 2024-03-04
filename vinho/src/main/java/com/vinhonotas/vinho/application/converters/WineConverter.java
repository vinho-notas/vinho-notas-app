package com.vinhonotas.vinho.application.converters;

import com.vinhonotas.vinho.domain.entities.WineEntity;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.vinho.interfaces.dtos.outputs.WineOutputDTO;
import com.vinhonotas.vinho.utils.EnumConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class WineConverter {

    public WineEntity toEntity(WineInputDTO wineInputDTO) {
        return WineEntity.builder()
                .name(wineInputDTO.getName())
                .price(wineInputDTO.getPrice())
                .purchaseLocation(wineInputDTO.getPurchaseLocation())
                .purchaseDate(wineInputDTO.getPurchaseDate())
                .wineType(EnumConverter.fromString(wineInputDTO.getWineType(), EnumWineType.class))
                .wineClassification(EnumConverter.fromString(wineInputDTO.getWineClassification(), EnumWineClassification.class))
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
                .wineType(wineInputDTO.getWineType() != null ? EnumConverter.fromString(wineInputDTO.getWineType(), EnumWineType.class) : wineSaved.getWineType())
                .wineClassification(wineInputDTO.getWineClassification() != null ? EnumConverter.fromString(wineInputDTO.getWineClassification(), EnumWineClassification.class) : wineSaved.getWineClassification())
                .alcoholContent(wineInputDTO.getAlcoholContent() != null ? wineInputDTO.getAlcoholContent() : wineSaved.getAlcoholContent())
                .volumeMl(wineInputDTO.getVolumeMl() != 0 ? wineInputDTO.getVolumeMl() : wineSaved.getVolumeMl())
                .grape(wineInputDTO.getGrape() != null ? wineInputDTO.getGrape() : wineSaved.getGrape())
                .winery(wineInputDTO.getWinery() != null ? wineInputDTO.getWinery() : wineSaved.getWinery())
                .serviceTemperature(wineInputDTO.getServiceTemperature() != null ? wineInputDTO.getServiceTemperature() : wineSaved.getServiceTemperature())
                .harvest(wineInputDTO.getHarvest() != null ? wineInputDTO.getHarvest() : wineSaved.getHarvest())
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

    public List<WineOutputDTO> toOutputDTOList(List<WineEntity> list) {
        return list.stream()
                .map(this::toOutputDTO)
                .toList();
    }

    public WineOutputDTO toOutputDTOUpdate(WineEntity wineUpdated, UUID uuid, WineOutputDTO wineOutputDTO) {
        return WineOutputDTO.builder()
                .id(uuid)
                .name(wineUpdated.getName() != null ? wineUpdated.getName() : wineOutputDTO.getName())
                .price(wineUpdated.getPrice() != null ? wineUpdated.getPrice() : wineOutputDTO.getPrice())
                .purchaseLocation(wineUpdated.getPurchaseLocation() != null ? wineUpdated.getPurchaseLocation() : wineOutputDTO.getPurchaseLocation())
                .purchaseDate(wineUpdated.getPurchaseDate() != null ? wineUpdated.getPurchaseDate() : wineOutputDTO.getPurchaseDate())
                .wineType(wineUpdated.getWineType() != null ? wineUpdated.getWineType() : wineOutputDTO.getWineType())
                .wineClassification(wineUpdated.getWineClassification() != null ? wineUpdated.getWineClassification() : wineOutputDTO.getWineClassification())
                .alcoholContent(wineUpdated.getAlcoholContent() != null ? wineUpdated.getAlcoholContent() : wineOutputDTO.getAlcoholContent())
                .volumeMl(wineUpdated.getVolumeMl() != 0 ? wineUpdated.getVolumeMl() : wineOutputDTO.getVolumeMl())
                .grape(wineUpdated.getGrape() != null ? wineUpdated.getGrape() : wineOutputDTO.getGrape())
                .winery(wineUpdated.getWinery() != null ? wineUpdated.getWinery() : wineOutputDTO.getWinery())
                .serviceTemperature(wineUpdated.getServiceTemperature() != null ? wineUpdated.getServiceTemperature() : wineOutputDTO.getServiceTemperature())
                .harvest(wineUpdated.getHarvest() != null ? wineUpdated.getHarvest() : wineOutputDTO.getHarvest())
                .country(wineUpdated.getCountry() != null ? wineUpdated.getCountry() : wineOutputDTO.getCountry())
                .guardTime(wineUpdated.getGuardTime() != null ? wineUpdated.getGuardTime() : wineOutputDTO.getGuardTime())
                .region(wineUpdated.getRegion() != null ? wineUpdated.getRegion() : wineOutputDTO.getRegion())
                .maturation(wineUpdated.getMaturation() != null ? wineUpdated.getMaturation() : wineOutputDTO.getMaturation())
                .harmonization(wineUpdated.getHarmonization() != null ? wineUpdated.getHarmonization() : wineOutputDTO.getHarmonization())
                .build();
    }

}
