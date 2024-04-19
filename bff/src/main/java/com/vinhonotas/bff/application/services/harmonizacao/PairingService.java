package com.vinhonotas.bff.application.services.harmonizacao;

import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.harmonizacao.PairingResponseDTO;

public interface PairingService {

    PairingResponseDTO getWineInformation(WineInputDTO wine);
    PairingResponseDTO getWinePairing(WineInputDTO wine);
    PairingResponseDTO getMenuPairing(WineInputDTO wine);

}
