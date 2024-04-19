package com.vinhonotas.bff.application.services.harmonizacao.impl;

import com.vinhonotas.bff.application.services.harmonizacao.PairingService;
import com.vinhonotas.bff.client.harmonizacao.PairingClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
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
    public PairingResponseDTO getWineInformation(WineInputDTO wine) {
        try {
            log.info("getWineInformation :: Buscando informações sobre o vinho: {}", wine);
            return pairingClient.getWineInformation(wine).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PairingResponseDTO getWinePairing(WineInputDTO wine) {
        try {
            log.info("getWinePairing :: Buscando harmonizações para o vinho: {}", wine);
            return pairingClient.getWinePairing(wine).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PairingResponseDTO getMenuPairing(WineInputDTO wine) {
        try {
            log.info("getMenuPairing :: Buscando menu harmonizado para o vinho: {}", wine);
            return pairingClient.getMenuPairing(wine).getBody();
        } catch (Exception e) {
            return null;
        }
    }
    
}
