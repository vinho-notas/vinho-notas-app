package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.application.gateways.CreateWineRepository;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineEntityMapper;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import org.springframework.stereotype.Repository;

@Repository
public class WineRepositoryJPA implements CreateWineRepository {

    private final WineRepository wineRepository;
    private final WineEntityMapper wineEntityMapper;

    public WineRepositoryJPA(WineRepository wineRepository, WineEntityMapper wineEntityMapper) {
        this.wineRepository = wineRepository;
        this.wineEntityMapper = wineEntityMapper;
    }

    @Override
    public WineEntity createWine(WineDomain wineDomain) {
        WineEntity wineEntity = wineEntityMapper.toWineEntity(wineDomain);
        return wineRepository.save(wineEntity);
    }

}
