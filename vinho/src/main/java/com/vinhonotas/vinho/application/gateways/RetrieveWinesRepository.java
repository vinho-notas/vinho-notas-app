package com.vinhonotas.vinho.application.gateways;

import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;

import java.util.List;

public interface RetrieveWinesRepository {

    List<WineEntity> retrieveAllWines();

}
