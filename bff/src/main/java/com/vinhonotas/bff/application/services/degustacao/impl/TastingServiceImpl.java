package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.degustacao.TastingService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.degustacao.TastingClient;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TastingServiceImpl implements TastingService {

    private final TastingClient tastingClient;

    @Override
    public TastingOutputDTO createTasting(TastingInputDTO tastingInputDTO) {
        log.info("createTasting :: Registrando uma degustação com os dados: {}", tastingInputDTO.toString());
        try {
            return tastingClient.createTasting(tastingInputDTO);
        } catch (Exception e) {
            log.error("createTasting :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<TastingOutputDTO> getAllTastings() {
        log.info("getAllTastings :: Listando todas as degustações");
        List<TastingOutputDTO> tastings = tastingClient.getAllTastings();
        if (tastings.isEmpty()) {
            log.error("getAllTastings :: Ocorreu um erro ao listar as degustações: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return tastings;
    }

    @Override
    public TastingOutputDTO getTastingById(String id) {
        log.info("getTastingById :: Buscando uma degustação pelo id: {}", id);
        TastingOutputDTO tasting = tastingClient.getTastingById(id);
        if (Objects.isNull(tasting)) {
            log.error("getTastingById :: Ocorreu um erro ao buscar a degustação: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return tasting;
    }

    @Override
    public TastingOutputDTO updateTasting(String id, TastingInputDTO tastingInputDTO) {
        log.info("updateTasting :: Atualizando degustação com os dados: {}", tastingInputDTO.toString());
        try {
            return tastingClient.updateTasting(id, tastingInputDTO);
        } catch (Exception e) {
            log.error("updateTasting :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deleteTasting(String id) {
        log.info("deleteTasting :: Deletando uma degustação pelo id: {}", id);
        try {
            tastingClient.deleteTasting(id);
        } catch (Exception e) {
            log.error("deleteTasting :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }

}
