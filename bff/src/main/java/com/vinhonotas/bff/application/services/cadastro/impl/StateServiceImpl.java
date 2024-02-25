package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.cadastro.StateService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.cadastro.StateClient;
import com.vinhonotas.bff.interfaces.dtos.outputs.StateOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateClient stateClient;

    @Override
    public List<StateOutputDTO> getAllStates() {
        List<StateOutputDTO> states = stateClient.getAllStates();
        if (states.isEmpty()) {
            throw new BadRequestException(MessagesConstants.STATES_NOT_FOUND);
        }
        return states;
    }

    @Override
    public StateOutputDTO getStateById(String id) {
        StateOutputDTO state = stateClient.getStateById(id);
        if (Objects.isNull(state)) {
            throw new BadRequestException(MessagesConstants.STATES_NOT_FOUND);
        }
        return state;
    }

    @Override
    public StateOutputDTO getStateByName(String name) {
        StateOutputDTO state = stateClient.getStateByName(name);
        if (Objects.isNull(state)) {
            throw new BadRequestException(MessagesConstants.STATES_NOT_FOUND);
        }
        return state;
    }

    @Override
    public StateOutputDTO getStateByUf(String uf) {
        StateOutputDTO state = stateClient.getStateByUf(uf);
        if (Objects.isNull(state)) {
            throw new BadRequestException(MessagesConstants.STATES_NOT_FOUND);
        }
        return state;
    }

}
