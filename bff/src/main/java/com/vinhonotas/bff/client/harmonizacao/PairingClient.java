package com.vinhonotas.bff.client.harmonizacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.harmonizacao.PairingInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.harmonizacao.PairingResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pairing", url = "${harmonizacao-api.url}", configuration = FeignConfig.class)
public interface PairingClient {

    @GetMapping("/pairing/information")
    PairingResponseDTO getWineInformation(@RequestParam(value = "wine") PairingInputDTO wine);

    @GetMapping("/pairing/pairings")
    PairingResponseDTO getWinePairing(@RequestParam(value = "wine") PairingInputDTO wine);

    @GetMapping("/pairing/menu")
    PairingResponseDTO getMenuPairing(@RequestParam(value = "wine") PairingInputDTO wine);

}
