package com.vinhonotas.bff.interfaces.controllers.vinho;

import com.vinhonotas.bff.application.services.vinho.WineService;
import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.vinho.WineOutputDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RateLimiter(name = "rateLimiter")
@RestController
@RequestMapping("/api/v1/wines")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@Tag(name = "Vinhos", description = "Operações relacionadas a vinhos")
public class WineController {

    private final WineService wineService;

    @Operation(summary = "Cria um vinho")
    @PostMapping
    public ResponseEntity<WineOutputDTO> createWine(@Valid @RequestBody WineInputDTO wineInputDTO) {
        return ResponseEntity.ok(wineService.createWine(wineInputDTO));
    }

    @Operation(summary = "Retorna todos os vinhos")
    @GetMapping
    public ResponseEntity<List<WineOutputDTO>> getAllWines() {
        return ResponseEntity.ok(wineService.getAllWines());
    }

    @Operation(summary = "Retorna um vinho pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<WineOutputDTO> getWineById(@PathVariable("id") String id) {
        return ResponseEntity.ok(wineService.getWineById(id));
    }

    @Operation(summary = "Atualiza um vinho pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<WineOutputDTO> updateWine(@PathVariable("id") String id, @Valid @RequestBody WineInputDTO wineInputDTO) {
        return ResponseEntity.ok(wineService.updateWine(id, wineInputDTO));
    }

    @Operation(summary = "Deleta um vinho pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWine(@PathVariable("id") String id) {
        wineService.deleteWine(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta uma lista de vinhos")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllWine(@RequestBody List<String> ids) {
        ids.forEach(wineService::deleteWine);
        return ResponseEntity.noContent().build();
    }

}
