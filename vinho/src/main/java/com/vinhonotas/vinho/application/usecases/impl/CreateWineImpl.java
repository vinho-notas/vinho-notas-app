package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.CreateWineRepository;
import com.vinhonotas.vinho.application.usecases.CreateWine;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.utils.MessagesConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CreateWineImpl implements CreateWine {

    private final CreateWineRepository createWineRepository;

    public CreateWineImpl(CreateWineRepository createWineRepository) {
        this.createWineRepository = createWineRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WineEntity createWine(WineDomain wineDomain) {
        log.info("create :: Recebendo requisição para registrar um vinho com os dados: {}", wineDomain);
        try {
            return createWineRepository.createWine(wineDomain);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {} ", MessagesConstants.ERROR_CREATE_WINE, e);
            throw new BadRequestException(MessagesConstants.ERROR_CREATE_WINE);
        }

    }

}
