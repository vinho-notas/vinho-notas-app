package com.vinhonotas.bff.interfaces.controllers.harmonizacao;

import com.vinhonotas.bff.application.services.harmonizacao.PairingService;
import com.vinhonotas.bff.interfaces.dtos.inputs.harmonizacao.PairingInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.harmonizacao.PairingResponseDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RateLimiter(name = "rateLimiter")
@RestController
@RequestMapping("/api/v1/pairing")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class PairingController {

    private final PairingService pairingService;

    @GetMapping("/information")
    public ResponseEntity<PairingResponseDTO> getWineInformation(@RequestParam(value = "wine") PairingInputDTO wine) {
        PairingResponseDTO wineInformation = pairingService.getWineInformation(wine);
        return ResponseEntity.ok(wineInformation);
    }

    @GetMapping("/pairings")
    public ResponseEntity<PairingResponseDTO> getWinePairing(@RequestParam(value = "wine") PairingInputDTO wine) {
        PairingResponseDTO winePairing = pairingService.getWinePairing(wine);
        return ResponseEntity.ok(winePairing);
    }

    @GetMapping("/menu")
    public ResponseEntity<PairingResponseDTO> getMenuPairing(@RequestParam(value = "wine") PairingInputDTO wine) {
        PairingResponseDTO menuPairing = pairingService.getMenuPairing(wine);
        return ResponseEntity.ok(menuPairing);
    }

}
