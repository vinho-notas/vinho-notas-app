package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.EnumPointScale;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingCardOutputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TastingCardConverter {

    private final VisualInspectionConverter visualInspectionConverter;
    private final OlfactoryInspectionConverter olfactoryInspectionConverter;
    private final GustatoryInspectionConverter gustatoryInspectionConverter;

    public TastingCardEntity toEntity(TastingCardInputDTO tastingCardInputDTO) {
        return TastingCardEntity.builder()
                .tastingData(tastingCardInputDTO.getTastingData())
                .wineTasted(tastingCardInputDTO.getWineTasted())
                .harvest(tastingCardInputDTO.getHarvest())
                .grapes(tastingCardInputDTO.getGrapes())
                .country(tastingCardInputDTO.getCountry())
                .region(tastingCardInputDTO.getRegion())
                .visualInspection(visualInspectionConverter.toEntity(tastingCardInputDTO.getVisualInspection()))
                .olfactoryInspection(olfactoryInspectionConverter.toEntity(tastingCardInputDTO.getOlfactoryInspection()))
                .gustatoryInspection(gustatoryInspectionConverter.toEntity(tastingCardInputDTO.getGustatoryInspection()))
                .opinion(tastingCardInputDTO.getOpinion())
                .pointScale(EnumConverter.fromString(tastingCardInputDTO.getPointScale(), EnumPointScale.class))
                .build();
    }

    public TastingCardEntity toEntityUpdate(TastingCardInputDTO tastingCardInputDTO, UUID id, TastingCardEntity tastingCardEntity) {
        return TastingCardEntity.builder()
                .id(id)
                .tastingData(tastingCardInputDTO.getTastingData() != null ? tastingCardInputDTO.getTastingData() : tastingCardEntity.getTastingData())
                .wineTasted(tastingCardInputDTO.getWineTasted() != null ? tastingCardInputDTO.getWineTasted() : tastingCardEntity.getWineTasted())
                .harvest(tastingCardInputDTO.getHarvest() != null ? tastingCardInputDTO.getHarvest() : tastingCardEntity.getHarvest())
                .grapes(tastingCardInputDTO.getGrapes() != null ? tastingCardInputDTO.getGrapes() : tastingCardEntity.getGrapes())
                .country(tastingCardInputDTO.getCountry() != null ? tastingCardInputDTO.getCountry() : tastingCardEntity.getCountry())
                .region(tastingCardInputDTO.getRegion() != null ? tastingCardInputDTO.getRegion() : tastingCardEntity.getRegion())
                .visualInspection(tastingCardInputDTO.getVisualInspection() != null ? visualInspectionConverter.toEntity(
                        tastingCardInputDTO.getVisualInspection()) : tastingCardEntity.getVisualInspection())
                .olfactoryInspection(tastingCardInputDTO.getOlfactoryInspection() != null ? olfactoryInspectionConverter.toEntity(
                        tastingCardInputDTO.getOlfactoryInspection()) : tastingCardEntity.getOlfactoryInspection())
                .gustatoryInspection(tastingCardInputDTO.getGustatoryInspection() != null ? gustatoryInspectionConverter.toEntity(
                        tastingCardInputDTO.getGustatoryInspection()) : tastingCardEntity.getGustatoryInspection())
                .opinion(tastingCardInputDTO.getOpinion() != null ? tastingCardInputDTO.getOpinion() : tastingCardEntity.getOpinion())
                .pointScale(tastingCardInputDTO.getPointScale() != null ? EnumConverter.fromString(tastingCardInputDTO
                        .getPointScale(), EnumPointScale.class) : tastingCardEntity.getPointScale())
                .build();
    }

    public TastingCardOutputDTO toOutputDTO(TastingCardEntity tastingCardEntity) {
        return TastingCardOutputDTO.builder()
                .id(tastingCardEntity.getId())
                .tastingData(tastingCardEntity.getTastingData())
                .wineTasted(tastingCardEntity.getWineTasted())
                .harvest(tastingCardEntity.getHarvest())
                .grapes(tastingCardEntity.getGrapes())
                .country(tastingCardEntity.getCountry())
                .region(tastingCardEntity.getRegion())
                .visualInspection(tastingCardEntity.getVisualInspection())
                .olfactoryInspection(tastingCardEntity.getOlfactoryInspection())
                .gustatoryInspection(tastingCardEntity.getGustatoryInspection())
                .opinion(tastingCardEntity.getOpinion())
                .pointScale(tastingCardEntity.getPointScale())
                .tasting(tastingCardEntity.getTasting())
                .build();
    }

    public List<TastingCardOutputDTO> toOutputDTOList(List<TastingCardEntity> tastingCardEntityList) {
        return tastingCardEntityList
                .stream()
                .map(this::toOutputDTO)
                .toList();
    }

    public Set<TastingCardEntity> toEntitySet(Set<TastingCardInputDTO> tastingCardInputDTOList) {
        return tastingCardInputDTOList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    public TastingCardOutputDTO toOutputDTOUpdate(TastingCardEntity tastingCardEntity, UUID id, TastingCardOutputDTO tastingCardOutputDTO) {
        return TastingCardOutputDTO.builder()
                .id(id)
                .tastingData(tastingCardOutputDTO.getTastingData() != null ? tastingCardOutputDTO.getTastingData() : tastingCardEntity.getTastingData())
                .wineTasted(tastingCardOutputDTO.getWineTasted() != null ? tastingCardOutputDTO.getWineTasted() : tastingCardEntity.getWineTasted())
                .harvest(tastingCardOutputDTO.getHarvest() != null ? tastingCardOutputDTO.getHarvest() : tastingCardEntity.getHarvest())
                .grapes(tastingCardOutputDTO.getGrapes() != null ? tastingCardOutputDTO.getGrapes() : tastingCardEntity.getGrapes())
                .country(tastingCardOutputDTO.getCountry() != null ? tastingCardOutputDTO.getCountry() : tastingCardEntity.getCountry())
                .region(tastingCardOutputDTO.getRegion() != null ? tastingCardOutputDTO.getRegion() : tastingCardEntity.getRegion())
                .visualInspection(tastingCardOutputDTO.getVisualInspection() != null ? tastingCardOutputDTO
                        .getVisualInspection() : tastingCardEntity.getVisualInspection())
                .olfactoryInspection(tastingCardOutputDTO.getOlfactoryInspection() != null ? tastingCardOutputDTO
                        .getOlfactoryInspection() : tastingCardEntity.getOlfactoryInspection())
                .gustatoryInspection(tastingCardOutputDTO.getGustatoryInspection() != null ? tastingCardOutputDTO
                        .getGustatoryInspection() : tastingCardEntity.getGustatoryInspection())
                .opinion(tastingCardOutputDTO.getOpinion() != null ? tastingCardOutputDTO.getOpinion() : tastingCardEntity.getOpinion())
                .pointScale(tastingCardOutputDTO.getPointScale() != null ? tastingCardOutputDTO.getPointScale() : tastingCardEntity.getPointScale())
                .tasting(tastingCardOutputDTO.getTasting() != null ? tastingCardOutputDTO.getTasting() : tastingCardEntity.getTasting())
                .build();
    }

}
