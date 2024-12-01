package com.vinhonotas.vinho.application.gateways;

import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;

public interface UpdateWineRepository {

    WineEntity updateWine(String id, WineInputDTO wineInputDTO);

}
