package com.vinhonotas.degustacao.interfaces.controllers;

import com.vinhonotas.degustacao.application.converters.TastingConverter;
import com.vinhonotas.degustacao.application.services.TastingService;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingOutputDTO;
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
@RequestMapping(value = "/api/v1/tasting")
@Tag(name = "Tasting", description = "Operações relacionadas a degustação do vinho")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class TastingController {

    private final TastingService tastingService;
    private final TastingConverter tastingConverter;

    @Operation(summary = "Cadastrar uma degustação")
    @PostMapping
    public ResponseEntity<TastingOutputDTO> createTasting(@Valid @RequestBody TastingInputDTO tastingInputDTO) {
        return ResponseEntity.ok(tastingConverter.toOutputDTO(tastingService.create(tastingInputDTO)));
    }

    @Operation(summary = "Retorna uma lista com todas as degustações cadastradas")
    @GetMapping
    public ResponseEntity<List<TastingOutputDTO>> getAllTastings() {
        return ResponseEntity.ok(tastingConverter.toOutputDTOList(tastingService.getAll()));
    }

    @Operation(summary = "Retorna uma degustação cadastrada pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<TastingOutputDTO> getTastingById(@PathVariable String id) {
        return ResponseEntity.ok(tastingConverter.toOutputDTO(tastingService.getById(UUID.fromString(id))));
    }

    @Operation(summary = "Atualiza uma degustação cadastrada pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<TastingOutputDTO> updateTasting(@PathVariable String id,
                                                              @Valid @RequestBody TastingInputDTO tastingInputDTO) {
        return ResponseEntity.ok(tastingConverter
                .toOutputDTOUpdate(tastingService.update(UUID.fromString(id), tastingInputDTO),
                        UUID.fromString(id),
                        tastingConverter.toOutputDTO(tastingService.update(UUID.fromString(id),
                                tastingInputDTO))));
    }

    @Operation(summary = "Deleta uma degustação cadastrada pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTasting(@PathVariable String id) {
        tastingService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
