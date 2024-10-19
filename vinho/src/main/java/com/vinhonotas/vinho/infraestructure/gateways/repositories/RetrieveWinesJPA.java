package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.application.gateways.RetrieveWinesRepository;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RetrieveWinesJPA implements RetrieveWinesRepository {

    private final WineRepository wineRepository;

    public RetrieveWinesJPA(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public List<WineEntity> retrieveAllWines() {
        return wineRepository.findAll();
    }

}
