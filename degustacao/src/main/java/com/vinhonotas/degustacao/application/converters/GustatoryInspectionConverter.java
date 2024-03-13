package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.GustatoryInspectionOutputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class GustatoryInspectionConverter {

    public GustatoryInspectionEntity toEntity(GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        return GustatoryInspectionEntity.builder()
                .tastingData(gustatoryInspectionInputDTO.getTastingData())
                .wineTasted(gustatoryInspectionInputDTO.getWineTasted())
                .body(EnumConverter.fromString(gustatoryInspectionInputDTO.getBody(), EnumBodyType.class))
                .sweetness(EnumConverter.fromString(gustatoryInspectionInputDTO.getSweetness(), EnumSweetnessType.class))
                .tannin(EnumConverter.fromString(gustatoryInspectionInputDTO.getTannin(), EnumTanninType.class))
                .classification(EnumConverter.fromString(gustatoryInspectionInputDTO.getClassification(), EnumClassificationType.class))
                .acidity(EnumConverter.fromString(gustatoryInspectionInputDTO.getAcidity(), EnumAcidityType.class))
                .alcohol(EnumConverter.fromString(gustatoryInspectionInputDTO.getAlcohol(), EnumAlcoholType.class))
                .persistence(EnumConverter.fromString(gustatoryInspectionInputDTO.getPersistence(), EnumPersistenceType.class))
                .maturity(EnumConverter.fromString(gustatoryInspectionInputDTO.getMaturity(), EnumMaturityType.class))
                .typicality(EnumConverter.fromString(gustatoryInspectionInputDTO.getTypicality(), EnumTypicalityType.class))
                .userreg(gustatoryInspectionInputDTO.getUserreg())
                .dthreg(LocalDateTime.now())
                .useralt(gustatoryInspectionInputDTO.getUseralt())
                .dthalt(gustatoryInspectionInputDTO.getDthalt())
                .build();
    }

    public GustatoryInspectionEntity toEntityUpdate(GustatoryInspectionInputDTO gustatoryInspectionInputDTO, UUID id,
                                                    GustatoryInspectionEntity gustatoryInspectionEntity) {
        return GustatoryInspectionEntity.builder()
                .id(id)
                .tastingData(gustatoryInspectionInputDTO.getTastingData() != null ? gustatoryInspectionInputDTO
                        .getTastingData() : gustatoryInspectionEntity.getTastingData())
                .wineTasted(gustatoryInspectionInputDTO.getWineTasted() != null ? gustatoryInspectionInputDTO
                        .getWineTasted() : gustatoryInspectionEntity.getWineTasted())
                .body(gustatoryInspectionInputDTO.getBody() != null ? EnumConverter.fromString(gustatoryInspectionInputDTO
                        .getBody(), EnumBodyType.class) : gustatoryInspectionEntity.getBody())
                .sweetness(gustatoryInspectionInputDTO.getSweetness() != null ? EnumConverter.fromString(gustatoryInspectionInputDTO
                        .getSweetness(), EnumSweetnessType.class) : gustatoryInspectionEntity.getSweetness())
                .tannin(gustatoryInspectionInputDTO.getTannin() != null ? EnumConverter.fromString(gustatoryInspectionInputDTO
                        .getTannin(), EnumTanninType.class) : gustatoryInspectionEntity.getTannin())
                .classification(gustatoryInspectionInputDTO.getClassification() != null ? EnumConverter.fromString(
                        gustatoryInspectionInputDTO.getClassification(), EnumClassificationType.class) :
                        gustatoryInspectionEntity.getClassification())
                .acidity(gustatoryInspectionInputDTO.getAcidity() != null ? EnumConverter.fromString(
                        gustatoryInspectionInputDTO.getAcidity(), EnumAcidityType.class) : gustatoryInspectionEntity.getAcidity())
                .alcohol(gustatoryInspectionInputDTO.getAlcohol() != null ? EnumConverter.fromString(gustatoryInspectionInputDTO
                        .getAlcohol(), EnumAlcoholType.class) : gustatoryInspectionEntity.getAlcohol())
                .persistence(gustatoryInspectionInputDTO.getPersistence() != null ? EnumConverter.fromString(
                        gustatoryInspectionInputDTO.getPersistence(), EnumPersistenceType.class) : gustatoryInspectionEntity.getPersistence())
                .maturity(gustatoryInspectionInputDTO.getMaturity() != null ? EnumConverter.fromString(
                        gustatoryInspectionInputDTO.getMaturity(), EnumMaturityType.class) : gustatoryInspectionEntity.getMaturity())
                .typicality(gustatoryInspectionInputDTO.getTypicality() != null ? EnumConverter.fromString(
                        gustatoryInspectionInputDTO.getTypicality(), EnumTypicalityType.class) : gustatoryInspectionEntity.getTypicality())
                .userreg(gustatoryInspectionInputDTO.getUserreg() != null ? gustatoryInspectionInputDTO.getUserreg() :
                        gustatoryInspectionEntity.getUserreg())
                .dthreg(gustatoryInspectionInputDTO.getDthreg() != null ? gustatoryInspectionInputDTO.getDthreg() :
                        gustatoryInspectionEntity.getDthreg())
                .useralt(gustatoryInspectionInputDTO.getUseralt() != null ? gustatoryInspectionInputDTO.getUseralt() :
                        gustatoryInspectionEntity.getUseralt())
                .dthalt(LocalDateTime.now())
                .build();
    }

    public GustatoryInspectionOutputDTO toOutputDTO(GustatoryInspectionEntity gustatoryInspectionEntity) {
        return GustatoryInspectionOutputDTO.builder()
                .id(gustatoryInspectionEntity.getId())
                .tastingData(gustatoryInspectionEntity.getTastingData())
                .wineTasted(gustatoryInspectionEntity.getWineTasted())
                .body(EnumConverter.toString(gustatoryInspectionEntity.getBody()))
                .sweetness(EnumConverter.toString(gustatoryInspectionEntity.getSweetness()))
                .tannin(EnumConverter.toString(gustatoryInspectionEntity.getTannin()))
                .classification(EnumConverter.toString(gustatoryInspectionEntity.getClassification()))
                .acidity(EnumConverter.toString(gustatoryInspectionEntity.getAcidity()))
                .alcohol(EnumConverter.toString(gustatoryInspectionEntity.getAlcohol()))
                .persistence(EnumConverter.toString(gustatoryInspectionEntity.getPersistence()))
                .maturity(EnumConverter.toString(gustatoryInspectionEntity.getMaturity()))
                .typicality(EnumConverter.toString(gustatoryInspectionEntity.getTypicality()))
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
                .tastingData(gustatoryInspectionOutputDTO.getTastingData() != null ? gustatoryInspectionOutputDTO
                        .getTastingData() : gustatoryInspectionEntity.getTastingData())
                .wineTasted(gustatoryInspectionOutputDTO.getWineTasted() != null ? gustatoryInspectionOutputDTO
                        .getWineTasted() : gustatoryInspectionEntity.getWineTasted())
                .body(gustatoryInspectionOutputDTO.getBody() != null ? gustatoryInspectionOutputDTO.getBody() :
                        EnumConverter.toString(gustatoryInspectionEntity.getBody()))
                .sweetness(gustatoryInspectionOutputDTO.getSweetness() != null ? gustatoryInspectionOutputDTO.getSweetness() :
                        EnumConverter.toString(gustatoryInspectionEntity.getSweetness()))
                .tannin(gustatoryInspectionOutputDTO.getTannin() != null ? gustatoryInspectionOutputDTO.getTannin() :
                        EnumConverter.toString(gustatoryInspectionEntity.getTannin()))
                .classification(gustatoryInspectionOutputDTO.getClassification() != null ? gustatoryInspectionOutputDTO
                        .getClassification() : EnumConverter.toString(gustatoryInspectionEntity.getClassification()))
                .acidity(gustatoryInspectionOutputDTO.getAcidity() != null ? gustatoryInspectionOutputDTO.getAcidity() :
                        EnumConverter.toString(gustatoryInspectionEntity.getAcidity()))
                .alcohol(gustatoryInspectionOutputDTO.getAlcohol() != null ? gustatoryInspectionOutputDTO.getAlcohol() :
                        EnumConverter.toString(gustatoryInspectionEntity.getAlcohol()))
                .persistence(gustatoryInspectionOutputDTO.getPersistence() != null ? gustatoryInspectionOutputDTO
                        .getPersistence() : EnumConverter.toString(gustatoryInspectionEntity.getPersistence()))
                .maturity(gustatoryInspectionOutputDTO.getMaturity() != null ? gustatoryInspectionOutputDTO.getMaturity() :
                        EnumConverter.toString(gustatoryInspectionEntity.getMaturity()))
                .typicality(gustatoryInspectionOutputDTO.getTypicality() != null ? gustatoryInspectionOutputDTO
                        .getTypicality() : EnumConverter.toString(gustatoryInspectionEntity.getTypicality()))
                .build();
    }

}
