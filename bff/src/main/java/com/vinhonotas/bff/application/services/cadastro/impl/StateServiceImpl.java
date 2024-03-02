package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.cadastro.StateService;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.cadastro.StateClient;
import com.vinhonotas.bff.interfaces.dtos.outputs.StateOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StateServiceImpl implements StateService {

    private final StateClient stateClient;

    @Override
    public List<StateOutputDTO> getAllStates() {
        log.info("getAllStates :: Listando todos os estados");
        List<StateOutputDTO> states = stateClient.getAllStates();
        if (states.isEmpty()) {
            log.error("getAllStates :: Ocorreu um erro ao listar os estados: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return states;
    }

    @Override
    public StateOutputDTO getStateById(String id) {
        log.info("getStateById :: Buscando estado pelo id: {}", id);
        StateOutputDTO state = stateClient.getStateById(id);
        if (Objects.isNull(state)) {
            log.error("getStateById :: Ocorreu um erro ao buscar o estado: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return state;
    }

    @Override
    public StateOutputDTO getStateByName(String name) {
        log.info("getStateByName :: Buscando estado pelo nome: {}", name);
        StateOutputDTO state = stateClient.getStateByName(name);
        if (Objects.isNull(state)) {
            log.error("getStateByName :: Ocorreu um erro ao buscar o estado: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return state;
    }

    @Override
    public StateOutputDTO getStateByUf(String uf) {
        log.info("getStateByUf :: Buscando estado pela sigla: {}", uf);
        StateOutputDTO state = stateClient.getStateByUf(uf);
        if (Objects.isNull(state)) {
            log.error("getStateByUf :: Ocorreu um erro ao buscar o estado: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return state;
    }

}
