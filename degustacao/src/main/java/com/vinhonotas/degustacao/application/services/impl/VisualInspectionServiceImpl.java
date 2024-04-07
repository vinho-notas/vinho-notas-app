package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.VisualInspectionConverter;
import com.vinhonotas.degustacao.application.services.VisualInspectionService;
import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
import com.vinhonotas.degustacao.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.exceptions.VisualInspectionNotFoundException;
import com.vinhonotas.degustacao.infraestructure.VisualInspectionRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VisualInspectionServiceImpl implements VisualInspectionService {

    private final VisualInspectionRepository visualInspectionRepository;
    private final VisualInspectionConverter visualInspectionConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VisualInspectionEntity create(VisualInspectionInputDTO visualInspectionInputDTO) {
        log.info("create :: Registrando uma nova inspeção visual com os dados: {}", visualInspectionInputDTO.toString());
        try {
            VisualInspectionEntity visualInspectionEntity = visualInspectionConverter.toEntity(visualInspectionInputDTO);
            return visualInspectionRepository.save(visualInspectionEntity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING_VISUAL_INSPECTION, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_VISUAL_INSPECTION);
        }
    }

    @Override
    public List<VisualInspectionEntity> getAll() {
        log.info("getAll :: Listando todas as inspeções visuais");
        List<VisualInspectionEntity> list = visualInspectionRepository.findAll();
        if (list.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao listar as inspeções visuais: {} ", MessagesConstants.VISUAL_INSPECTION_NOT_FOUND);
            throw new VisualInspectionNotFoundException(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND);
        }
        return list;
    }

    @Override
    public VisualInspectionEntity getById(UUID id) {
        log.info("getById :: Buscando inspeção visual pelo id: {}", id);
        return visualInspectionRepository.findById(id)
                .orElseThrow(() -> new VisualInspectionNotFoundException(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VisualInspectionEntity update(UUID id, VisualInspectionInputDTO visualInspectionInputDTO) {
        log.info("update :: Atualizando inspeção visual com os dados: {}", visualInspectionInputDTO.toString());
        try {
            VisualInspectionEntity entity = this.getById(id);
            return visualInspectionRepository.save(visualInspectionConverter.toEntityUpdate(visualInspectionInputDTO, id, entity));
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING_VISUAL_INSPECTION, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_VISUAL_INSPECTION);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando inspeção visual pelo id: {}", id);
        Optional<VisualInspectionEntity> opt = visualInspectionRepository.findById(id);
        if (opt.isEmpty()) {
            log.error("delete :: Ocorreu um erro ao deletar a inspeção visual: {} ", MessagesConstants.VISUAL_INSPECTION_NOT_FOUND);
            throw new VisualInspectionNotFoundException(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND);
        }
        try {
            visualInspectionRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_DELETING_VISUAL_INSPECTION, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_VISUAL_INSPECTION);
        }

    }
}
