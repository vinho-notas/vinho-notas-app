package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.degustacao.GustatoryInspectionService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.degustacao.GustatoryInspectionClient;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class GustatoryInspectionServiceImpl implements GustatoryInspectionService {

    private final GustatoryInspectionClient gustatoryInspectionClient;

    @Override
    public GustatoryInspectionOutputDTO createGustatoryInspection(GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        log.info("createGustatoryInspection :: Registrando uma inspeção gustativa com os dados: {}", gustatoryInspectionInputDTO.toString());
        try {
            return gustatoryInspectionClient.createGustatoryInspection(gustatoryInspectionInputDTO);
        } catch (Exception e) {
            log.error("createGustatoryInspection :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<GustatoryInspectionOutputDTO> getAllGustatoryInspections() {
        log.info("getAllGustatoryInspections :: Listando todas as inspeções gustativas");
        List<GustatoryInspectionOutputDTO> gustatoryInspectionList = gustatoryInspectionClient.getAllGustatoryInspections();
        if (gustatoryInspectionList.isEmpty()) {
            log.error("getAllGustatoryInspections :: Ocorreu um erro ao listar as inspeções gustativas: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return gustatoryInspectionList;
    }

    @Override
    public GustatoryInspectionOutputDTO getGustatoryInspectionById(String id) {
        log.info("getGustatoryInspectionById :: Buscando uma inspeção gustativa pelo id: {}", id);
        GustatoryInspectionOutputDTO gustatoryInspection = gustatoryInspectionClient.getGustatoryInspectionById(id);
        if (Objects.isNull(gustatoryInspection)) {
            log.error("getGustatoryInspectionById :: Ocorreu um erro ao buscar a inspeção gustativa: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return gustatoryInspection;
    }

    @Override
    public GustatoryInspectionOutputDTO updateGustatoryInspection(String id, GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        log.info("updateGustatoryInspection :: Atualizando uma inspeção gustativa com os dados: {}", gustatoryInspectionInputDTO.toString());
        try {
            return gustatoryInspectionClient.updateGustatoryInspection(id, gustatoryInspectionInputDTO);
        } catch (Exception e) {
            log.error("updateGustatoryInspection :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deleteGustatoryInspection(String id) {
        log.info("deleteGustatoryInspection :: Deletando uma inspeção gustativa pelo id: {}", id);
        try {
            gustatoryInspectionClient.deleteGustatoryInspection(id);
        } catch (Exception e) {
            log.error("deleteGustatoryInspection :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }
}
