package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.usecases.UpdateWine;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateWineImpl implements UpdateWine {

    @Override
    public WineEntity updateWine(String id, WineDomain wineDomain) {
        return null;
    }
}
