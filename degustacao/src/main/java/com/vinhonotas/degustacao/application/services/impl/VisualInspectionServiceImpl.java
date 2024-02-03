package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.VisualInspectionConverter;
import com.vinhonotas.degustacao.application.services.VisualInspectionService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
import com.vinhonotas.degustacao.infraestructure.VisualInspectionRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisualInspectionServiceImpl implements VisualInspectionService {

    private final VisualInspectionRepository visualInspectionRepository;
    private final VisualInspectionConverter visualInspectionConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VisualInspectionEntity create(VisualInspectionInputDTO visualInspectionInputDTO) {
        try {
            VisualInspectionEntity visualInspectionEntity = visualInspectionConverter.toEntity(visualInspectionInputDTO);
            return visualInspectionRepository.save(visualInspectionEntity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_VISUAL_INSPECTION);
        }
    }

    @Override
    public List<VisualInspectionEntity> getAll() {
        List<VisualInspectionEntity> list = visualInspectionRepository.findAll();
        if (list.isEmpty()) {
            throw new BadRequestException(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND);
        }
        return list;
    }

    @Override
    public VisualInspectionEntity getById(UUID id) {
        return visualInspectionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VisualInspectionEntity update(UUID id, VisualInspectionInputDTO visualInspectionInputDTO) {
        try {
            VisualInspectionEntity entity = this.getById(id);
            return visualInspectionRepository.save(visualInspectionConverter.toEntityUpdate(visualInspectionInputDTO, id, entity));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_VISUAL_INSPECTION);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<VisualInspectionEntity> opt = visualInspectionRepository.findById(id);
        if (opt.isEmpty()) {
            throw new BadRequestException(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND);
        }
        try {
            visualInspectionRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_VISUAL_INSPECTION);
        }

    }
}
