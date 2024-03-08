package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.degustacao.AromasService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.degustacao.AromasClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.AromasInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.AromasOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AromasServiceImpl implements AromasService {

    private final AromasClient aromasClient;

    @Override
    public AromasOutputDTO createAromas(AromasInputDTO aromasInputDTO) {
        log.info("createAromas :: Registrando um novo aroma com os dados: {}", aromasInputDTO.toString());
        try {
            return aromasClient.createAromas(aromasInputDTO);
        } catch (Exception e) {
            log.error("createAromas :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<AromasOutputDTO> getAllAromas() {
        log.info("getAllAromas :: Listando todos os aromas");
        List<AromasOutputDTO> aromas = aromasClient.getAllAromas();
        if (aromas.isEmpty()) {
            log.error("getAllAromas :: Ocorreu um erro ao listar os aromas: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return aromas;
    }

    @Override
    public AromasOutputDTO getAromasById(String id) {
        log.info("getAromasById :: Buscando aroma pelo id: {}", id);
        AromasOutputDTO aroma = aromasClient.getAromasById(id);
        if (Objects.isNull(aroma)) {
            log.error("getAromasById :: Ocorreu um erro ao buscar o aroma: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return aroma;
    }

    @Override
    public AromasOutputDTO updateAromas(String id, AromasInputDTO aromasInputDTO) {
        log.info("updateAromas :: Atualizando aroma com os dados: {}", aromasInputDTO.toString());
        try {
            return aromasClient.updateAromas(id, aromasInputDTO);
        } catch (Exception e) {
            log.error("updateAromas :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deleteAromas(String id) {
        log.info("deleteAromas :: Deletando aroma pelo id: {}", id);
        try {
            aromasClient.deleteAromas(id);
        } catch (Exception e) {
            log.error("deleteAromas :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }
}
