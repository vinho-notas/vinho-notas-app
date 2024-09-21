package com.vinhonotas.vinho.application.usecases;

import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;

public interface CreateWine {

    WineDomain createWine(WineInputDTO wineInputDTO);

}
