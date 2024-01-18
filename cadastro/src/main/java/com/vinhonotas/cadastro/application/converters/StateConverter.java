package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StateConverter {

    public StateEntity toEntity(StateInputDTO stateInputDTO) {
        return StateEntity.builder()
                .stateName(stateInputDTO.getStateName())
                .uf(stateInputDTO.getUf())
                .country(stateInputDTO.getCountry())
                .build();
    }

    public StateEntity toEntityUpdate(StateEntity entity, UUID id, StateInputDTO stateInputDTO) {
        return StateEntity.builder()
                .id(id)
                .stateName(stateInputDTO.getStateName() != null ? stateInputDTO.getStateName() : entity.getStateName())
                .uf(stateInputDTO.getUf() != null ? stateInputDTO.getUf() : entity.getUf())
                .country(stateInputDTO.getCountry() != null ? stateInputDTO.getCountry() : entity.getCountry())
                .build();
    }
}
