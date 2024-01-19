package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.StateOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public StateOutputDTO convertToOutputDTO(StateEntity state) {
        return StateOutputDTO.builder()
                .id(state.getId())
                .stateName(state.getStateName())
                .uf(state.getUf())
                .country(state.getCountry())
                .build();
    }

    public List<StateOutputDTO> convertToOutputDTOList(List<StateEntity> list) {
        return list.stream()
                .map(this::convertToOutputDTO)
                .toList();
    }

    public StateOutputDTO convertToOutputDTOUpdate(StateEntity state, UUID id, StateOutputDTO stateOutputDTO) {
        return StateOutputDTO.builder()
                .id(id)
                .stateName(stateOutputDTO.getStateName() != null ? stateOutputDTO.getStateName() : state.getStateName())
                .uf(stateOutputDTO.getUf() != null ? stateOutputDTO.getUf() : state.getUf())
                .country(stateOutputDTO.getCountry() != null ? stateOutputDTO.getCountry() : state.getCountry())
                .build();
    }
}
