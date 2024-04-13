package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingCardOutputDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TastingCardConverter {

    private final ModelMapper modelMapper;

    public TastingCardEntity toEntity(TastingCardInputDTO tastingCardInputDTO) {
        return modelMapper.map(tastingCardInputDTO, TastingCardEntity.class);
    }

    public TastingCardInputDTO toInputDTO(TastingCardEntity tastingCardEntity) {
        return modelMapper.map(tastingCardEntity, TastingCardInputDTO.class);
    }

    public TastingCardOutputDTO toOutputDTO(TastingCardEntity tastingCardEntity) {
        return modelMapper.map(tastingCardEntity, TastingCardOutputDTO.class);
    }


    public TastingCardEntity toEntityUpdate(TastingCardInputDTO inputDTO, UUID id) {
        TastingCardEntity mapped = modelMapper.map(inputDTO, TastingCardEntity.class);
        mapped.setId(id);
        mapped.setUseralt("userAlt");
        mapped.setDthalt(LocalDateTime.now());
        return mapped;
    }
}
