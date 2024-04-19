package com.vinhonotas.bff.application.services.harmonizacao;

import com.vinhonotas.bff.interfaces.dtos.inputs.harmonizacao.PairingInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.harmonizacao.PairingResponseDTO;

public interface PairingService {

    PairingResponseDTO getWineInformation(PairingInputDTO wine);
    PairingResponseDTO getWinePairing(PairingInputDTO wine);
    PairingResponseDTO getMenuPairing(PairingInputDTO wine);

}
