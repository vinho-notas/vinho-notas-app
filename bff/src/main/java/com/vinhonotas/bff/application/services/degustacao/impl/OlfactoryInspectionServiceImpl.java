package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.degustacao.OlfactoryInspectionService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.degustacao.OlfactoryInspectionClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.OlfactoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.OlfactoryInspectionOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class OlfactoryInspectionServiceImpl implements OlfactoryInspectionService {

    private final OlfactoryInspectionClient olfactoryInspectionClient;

    @Override
    public OlfactoryInspectionOutputDTO createOlafactoryInspection(OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        log.info("createOlafactoryInspection :: Registrando uma nova inspeção olfativa com os dados: {}", olfactoryInspectionInputDTO.toString());
        try {
            return olfactoryInspectionClient.createOlafactoryInspection(olfactoryInspectionInputDTO);
        } catch (Exception e) {
            log.error("createOlafactoryInspection :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<OlfactoryInspectionOutputDTO> getAllOlfactoryInspections() {
        log.info("getAllOlfactoryInspections :: Listando todas as inspeções olfativas");
        List<OlfactoryInspectionOutputDTO> allOlfactoryInspections = olfactoryInspectionClient.getAllOlfactoryInspections();
        if (allOlfactoryInspections.isEmpty()) {
            log.error("getAllOlfactoryInspections :: Ocorreu um erro ao listar as inspeções olfativas: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return allOlfactoryInspections;
    }

    @Override
    public OlfactoryInspectionOutputDTO getOlfactoryInspectionById(String id) {
        log.info("getOlfactoryInspectionById :: Buscando inspeção olfativa pelo id: {}", id);
        OlfactoryInspectionOutputDTO allOlfactoryInspection = olfactoryInspectionClient.getOlfactoryInspectionById(id);
        if (Objects.isNull(allOlfactoryInspection)) {
            log.error("getOlfactoryInspectionById :: Ocorreu um erro ao buscar a inspeção olfativa: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return allOlfactoryInspection;
    }

    @Override
    public OlfactoryInspectionOutputDTO updateOlfactoryInspection(String id, OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        log.info("updateOlfactoryInspection :: Atualizando inspeção olfativa com os dados: {}", olfactoryInspectionInputDTO.toString());
        try {
            return olfactoryInspectionClient.updateOlfactoryInspection(id, olfactoryInspectionInputDTO);
        } catch (Exception e) {
            log.error("updateOlfactoryInspection :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deleteOlfactoryInspection(String id) {
        log.info("deleteOlfactoryInspection :: Deletando inspeção olfativa pelo id: {}", id);
        try {
            olfactoryInspectionClient.deleteOlfactoryInspection(id);
        } catch (Exception e) {
            log.error("deleteOlfactoryInspection :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }

}
