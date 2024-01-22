package com.vinhonotas.vinho.application.services;

import com.vinhonotas.vinho.domain.entities.WineEntity;
import com.vinhonotas.vinho.interfaces.dtos.inputs.WineInputDTO;

import java.util.List;
import java.util.UUID;

public interface WineService {

    WineEntity create(WineInputDTO wineInputDTO);
    List<WineEntity> getAll();
    WineEntity getById(UUID id);
    WineEntity update(UUID id, WineInputDTO wineInputDTO);
    void delete(UUID id);
}
