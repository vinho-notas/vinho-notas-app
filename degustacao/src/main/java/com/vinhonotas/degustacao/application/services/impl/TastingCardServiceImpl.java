package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.TastingCardConverter;
import com.vinhonotas.degustacao.application.services.TastingCardService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.infraestructure.TastingCardRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TastingCardServiceImpl implements TastingCardService {

    private final TastingCardRepository tastingCardRepository;
    private final TastingCardConverter tastingCardConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TastingCardEntity create(TastingCardInputDTO inputDTO) {
        try {
            TastingCardEntity entity = tastingCardConverter.toEntity(inputDTO);
            return tastingCardRepository.save(entity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_TASTING_CARD);
        }
    }

    @Override
    public List<TastingCardEntity> getAll() {
        List<TastingCardEntity> list = tastingCardRepository.findAll();
        if (list.isEmpty()) {
            throw new BadRequestException(MessagesConstants.TASTING_CARD_NOT_FOUND);
        }
        return list;
    }

    @Override
    public TastingCardEntity getById(UUID id) {
        return tastingCardRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.TASTING_CARD_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TastingCardEntity update(UUID id, TastingCardInputDTO inputDTO) {
        try {
            TastingCardEntity entity = this.getById(id);
            return tastingCardRepository.save(tastingCardConverter.toEntityUpdate(inputDTO, id, entity));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_TASTING_CARD);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<TastingCardEntity> opt = tastingCardRepository.findById(id);
        if (opt.isEmpty()) {
            throw new BadRequestException(MessagesConstants.TASTING_CARD_NOT_FOUND);
        }
        try {
            tastingCardRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_TASTING_CARD);
        }
    }

}
