package com.vinhonotas.vinho.application.gateways;

import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;

public interface CreateWineRepository {

    WineEntity createWine(WineDomain wineDomain);

}
