package com.vinhonotas.vinho.application.usecases;

import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;

public interface RetrieveWineById {

    WineEntity retrieveWineById(String id);

}
