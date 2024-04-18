package com.vinhonotas.harmonizacao.interfaces.controllers;

import com.vinhonotas.harmonizacao.application.services.PairingService;
import com.vinhonotas.harmonizacao.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.harmonizacao.interfaces.dtos.outputs.PairingResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pairing")
@RequiredArgsConstructor
@Slf4j
public class PairingController {

    private final PairingService pairingService;

    @GetMapping("/information")
    public ResponseEntity<PairingResponseDTO> getWineInformation(@RequestParam(value = "wine") WineInputDTO wine) {
        PairingResponseDTO wineInformation = pairingService.getWineInformation(wine);
        return ResponseEntity.ok(wineInformation);
    }

    @GetMapping("/pairings")
    public ResponseEntity<PairingResponseDTO> getWinePairing(@RequestParam(value = "wine") WineInputDTO wine) {
        PairingResponseDTO winePairing = pairingService.getWinePairing(wine);
        return ResponseEntity.ok(winePairing);
    }

    @GetMapping("/menu")
    public ResponseEntity<PairingResponseDTO> getMenuPairing(@RequestParam(value = "wine") WineInputDTO wine) {
        PairingResponseDTO menuPairing = pairingService.getMenuPairing(wine);
        return ResponseEntity.ok(menuPairing);
    }

}
