package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.StateOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StateConverter {

    private final CountryConverter countryConverter;

    public StateEntity convertToEntity(StateInputDTO stateInputDTO) {
        return StateEntity.builder()
                .id(UUID.fromString(stateInputDTO.getId()))
                .stateName(stateInputDTO.getStateName())
                .uf(stateInputDTO.getUf())
                .country(countryConverter.convertToEntity(stateInputDTO.getCountry()))
                .userreg(stateInputDTO.getUserreg())
                .dthreg(LocalDateTime.now())
                .useralt(stateInputDTO.getUseralt())
                .dthalt(stateInputDTO.getDthalt())
                .build();
    }

    public StateEntity convertToEntityUpdate(StateEntity entity, UUID id, StateInputDTO stateInputDTO) {
        return StateEntity.builder()
                .id(id)
                .stateName(stateInputDTO.getStateName() != null ? stateInputDTO.getStateName() : entity.getStateName())
                .uf(stateInputDTO.getUf() != null ? stateInputDTO.getUf() : entity.getUf())
                .country(stateInputDTO.getCountry() != null ?
                        countryConverter.convertToEntity(stateInputDTO.getCountry()) : entity.getCountry())
                .userreg(stateInputDTO.getUserreg() != null ? stateInputDTO.getUserreg() : entity.getUserreg())
                .dthreg(stateInputDTO.getDthreg() != null ? stateInputDTO.getDthreg() : entity.getDthreg())
                .useralt(stateInputDTO.getUseralt() != null ? stateInputDTO.getUseralt() : entity.getUseralt())
                .dthalt(LocalDateTime.now())
                .build();
    }

    public StateOutputDTO convertToOutputDTO(StateEntity state) {
        return StateOutputDTO.builder()
                .id(state.getId())
                .stateName(state.getStateName())
                .uf(state.getUf())
                .country(countryConverter.convertToOutputDTO(state.getCountry()))
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
                .country(stateOutputDTO.getCountry() != null ? stateOutputDTO.getCountry()
                        : countryConverter.convertToOutputDTO(state.getCountry()))
                .build();
    }

    public StateInputDTO convertToInputDTO(StateEntity state) {
        return StateInputDTO.builder()
                .id(state.getId().toString())
                .stateName(state.getStateName())
                .uf(state.getUf())
                .country(countryConverter.convertToInputDTO(state.getCountry()))
                .build();
    }

}
