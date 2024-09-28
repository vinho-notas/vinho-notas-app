package com.vinhonotas.vinho.application.usecases;

import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;

public interface CreateWine {

    WineEntity createWine(WineDomain wineDomain);

}
