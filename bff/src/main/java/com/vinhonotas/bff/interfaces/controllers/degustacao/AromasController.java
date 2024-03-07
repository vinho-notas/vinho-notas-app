package com.vinhonotas.bff.interfaces.controllers.degustacao;

import com.vinhonotas.bff.application.services.degustacao.AromasService;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.AromasInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.AromasOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aromas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class AromasController {

    private final AromasService aromasService;

    @PostMapping
    public ResponseEntity<AromasOutputDTO> createAromas(@Valid @RequestBody AromasInputDTO aromasInputDTO) {
        return ResponseEntity.ok(aromasService.createAromas(aromasInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<AromasOutputDTO>> getAllAromas() {
        return ResponseEntity.ok(aromasService.getAllAromas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AromasOutputDTO> getAromasById(@PathVariable("id") String id) {
        return ResponseEntity.ok(aromasService.getAromasById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AromasOutputDTO> updateAromas(@PathVariable ("id") String id, @Valid @RequestBody AromasInputDTO aromasInputDTO) {
        return ResponseEntity.ok(aromasService.updateAromas(id, aromasInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAromas(@PathVariable ("id") String id) {
        aromasService.deleteAromas(id);
        return ResponseEntity.noContent().build();
    }

}
