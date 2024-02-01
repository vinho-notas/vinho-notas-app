package com.vinhonotas.degustacao.application.services;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;

import java.util.List;
import java.util.UUID;

public interface AromasService {

    AromasEntity create(AromasInputDTO aromasInputDTO);
    List<AromasEntity> getAll();
    AromasEntity getById(UUID id);
    AromasEntity update(UUID id, AromasInputDTO aromasInputDTO);
    void delete(UUID id);
}
