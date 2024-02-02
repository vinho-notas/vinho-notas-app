package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.GustatoryInspectionConverter;
import com.vinhonotas.degustacao.application.services.GustatoryInspectionService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.infraestructure.GustatoryInspectionRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GustatoryInspectionServiceImpl implements GustatoryInspectionService {

    private final GustatoryInspectionRepository gustatoryInspectionRepository;
    private final GustatoryInspectionConverter gustatoryInspectionConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GustatoryInspectionEntity create(GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        try {
            return gustatoryInspectionRepository.save(gustatoryInspectionConverter.toEntity(gustatoryInspectionInputDTO));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_CREATE_GUSTATORY_INSPECTION);
        }
    }

    @Override
    public List<GustatoryInspectionEntity> getAll() {
        List<GustatoryInspectionEntity> list = gustatoryInspectionRepository.findAll();
        if (list.isEmpty()) {
            throw new BadRequestException(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND);
        }
        return list;
    }

    @Override
    public GustatoryInspectionEntity getById(UUID id) {
        return gustatoryInspectionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GustatoryInspectionEntity update(UUID id, GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        try {
            GustatoryInspectionEntity entity = this.getById(id);
            return gustatoryInspectionRepository.save(gustatoryInspectionConverter.toEntityUpdate(gustatoryInspectionInputDTO, id, entity));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_GUSTATORY_INSPECTION);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<GustatoryInspectionEntity> opt = gustatoryInspectionRepository.findById(id);
        if (opt.isEmpty()) {
            throw new BadRequestException(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND);
        }
        try {
            gustatoryInspectionRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_GUSTATORY_INSPECTION);
        }

    }
}
