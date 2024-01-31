package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.OlfactoryInspectionOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OlfactoryInspectionConverter {

    public OlfactoryInspectionEntity toEntity(OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        return OlfactoryInspectionEntity.builder()
                .tastingData(olfactoryInspectionInputDTO.getTastingData())
                .wineTasted(olfactoryInspectionInputDTO.getWineTasted())
                .intensity(olfactoryInspectionInputDTO.getIntensity())
                .persistence(olfactoryInspectionInputDTO.getPersistence())
                .quality(olfactoryInspectionInputDTO.getQuality())
                .aromas(olfactoryInspectionInputDTO.getAromas())
                .classification(olfactoryInspectionInputDTO.getClassification())
                .tastingCard(olfactoryInspectionInputDTO.getTastingCard())
                .build();
    }

    public OlfactoryInspectionEntity toEntityUpdate(OlfactoryInspectionInputDTO olfactoryInspectionInputDTO, UUID id,
                                                    OlfactoryInspectionEntity olfactoryInspectionEntity) {
        return OlfactoryInspectionEntity.builder()
                .id(id)
                .tastingData(olfactoryInspectionInputDTO.getTastingData() != null ? olfactoryInspectionInputDTO.getTastingData() : olfactoryInspectionEntity.getTastingData())
                .wineTasted(olfactoryInspectionInputDTO.getWineTasted() != null ? olfactoryInspectionInputDTO.getWineTasted() : olfactoryInspectionEntity.getWineTasted())
                .intensity(olfactoryInspectionInputDTO.getIntensity() != null ? olfactoryInspectionInputDTO.getIntensity() : olfactoryInspectionEntity.getIntensity())
                .persistence(olfactoryInspectionInputDTO.getPersistence() != null ? olfactoryInspectionInputDTO.getPersistence() : olfactoryInspectionEntity.getPersistence())
                .quality(olfactoryInspectionInputDTO.getQuality() != null ? olfactoryInspectionInputDTO.getQuality() : olfactoryInspectionEntity.getQuality())
                .aromas(olfactoryInspectionInputDTO.getAromas() != null ? olfactoryInspectionInputDTO.getAromas() : olfactoryInspectionEntity.getAromas())
                .classification(olfactoryInspectionInputDTO.getClassification() != null ? olfactoryInspectionInputDTO.getClassification() : olfactoryInspectionEntity.getClassification())
                .tastingCard(olfactoryInspectionInputDTO.getTastingCard() != null ? olfactoryInspectionInputDTO.getTastingCard() : olfactoryInspectionEntity.getTastingCard())
                .build();
    }

    public OlfactoryInspectionOutputDTO toOutputDTO(OlfactoryInspectionEntity olfactoryInspectionEntity) {
        return OlfactoryInspectionOutputDTO.builder()
                .id(olfactoryInspectionEntity.getId())
                .tastingData(olfactoryInspectionEntity.getTastingData())
                .wineTasted(olfactoryInspectionEntity.getWineTasted())
                .intensity(olfactoryInspectionEntity.getIntensity())
                .persistence(olfactoryInspectionEntity.getPersistence())
                .quality(olfactoryInspectionEntity.getQuality())
                .aromas(olfactoryInspectionEntity.getAromas())
                .classification(olfactoryInspectionEntity.getClassification())
                .tastingCard(olfactoryInspectionEntity.getTastingCard())
                .build();
    }

    public List<OlfactoryInspectionOutputDTO> toOutputDTOList(List<OlfactoryInspectionEntity> list) {
        return list
                .stream()
                .map(this::toOutputDTO)
                .toList();
    }

    public OlfactoryInspectionOutputDTO toOutputDTOUpdate(OlfactoryInspectionEntity olfactoryInspectionEntity, UUID id,
                                                          OlfactoryInspectionOutputDTO olfactoryInspectionOutputDTO) {
        return OlfactoryInspectionOutputDTO.builder()
                .id(id)
                .tastingData(olfactoryInspectionOutputDTO.getTastingData() != null ? olfactoryInspectionOutputDTO.getTastingData() : olfactoryInspectionEntity.getTastingData())
                .wineTasted(olfactoryInspectionOutputDTO.getWineTasted() != null ? olfactoryInspectionOutputDTO.getWineTasted() : olfactoryInspectionEntity.getWineTasted())
                .intensity(olfactoryInspectionOutputDTO.getIntensity() != null ? olfactoryInspectionOutputDTO.getIntensity() : olfactoryInspectionEntity.getIntensity())
                .persistence(olfactoryInspectionOutputDTO.getPersistence() != null ? olfactoryInspectionOutputDTO.getPersistence() : olfactoryInspectionEntity.getPersistence())
                .quality(olfactoryInspectionOutputDTO.getQuality() != null ? olfactoryInspectionOutputDTO.getQuality() : olfactoryInspectionEntity.getQuality())
                .aromas(olfactoryInspectionOutputDTO.getAromas() != null ? olfactoryInspectionOutputDTO.getAromas() : olfactoryInspectionEntity.getAromas())
                .classification(olfactoryInspectionOutputDTO.getClassification() != null ? olfactoryInspectionOutputDTO.getClassification() : olfactoryInspectionEntity.getClassification())
                .tastingCard(olfactoryInspectionOutputDTO.getTastingCard() != null ? olfactoryInspectionOutputDTO.getTastingCard() : olfactoryInspectionEntity.getTastingCard())
                .build();
    }

}
