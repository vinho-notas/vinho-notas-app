package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.RetrieveWinesRepository;
import com.vinhonotas.vinho.application.usecases.RetrieveWines;
import com.vinhonotas.vinho.domain.entities.exceptions.WineNotFoundException;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.utils.MessagesConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RetrieveWinesImpl implements RetrieveWines {

    private final RetrieveWinesRepository retrieveWinesRepository;

    public RetrieveWinesImpl(RetrieveWinesRepository retrieveWinesRepository) {
        this.retrieveWinesRepository = retrieveWinesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WineEntity> retrieveAllWines() {
        log.info("retrieveAllWines:: Buscando todos os vinhos");
        List<WineEntity> retrievedAllWines = retrieveWinesRepository.retrieveAllWines();
        if (retrievedAllWines.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao listar os vinhos: {} ", MessagesConstants.ERROR_WINE_NOT_FOUND);
            throw new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND);
        }
        return retrievedAllWines;
    }
    
}
