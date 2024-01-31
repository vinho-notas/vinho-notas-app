package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.VisualInspectionOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class VisualInspectionConverter {

    public VisualInspectionEntity toEntity(VisualInspectionInputDTO visualInspectionInputDTO){
        return VisualInspectionEntity.builder()
                .tastingData(visualInspectionInputDTO.getTastingData())
                .wineTasted(visualInspectionInputDTO.getWineTasted())
                .clarity(visualInspectionInputDTO.getClarity())
                .brightness(visualInspectionInputDTO.getBrightness())
                .viscosity(visualInspectionInputDTO.getViscosity())
                .colorRed(visualInspectionInputDTO.getColorRed())
                .colorWhite(visualInspectionInputDTO.getColorWhite())
                .colorRose(visualInspectionInputDTO.getColorRose())
                .classification(visualInspectionInputDTO.getClassification())
                .tastingCard(visualInspectionInputDTO.getTastingCard())
                .build();
    }

    public VisualInspectionEntity toEntityUpdate(VisualInspectionInputDTO visualInspectionInputDTO, UUID id, VisualInspectionEntity visualInspectionEntity){
        return VisualInspectionEntity.builder()
                .id(id)
                .tastingData(visualInspectionInputDTO.getTastingData() != null ? visualInspectionInputDTO.getTastingData() : visualInspectionEntity.getTastingData())
                .wineTasted(visualInspectionInputDTO.getWineTasted() != null ? visualInspectionInputDTO.getWineTasted() : visualInspectionEntity.getWineTasted())
                .clarity(visualInspectionInputDTO.getClarity() != null ? visualInspectionInputDTO.getClarity() : visualInspectionEntity.getClarity())
                .brightness(visualInspectionInputDTO.getBrightness() != null ? visualInspectionInputDTO.getBrightness() : visualInspectionEntity.getBrightness())
                .viscosity(visualInspectionInputDTO.getViscosity() != null ? visualInspectionInputDTO.getViscosity() : visualInspectionEntity.getViscosity())
                .colorRed(visualInspectionInputDTO.getColorRed() != null ? visualInspectionInputDTO.getColorRed() : visualInspectionEntity.getColorRed())
                .colorWhite(visualInspectionInputDTO.getColorWhite() != null ? visualInspectionInputDTO.getColorWhite() : visualInspectionEntity.getColorWhite())
                .colorRose(visualInspectionInputDTO.getColorRose() != null ? visualInspectionInputDTO.getColorRose() : visualInspectionEntity.getColorRose())
                .classification(visualInspectionInputDTO.getClassification() != null ? visualInspectionInputDTO.getClassification() : visualInspectionEntity.getClassification())
                .tastingCard(visualInspectionInputDTO.getTastingCard() != null ? visualInspectionInputDTO.getTastingCard() : visualInspectionEntity.getTastingCard())
                .build();
    }
    public VisualInspectionOutputDTO toOutputDTO(VisualInspectionEntity visualInspectionEntity){
        return VisualInspectionOutputDTO.builder()
                .id(visualInspectionEntity.getId())
                .tastingData(visualInspectionEntity.getTastingData())
                .wineTasted(visualInspectionEntity.getWineTasted())
                .clarity(visualInspectionEntity.getClarity())
                .brightness(visualInspectionEntity.getBrightness())
                .viscosity(visualInspectionEntity.getViscosity())
                .colorRed(visualInspectionEntity.getColorRed())
                .colorWhite(visualInspectionEntity.getColorWhite())
                .colorRose(visualInspectionEntity.getColorRose())
                .classification(visualInspectionEntity.getClassification())
                .tastingCard(visualInspectionEntity.getTastingCard())
                .build();
    }

    public List<VisualInspectionOutputDTO> toOutputDTOList(List<VisualInspectionEntity> visualInspectionEntityList){
        return visualInspectionEntityList
                .stream()
                .map(this::toOutputDTO)
                .toList();
    }

    public VisualInspectionOutputDTO toOutputDTOUpdate(VisualInspectionEntity visualInspectionEntity, UUID id,
                                                       VisualInspectionOutputDTO visualInspectionOutputDTO){
        return VisualInspectionOutputDTO.builder()
                .id(id)
                .tastingData(visualInspectionOutputDTO.getTastingData() != null ? visualInspectionOutputDTO.getTastingData() : visualInspectionEntity.getTastingData())
                .wineTasted(visualInspectionOutputDTO.getWineTasted() != null ? visualInspectionOutputDTO.getWineTasted() : visualInspectionEntity.getWineTasted())
                .clarity(visualInspectionOutputDTO.getClarity() != null ? visualInspectionOutputDTO.getClarity()     : visualInspectionEntity.getClarity())
                .brightness(visualInspectionOutputDTO.getBrightness() != null ? visualInspectionOutputDTO.getBrightness() : visualInspectionEntity.getBrightness())
                .viscosity(visualInspectionOutputDTO.getViscosity() != null ? visualInspectionOutputDTO.getViscosity() : visualInspectionEntity.getViscosity())
                .colorRed(visualInspectionOutputDTO.getColorRed() != null ? visualInspectionOutputDTO.getColorRed() : visualInspectionEntity.getColorRed())
                .colorWhite(visualInspectionOutputDTO.getColorWhite() != null ? visualInspectionOutputDTO.getColorWhite() : visualInspectionEntity.getColorWhite())
                .colorRose(visualInspectionOutputDTO.getColorRose() != null ? visualInspectionOutputDTO.getColorRose() : visualInspectionEntity.getColorRose())
                .classification(visualInspectionOutputDTO.getClassification() != null ? visualInspectionOutputDTO.getClassification() : visualInspectionEntity.getClassification())
                .tastingCard(visualInspectionOutputDTO.getTastingCard() != null ? visualInspectionOutputDTO.getTastingCard() : visualInspectionEntity.getTastingCard())
                .build();
    }

}
