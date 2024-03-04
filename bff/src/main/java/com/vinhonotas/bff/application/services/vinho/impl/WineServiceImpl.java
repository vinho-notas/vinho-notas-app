package com.vinhonotas.bff.application.services.vinho.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.application.services.vinho.WineService;
import com.vinhonotas.bff.client.vinho.WineClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.vinho.WineOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class WineServiceImpl implements WineService {

    private final WineClient wineClient;

    @Override
    public WineOutputDTO createWine(WineInputDTO wineInputDTO) {
        log.info("createWine :: Registrando um vinho com os dados: {}", wineInputDTO.toString());
        try {
            return wineClient.createWine(wineInputDTO);
        } catch (Exception e) {
            log.error("createWine :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<WineOutputDTO> getAllWines() {
        log.info("getAllWines :: Listando todos os vinhos");
        List<WineOutputDTO> wines = wineClient.getAllWines();
        if (wines.isEmpty()) {
            log.error("getAllWines :: Ocorreu um erro ao listar os vinhos: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return wines;
    }

    @Override
    public WineOutputDTO getWineById(String id) {
        log.info("getWineById :: Buscando vinho pelo id: {}", id);
        WineOutputDTO wine = wineClient.getWineById(id);
        if (Objects.isNull(wine)) {
            log.error("getWineById :: Ocorreu um erro ao buscar o vinho: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return wine;
    }

    @Override
    public WineOutputDTO updateWine(String id, WineInputDTO wineInputDTO) {
        log.info("updateWine :: Atualizando vinho com os dados: {}", wineInputDTO.toString());
        try {
            return wineClient.updateWine(id, wineInputDTO);
        } catch (Exception e) {
            log.error("updateWine :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deleteWine(String id) {
        log.info("deleteWine :: Deletando vinho pelo id: {}", id);
        try {
            wineClient.deleteWine(id);
        } catch (Exception e) {
            log.error("deleteWine :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }

}
