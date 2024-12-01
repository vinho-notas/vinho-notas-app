package com.vinhonotas.vinho.application.usecases;

import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import jakarta.validation.Valid;

public interface UpdateWine {

    WineEntity updateWine(String id, @Valid WineInputDTO wineDomain);

}
