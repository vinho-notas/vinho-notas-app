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

    public TastingCardInputDTO toInputDTO(TastingCardEntity tastingCardEntity) {
        return modelMapper.map(tastingCardEntity, TastingCardInputDTO.class);
    }

    public TastingCardOutputDTO toOutputDTO(TastingCardEntity tastingCardEntity) {
        customModelMapperToOutputDTO();

        return getTastingCardOutputDTO(tastingCardEntity);
    }

    public TastingCardEntity toEntityUpdate(TastingCardInputDTO inputDTO, UUID id) {
        TastingCardEntity mapped = modelMapper.map(inputDTO, TastingCardEntity.class);
        mapped.setId(id);
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
        mapped.setTastingType(EnumConverter.toString(tastingCardEntity.getTastingType()));
        mapped.setClarity(EnumConverter.toString(tastingCardEntity.getClarity()));
        mapped.setBrightness(EnumConverter.toString(tastingCardEntity.getBrightness()));
        mapped.setViscosity(EnumConverter.toString(tastingCardEntity.getViscosity()));
        mapped.setColorRed(EnumConverter.toString(tastingCardEntity.getColorRed()));
        mapped.setColorWhite(EnumConverter.toString(tastingCardEntity.getColorWhite()));
        mapped.setColorRose(EnumConverter.toString(tastingCardEntity.getColorRose()));
        mapped.setVisualInspectionClassification(EnumConverter.toString(tastingCardEntity.getVisualInspectionClassification()));
        mapped.setIntensity(EnumConverter.toString(tastingCardEntity.getIntensity()));
        mapped.setOlfactoryInspectionPersistence(EnumConverter.toString(tastingCardEntity.getOlfactoryInspectionPersistence()));
        mapped.setQuality(EnumConverter.toString(tastingCardEntity.getQuality()));
        mapped.setFruity(EnumConverter.toString(tastingCardEntity.getFruity()));
        mapped.setDryFruits(EnumConverter.toString(tastingCardEntity.getDryFruits()));
        mapped.setFlorals(EnumConverter.toString(tastingCardEntity.getFlorals()));
        mapped.setVegetablesAndHerbs(EnumConverter.toString(tastingCardEntity.getVegetablesAndHerbs()));
        mapped.setMinerals(EnumConverter.toString(tastingCardEntity.getMinerals()));
        mapped.setSpices(EnumConverter.toString(tastingCardEntity.getSpices()));
        mapped.setAnimals(EnumConverter.toString(tastingCardEntity.getAnimals()));
        mapped.setEmpireumatics(EnumConverter.toString(tastingCardEntity.getEmpireumatics()));
        mapped.setWood(EnumConverter.toString(tastingCardEntity.getWood()));
        mapped.setChemicals(EnumConverter.toString(tastingCardEntity.getChemicals()));
        mapped.setLacteal(EnumConverter.toString(tastingCardEntity.getLacteal()));
        mapped.setSweets(EnumConverter.toString(tastingCardEntity.getSweets()));
        mapped.setOlfactoryInspectionClassification(EnumConverter.toString(tastingCardEntity.getOlfactoryInspectionClassification()));
        mapped.setBody(EnumConverter.toString(tastingCardEntity.getBody()));
        mapped.setSweetness(EnumConverter.toString(tastingCardEntity.getSweetness()));
        mapped.setTannin(EnumConverter.toString(tastingCardEntity.getTannin()));
        mapped.setAcidity(EnumConverter.toString(tastingCardEntity.getAcidity()));
        mapped.setAlcohol(EnumConverter.toString(tastingCardEntity.getAlcohol()));
        mapped.setGustatoryInspectionPersistence(EnumConverter.toString(tastingCardEntity.getGustatoryInspectionPersistence()));
        mapped.setMaturity(EnumConverter.toString(tastingCardEntity.getMaturity()));
        mapped.setTypicality(EnumConverter.toString(tastingCardEntity.getTypicality()));
        mapped.setGustatoryInspectionClassification(EnumConverter.toString(tastingCardEntity.getGustatoryInspectionClassification()));
        mapped.setPointScale(EnumConverter.toString(tastingCardEntity.getPointScale()));
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
