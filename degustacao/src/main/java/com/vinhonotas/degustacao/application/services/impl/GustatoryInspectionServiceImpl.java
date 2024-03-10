package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.GustatoryInspectionConverter;
import com.vinhonotas.degustacao.application.services.GustatoryInspectionService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.infraestructure.GustatoryInspectionRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
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
public class GustatoryInspectionServiceImpl implements GustatoryInspectionService {

    private final GustatoryInspectionRepository gustatoryInspectionRepository;
    private final GustatoryInspectionConverter gustatoryInspectionConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GustatoryInspectionEntity create(GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        log.info("create :: Registrando uma nova inspeção gustativa com os dados: {}", gustatoryInspectionInputDTO.toString());
        try {
            return gustatoryInspectionRepository.save(gustatoryInspectionConverter.toEntity(gustatoryInspectionInputDTO));
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING_GUSTATORY_INSPECTION, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_GUSTATORY_INSPECTION);
        }
    }

    @Override
    public List<GustatoryInspectionEntity> getAll() {
        log.info("getAll :: Listando todas as inspeções gustativas");
        List<GustatoryInspectionEntity> list = gustatoryInspectionRepository.findAll();
        if (list.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao listar as inspeções gustativas: {} ", MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND);
            throw new BadRequestException(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND);
        }
        return list;
    }

    @Override
    public GustatoryInspectionEntity getById(UUID id) {
        log.info("getById :: Buscando inspeção gustativa pelo id: {}", id.toString());
        return gustatoryInspectionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GustatoryInspectionEntity update(UUID id, GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        log.info("update :: Atualizando inspeção gustativa com os dados: {}", gustatoryInspectionInputDTO.toString());
        try {
            GustatoryInspectionEntity entity = this.getById(id);
            return gustatoryInspectionRepository.save(gustatoryInspectionConverter.toEntityUpdate(gustatoryInspectionInputDTO, id, entity));
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING_GUSTATORY_INSPECTION, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_GUSTATORY_INSPECTION);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando inspeção gustativa pelo id: {}", id.toString());
        Optional<GustatoryInspectionEntity> opt = gustatoryInspectionRepository.findById(id);
        if (opt.isEmpty()) {
            log.error("delete :: Ocorreu um erro ao deletar a inspeção gustativa: {} ", MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND);
            throw new BadRequestException(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND);
        }
        try {
            gustatoryInspectionRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro ao deletar a inspeção gustativa: {} ", MessagesConstants.ERROR_WHEN_DELETING_GUSTATORY_INSPECTION, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_GUSTATORY_INSPECTION);
        }
    }

}
