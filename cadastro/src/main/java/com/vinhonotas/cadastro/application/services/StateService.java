package com.vinhonotas.cadastro.application.services;

import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;

import java.util.List;
import java.util.UUID;

public interface StateService {

    StateEntity create(StateInputDTO stateInputDTO);
    List<StateEntity> getAll();
    StateEntity getById(UUID id);
    StateEntity getByName(String name);
    List<StateEntity> getByUf(String uf);
    StateEntity update(UUID id, StateInputDTO stateInputDTO);
    void delete(UUID id);
}
