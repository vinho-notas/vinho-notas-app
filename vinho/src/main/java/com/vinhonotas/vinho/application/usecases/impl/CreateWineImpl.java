package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.WineRepository;
import com.vinhonotas.vinho.application.usecases.CreateWine;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.v1.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.v1.utils.MessagesConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class CreateWineImpl implements CreateWine {

    private final WineRepository wineRepository;

    public CreateWineImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WineDomain createWine(WineInputDTO wineInputDTO) {
        log.info("create :: Recebendo requieisção para registrar um vinho com os dados: {}", wineInputDTO);
        try {
            return wineRepository.createWine(wineInputDTO);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {} ", MessagesConstants.ERROR_CREATE_WINE, e);
            throw new BadRequestException(MessagesConstants.ERROR_CREATE_WINE);
        }

    }

}
