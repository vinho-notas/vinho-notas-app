package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.application.gateways.RetrieveWineByIdRepository;
import com.vinhonotas.vinho.domain.entities.exceptions.WineNotFoundException;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import com.vinhonotas.vinho.utils.MessagesConstants;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RetrieveWineByIdRepositoryJPA implements RetrieveWineByIdRepository {

    private final WineRepository wineRepository;

    public RetrieveWineByIdRepositoryJPA(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public WineEntity retrieveWineById(String id) {

        return wineRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND));
    }

}
