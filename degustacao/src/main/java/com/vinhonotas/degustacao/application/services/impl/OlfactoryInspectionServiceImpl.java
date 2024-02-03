package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.OlfactoryInspectionConverter;
import com.vinhonotas.degustacao.application.services.OlfactoryInspectionService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import com.vinhonotas.degustacao.infraestructure.OlfactoryInspectionRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OlfactoryInspectionServiceImpl implements OlfactoryInspectionService {

    private final OlfactoryInspectionRepository olfactoryInspectionRepository;
    private final OlfactoryInspectionConverter olfactoryInspectionConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OlfactoryInspectionEntity create(OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        try {
            OlfactoryInspectionEntity olfactoryInspectionEntity = olfactoryInspectionConverter.toEntity(olfactoryInspectionInputDTO);
            return olfactoryInspectionRepository.save(olfactoryInspectionEntity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_OLFACTORY_INSPECTION);
        }
    }

    @Override
    public List<OlfactoryInspectionEntity> getAll() {
        List<OlfactoryInspectionEntity> list = olfactoryInspectionRepository.findAll();
        if (list.isEmpty()) {
            throw new BadRequestException(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND);
        }
        return list;
    }

    @Override
    public OlfactoryInspectionEntity getById(UUID id) {
        return olfactoryInspectionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OlfactoryInspectionEntity update(UUID id, OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        try {
            OlfactoryInspectionEntity entity = this.getById(id);
            return olfactoryInspectionRepository.save(olfactoryInspectionConverter.toEntityUpdate(olfactoryInspectionInputDTO, id, entity));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_OLFACTORY_INSPECTION);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<OlfactoryInspectionEntity> opt = olfactoryInspectionRepository.findById(id);
        if (opt.isEmpty()) {
            throw new BadRequestException(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND);
        }
        try {
            olfactoryInspectionRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_OLFACTORY_INSPECTION);
        }
    }

}
