package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.UpdateWineRepository;
import com.vinhonotas.vinho.application.usecases.UpdateWine;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineDomainMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateWineImpl implements UpdateWine {

    private final UpdateWineRepository updateWineRepository;
    private final WineDomainMapper wineDomainMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WineEntity updateWine(String id, @Valid WineInputDTO wineInputDTO) {
        log.info("updateWine :: Atualizando vinho com id: {}", id);
        return updateWineRepository.updateWine(id, wineInputDTO);
    }

}
