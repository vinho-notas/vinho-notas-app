package com.vinhonotas.vinho.v1.application.services;

import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;

import java.util.List;
import java.util.UUID;

public interface WineService {

    WineEntity create(WineInputDTO wineInputDTO);
    List<WineEntity> getAll();
    WineEntity getById(UUID id);
    WineEntity update(UUID id, WineInputDTO wineInputDTO);
    void delete(UUID id);
}
