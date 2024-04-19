package com.vinhonotas.bff.client.harmonizacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.harmonizacao.PairingResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pairing", url = "${harmonizacao-api.url}", configuration = FeignConfig.class)
public interface PairingClient {

    @GetMapping("/pairing/information")
    ResponseEntity<PairingResponseDTO> getWineInformation(@RequestParam(value = "wine") WineInputDTO wine);

    @GetMapping("/pairing/pairings")
    ResponseEntity<PairingResponseDTO> getWinePairing(@RequestParam(value = "wine") WineInputDTO wine);

    @GetMapping("/pairing/menu")
    ResponseEntity<PairingResponseDTO> getMenuPairing(@RequestParam(value = "wine") WineInputDTO wine);

}
