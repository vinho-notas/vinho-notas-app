package com.vinhonotas.bff.interfaces.controllers.vinho;

import com.vinhonotas.bff.application.services.vinho.WineService;
import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.vinho.WineOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wines")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class WineController {

    private final WineService wineService;

    @PostMapping
    public ResponseEntity<WineOutputDTO> createWine(@Valid @RequestBody WineInputDTO wineInputDTO) {
        return ResponseEntity.ok(wineService.createWine(wineInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<WineOutputDTO>> getAllWines() {
        return ResponseEntity.ok(wineService.getAllWines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WineOutputDTO> getWineById(@PathVariable("id") String id) {
        return ResponseEntity.ok(wineService.getWineById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WineOutputDTO> updateWine(@PathVariable("id") String id, @Valid @RequestBody WineInputDTO wineInputDTO) {
        return ResponseEntity.ok(wineService.updateWine(id, wineInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWine(@PathVariable("id") String id) {
        wineService.deleteWine(id);
        return ResponseEntity.noContent().build();
    }

}
