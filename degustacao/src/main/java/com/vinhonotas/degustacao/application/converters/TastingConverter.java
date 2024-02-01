package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TastingConverter {

    public TastingEntity toEntity(TastingInputDTO tastingInputDTO) {
        return TastingEntity.builder()
                .tastingData(tastingInputDTO.getTastingData())
                .tastingType(tastingInputDTO.getTastingType())
                .tastingCards(tastingInputDTO.getTastingCards())
                .build();
    }

    public TastingEntity toEntityUpdate(TastingInputDTO tastingInputDTO, UUID id, TastingEntity tastingEntity) {
        return TastingEntity.builder()
                .id(id)
                .tastingData(tastingInputDTO.getTastingData() != null ? tastingInputDTO.getTastingData() : tastingEntity.getTastingData())
                .tastingType(tastingInputDTO.getTastingType() != null ? tastingInputDTO.getTastingType() : tastingEntity.getTastingType())
                .tastingCards(tastingInputDTO.getTastingCards() != null ? tastingInputDTO.getTastingCards() : tastingEntity.getTastingCards())
                .build();
    }

    public TastingOutputDTO toOutputDTO(TastingEntity tastingEntity) {
        return TastingOutputDTO.builder()
                .id(tastingEntity.getId())
                .tastingData(tastingEntity.getTastingData())
                .tastingType(tastingEntity.getTastingType())
                .tastingCards(tastingEntity.getTastingCards())
                .build();
    }

    public List<TastingOutputDTO> toOutputDTOList(List<TastingEntity> tastingEntityList) {
        return tastingEntityList
                .stream()
                .map(this::toOutputDTO)
                .toList();
    }

    public TastingOutputDTO toOutputDTOUpdate(TastingEntity tastingEntity, UUID id, TastingOutputDTO tastingOutputDTO) {
        return TastingOutputDTO.builder()
                .id(id)
                .tastingData(tastingOutputDTO.getTastingData() != null ? tastingOutputDTO.getTastingData() : tastingEntity.getTastingData())
                .tastingType(tastingOutputDTO.getTastingType() != null ? tastingOutputDTO.getTastingType() : tastingEntity.getTastingType())
                .tastingCards(tastingOutputDTO.getTastingCards() != null ? tastingOutputDTO.getTastingCards() : tastingEntity.getTastingCards())
                .build();
    }

}
