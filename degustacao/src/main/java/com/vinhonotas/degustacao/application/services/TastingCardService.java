package com.vinhonotas.degustacao.application.services;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;

import java.util.List;
import java.util.UUID;

public interface TastingCardService {

    TastingCardEntity create(TastingCardInputDTO inputDTO);
    List<TastingCardEntity> getAll();
    TastingCardEntity getById(UUID id);
    TastingCardEntity update(UUID id, TastingCardInputDTO inputDTO);
    void delete(UUID id);

}
