package com.vinhonotas.degustacao.interfaces.controllers;

import com.vinhonotas.degustacao.application.converters.AromasConverter;
import com.vinhonotas.degustacao.application.services.AromasService;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.AromasOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/aromas")
@Tag(name = "Aromas", description = "Operações relacionadas a percepção de Aromas dos vinhos")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class AromasController {

    private final AromasService aromasService;
    private final AromasConverter aromasConverter;

    @Operation(summary = "Cadastra os aromas")
    @PostMapping
    public ResponseEntity<AromasOutputDTO> createAromas(@Valid @RequestBody AromasInputDTO aromasInputDTO) {
        return ResponseEntity.ok(aromasConverter.toOutputDTO(aromasService.create(aromasInputDTO)));
    }

    @Operation(summary = "Retorna uma lista com todos os aromas cadastrados")
    @GetMapping
    public ResponseEntity<List<AromasOutputDTO>> getAllAromas() {
        return ResponseEntity.ok(aromasConverter.toOutputDTOList(aromasService.getAll()));
    }

    @Operation(summary = "Retorna um aroma pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<AromasOutputDTO> getAromasById(@PathVariable String id) {
        return ResponseEntity.ok(aromasConverter.toOutputDTO(aromasService.getById(UUID.fromString(id))));
    }

    @Operation(summary = "Atualiza um aroma pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<AromasOutputDTO> updateAromas(@PathVariable String id, @Valid @RequestBody AromasInputDTO aromasInputDTO) {
        return ResponseEntity.ok(aromasConverter.toOutputDTO(aromasService.update(UUID.fromString(id), aromasInputDTO)));
    }

    @Operation(summary = "Deleta um aroma pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAromas(@PathVariable String id) {
        aromasService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
