package com.vinhonotas.degustacao.application.services;

import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingInputDTO;

import java.util.Set;
import java.util.UUID;

public interface TastingService {

    TastingEntity create(TastingInputDTO tastingInputDTO);
    Set<TastingEntity> getAll();
    TastingEntity getById(UUID id);
    TastingEntity update(UUID id, TastingInputDTO tastingInputDTO);
    void delete(UUID id);

}
