package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import com.vinhonotas.degustacao.domain.enums.EnumClassificationType;
import com.vinhonotas.degustacao.domain.enums.EnumIntensityType;
import com.vinhonotas.degustacao.domain.enums.EnumPersistenceType;
import com.vinhonotas.degustacao.domain.enums.EnumQualityType;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.OlfactoryInspectionOutputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OlfactoryInspectionConverter {

    private final AromasConverter aromasConverter;

    public OlfactoryInspectionEntity toEntity(OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        return OlfactoryInspectionEntity.builder()
                .tastingData(olfactoryInspectionInputDTO.getTastingData())
                .wineTasted(olfactoryInspectionInputDTO.getWineTasted())
                .intensity(EnumConverter.fromString(olfactoryInspectionInputDTO.getIntensity(), EnumIntensityType.class))
                .persistence(EnumConverter.fromString(olfactoryInspectionInputDTO.getPersistence(), EnumPersistenceType.class))
                .quality(EnumConverter.fromString(olfactoryInspectionInputDTO.getQuality(), EnumQualityType.class))
                .aromas(aromasConverter.toEntity(olfactoryInspectionInputDTO.getAromas()))
                .classification(EnumConverter.fromString(olfactoryInspectionInputDTO.getClassification(), EnumClassificationType.class))
                .userreg(olfactoryInspectionInputDTO.getUserreg())
                .dthreg(LocalDateTime.now())
                .useralt(olfactoryInspectionInputDTO.getUseralt())
                .dthalt(olfactoryInspectionInputDTO.getDthalt())
                .build();
    }

    public OlfactoryInspectionEntity toEntityUpdate(OlfactoryInspectionInputDTO olfactoryInspectionInputDTO, UUID id,
                                                    OlfactoryInspectionEntity olfactoryInspectionEntity) {
        return OlfactoryInspectionEntity.builder()
                .id(id)
                .tastingData(olfactoryInspectionInputDTO.getTastingData() != null ? olfactoryInspectionInputDTO
                        .getTastingData() : olfactoryInspectionEntity.getTastingData())
                .wineTasted(olfactoryInspectionInputDTO.getWineTasted() != null ? olfactoryInspectionInputDTO
                        .getWineTasted() : olfactoryInspectionEntity.getWineTasted())
                .intensity(olfactoryInspectionInputDTO.getIntensity() != null ? EnumConverter.fromString(
                        olfactoryInspectionInputDTO.getIntensity(), EnumIntensityType.class) : olfactoryInspectionEntity.getIntensity())
                .persistence(olfactoryInspectionInputDTO.getPersistence() != null ? EnumConverter.fromString(
                        olfactoryInspectionInputDTO.getPersistence(), EnumPersistenceType.class) : olfactoryInspectionEntity.getPersistence())
                .quality(olfactoryInspectionInputDTO.getQuality() != null ? EnumConverter.fromString(
                        olfactoryInspectionInputDTO.getQuality(), EnumQualityType.class) : olfactoryInspectionEntity.getQuality())
                .aromas(olfactoryInspectionInputDTO.getAromas() != null ? aromasConverter.toEntity(
                        olfactoryInspectionInputDTO.getAromas()) : olfactoryInspectionEntity.getAromas())
                .classification(olfactoryInspectionInputDTO.getClassification() != null ? EnumConverter.fromString(
                        olfactoryInspectionInputDTO.getClassification(), EnumClassificationType.class) :
                        olfactoryInspectionEntity.getClassification())
                .userreg(olfactoryInspectionEntity.getUserreg() != null ? olfactoryInspectionEntity.getUserreg() :
                        olfactoryInspectionInputDTO.getUserreg())
                .dthreg(olfactoryInspectionEntity.getDthreg() != null ? olfactoryInspectionEntity.getDthreg() :
                        olfactoryInspectionInputDTO.getDthreg())
                .useralt(olfactoryInspectionInputDTO.getUseralt() != null ? olfactoryInspectionInputDTO.getUseralt() :
                        olfactoryInspectionEntity.getUseralt())
                .dthalt(LocalDateTime.now())
                .build();
    }

    public OlfactoryInspectionOutputDTO toOutputDTO(OlfactoryInspectionEntity olfactoryInspectionEntity) {
        return OlfactoryInspectionOutputDTO.builder()
                .id(olfactoryInspectionEntity.getId())
                .tastingData(olfactoryInspectionEntity.getTastingData())
                .wineTasted(olfactoryInspectionEntity.getWineTasted())
                .intensity(EnumConverter.toString(olfactoryInspectionEntity.getIntensity()))
                .persistence(EnumConverter.toString(olfactoryInspectionEntity.getPersistence()))
                .quality(EnumConverter.toString(olfactoryInspectionEntity.getQuality()))
                .aromas(aromasConverter.toOutputDTO(olfactoryInspectionEntity.getAromas()))
                .classification(EnumConverter.toString(olfactoryInspectionEntity.getClassification()))
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
                .tastingData(olfactoryInspectionOutputDTO.getTastingData() != null ? olfactoryInspectionOutputDTO
                        .getTastingData() : olfactoryInspectionEntity.getTastingData())
                .wineTasted(olfactoryInspectionOutputDTO.getWineTasted() != null ? olfactoryInspectionOutputDTO
                        .getWineTasted() : olfactoryInspectionEntity.getWineTasted())
                .intensity(olfactoryInspectionOutputDTO.getIntensity() != null ? olfactoryInspectionOutputDTO
                        .getIntensity() : EnumConverter.toString(olfactoryInspectionEntity.getIntensity()))
                .persistence(olfactoryInspectionOutputDTO.getPersistence() != null ? olfactoryInspectionOutputDTO
                        .getPersistence() : EnumConverter.toString(olfactoryInspectionEntity.getPersistence()))
                .quality(olfactoryInspectionOutputDTO.getQuality() != null ? olfactoryInspectionOutputDTO.getQuality() :
                        EnumConverter.toString(olfactoryInspectionEntity.getQuality()))
                .aromas(olfactoryInspectionOutputDTO.getAromas() != null ? olfactoryInspectionOutputDTO.getAromas() :
                        aromasConverter.toOutputDTO(olfactoryInspectionEntity.getAromas()))
                .classification(olfactoryInspectionOutputDTO.getClassification() != null ? olfactoryInspectionOutputDTO
                        .getClassification() : EnumConverter.toString(olfactoryInspectionEntity.getClassification()))
                .build();
    }

}
