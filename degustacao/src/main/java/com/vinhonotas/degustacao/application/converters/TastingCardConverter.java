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

    private TastingCardEntity getTastingCardEntity(TastingCardInputDTO tastingCardInputDTO) {
        TastingCardEntity entity = modelMapper.map(tastingCardInputDTO, TastingCardEntity.class);
        entity.setTastingType(tastingCardInputDTO.getTastingType() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getTastingType(), EnumTastingType.class) : null);
        entity.setClarity(tastingCardInputDTO.getClarity() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getClarity(), EnumClarityType.class) : null);
        entity.setBrightness(tastingCardInputDTO.getBrightness() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getBrightness(), EnumBrightnessType.class) : null);
        entity.setViscosity(tastingCardInputDTO.getViscosity() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getViscosity(), EnumViscosityType.class) : null);
        entity.setColorRed(tastingCardInputDTO.getColorRed() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getColorRed(), EnumRedColorType.class) : null);
        entity.setColorWhite(tastingCardInputDTO.getColorWhite() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getColorWhite(), EnumWhiteColorType.class) : null);
        entity.setColorRose(tastingCardInputDTO.getColorRose() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getColorRose(), EnumRoseColorType.class) : null);
        entity.setVisualInspectionClassification(tastingCardInputDTO.getVisualInspectionClassification() != null ?
                EnumConverter.fromString(tastingCardInputDTO.getVisualInspectionClassification(),
                        EnumClassificationType.class) : null);
        entity.setIntensity(tastingCardInputDTO.getIntensity() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getIntensity(), EnumIntensityType.class) : null);
        entity.setOlfactoryInspectionPersistence(tastingCardInputDTO.getOlfactoryInspectionPersistence() != null ?
                EnumConverter.fromString(tastingCardInputDTO.getOlfactoryInspectionPersistence(),
                        EnumPersistenceType.class) : null);
        entity.setQuality(tastingCardInputDTO.getQuality() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getQuality(), EnumQualityType.class) : null);
        entity.setFruity(tastingCardInputDTO.getFruity() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getFruity(), EnumFruityType.class) : null);
        entity.setDryFruits(tastingCardInputDTO.getDryFruits() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getDryFruits(), EnumDryFruitsType.class) : null);
        entity.setFlorals(tastingCardInputDTO.getFlorals() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getFlorals(), EnumFloralsType.class) : null);
        entity.setVegetablesAndHerbs(tastingCardInputDTO.getVegetablesAndHerbs() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getVegetablesAndHerbs(), EnumVegetablesAndHerbsType.class) : null);
        entity.setMinerals(tastingCardInputDTO.getMinerals() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getMinerals(), EnumMineralsType.class) : null);
        entity.setSpices(tastingCardInputDTO.getSpices() != null ? EnumConverter.fromString(tastingCardInputDTO.getSpices(),
                EnumSpicesType.class) : null);
        entity.setAnimals(tastingCardInputDTO.getAnimals() != null ? EnumConverter.fromString(tastingCardInputDTO.getAnimals(),
                EnumAnimalsType.class) : null);
        entity.setEmpireumatics(tastingCardInputDTO.getEmpireumatics() != null ? EnumConverter.fromString(
                tastingCardInputDTO.getEmpireumatics(), EnumEmpireumaticsType.class) : null);
        entity.setWood(tastingCardInputDTO.getWood() != null ? EnumConverter.fromString(tastingCardInputDTO.getWood(),
                EnumWoodType.class) : null);
        entity.setChemicals(tastingCardInputDTO.getChemicals() != null ? EnumConverter.fromString(tastingCardInputDTO
                .getChemicals(), EnumChemicalsAndEtherealType.class) : null);
        entity.setLacteal(tastingCardInputDTO.getLacteal() != null ? EnumConverter.fromString(tastingCardInputDTO
                .getLacteal(), EnumLactealType.class) : null);
        entity.setSweets(tastingCardInputDTO.getSweets() != null ? EnumConverter.fromString(tastingCardInputDTO
                .getSweets(), EnumSweetsType.class) : null);
        entity.setOlfactoryInspectionClassification(tastingCardInputDTO.getOlfactoryInspectionClassification() != null ?
                EnumConverter.fromString(tastingCardInputDTO.getOlfactoryInspectionClassification(),
                        EnumClassificationType.class) : null);
        entity.setBody(tastingCardInputDTO.getBody() != null ? EnumConverter.fromString(tastingCardInputDTO.getBody(),
                EnumBodyType.class) : null);
        entity.setSweetness(tastingCardInputDTO.getSweetness() != null ? EnumConverter.fromString(tastingCardInputDTO
                .getSweetness(), EnumSweetnessType.class) : null);
        entity.setTannin(tastingCardInputDTO.getTannin() != null ? EnumConverter.fromString(tastingCardInputDTO
                .getTannin(), EnumTanninType.class) : null);
        entity.setAcidity(tastingCardInputDTO.getAcidity() != null ? EnumConverter.fromString(tastingCardInputDTO
                .getAcidity(), EnumAcidityType.class) : null);
        entity.setAlcohol(tastingCardInputDTO.getAlcohol() != null ? EnumConverter.fromString(tastingCardInputDTO
                .getAlcohol(), EnumAlcoholType.class) : null);
        entity.setGustatoryInspectionPersistence(tastingCardInputDTO.getGustatoryInspectionPersistence() != null ?
                EnumConverter.fromString(tastingCardInputDTO.getGustatoryInspectionPersistence(),
                        EnumPersistenceType.class) : null);
        entity.setMaturity(tastingCardInputDTO.getMaturity() != null ? EnumConverter.fromString(tastingCardInputDTO
                .getMaturity(), EnumMaturityType.class) : null);
        entity.setTypicality(tastingCardInputDTO.getTypicality() != null ? EnumConverter.fromString(tastingCardInputDTO
                .getTypicality(), EnumTypicalityType.class) : null);
        entity.setGustatoryInspectionClassification(tastingCardInputDTO.getGustatoryInspectionClassification() != null ?
                EnumConverter.fromString(tastingCardInputDTO.getGustatoryInspectionClassification(),
                        EnumClassificationType.class) : null);
        entity.setPointScale(tastingCardInputDTO.getPointScale() != null ? EnumConverter.fromString(tastingCardInputDTO
                .getPointScale(), EnumPointScale.class) : null);
        return entity;
    }

    private TastingCardOutputDTO getTastingCardOutputDTO(TastingCardEntity tastingCardEntity) {
        TastingCardOutputDTO mapped = customModelMapper.map(tastingCardEntity, TastingCardOutputDTO.class);
        mapped.setTastingType(tastingCardEntity.getTastingType() != null ? EnumConverter.toString(tastingCardEntity
                .getTastingType()) : null);
        mapped.setClarity(tastingCardEntity.getClarity() != null ? EnumConverter.toString(tastingCardEntity.getClarity()) : null);
        mapped.setBrightness(tastingCardEntity.getBrightness() != null ? EnumConverter.toString(tastingCardEntity
                .getBrightness()) : null);
        mapped.setViscosity(tastingCardEntity.getViscosity() != null ? EnumConverter.toString(tastingCardEntity
                .getViscosity()) : null);
        mapped.setColorRed(tastingCardEntity.getColorRed() != null ? EnumConverter.toString(tastingCardEntity
                .getColorRed()) : null);
        mapped.setColorWhite(tastingCardEntity.getColorWhite() != null ? EnumConverter.toString(tastingCardEntity
                .getColorWhite()) : null);
        mapped.setColorRose(tastingCardEntity.getColorRose() != null ? EnumConverter.toString(tastingCardEntity
                .getColorRose()) : null);
        mapped.setVisualInspectionClassification(tastingCardEntity.getVisualInspectionClassification() != null ?
                EnumConverter.toString(tastingCardEntity.getVisualInspectionClassification()) : null);
        mapped.setIntensity(tastingCardEntity.getIntensity() != null ? EnumConverter.toString(tastingCardEntity
                .getIntensity()) : null);
        mapped.setOlfactoryInspectionPersistence(tastingCardEntity.getOlfactoryInspectionPersistence() != null ?
                EnumConverter.toString(tastingCardEntity.getOlfactoryInspectionPersistence()) : null);
        mapped.setQuality(tastingCardEntity.getQuality() != null ? EnumConverter.toString(tastingCardEntity
                .getQuality()) : null);
        mapped.setFruity(tastingCardEntity.getFruity() != null ? EnumConverter.toString(tastingCardEntity
                .getFruity()) : null);
        mapped.setDryFruits(tastingCardEntity.getDryFruits() != null ? EnumConverter.toString(tastingCardEntity
                .getDryFruits()) : null);
        mapped.setFlorals(tastingCardEntity.getFlorals() != null ? EnumConverter.toString(tastingCardEntity
                .getFlorals()) : null);
        mapped.setVegetablesAndHerbs(tastingCardEntity.getVegetablesAndHerbs() != null ? EnumConverter.toString(
                tastingCardEntity.getVegetablesAndHerbs()) : null);
        mapped.setMinerals(tastingCardEntity.getMinerals() != null ? EnumConverter.toString(tastingCardEntity
                .getMinerals()) : null);
        mapped.setSpices(tastingCardEntity.getSpices() != null ? EnumConverter.toString(tastingCardEntity
                .getSpices()) : null);
        mapped.setAnimals(tastingCardEntity.getAnimals() != null ? EnumConverter.toString(tastingCardEntity
                .getAnimals()) : null);
        mapped.setEmpireumatics(tastingCardEntity.getEmpireumatics() != null ? EnumConverter.toString(tastingCardEntity
                .getEmpireumatics()) : null);
        mapped.setWood(tastingCardEntity.getWood() != null ? EnumConverter.toString(tastingCardEntity.getWood()) : null);
        mapped.setChemicals(tastingCardEntity.getChemicals() != null ? EnumConverter.toString(tastingCardEntity
                .getChemicals()) : null);
        mapped.setLacteal(tastingCardEntity.getLacteal() != null ? EnumConverter.toString(tastingCardEntity
                .getLacteal()) : null);
        mapped.setSweets(tastingCardEntity.getSweets() != null ? EnumConverter.toString(tastingCardEntity
                .getSweets()) : null);
        mapped.setOlfactoryInspectionClassification(tastingCardEntity.getOlfactoryInspectionClassification() != null ?
                EnumConverter.toString(tastingCardEntity.getOlfactoryInspectionClassification()) : null);
        mapped.setBody(tastingCardEntity.getBody() != null ? EnumConverter.toString(tastingCardEntity.getBody()) : null);
        mapped.setSweetness(tastingCardEntity.getSweetness() != null ? EnumConverter.toString(tastingCardEntity
                .getSweetness()) : null);
        mapped.setTannin(tastingCardEntity.getTannin() != null ? EnumConverter.toString(tastingCardEntity
                .getTannin()) : null);
        mapped.setAcidity(tastingCardEntity.getAcidity() != null ? EnumConverter.toString(tastingCardEntity
                .getAcidity()) : null);
        mapped.setAlcohol(tastingCardEntity.getAlcohol() != null ? EnumConverter.toString(tastingCardEntity
                .getAlcohol()) : null);
        mapped.setGustatoryInspectionPersistence(tastingCardEntity.getGustatoryInspectionPersistence() != null ?
                EnumConverter.toString(tastingCardEntity.getGustatoryInspectionPersistence()) : null);
        mapped.setMaturity(tastingCardEntity.getMaturity() != null ? EnumConverter.toString(tastingCardEntity
                .getMaturity()) : null);
        mapped.setTypicality(tastingCardEntity.getTypicality() != null ? EnumConverter.toString(tastingCardEntity
                .getTypicality()) : null);
        mapped.setGustatoryInspectionClassification(tastingCardEntity.getGustatoryInspectionClassification() != null ?
                EnumConverter.toString(tastingCardEntity.getGustatoryInspectionClassification()) : null);
        mapped.setPointScale(tastingCardEntity.getPointScale() != null ? EnumConverter.toString(tastingCardEntity
                .getPointScale()) : null);
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
