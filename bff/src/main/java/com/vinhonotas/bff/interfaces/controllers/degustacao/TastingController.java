package com.vinhonotas.bff.interfaces.controllers.degustacao;

import com.vinhonotas.bff.application.services.degustacao.TastingService;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasting")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class TastingController {

    private final TastingService tastingService;

    @PostMapping
    public ResponseEntity<TastingOutputDTO> createTasting(@Valid @RequestBody TastingInputDTO tastingInputDTO) {
        return ResponseEntity.ok(tastingService.createTasting(tastingInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<TastingOutputDTO>> getAllTastings() {
        return ResponseEntity.ok(tastingService.getAllTastings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TastingOutputDTO> getTastingById(@PathVariable ("id") String id) {
        return ResponseEntity.ok(tastingService.getTastingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TastingOutputDTO> updateTasting(@PathVariable ("id") String id, @Valid @RequestBody TastingInputDTO tastingInputDTO) {
        return ResponseEntity.ok(tastingService.updateTasting(id, tastingInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTasting(@PathVariable String id) {
        tastingService.deleteTasting(id);
        return ResponseEntity.noContent().build();
    }

}
