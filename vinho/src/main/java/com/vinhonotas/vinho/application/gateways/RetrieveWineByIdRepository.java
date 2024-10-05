package com.vinhonotas.vinho.application.gateways;

import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;

public interface RetrieveWineByIdRepository {

    WineEntity retrieveWineById(String id);
    
}
