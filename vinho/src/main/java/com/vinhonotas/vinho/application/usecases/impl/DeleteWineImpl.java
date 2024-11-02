package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.DeleteWineRepository;
import com.vinhonotas.vinho.application.gateways.RetrieveWineByIdRepository;
import com.vinhonotas.vinho.application.usecases.DeleteWine;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.utils.MessagesConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class DeleteWineImpl implements DeleteWine {

    private final DeleteWineRepository deleteWineRepository;
    private final RetrieveWineByIdRepository retrieveWineByIdRepository;

    public DeleteWineImpl(DeleteWineRepository deleteWineRepository, RetrieveWineByIdRepository retrieveWineByIdRepository) {
        this.deleteWineRepository = deleteWineRepository;
        this.retrieveWineByIdRepository = retrieveWineByIdRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteWineById(UUID id) {
        WineEntity wineRetrieved = retrieveWineByIdRepository.retrieveWineById(id.toString());
        if (Objects.isNull(wineRetrieved)) {
            log.error("deleteWineById :: Ocorreu um erro ao deletar o vinho: {} ", MessagesConstants.ERROR_WINE_NOT_FOUND);
            return;
        }
        deleteWineRepository.deleteWineById(id);
    }

}
