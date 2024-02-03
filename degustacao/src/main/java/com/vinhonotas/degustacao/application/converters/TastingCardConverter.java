package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingCardOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TastingCardConverter {

    public TastingCardEntity toEntity(TastingCardInputDTO tastingCardInputDTO) {
        return TastingCardEntity.builder()
                .tastingData(tastingCardInputDTO.getTastingData())
                .wineTasted(tastingCardInputDTO.getWineTasted())
                .harvest(tastingCardInputDTO.getHarvest())
                .grapes(tastingCardInputDTO.getGrapes())
                .country(tastingCardInputDTO.getCountry())
                .region(tastingCardInputDTO.getRegion())
                .visualInspection(tastingCardInputDTO.getVisualInspection())
                .olfactoryInspection(tastingCardInputDTO.getOlfactoryInspection())
                .gustatoryInspection(tastingCardInputDTO.getGustatoryInspection())
                .opinion(tastingCardInputDTO.getOpinion())
                .pointScale(tastingCardInputDTO.getPointScale())
                .tasting(tastingCardInputDTO.getTasting())
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
                .visualInspection(tastingCardInputDTO.getVisualInspection() != null ? tastingCardInputDTO.getVisualInspection() : tastingCardEntity.getVisualInspection())
                .olfactoryInspection(tastingCardInputDTO.getOlfactoryInspection() != null ? tastingCardInputDTO.getOlfactoryInspection() : tastingCardEntity.getOlfactoryInspection())
                .gustatoryInspection(tastingCardInputDTO.getGustatoryInspection() != null ? tastingCardInputDTO.getGustatoryInspection() : tastingCardEntity.getGustatoryInspection())
                .opinion(tastingCardInputDTO.getOpinion() != null ? tastingCardInputDTO.getOpinion() : tastingCardEntity.getOpinion())
                .pointScale(tastingCardInputDTO.getPointScale() != null ? tastingCardInputDTO.getPointScale() : tastingCardEntity.getPointScale())
                .tasting(tastingCardInputDTO.getTasting() != null ? tastingCardInputDTO.getTasting() : tastingCardEntity.getTasting())
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

    public TastingCardOutputDTO toOutputDTOUpdate(TastingCardEntity tastingCardEntity, UUID id, TastingCardOutputDTO tastingCardOutputDTO) {
        return TastingCardOutputDTO.builder()
                .id(id)
                .tastingData(tastingCardOutputDTO.getTastingData() != null ? tastingCardOutputDTO.getTastingData() : tastingCardEntity.getTastingData())
                .wineTasted(tastingCardOutputDTO.getWineTasted() != null ? tastingCardOutputDTO.getWineTasted() : tastingCardEntity.getWineTasted())
                .harvest(tastingCardOutputDTO.getHarvest() != null ? tastingCardOutputDTO.getHarvest() : tastingCardEntity.getHarvest())
                .grapes(tastingCardOutputDTO.getGrapes() != null ? tastingCardOutputDTO.getGrapes() : tastingCardEntity.getGrapes())
                .country(tastingCardOutputDTO.getCountry() != null ? tastingCardOutputDTO.getCountry() : tastingCardEntity.getCountry())
                .region(tastingCardOutputDTO.getRegion() != null ? tastingCardOutputDTO.getRegion() : tastingCardEntity.getRegion())
                .visualInspection(tastingCardOutputDTO.getVisualInspection() != null ? tastingCardOutputDTO.getVisualInspection() : tastingCardEntity.getVisualInspection())
                .olfactoryInspection(tastingCardOutputDTO.getOlfactoryInspection() != null ? tastingCardOutputDTO.getOlfactoryInspection() : tastingCardEntity.getOlfactoryInspection())
                .gustatoryInspection(tastingCardOutputDTO.getGustatoryInspection() != null ? tastingCardOutputDTO.getGustatoryInspection() : tastingCardEntity.getGustatoryInspection())
                .opinion(tastingCardOutputDTO.getOpinion() != null ? tastingCardOutputDTO.getOpinion() : tastingCardEntity.getOpinion())
                .pointScale(tastingCardOutputDTO.getPointScale() != null ? tastingCardOutputDTO.getPointScale() : tastingCardEntity.getPointScale())
                .tasting(tastingCardOutputDTO.getTasting() != null ? tastingCardOutputDTO.getTasting() : tastingCardEntity.getTasting())
                .build();
    }

}
