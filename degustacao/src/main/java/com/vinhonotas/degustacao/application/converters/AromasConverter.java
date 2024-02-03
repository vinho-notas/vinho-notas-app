package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.AromasOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AromasConverter {

    public AromasEntity toEntity(AromasInputDTO aromasInputDTO) {
        return AromasEntity.builder()
                .tastingData(aromasInputDTO.getTastingData())
                .wineTasted(aromasInputDTO.getWineTasted())
                .fruity(aromasInputDTO.getFruity())
                .dryFruits(aromasInputDTO.getDryFruits())
                .florals(aromasInputDTO.getFlorals())
                .vegetablesAndHerbs(aromasInputDTO.getVegetablesAndHerbs())
                .minerals(aromasInputDTO.getMinerals())
                .spices(aromasInputDTO.getSpices())
                .animals(aromasInputDTO.getAnimals())
                .empireumatics(aromasInputDTO.getEmpireumatics())
                .wood(aromasInputDTO.getWood())
                .chemicals(aromasInputDTO.getChemicals())
                .lacteal(aromasInputDTO.getLacteal())
                .sweets(aromasInputDTO.getSweets())
                .build();
    }

    public AromasEntity toEntityUpdate(AromasInputDTO aromasInputDTO, UUID id, AromasEntity aromasEntity) {
        return AromasEntity.builder()
                .id(id)
                .tastingData(aromasInputDTO.getTastingData() != null ? aromasInputDTO.getTastingData() : aromasEntity.getTastingData())
                .wineTasted(aromasInputDTO.getWineTasted() != null ? aromasInputDTO.getWineTasted() : aromasEntity.getWineTasted())
                .fruity(aromasInputDTO.getFruity() != null ? aromasInputDTO.getFruity() : aromasEntity.getFruity())
                .dryFruits(aromasInputDTO.getDryFruits() != null ? aromasInputDTO.getDryFruits() : aromasEntity.getDryFruits())
                .florals(aromasInputDTO.getFlorals() != null ? aromasInputDTO.getFlorals() : aromasEntity.getFlorals())
                .vegetablesAndHerbs(aromasInputDTO.getVegetablesAndHerbs() != null ? aromasInputDTO.getVegetablesAndHerbs() : aromasEntity.getVegetablesAndHerbs())
                .minerals(aromasInputDTO.getMinerals() != null ? aromasInputDTO.getMinerals() : aromasEntity.getMinerals())
                .spices(aromasInputDTO.getSpices() != null ? aromasInputDTO.getSpices() : aromasEntity.getSpices())
                .animals(aromasInputDTO.getAnimals() != null ? aromasInputDTO.getAnimals() : aromasEntity.getAnimals())
                .empireumatics(aromasInputDTO.getEmpireumatics() != null ? aromasInputDTO.getEmpireumatics() : aromasEntity.getEmpireumatics())
                .wood(aromasInputDTO.getWood() != null ? aromasInputDTO.getWood() : aromasEntity.getWood())
                .chemicals(aromasInputDTO.getChemicals() != null ? aromasInputDTO.getChemicals() : aromasEntity.getChemicals())
                .lacteal(aromasInputDTO.getLacteal() != null ? aromasInputDTO.getLacteal() : aromasEntity.getLacteal())
                .sweets(aromasInputDTO.getSweets() != null ? aromasInputDTO.getSweets() : aromasEntity.getSweets())
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
                .vegetablesAndHerbs(aromasOutputDTO.getVegetablesAndHerbs() != null ? aromasOutputDTO.getVegetablesAndHerbs() : aromasEntity.getVegetablesAndHerbs())
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
