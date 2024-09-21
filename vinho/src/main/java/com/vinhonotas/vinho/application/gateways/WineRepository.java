package com.vinhonotas.vinho.application.gateways;

import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;

public interface WineRepository {

    WineDomain createWine(WineInputDTO wineInputDTO);

}
