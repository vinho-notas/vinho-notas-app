package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.AromasConverter;
import com.vinhonotas.degustacao.application.services.AromasService;
import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.entities.exceptions.AromasNotFoundException;
import com.vinhonotas.degustacao.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.degustacao.infraestructure.AromasRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
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
public class AromasServiceImpl implements AromasService {

    private final AromasRepository aromasRepository;
    private final AromasConverter aromasConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AromasEntity create(AromasInputDTO aromasInputDTO) {
        log.info("create :: Registrando um novo aroma com os dados: {}", aromasInputDTO.toString());
        try {
            AromasEntity aromasEntity = aromasConverter.toEntity(aromasInputDTO);
            return aromasRepository.save(aromasEntity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING_AROMAS, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_AROMAS);
        }
    }

    @Override
    public List<AromasEntity> getAll() {
        log.info("getAll :: Listando todos os aromas");
        List<AromasEntity> list = aromasRepository.findAll();
        if (list.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao listar os aromas: {} ", MessagesConstants.AROMAS_NOT_FOUND);
            throw new AromasNotFoundException(MessagesConstants.AROMAS_NOT_FOUND);
        }
        return list;
    }

    @Override
    public AromasEntity getById(UUID id) {
        log.info("getById :: Buscando aroma pelo id: {}", id.toString());
        return aromasRepository.findById(id)
                .orElseThrow(() -> new AromasNotFoundException(MessagesConstants.AROMAS_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AromasEntity update(UUID id, AromasInputDTO aromasInputDTO) {
        log.info("update :: Atualizando aroma com os dados: {}", aromasInputDTO.toString());
        try {
            AromasEntity entity = this.getById(id);
            return aromasRepository.save(aromasConverter.toEntityUpdate(aromasInputDTO, id, entity));
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING_AROMAS, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_AROMAS);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando aroma pelo id: {}", id.toString());
        Optional<AromasEntity> opt = aromasRepository.findById(id);
        if (opt.isEmpty()) {
            log.error("delete :: Ocorreu um erro ao deletar o aroma: {} ", MessagesConstants.AROMAS_NOT_FOUND);
            throw new AromasNotFoundException(MessagesConstants.AROMAS_NOT_FOUND);
        }

        try {
            aromasRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro ao deletar o aroma: {} ", MessagesConstants.ERROR_WHEN_DELETING_AROMAS, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_AROMAS);
        }
    }

}
