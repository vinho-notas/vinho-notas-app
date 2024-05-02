package com.vinhonotas.avaliacao.application.services.impl;

import com.vinhonotas.avaliacao.application.converters.PointScaleConverter;
import com.vinhonotas.avaliacao.application.services.PointScaleService;
import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import com.vinhonotas.avaliacao.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.avaliacao.domain.entities.exceptions.PointScaleNotFoundException;
import com.vinhonotas.avaliacao.infraestructure.PointScaleRepository;
import com.vinhonotas.avaliacao.interfaces.dtos.inputs.PointScaleInputDTO;
import com.vinhonotas.avaliacao.utils.MessagesConstants;
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
public class PointScaleServiceImpl implements PointScaleService {

    private final PointScaleRepository pointScaleRepository;
    private final PointScaleConverter pointScaleConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PointScaleEntity create(PointScaleInputDTO pointScaleInputDTO) {
        log.info("create :: Registrando uma avaliação com os dados: {}", pointScaleInputDTO.toString());
        try {
            return pointScaleRepository.save(pointScaleConverter.toEntity(pointScaleInputDTO));
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.ERROR_CREATE_POINT_SCALE, e);
            throw new BadRequestException(MessagesConstants.ERROR_CREATE_POINT_SCALE);
        }
    }

    @Override
    public List<PointScaleEntity> getAll() {
        log.info("getAll :: Listando todas as avaliações");
        List<PointScaleEntity> list = pointScaleRepository.findAll();
        if (list.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao buscar as avaliações: {}", MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND);
            throw new PointScaleNotFoundException(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND);
        }
        return list;
    }

    @Override
    public PointScaleEntity getById(UUID id) {
        log.info("getById :: Buscando uma avaliação pelo id: {}", id.toString());
        return pointScaleRepository.findById(id).orElseThrow(() ->
                new PointScaleNotFoundException(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PointScaleEntity update(UUID id, PointScaleInputDTO pointScaleInputDTO) {
        log.info("update :: Atualizando a avaliação com os dados: {}", pointScaleInputDTO.toString());
        try {
            PointScaleEntity pointScale = this.getById(id);
            return pointScaleRepository.save(pointScaleConverter.toEntityUpdate(pointScaleInputDTO, id, pointScale));
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {}", MessagesConstants.ERROR_UPDATE_POINT_SCALE, e);
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_POINT_SCALE);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando a avaliação pelo id: {}", id.toString());
        Optional<PointScaleEntity> pointScale = pointScaleRepository.findById(id);
        if (pointScale.isEmpty()) {
            log.error("delete :: Ocorreu um erro ao deletar a avaliação: {}", MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND);
            throw new PointScaleNotFoundException(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND);
        }
        try {
            pointScaleRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro: {}", MessagesConstants.ERROR_DELETE_POINT_SCALE, e);
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_POINT_SCALE);
        }
    }

}
