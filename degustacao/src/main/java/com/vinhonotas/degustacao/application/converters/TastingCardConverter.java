package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingCardOutputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TastingCardConverter {

    private final ModelMapper modelMapper;
    private final ModelMapper customModelMapper;

    public TastingCardEntity toEntity(TastingCardInputDTO tastingCardInputDTO) {
        return getTastingCardEntity(tastingCardInputDTO);
    }

    public TastingCardOutputDTO toOutputDTO(TastingCardEntity tastingCardEntity) {
        customModelMapperToOutputDTO();
        return getTastingCardOutputDTO(tastingCardEntity);
    }

    public TastingCardEntity toEntityUpdate(TastingCardInputDTO inputDTO, UUID id) {
        TastingCardEntity mapped = getTastingCardEntity(inputDTO);
        mapped.setId(id);
        mapped.setUserreg(inputDTO.getUserreg());
        mapped.setDthreg(inputDTO.getDthreg());
        mapped.setUseralt("userAlt");
        mapped.setDthalt(LocalDateTime.now());
        return mapped;
    }

    public Set<TastingCardOutputDTO> toOutputDTOList(Set<TastingCardEntity> all) {
        return modelMapper.map(all, Set.class);
    }

    private TastingCardEntity getTastingCardEntity(TastingCardInputDTO tastingCardInputDTO) {
        TastingCardEntity entity = modelMapper.map(tastingCardInputDTO, TastingCardEntity.class);
        entity.setTastingType(EnumConverter.fromString(tastingCardInputDTO.getTastingType(), EnumTastingType.class));
        entity.setClarity(EnumConverter.fromString(tastingCardInputDTO.getClarity(), EnumClarityType.class));
        entity.setBrightness(EnumConverter.fromString(tastingCardInputDTO.getBrightness(), EnumBrightnessType.class));
        entity.setViscosity(EnumConverter.fromString(tastingCardInputDTO.getViscosity(), EnumViscosityType.class));
        entity.setColorRed(EnumConverter.fromString(tastingCardInputDTO.getColorRed(), EnumRedColorType.class));
        entity.setColorWhite(EnumConverter.fromString(tastingCardInputDTO.getColorWhite(), EnumWhiteColorType.class));
        entity.setColorRose(EnumConverter.fromString(tastingCardInputDTO.getColorRose(), EnumRoseColorType.class));
        entity.setVisualInspectionClassification(EnumConverter.fromString(tastingCardInputDTO
                .getVisualInspectionClassification(), EnumClassificationType.class));
        entity.setIntensity(EnumConverter.fromString(tastingCardInputDTO.getIntensity(), EnumIntensityType.class));
        entity.setOlfactoryInspectionPersistence(EnumConverter.fromString(tastingCardInputDTO
                .getOlfactoryInspectionPersistence(), EnumPersistenceType.class));
        entity.setQuality(EnumConverter.fromString(tastingCardInputDTO.getQuality(), EnumQualityType.class));
        entity.setFruity(EnumConverter.fromString(tastingCardInputDTO.getFruity(), EnumFruityType.class));
        entity.setDryFruits(EnumConverter.fromString(tastingCardInputDTO.getDryFruits(), EnumDryFruitsType.class));
        entity.setFlorals(EnumConverter.fromString(tastingCardInputDTO.getFlorals(), EnumFloralsType.class));
        entity.setVegetablesAndHerbs(EnumConverter.fromString(tastingCardInputDTO.getVegetablesAndHerbs(),
                EnumVegetablesAndHerbsType.class));
        entity.setMinerals(EnumConverter.fromString(tastingCardInputDTO.getMinerals(), EnumMineralsType.class));
        entity.setSpices(EnumConverter.fromString(tastingCardInputDTO.getSpices(), EnumSpicesType.class));
        entity.setAnimals(EnumConverter.fromString(tastingCardInputDTO.getAnimals(), EnumAnimalsType.class));
        entity.setEmpireumatics(EnumConverter.fromString(tastingCardInputDTO.getEmpireumatics(), EnumEmpireumaticsType.class));
        entity.setWood(EnumConverter.fromString(tastingCardInputDTO.getWood(), EnumWoodType.class));
        entity.setChemicals(EnumConverter.fromString(tastingCardInputDTO.getChemicals(), EnumChemicalsAndEtherealType.class));
        entity.setLacteal(EnumConverter.fromString(tastingCardInputDTO.getLacteal(), EnumLactealType.class));
        entity.setSweets(EnumConverter.fromString(tastingCardInputDTO.getSweets(), EnumSweetsType.class));
        entity.setOlfactoryInspectionClassification(EnumConverter.fromString(tastingCardInputDTO
                .getOlfactoryInspectionClassification(), EnumClassificationType.class));
        entity.setBody(EnumConverter.fromString(tastingCardInputDTO.getBody(), EnumBodyType.class));
        entity.setSweetness(EnumConverter.fromString(tastingCardInputDTO.getSweetness(), EnumSweetnessType.class));
        entity.setTannin(EnumConverter.fromString(tastingCardInputDTO.getTannin(), EnumTanninType.class));
        entity.setAcidity(EnumConverter.fromString(tastingCardInputDTO.getAcidity(), EnumAcidityType.class));
        entity.setAlcohol(EnumConverter.fromString(tastingCardInputDTO.getAlcohol(), EnumAlcoholType.class));
        entity.setGustatoryInspectionPersistence(EnumConverter.fromString(tastingCardInputDTO
                .getGustatoryInspectionPersistence(), EnumPersistenceType.class));
        entity.setMaturity(EnumConverter.fromString(tastingCardInputDTO.getMaturity(), EnumMaturityType.class));
        entity.setTypicality(EnumConverter.fromString(tastingCardInputDTO.getTypicality(), EnumTypicalityType.class));
        entity.setGustatoryInspectionClassification(EnumConverter.fromString(tastingCardInputDTO
                .getGustatoryInspectionClassification(), EnumClassificationType.class));
        entity.setPointScale(EnumConverter.fromString(tastingCardInputDTO.getPointScale(), EnumPointScale.class));
        return entity;
    }

    private TastingCardOutputDTO getTastingCardOutputDTO(TastingCardEntity tastingCardEntity) {
        TastingCardOutputDTO mapped = customModelMapper.map(tastingCardEntity, TastingCardOutputDTO.class);
        mapped.setTastingType(tastingCardEntity.getTastingType() != null ? EnumConverter.toString(tastingCardEntity.getTastingType()) : null);
        mapped.setClarity(tastingCardEntity.getClarity() != null ? EnumConverter.toString(tastingCardEntity.getClarity()) : null);
        mapped.setBrightness(tastingCardEntity.getBrightness() != null ? EnumConverter.toString(tastingCardEntity.getBrightness()) : null);
        mapped.setViscosity(tastingCardEntity.getViscosity() != null ? EnumConverter.toString(tastingCardEntity.getViscosity()) : null);
        mapped.setColorRed(tastingCardEntity.getColorRed() != null ? EnumConverter.toString(tastingCardEntity.getColorRed()) : null);
        mapped.setColorWhite(tastingCardEntity.getColorWhite() != null ? EnumConverter.toString(tastingCardEntity.getColorWhite()) : null);
        mapped.setColorRose(tastingCardEntity.getColorRose() != null ? EnumConverter.toString(tastingCardEntity.getColorRose()) : null);
        mapped.setVisualInspectionClassification(tastingCardEntity.getVisualInspectionClassification() != null ? EnumConverter.toString(
                tastingCardEntity.getVisualInspectionClassification()) : null);
        mapped.setIntensity(tastingCardEntity.getIntensity() != null ? EnumConverter.toString(tastingCardEntity.getIntensity()) : null);
        mapped.setOlfactoryInspectionPersistence(tastingCardEntity.getOlfactoryInspectionPersistence() != null ? EnumConverter.toString(
                tastingCardEntity.getOlfactoryInspectionPersistence()) : null);
        mapped.setQuality(tastingCardEntity.getQuality() != null ? EnumConverter.toString(tastingCardEntity.getQuality()) : null);
        mapped.setFruity(tastingCardEntity.getFruity() != null ? EnumConverter.toString(tastingCardEntity.getFruity()) : null);
        mapped.setDryFruits(tastingCardEntity.getDryFruits() != null ? EnumConverter.toString(tastingCardEntity.getDryFruits()) : null);
        mapped.setFlorals(tastingCardEntity.getFlorals() != null ? EnumConverter.toString(tastingCardEntity.getFlorals()) : null);
        mapped.setVegetablesAndHerbs(tastingCardEntity.getVegetablesAndHerbs() != null ? EnumConverter.toString(
                tastingCardEntity.getVegetablesAndHerbs()) : null);
        mapped.setMinerals(tastingCardEntity.getMinerals() != null ? EnumConverter.toString(tastingCardEntity.getMinerals()) : null);
        mapped.setSpices(tastingCardEntity.getSpices() != null ? EnumConverter.toString(tastingCardEntity.getSpices()) : null);
        mapped.setAnimals(tastingCardEntity.getAnimals() != null ? EnumConverter.toString(tastingCardEntity.getAnimals()) : null);
        mapped.setEmpireumatics(tastingCardEntity.getEmpireumatics() != null ? EnumConverter.toString(tastingCardEntity.getEmpireumatics()) : null);
        mapped.setWood(tastingCardEntity.getWood() != null ? EnumConverter.toString(tastingCardEntity.getWood()) : null);
        mapped.setChemicals(tastingCardEntity.getChemicals() != null ? EnumConverter.toString(tastingCardEntity.getChemicals()) : null);
        mapped.setLacteal(tastingCardEntity.getLacteal() != null ? EnumConverter.toString(tastingCardEntity.getLacteal()) : null);
        mapped.setSweets(tastingCardEntity.getSweets() != null ? EnumConverter.toString(tastingCardEntity.getSweets()) : null);
        mapped.setOlfactoryInspectionClassification(tastingCardEntity.getOlfactoryInspectionClassification() != null ? EnumConverter.toString(
                tastingCardEntity.getOlfactoryInspectionClassification()) : null);
        mapped.setBody(tastingCardEntity.getBody() != null ? EnumConverter.toString(tastingCardEntity.getBody()) : null);
        mapped.setSweetness(tastingCardEntity.getSweetness() != null ? EnumConverter.toString(tastingCardEntity.getSweetness()) : null);
        mapped.setTannin(tastingCardEntity.getTannin() != null ? EnumConverter.toString(tastingCardEntity.getTannin()) : null);
        mapped.setAcidity(tastingCardEntity.getAcidity() != null ? EnumConverter.toString(tastingCardEntity.getAcidity()) : null);
        mapped.setAlcohol(tastingCardEntity.getAlcohol() != null ? EnumConverter.toString(tastingCardEntity.getAlcohol()) : null);
        mapped.setGustatoryInspectionPersistence(tastingCardEntity.getGustatoryInspectionPersistence() != null ? EnumConverter.toString(
                tastingCardEntity.getGustatoryInspectionPersistence()) : null);
        mapped.setMaturity(tastingCardEntity.getMaturity() != null ? EnumConverter.toString(tastingCardEntity.getMaturity()) : null);
        mapped.setTypicality(tastingCardEntity.getTypicality() != null ? EnumConverter.toString(tastingCardEntity.getTypicality()) : null);
        mapped.setGustatoryInspectionClassification(tastingCardEntity.getGustatoryInspectionClassification() != null ? EnumConverter.toString(
                tastingCardEntity.getGustatoryInspectionClassification()) : null);
        mapped.setPointScale(tastingCardEntity.getPointScale() != null ? EnumConverter.toString(tastingCardEntity.getPointScale()) : null);
        return mapped;
    }

    private void customModelMapperToOutputDTO() {
        customModelMapper.typeMap(TastingCardEntity.class, TastingCardOutputDTO.class)
                .addMapping(TastingCardEntity::getId, TastingCardOutputDTO::setId)
                .addMapping(TastingCardEntity::getWineTasted, TastingCardOutputDTO::setWineTasted)
                .addMapping(TastingCardEntity::getTastingData, TastingCardOutputDTO::setTastingData)
                .addMapping(TastingCardEntity::getHarvest, TastingCardOutputDTO::setHarvest)
                .addMapping(TastingCardEntity::getGrapes, TastingCardOutputDTO::setGrapes)
                .addMapping(TastingCardEntity::getCountry, TastingCardOutputDTO::setCountry)
                .addMapping(TastingCardEntity::getRegion, TastingCardOutputDTO::setRegion)
                .addMapping(TastingCardEntity::getOpinion, TastingCardOutputDTO::setOpinion);
    }

}
