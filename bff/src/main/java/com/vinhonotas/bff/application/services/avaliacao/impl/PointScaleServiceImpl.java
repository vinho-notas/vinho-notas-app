package com.vinhonotas.bff.application.services.avaliacao.impl;

import com.vinhonotas.bff.application.services.avaliacao.PointScaleService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.avaliacao.PointScaleClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.avaliacao.PointScaleInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.avaliacao.PointScaleOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointScaleServiceImpl implements PointScaleService {

    private final PointScaleClient pointScaleClient;

    @Override
    public PointScaleOutputDTO createPointScale(PointScaleInputDTO pointScaleInputDTO) {
        log.info("createPointScale :: Registrando uma avaliação com os dados: {}", pointScaleInputDTO.toString());
        try {
            return pointScaleClient.createPointScale(pointScaleInputDTO);
        } catch (Exception e) {
            log.error("createPointScale :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<PointScaleOutputDTO> getAllPointScale() {
        log.info("getAllPointScale :: Listando todas as avaliações");
        List<PointScaleOutputDTO> allPointScale = pointScaleClient.getAllPointScale();
        if (allPointScale.isEmpty()) {
            log.error("getAllPointScale :: Ocorreu um erro ao listar as avaliações: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return allPointScale;
    }

    @Override
    public PointScaleOutputDTO getPointScaleById(String id) {
        log.info("getPointScaleById :: Buscando avaliação pelo id: {}", id);
        PointScaleOutputDTO pointScale = pointScaleClient.getPointScaleById(id);
        if (Objects.isNull(pointScale)) {
            log.error("getPointScaleById :: Ocorreu um erro ao buscar a avaliação: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return pointScale;
    }

    @Override
    public PointScaleOutputDTO updatePointScale(String id, PointScaleInputDTO pointScaleInputDTO) {
        log.info("updatePointScale :: Atualizando avaliação com os dados: {}", pointScaleInputDTO.toString());
        try {
            return pointScaleClient.updatePointScale(id, pointScaleInputDTO);
        } catch (Exception e) {
            log.error("updatePointScale :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deletePointScale(String id) {
        log.info("deletePointScale :: Deletando avaliação pelo id: {}", id);
        try {
            pointScaleClient.deletePointScale(id);
        } catch (Exception e) {
            log.error("deletePointScale :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }

}
