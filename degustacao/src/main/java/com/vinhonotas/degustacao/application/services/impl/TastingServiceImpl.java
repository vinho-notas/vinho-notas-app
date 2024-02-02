package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.TastingConverter;
import com.vinhonotas.degustacao.application.services.TastingService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import com.vinhonotas.degustacao.infraestructure.TastingRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TastingServiceImpl implements TastingService {

    private final TastingRepository tastingRepository;
    private final TastingConverter tastingConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TastingEntity create(TastingInputDTO tastingInputDTO) {
        try {
            TastingEntity tastingEntity = tastingConverter.toEntity(tastingInputDTO);
            return tastingRepository.save(tastingEntity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_TASTING);
        }
    }

    @Override
    public List<TastingEntity> getAll() {
        List<TastingEntity> list = tastingRepository.findAll();
        if (list.isEmpty()) {
            throw new BadRequestException(MessagesConstants.TASTING_NOT_FOUND);
        }
        return list;
    }

    @Override
    public TastingEntity getById(UUID id) {
        return tastingRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.TASTING_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TastingEntity update(UUID id, TastingInputDTO tastingInputDTO) {
        try {
            TastingEntity entity = this.getById(id);
            return tastingRepository.save(tastingConverter.toEntityUpdate(tastingInputDTO, id, entity));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_TASTING);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<TastingEntity> opt = tastingRepository.findById(id);
        if (opt.isEmpty()) {
            throw new BadRequestException(MessagesConstants.TASTING_NOT_FOUND);
        }
        try {
            tastingRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_TASTING);
        }
    }

}
