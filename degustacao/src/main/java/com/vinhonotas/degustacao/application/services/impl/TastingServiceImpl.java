package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.TastingConverter;
import com.vinhonotas.degustacao.application.services.TastingService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import com.vinhonotas.degustacao.infraestructure.TastingRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingInputDTO;
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
public class TastingServiceImpl implements TastingService {

    private final TastingRepository tastingRepository;
    private final TastingConverter tastingConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TastingEntity create(TastingInputDTO tastingInputDTO) {
        log.info("create :: Registrando uma degustação com os dados: {}", tastingInputDTO.toString());
        try {
            TastingEntity tastingEntity = tastingConverter.toEntity(tastingInputDTO);
            return tastingRepository.save(tastingEntity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING_TASTING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_TASTING);
        }
    }

    @Override
    public List<TastingEntity> getAll() {
        log.info("getAll :: Listando todas as degustações");
        List<TastingEntity> list = tastingRepository.findAll();
        if (list.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao listar as degustações: {} ", MessagesConstants.TASTING_NOT_FOUND);
            throw new BadRequestException(MessagesConstants.TASTING_NOT_FOUND);
        }
        return list;
    }

    @Override
    public TastingEntity getById(UUID id) {
        log.info("getById :: Buscando degustação pelo id: {}", id);
        return tastingRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.TASTING_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TastingEntity update(UUID id, TastingInputDTO tastingInputDTO) {
        log.info("update :: Atualizando degustação com os dados: {}", tastingInputDTO.toString());
        try {
            TastingEntity entity = this.getById(id);
            return tastingRepository.save(tastingConverter.toEntityUpdate(tastingInputDTO, id, entity));
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING_TASTING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_TASTING);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando degustação pelo id: {}", id.toString());
        Optional<TastingEntity> opt = tastingRepository.findById(id);
        if (opt.isEmpty()) {
            log.error("delete :: Ocorreu um erro ao deletar a degustação: {} ", MessagesConstants.TASTING_NOT_FOUND);
            throw new BadRequestException(MessagesConstants.TASTING_NOT_FOUND);
        }
        try {
            tastingRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro ao deletar a degustação: {} ", MessagesConstants.ERROR_WHEN_DELETING_TASTING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_TASTING);
        }
    }

}
