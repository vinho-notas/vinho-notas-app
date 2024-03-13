package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.TastingCardConverter;
import com.vinhonotas.degustacao.application.services.TastingCardService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.infraestructure.TastingCardRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TastingCardServiceImpl implements TastingCardService {

    private final TastingCardRepository tastingCardRepository;
    private final TastingCardConverter tastingCardConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TastingCardEntity create(TastingCardInputDTO inputDTO) {
        log.info("create :: Registrando uma ficha de degustação com os dados: {}", inputDTO.toString());
        try {
            TastingCardEntity entity = tastingCardConverter.toEntity(inputDTO);
            return tastingCardRepository.save(entity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING_TASTING_CARD, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_TASTING_CARD);
        }
    }

    @Override
    public Set<TastingCardEntity> getAll() {
        log.info("getAll :: Listando todas as fichas de degustações");
        var list = tastingCardRepository.findAll();
        if (list.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao listar as fichas de degustações: {} ", MessagesConstants.TASTING_CARD_NOT_FOUND);
            throw new BadRequestException(MessagesConstants.TASTING_CARD_NOT_FOUND);
        }
        return new HashSet<>(list);
    }

    @Override
    public TastingCardEntity getById(UUID id) {
        log.info("getById :: Buscando ficha de degustação pelo id: {}", id);
        return tastingCardRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.TASTING_CARD_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TastingCardEntity update(UUID id, TastingCardInputDTO inputDTO) {
        log.info("update :: Atualizando ficha de degustação com os dados: {}", inputDTO.toString());
        try {
            TastingCardEntity entity = this.getById(id);
            return tastingCardRepository.save(tastingCardConverter.toEntityUpdate(inputDTO, id, entity));
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING_TASTING_CARD, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_TASTING_CARD);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando ficha de degustação pelo id: {}", id);
        Optional<TastingCardEntity> opt = tastingCardRepository.findById(id);
        if (opt.isEmpty()) {
            log.error("delete :: Ocorreu um erro ao deletar a ficha de degustação: {} ", MessagesConstants.TASTING_CARD_NOT_FOUND);
            throw new BadRequestException(MessagesConstants.TASTING_CARD_NOT_FOUND);
        }
        try {
            tastingCardRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro ao deletar a ficha de degustação: {} ", MessagesConstants.ERROR_WHEN_DELETING_TASTING_CARD, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_TASTING_CARD);
        }
    }

}
