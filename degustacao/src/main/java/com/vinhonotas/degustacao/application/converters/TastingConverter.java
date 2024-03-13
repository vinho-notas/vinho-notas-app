package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import com.vinhonotas.degustacao.domain.enums.EnumTastingType;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingOutputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TastingConverter {

private final TastingCardConverter tastingCardConverter;

    public TastingEntity toEntityUpdate(TastingInputDTO tastingInputDTO, UUID id, TastingEntity tastingEntity) {
        return TastingEntity.builder()
                .id(id)
                .tastingData(tastingInputDTO.getTastingData() != null ? tastingInputDTO.getTastingData() : tastingEntity
                        .getTastingData())
                .tastingType(tastingInputDTO.getTastingType() != null ? EnumConverter.fromString(tastingInputDTO
                        .getTastingType(), EnumTastingType.class) : tastingEntity.getTastingType())
                .tastingCards(tastingInputDTO.getTastingCards() != null ? tastingCardConverter
                        .toEntitySet(tastingInputDTO.getTastingCards()) : tastingEntity.getTastingCards())

                .userreg(tastingInputDTO.getUserreg() != null ? tastingInputDTO.getUserreg() : tastingEntity.getUserreg())
                .dthreg(tastingInputDTO.getDthreg() != null ? tastingInputDTO.getDthreg() : tastingEntity.getDthreg())
                .useralt(tastingInputDTO.getUseralt() != null ? tastingInputDTO.getUseralt() : tastingEntity.getUseralt())
                .dthalt(LocalDateTime.now())
                .build();
    }

    public TastingEntity toEntity(TastingInputDTO tastingInputDTO) {
        return TastingEntity.builder()
                .tastingData(tastingInputDTO.getTastingData())
                .tastingType(EnumConverter.fromString(tastingInputDTO.getTastingType(), EnumTastingType.class))
                .tastingCards(tastingCardConverter.toEntitySet(tastingInputDTO.getTastingCards()))
                .userreg(tastingInputDTO.getUserreg())
                .dthreg(LocalDateTime.now())
                .useralt(tastingInputDTO.getUseralt())
                .dthalt(tastingInputDTO.getDthalt())
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
