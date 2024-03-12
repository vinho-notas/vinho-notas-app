package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.VisualInspectionOutputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class VisualInspectionConverter {

    public VisualInspectionEntity toEntity(VisualInspectionInputDTO visualInspectionInputDTO){
        return VisualInspectionEntity.builder()
                .tastingData(visualInspectionInputDTO.getTastingData())
                .wineTasted(visualInspectionInputDTO.getWineTasted())
                .clarity(EnumConverter.fromString(visualInspectionInputDTO.getClarity(), EnumClarityType.class))
                .brightness(EnumConverter.fromString(visualInspectionInputDTO.getBrightness(), EnumBrightnessType.class))
                .viscosity(EnumConverter.fromString(visualInspectionInputDTO.getViscosity(), EnumViscosityType.class))
                .colorRed(EnumConverter.fromString(visualInspectionInputDTO.getColorRed(), EnumRedColorType.class))
                .colorWhite(EnumConverter.fromString(visualInspectionInputDTO.getColorWhite(), EnumWhiteColorType.class))
                .colorRose(EnumConverter.fromString(visualInspectionInputDTO.getColorRose(), EnumRoseColorType.class))
                .classification(EnumConverter.fromString(visualInspectionInputDTO.getClassification(), EnumClassificationType.class))
                .build();
    }

    public VisualInspectionEntity toEntityUpdate(VisualInspectionInputDTO visualInspectionInputDTO, UUID id, VisualInspectionEntity visualInspectionEntity){
        return VisualInspectionEntity.builder()
                .id(id)
                .tastingData(visualInspectionInputDTO.getTastingData() != null ? visualInspectionInputDTO.getTastingData()
                        : visualInspectionEntity.getTastingData())
                .wineTasted(visualInspectionInputDTO.getWineTasted() != null ? visualInspectionInputDTO.getWineTasted()
                        : visualInspectionEntity.getWineTasted())
                .clarity(visualInspectionInputDTO.getClarity() != null ? EnumConverter.fromString(visualInspectionInputDTO
                        .getClarity(), EnumClarityType.class) : visualInspectionEntity.getClarity())
                .brightness(visualInspectionInputDTO.getBrightness() != null ? EnumConverter.fromString(visualInspectionInputDTO
                        .getBrightness(), EnumBrightnessType.class) : visualInspectionEntity.getBrightness())
                .viscosity(visualInspectionInputDTO.getViscosity() != null ? EnumConverter.fromString(visualInspectionInputDTO
                        .getViscosity(), EnumViscosityType.class) : visualInspectionEntity.getViscosity())
                .colorRed(visualInspectionInputDTO.getColorRed() != null ? EnumConverter.fromString(visualInspectionInputDTO
                        .getColorRed(), EnumRedColorType.class) : visualInspectionEntity.getColorRed())
                .colorWhite(visualInspectionInputDTO.getColorWhite() != null ? EnumConverter.fromString(visualInspectionInputDTO
                        .getColorWhite(), EnumWhiteColorType.class) : visualInspectionEntity.getColorWhite())
                .colorRose(visualInspectionInputDTO.getColorRose() != null ? EnumConverter.fromString(visualInspectionInputDTO
                        .getColorRose(), EnumRoseColorType.class) : visualInspectionEntity.getColorRose())
                .classification(visualInspectionInputDTO.getClassification() != null ? EnumConverter.fromString(
                        visualInspectionInputDTO.getClassification(), EnumClassificationType.class) : visualInspectionEntity
                        .getClassification())
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
                .tastingData(visualInspectionOutputDTO.getTastingData() != null ? visualInspectionOutputDTO.getTastingData() :
                        visualInspectionEntity.getTastingData())
                .wineTasted(visualInspectionOutputDTO.getWineTasted() != null ? visualInspectionOutputDTO.getWineTasted() :
                        visualInspectionEntity.getWineTasted())
                .clarity(visualInspectionOutputDTO.getClarity() != null ? visualInspectionOutputDTO.getClarity() :
                        visualInspectionEntity.getClarity())
                .brightness(visualInspectionOutputDTO.getBrightness() != null ? visualInspectionOutputDTO.getBrightness() :
                        visualInspectionEntity.getBrightness())
                .viscosity(visualInspectionOutputDTO.getViscosity() != null ? visualInspectionOutputDTO.getViscosity() :
                        visualInspectionEntity.getViscosity())
                .colorRed(visualInspectionOutputDTO.getColorRed() != null ? visualInspectionOutputDTO.getColorRed() :
                        visualInspectionEntity.getColorRed())
                .colorWhite(visualInspectionOutputDTO.getColorWhite() != null ? visualInspectionOutputDTO.getColorWhite() :
                        visualInspectionEntity.getColorWhite())
                .colorRose(visualInspectionOutputDTO.getColorRose() != null ? visualInspectionOutputDTO.getColorRose() :
                        visualInspectionEntity.getColorRose())
                .classification(visualInspectionOutputDTO.getClassification() != null ? visualInspectionOutputDTO.getClassification() :
                        visualInspectionEntity.getClassification())
                .build();
    }

}
