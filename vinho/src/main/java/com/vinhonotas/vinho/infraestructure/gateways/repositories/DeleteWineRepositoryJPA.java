package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.application.gateways.DeleteWineRepository;
import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import com.vinhonotas.vinho.utils.MessagesConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Slf4j
@Repository
public class DeleteWineRepositoryJPA implements DeleteWineRepository {

    private final WineRepository wineRepository;

    public DeleteWineRepositoryJPA(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public void deleteWineById(UUID id) {
        try {
            wineRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro ao deletar o vinho: {} ", MessagesConstants.ERROR_DELETE_WINE, e);
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_WINE);
        }
    }

}
