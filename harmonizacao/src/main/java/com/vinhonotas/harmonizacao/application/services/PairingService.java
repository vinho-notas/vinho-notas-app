package com.vinhonotas.harmonizacao.application.services;

import com.vinhonotas.harmonizacao.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.harmonizacao.interfaces.dtos.outputs.PairingResponseDTO;

public interface PairingService {

    PairingResponseDTO getWineInformation(WineInputDTO wine);
    PairingResponseDTO getWinePairing(WineInputDTO wine);
    PairingResponseDTO getMenuPairing(WineInputDTO wine);

}
