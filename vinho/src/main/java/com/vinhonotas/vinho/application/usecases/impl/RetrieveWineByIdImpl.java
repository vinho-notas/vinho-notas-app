package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.RetrieveWineByIdRepository;
import com.vinhonotas.vinho.application.usecases.RetrieveWineById;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetrieveWineByIdImpl implements RetrieveWineById {

    private final RetrieveWineByIdRepository retrieveWineByIdRepository;

    public RetrieveWineByIdImpl(RetrieveWineByIdRepository retrieveWineByIdRepository) {
        this.retrieveWineByIdRepository = retrieveWineByIdRepository;
    }

    @Override
    public WineEntity retrieveWineById(String id) {
        log.info("retrieveWineById:: Buscando vinho pelo id: {}", id);

        return retrieveWineByIdRepository.retrieveWineById(id);

    }

}
