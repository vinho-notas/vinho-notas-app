package com.vinhonotas.bff.application.services.harmonizacao.impl;

import com.vinhonotas.bff.application.services.harmonizacao.PairingService;
import com.vinhonotas.bff.client.harmonizacao.PairingClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.harmonizacao.PairingInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.harmonizacao.PairingResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PairingServiceImpl implements PairingService {

    private final PairingClient pairingClient;

    @Override
    public PairingResponseDTO getWineInformation(PairingInputDTO wine) {
        try {
            log.info("getWineInformation :: Buscando informações sobre o vinho: {}", wine);
            return pairingClient.getWineInformation(wine);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PairingResponseDTO getWinePairing(PairingInputDTO wine) {
        try {
            log.info("getWinePairing :: Buscando harmonizações para o vinho: {}", wine);
            return pairingClient.getWinePairing(wine);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PairingResponseDTO getMenuPairing(PairingInputDTO wine) {
        try {
            log.info("getMenuPairing :: Buscando menu harmonizado para o vinho: {}", wine);
            return pairingClient.getMenuPairing(wine);
        } catch (Exception e) {
            return null;
        }
    }

}
