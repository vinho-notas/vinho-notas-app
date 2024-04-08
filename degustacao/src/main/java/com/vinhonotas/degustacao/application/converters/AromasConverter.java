package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.AromasOutputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class AromasConverter {

    public AromasEntity toEntity(AromasInputDTO aromasInputDTO) {
        return AromasEntity.builder()
                .tastingData(aromasInputDTO.getTastingData())
                .wineTasted(aromasInputDTO.getWineTasted())
                .fruity(EnumConverter.fromString(aromasInputDTO.getFruity(), EnumFruityType.class))
                .dryFruits(EnumConverter.fromString(aromasInputDTO.getDryFruits(), EnumDryFruitsType.class))
                .florals(EnumConverter.fromString(aromasInputDTO.getFlorals(), EnumFloralsType.class))
                .vegetablesAndHerbs(EnumConverter.fromString(aromasInputDTO.getVegetablesAndHerbs(), EnumVegetablesAndHerbsType.class))
                .minerals(EnumConverter.fromString(aromasInputDTO.getMinerals(), EnumMineralsType.class))
                .spices(EnumConverter.fromString(aromasInputDTO.getSpices(), EnumSpicesType.class))
                .animals(EnumConverter.fromString(aromasInputDTO.getAnimals(), EnumAnimalsType.class))
                .empireumatics(EnumConverter.fromString(aromasInputDTO.getEmpireumatics(), EnumEmpireumaticsType.class))
                .wood(EnumConverter.fromString(aromasInputDTO.getWood(), EnumWoodType.class))
                .chemicals(EnumConverter.fromString(aromasInputDTO.getChemicals(), EnumChemicalsAndEtherealType.class))
                .lacteal(EnumConverter.fromString(aromasInputDTO.getLacteal(), EnumLactealType.class))
                .sweets(EnumConverter.fromString(aromasInputDTO.getSweets(), EnumSweetsType.class))
                .userreg(aromasInputDTO.getUserreg())
                .dthreg(LocalDateTime.now())
                .useralt(aromasInputDTO.getUseralt())
                .dthalt(aromasInputDTO.getDthalt())
                .build();
    }

    public AromasEntity toEntityUpdate(AromasInputDTO aromasInputDTO, UUID id, AromasEntity aromasEntity) {
        return AromasEntity.builder()
                .id(id)
                .tastingData(aromasInputDTO.getTastingData() != null ? aromasInputDTO.getTastingData() :
                        aromasEntity.getTastingData())
                .wineTasted(aromasInputDTO.getWineTasted() != null ? aromasInputDTO.getWineTasted() : aromasEntity
                        .getWineTasted())
                .fruity(aromasInputDTO.getFruity() != null ? EnumConverter.fromString(aromasInputDTO.getFruity(),
                        EnumFruityType.class) : aromasEntity.getFruity())
                .dryFruits(aromasInputDTO.getDryFruits() != null ? EnumConverter.fromString(aromasInputDTO
                        .getDryFruits(), EnumDryFruitsType.class) : aromasEntity.getDryFruits())
                .florals(aromasInputDTO.getFlorals() != null ? EnumConverter.fromString(aromasInputDTO.getFlorals(),
                        EnumFloralsType.class) : aromasEntity.getFlorals())
                .vegetablesAndHerbs(aromasInputDTO.getVegetablesAndHerbs() != null ? EnumConverter.fromString(
                        aromasInputDTO.getVegetablesAndHerbs(), EnumVegetablesAndHerbsType.class) : aromasEntity.getVegetablesAndHerbs())
                .minerals(aromasInputDTO.getMinerals() != null ? EnumConverter.fromString(aromasInputDTO.getMinerals(),
                        EnumMineralsType.class) : aromasEntity.getMinerals())
                .spices(aromasInputDTO.getSpices() != null ? EnumConverter.fromString(aromasInputDTO.getSpices(),
                        EnumSpicesType.class) : aromasEntity.getSpices())
                .animals(aromasInputDTO.getAnimals() != null ? EnumConverter.fromString(aromasInputDTO.getAnimals(),
                        EnumAnimalsType.class) : aromasEntity.getAnimals())
                .empireumatics(aromasInputDTO.getEmpireumatics() != null ? EnumConverter.fromString(aromasInputDTO
                        .getEmpireumatics(), EnumEmpireumaticsType.class) : aromasEntity.getEmpireumatics())
                .wood(aromasInputDTO.getWood() != null ? EnumConverter.fromString(aromasInputDTO.getWood(),
                        EnumWoodType.class) : aromasEntity.getWood())
                .chemicals(aromasInputDTO.getChemicals() != null ? EnumConverter.fromString(aromasInputDTO.getChemicals(),
                        EnumChemicalsAndEtherealType.class) : aromasEntity.getChemicals())
                .lacteal(aromasInputDTO.getLacteal() != null ? EnumConverter.fromString(aromasInputDTO.getLacteal(),
                        EnumLactealType.class) : aromasEntity.getLacteal())
                .sweets(aromasInputDTO.getSweets() != null ? EnumConverter.fromString(aromasInputDTO.getSweets(),
                        EnumSweetsType.class) : aromasEntity.getSweets())
                .userreg(aromasInputDTO.getUserreg() != null ? aromasInputDTO.getUserreg() : aromasEntity.getUserreg())
                .dthreg(aromasInputDTO.getDthreg() != null ? aromasInputDTO.getDthreg() : aromasEntity.getDthreg())
                .useralt(aromasInputDTO.getUseralt() != null ? aromasInputDTO.getUseralt() : aromasEntity.getUseralt())
                .dthalt(LocalDateTime.now())
                .build();
    }

    public AromasOutputDTO toOutputDTO(AromasEntity aromasEntity) {
        return AromasOutputDTO.builder()
                .id(aromasEntity.getId())
                .tastingData(aromasEntity.getTastingData())
                .wineTasted(aromasEntity.getWineTasted())
                .fruity(EnumConverter.toString(aromasEntity.getFruity()))
                .dryFruits(EnumConverter.toString(aromasEntity.getDryFruits()))
                .florals(EnumConverter.toString(aromasEntity.getFlorals()))
                .vegetablesAndHerbs(EnumConverter.toString(aromasEntity.getVegetablesAndHerbs()))
                .minerals(EnumConverter.toString(aromasEntity.getMinerals()))
                .spices(EnumConverter.toString(aromasEntity.getSpices()))
                .animals(EnumConverter.toString(aromasEntity.getAnimals()))
                .empireumatics(EnumConverter.toString(aromasEntity.getEmpireumatics()))
                .wood(EnumConverter.toString(aromasEntity.getWood()))
                .chemicals(EnumConverter.toString(aromasEntity.getChemicals()))
                .lacteal(EnumConverter.toString(aromasEntity.getLacteal()))
                .sweets(EnumConverter.toString(aromasEntity.getSweets()))
                .build();
    }

    public List<AromasOutputDTO> toOutputDTOList(List<AromasEntity> aromasEntityList) {
        return aromasEntityList
                .stream()
                .map(this::toOutputDTO)
                .toList();
    }

    public AromasOutputDTO toOutputDTOUpdate(AromasEntity aromasEntity, UUID id, AromasOutputDTO aromasOutputDTO) {
        return AromasOutputDTO.builder()
                .id(id)
                .tastingData(aromasOutputDTO.getTastingData() != null ? aromasOutputDTO.getTastingData() :
                        aromasEntity.getTastingData())
                .wineTasted(aromasOutputDTO.getWineTasted() != null ? aromasOutputDTO.getWineTasted() :
                        aromasEntity.getWineTasted())
                .fruity(aromasOutputDTO.getFruity() != null ? aromasOutputDTO.getFruity() : EnumConverter
                        .toString(aromasEntity.getFruity()))
                .dryFruits(aromasOutputDTO.getDryFruits() != null ? aromasOutputDTO.getDryFruits() : EnumConverter
                        .toString(aromasEntity.getDryFruits()))
                .florals(aromasOutputDTO.getFlorals() != null ? aromasOutputDTO.getFlorals() : EnumConverter
                        .toString(aromasEntity.getFlorals()))
                .vegetablesAndHerbs(aromasOutputDTO.getVegetablesAndHerbs() != null ? aromasOutputDTO.getVegetablesAndHerbs() :
                        EnumConverter.toString(aromasEntity.getVegetablesAndHerbs()))
                .minerals(aromasOutputDTO.getMinerals() != null ? aromasOutputDTO.getMinerals() : EnumConverter
                        .toString(aromasEntity.getMinerals()))
                .spices(aromasOutputDTO.getSpices() != null ? aromasOutputDTO.getSpices() : EnumConverter.toString(
                        aromasEntity.getSpices()))
                .animals(aromasOutputDTO.getAnimals() != null ? aromasOutputDTO.getAnimals() : EnumConverter.toString(
                        aromasEntity.getAnimals()))
                .empireumatics(aromasOutputDTO.getEmpireumatics() != null ? aromasOutputDTO.getEmpireumatics() :
                        EnumConverter.toString(aromasEntity.getEmpireumatics()))
                .wood(aromasOutputDTO.getWood() != null ? aromasOutputDTO.getWood() : EnumConverter.toString(
                        aromasEntity.getWood()))
                .chemicals(aromasOutputDTO.getChemicals() != null ? aromasOutputDTO.getChemicals() : EnumConverter.toString(
                        aromasEntity.getChemicals()))
                .lacteal(aromasOutputDTO.getLacteal() != null ? aromasOutputDTO.getLacteal() : EnumConverter.toString(
                        aromasEntity.getLacteal()))
                .sweets(aromasOutputDTO.getSweets() != null ? aromasOutputDTO.getSweets() : EnumConverter.toString(
                        aromasEntity.getSweets()))
                .build();
    }
}
