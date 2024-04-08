package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.OlfactoryInspectionConverter;
import com.vinhonotas.degustacao.application.services.OlfactoryInspectionService;
import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import com.vinhonotas.degustacao.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.exceptions.OlfactoryInspectionNotFoundException;
import com.vinhonotas.degustacao.infraestructure.OlfactoryInspectionRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;
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
public class OlfactoryInspectionServiceImpl implements OlfactoryInspectionService {

    private final OlfactoryInspectionRepository olfactoryInspectionRepository;
    private final OlfactoryInspectionConverter olfactoryInspectionConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OlfactoryInspectionEntity create(OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        log.info("create :: Registrando uma nova inspeção olfativa com os dados: {}", olfactoryInspectionInputDTO.toString());
        try {
            OlfactoryInspectionEntity olfactoryInspectionEntity = olfactoryInspectionConverter.toEntity(olfactoryInspectionInputDTO);
            return olfactoryInspectionRepository.save(olfactoryInspectionEntity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING_OLFACTORY_INSPECTION, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_OLFACTORY_INSPECTION);
        }
    }

    @Override
    public List<OlfactoryInspectionEntity> getAll() {
        log.info("getAll :: Listando todas as inspeções olfativas");
        List<OlfactoryInspectionEntity> list = olfactoryInspectionRepository.findAll();
        if (list.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao listar as inspeções olfativas: {} ", MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND);
            throw new OlfactoryInspectionNotFoundException(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND);
        }
        return list;
    }

    @Override
    public OlfactoryInspectionEntity getById(UUID id) {
        log.info("getById :: Buscando inspeção olfativa pelo id: {}", id.toString());
        return olfactoryInspectionRepository.findById(id)
                .orElseThrow(() -> new OlfactoryInspectionNotFoundException(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OlfactoryInspectionEntity update(UUID id, OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        log.info("update :: Atualizando inspeção olfativa com os dados: {}", olfactoryInspectionInputDTO.toString());
        try {
            OlfactoryInspectionEntity entity = this.getById(id);
            return olfactoryInspectionRepository.save(olfactoryInspectionConverter.toEntityUpdate(olfactoryInspectionInputDTO, id, entity));
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING_OLFACTORY_INSPECTION, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_OLFACTORY_INSPECTION);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando inspeção olfativa pelo id: {}", id.toString());
        Optional<OlfactoryInspectionEntity> opt = olfactoryInspectionRepository.findById(id);
        if (opt.isEmpty()) {
            log.error("delete :: Ocorreu um erro ao deletar a inspeção olfativa: {} ", MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND);
            throw new OlfactoryInspectionNotFoundException(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND);
        }
        try {
            olfactoryInspectionRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro ao deletar a inspeção olfativa: {} ", MessagesConstants.ERROR_WHEN_DELETING_OLFACTORY_INSPECTION, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_OLFACTORY_INSPECTION);
        }
    }

}
