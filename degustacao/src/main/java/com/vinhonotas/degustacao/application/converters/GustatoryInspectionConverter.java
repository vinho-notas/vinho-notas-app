package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.GustatoryInspectionOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GustatoryInspectionConverter {

    public GustatoryInspectionEntity toEntity(GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        return GustatoryInspectionEntity.builder()
                .tastingData(gustatoryInspectionInputDTO.getTastingData())
                .wineTasted(gustatoryInspectionInputDTO.getWineTasted())
                .body(gustatoryInspectionInputDTO.getBody())
                .sweetness(gustatoryInspectionInputDTO.getSweetness())
                .tannin(gustatoryInspectionInputDTO.getTannin())
                .classification(gustatoryInspectionInputDTO.getClassification())
                .acidity(gustatoryInspectionInputDTO.getAcidity())
                .alcohol(gustatoryInspectionInputDTO.getAlcohol())
                .persistence(gustatoryInspectionInputDTO.getPersistence())
                .maturity(gustatoryInspectionInputDTO.getMaturity())
                .typicality(gustatoryInspectionInputDTO.getTypicality())
                .tastingCard(gustatoryInspectionInputDTO.getTastingCard())
                .build();
    }

    public GustatoryInspectionEntity toEntityUpdate(GustatoryInspectionInputDTO gustatoryInspectionInputDTO, UUID id,
                                                    GustatoryInspectionEntity gustatoryInspectionEntity) {
        return GustatoryInspectionEntity.builder()
                .id(id)
                .tastingData(gustatoryInspectionInputDTO.getTastingData() != null ? gustatoryInspectionInputDTO.getTastingData() : gustatoryInspectionEntity.getTastingData())
                .wineTasted(gustatoryInspectionInputDTO.getWineTasted() != null ? gustatoryInspectionInputDTO.getWineTasted() : gustatoryInspectionEntity.getWineTasted())
                .body(gustatoryInspectionInputDTO.getBody() != null ? gustatoryInspectionInputDTO.getBody() : gustatoryInspectionEntity.getBody())
                .sweetness(gustatoryInspectionInputDTO.getSweetness() != null ? gustatoryInspectionInputDTO.getSweetness() : gustatoryInspectionEntity.getSweetness())
                .tannin(gustatoryInspectionInputDTO.getTannin() != null ? gustatoryInspectionInputDTO.getTannin() : gustatoryInspectionEntity.getTannin())
                .classification(gustatoryInspectionInputDTO.getClassification() != null ? gustatoryInspectionInputDTO.getClassification() : gustatoryInspectionEntity.getClassification())
                .acidity(gustatoryInspectionInputDTO.getAcidity() != null ? gustatoryInspectionInputDTO.getAcidity() : gustatoryInspectionEntity.getAcidity())
                .alcohol(gustatoryInspectionInputDTO.getAlcohol() != null ? gustatoryInspectionInputDTO.getAlcohol() : gustatoryInspectionEntity.getAlcohol())
                .persistence(gustatoryInspectionInputDTO.getPersistence() != null ? gustatoryInspectionInputDTO.getPersistence() : gustatoryInspectionEntity.getPersistence())
                .maturity(gustatoryInspectionInputDTO.getMaturity() != null ? gustatoryInspectionInputDTO.getMaturity() : gustatoryInspectionEntity.getMaturity())
                .typicality(gustatoryInspectionInputDTO.getTypicality() != null ? gustatoryInspectionInputDTO.getTypicality() : gustatoryInspectionEntity.getTypicality())
                .tastingCard(gustatoryInspectionInputDTO.getTastingCard() != null ? gustatoryInspectionInputDTO.getTastingCard() : gustatoryInspectionEntity.getTastingCard())
                .build();
    }

    public GustatoryInspectionOutputDTO toOutputDTO(GustatoryInspectionEntity gustatoryInspectionEntity) {
        return GustatoryInspectionOutputDTO.builder()
                .id(gustatoryInspectionEntity.getId())
                .tastingData(gustatoryInspectionEntity.getTastingData())
                .wineTasted(gustatoryInspectionEntity.getWineTasted())
                .body(gustatoryInspectionEntity.getBody())
                .sweetness(gustatoryInspectionEntity.getSweetness())
                .tannin(gustatoryInspectionEntity.getTannin())
                .classification(gustatoryInspectionEntity.getClassification())
                .acidity(gustatoryInspectionEntity.getAcidity())
                .alcohol(gustatoryInspectionEntity.getAlcohol())
                .persistence(gustatoryInspectionEntity.getPersistence())
                .maturity(gustatoryInspectionEntity.getMaturity())
                .typicality(gustatoryInspectionEntity.getTypicality())
                .tastingCard(gustatoryInspectionEntity.getTastingCard())
                .build();
    }

    public List<GustatoryInspectionOutputDTO> toOutputDTOList(List<GustatoryInspectionEntity> list) {
        return list
                .stream()
                .map(this::toOutputDTO)
                .toList();
    }


    public GustatoryInspectionOutputDTO toOutputDTOUpdate(GustatoryInspectionEntity gustatoryInspectionEntity, UUID id,
                                                          GustatoryInspectionOutputDTO gustatoryInspectionOutputDTO) {
        return GustatoryInspectionOutputDTO.builder()
                .id(id)
                .tastingData(gustatoryInspectionOutputDTO.getTastingData() != null ? gustatoryInspectionOutputDTO.getTastingData() : gustatoryInspectionEntity.getTastingData())
                .wineTasted(gustatoryInspectionOutputDTO.getWineTasted() != null ? gustatoryInspectionOutputDTO.getWineTasted() : gustatoryInspectionEntity.getWineTasted())
                .body(gustatoryInspectionOutputDTO.getBody() != null ? gustatoryInspectionOutputDTO.getBody() : gustatoryInspectionEntity.getBody())
                .sweetness(gustatoryInspectionOutputDTO.getSweetness() != null ? gustatoryInspectionOutputDTO.getSweetness() : gustatoryInspectionEntity.getSweetness())
                .tannin(gustatoryInspectionOutputDTO.getTannin() != null ? gustatoryInspectionOutputDTO.getTannin() : gustatoryInspectionEntity.getTannin())
                .classification(gustatoryInspectionOutputDTO.getClassification() != null ? gustatoryInspectionOutputDTO.getClassification() : gustatoryInspectionEntity.getClassification())
                .acidity(gustatoryInspectionOutputDTO.getAcidity() != null ? gustatoryInspectionOutputDTO.getAcidity() : gustatoryInspectionEntity.getAcidity())
                .alcohol(gustatoryInspectionOutputDTO.getAlcohol() != null ? gustatoryInspectionOutputDTO.getAlcohol() : gustatoryInspectionEntity.getAlcohol())
                .persistence(gustatoryInspectionOutputDTO.getPersistence() != null ? gustatoryInspectionOutputDTO.getPersistence() : gustatoryInspectionEntity.getPersistence())
                .maturity(gustatoryInspectionOutputDTO.getMaturity() != null ? gustatoryInspectionOutputDTO.getMaturity() : gustatoryInspectionEntity.getMaturity())
                .typicality(gustatoryInspectionOutputDTO.getTypicality() != null ? gustatoryInspectionOutputDTO.getTypicality() : gustatoryInspectionEntity.getTypicality())
                .tastingCard(gustatoryInspectionOutputDTO.getTastingCard() != null ? gustatoryInspectionOutputDTO.getTastingCard() : gustatoryInspectionEntity.getTastingCard())
                .build();
    }

}
