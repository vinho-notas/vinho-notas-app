package com.vinhonotas.vinho.application.usecases;

import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;

import java.util.List;

public interface RetrieveWines {

    List<WineEntity> retrieveAllWines();

}
