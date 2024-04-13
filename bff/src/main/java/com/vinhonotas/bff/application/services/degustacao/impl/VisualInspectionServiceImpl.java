package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.degustacao.VisualInspectionService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.degustacao.VisualInspectionClient;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class VisualInspectionServiceImpl implements VisualInspectionService {

    private final VisualInspectionClient visualInspectionClient;

    @Override
    public VisualInspectionOutputDTO createVisualInspection(VisualInspectionInputDTO visualInspectionInputDTO) {
        log.info("createVisualInspection :: Registrando uma inspeção visual com os dados: {}", visualInspectionInputDTO.toString());
        try {
            return visualInspectionClient.createVisualInspection(visualInspectionInputDTO);
        } catch (Exception e) {
            log.error("createVisualInspection :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<VisualInspectionOutputDTO> getAllVisualInspections() {
        log.info("getAllVisualInspections :: Listando todas as inspeções visuais");
        List<VisualInspectionOutputDTO> visualInspectionList = visualInspectionClient.getAllVisualInspections();
        if (visualInspectionList.isEmpty()) {
            log.error("getAllVisualInspections :: Ocorreu um erro ao listar as inspeções visuais: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return visualInspectionList;
    }

    @Override
    public VisualInspectionOutputDTO getVisualInspectionById(String id) {
        log.info("getVisualInspectionById :: Buscando uma inspeção visual pelo id: {}", id);
        VisualInspectionOutputDTO visualInspection = visualInspectionClient.getVisualInspectionById(id);
        if (Objects.isNull(visualInspection)) {
            log.error("getVisualInspectionById :: Ocorreu um erro ao buscar a inspeção visual: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return visualInspection;
    }

    @Override
    public VisualInspectionOutputDTO updateVisualInspection(String id, VisualInspectionInputDTO visualInspectionInputDTO) {
        log.info("updateVisualInspection :: Atualizando a inspeção visual com os dados: {}", visualInspectionInputDTO.toString());
        try {
            return visualInspectionClient.updateVisualInspection(id, visualInspectionInputDTO);
        } catch (Exception e) {
            log.error("updateVisualInspection :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deleteVisualInspection(String id) {
        log.info("deleteVisualInspection :: Deletando uma inspeção visual pelo id: {}", id);
        try {
            visualInspectionClient.deleteVisualInspection(id);
        } catch (Exception e) {
            log.error("deleteVisualInspection :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }

}
