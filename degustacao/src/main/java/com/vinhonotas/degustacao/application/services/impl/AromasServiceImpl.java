package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.AromasConverter;
import com.vinhonotas.degustacao.application.services.AromasService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.infraestructure.AromasRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AromasServiceImpl implements AromasService {

    private final AromasRepository aromasRepository;
    private final AromasConverter aromasConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AromasEntity create(AromasInputDTO aromasInputDTO) {
        try {
            AromasEntity aromasEntity = aromasConverter.toEntity(aromasInputDTO);
            return aromasRepository.save(aromasEntity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_AROMAS);
        }
    }

    @Override
    public List<AromasEntity> getAll() {
        List<AromasEntity> list = aromasRepository.findAll();
        if (list.isEmpty()) {
            throw new BadRequestException(MessagesConstants.AROMAS_NOT_FOUND);
        }
        return list;
    }

    @Override
    public AromasEntity getById(UUID id) {
        return aromasRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.AROMAS_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AromasEntity update(UUID id, AromasInputDTO aromasInputDTO) {
        try {
            AromasEntity entity = this.getById(id);
            return aromasRepository.save(aromasConverter.toEntityUpdate(aromasInputDTO, id, entity));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_AROMAS);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<AromasEntity> opt = aromasRepository.findById(id);
        if (opt.isEmpty()) {
            throw new BadRequestException(MessagesConstants.AROMAS_NOT_FOUND);
        }

        try {
            aromasRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_AROMAS);
        }
    }
}
