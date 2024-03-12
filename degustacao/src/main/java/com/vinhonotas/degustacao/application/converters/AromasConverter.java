package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.AromasOutputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import org.springframework.stereotype.Component;

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
                .build();
    }

    public AromasOutputDTO toOutputDTO(AromasEntity aromasEntity) {
        return AromasOutputDTO.builder()
                .id(aromasEntity.getId())
                .tastingData(aromasEntity.getTastingData())
                .wineTasted(aromasEntity.getWineTasted())
                .fruity(aromasEntity.getFruity())
                .dryFruits(aromasEntity.getDryFruits())
                .florals(aromasEntity.getFlorals())
                .vegetablesAndHerbs(aromasEntity.getVegetablesAndHerbs())
                .minerals(aromasEntity.getMinerals())
                .spices(aromasEntity.getSpices())
                .animals(aromasEntity.getAnimals())
                .empireumatics(aromasEntity.getEmpireumatics())
                .wood(aromasEntity.getWood())
                .chemicals(aromasEntity.getChemicals())
                .lacteal(aromasEntity.getLacteal())
                .sweets(aromasEntity.getSweets())
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
                .tastingData(aromasOutputDTO.getTastingData() != null ? aromasOutputDTO.getTastingData() : aromasEntity.getTastingData())
                .wineTasted(aromasOutputDTO.getWineTasted() != null ? aromasOutputDTO.getWineTasted() : aromasEntity.getWineTasted())
                .fruity(aromasOutputDTO.getFruity() != null ? aromasOutputDTO.getFruity() : aromasEntity.getFruity())
                .dryFruits(aromasOutputDTO.getDryFruits() != null ? aromasOutputDTO.getDryFruits() : aromasEntity.getDryFruits())
                .florals(aromasOutputDTO.getFlorals() != null ? aromasOutputDTO.getFlorals() : aromasEntity.getFlorals())
                .vegetablesAndHerbs(aromasOutputDTO.getVegetablesAndHerbs() != null ? aromasOutputDTO.getVegetablesAndHerbs() :
                        aromasEntity.getVegetablesAndHerbs())
                .minerals(aromasOutputDTO.getMinerals() != null ? aromasOutputDTO.getMinerals() : aromasEntity.getMinerals())
                .spices(aromasOutputDTO.getSpices() != null ? aromasOutputDTO.getSpices() : aromasEntity.getSpices())
                .animals(aromasOutputDTO.getAnimals() != null ? aromasOutputDTO.getAnimals() : aromasEntity.getAnimals())
                .empireumatics(aromasOutputDTO.getEmpireumatics() != null ? aromasOutputDTO.getEmpireumatics() : aromasEntity.getEmpireumatics())
                .wood(aromasOutputDTO.getWood() != null ? aromasOutputDTO.getWood() : aromasEntity.getWood())
                .chemicals(aromasOutputDTO.getChemicals() != null ? aromasOutputDTO.getChemicals() : aromasEntity.getChemicals())
                .lacteal(aromasOutputDTO.getLacteal() != null ? aromasOutputDTO.getLacteal() : aromasEntity.getLacteal())
                .sweets(aromasOutputDTO.getSweets() != null ? aromasOutputDTO.getSweets() : aromasEntity.getSweets())
                .build();
    }
}
