package com.vinhonotas.cadastro.application.services;

import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;

import java.util.List;
import java.util.UUID;

public interface StateService {

    public StateEntity create(StateInputDTO stateInputDTO);
    public List<StateEntity> getAll();
    public StateEntity getById(UUID id);
    public StateEntity getByName(String name);
    public List<StateEntity> getByUf(String uf);
    public StateEntity update(UUID id, StateInputDTO stateInputDTO);
    public void delete(UUID id);
}
