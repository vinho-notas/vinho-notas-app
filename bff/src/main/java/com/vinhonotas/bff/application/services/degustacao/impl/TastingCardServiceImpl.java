package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.degustacao.TastingCardService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.degustacao.TastingCardClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingCardInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingCardOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TastingCardServiceImpl implements TastingCardService {

    private final TastingCardClient tastingCardClient;

    @Override
    public TastingCardOutputDTO createTastingCard(TastingCardInputDTO tastingCardInputDTO) {
        log.info("createTastingCard :: Registrando uma ficha de degustação com os dados: {}", tastingCardInputDTO.toString());
        try {
            return tastingCardClient.createTastingCard(tastingCardInputDTO);
        } catch (Exception e) {
            log.error("createTastingCard :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<TastingCardOutputDTO> getAllTastingCards() {
        log.info("getAllTastingCards :: Listando todas as fichas de degustação");
        List<TastingCardOutputDTO> tastingCards = tastingCardClient.getAllTastingCards();
        if (tastingCards.isEmpty()) {
            log.error("getAllTastingCards :: Ocorreu um erro ao listar as fichas de degustação: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return tastingCards;
    }

    @Override
    public TastingCardOutputDTO getTastingCardById(String id) {
        log.info("getTastingCardById :: Buscando uma ficha de degustação pelo id: {}", id);
        TastingCardOutputDTO tastingCard = tastingCardClient.getTastingCardById(id);
        if (Objects.isNull(tastingCard)) {
            log.error("getTastingCardById :: Ocorreu um erro ao buscar a ficha de degustação: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return tastingCard;
    }

    @Override
    public TastingCardOutputDTO updateTastingCard(String id, TastingCardInputDTO tastingCardInputDTO) {
        log.info("updateTastingCard :: Atualizando ficha de degustação com os dados: {}", tastingCardInputDTO.toString());
        try {
            return tastingCardClient.updateTastingCard(id, tastingCardInputDTO);
        } catch (Exception e) {
            log.error("updateTastingCard :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }

    }

    @Override
    public void deleteTastingCard(String id) {
        log.info("deleteTastingCard :: Deletando uma ficha degustação pelo id: {}", id);
        try {
            this.getTastingCardById(id);
            tastingCardClient.deleteTastingCard(id);
        } catch (Exception e) {
            log.error("deleteTastingCard :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }

}
