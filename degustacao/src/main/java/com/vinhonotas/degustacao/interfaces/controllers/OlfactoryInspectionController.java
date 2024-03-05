package com.vinhonotas.degustacao.interfaces.controllers;

import com.vinhonotas.degustacao.application.converters.OlfactoryInspectionConverter;
import com.vinhonotas.degustacao.application.services.OlfactoryInspectionService;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.OlfactoryInspectionOutputDTO;
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
@RequestMapping(value = "/api/v1/olfactory-inspection")
@Tag(name = "Olfactory Inspection", description = "Operações relacionadas a percepção olfativa na degustação do vinho")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class OlfactoryInspectionController {

    private final OlfactoryInspectionService olfactoryInspectionService;
    private final OlfactoryInspectionConverter olfactoryInspectionConverter;

    @Operation(summary = "Cadastrar percepções olfativas")
    @PostMapping
    public ResponseEntity<OlfactoryInspectionOutputDTO> createOlafactoryInspection(@Valid @RequestBody OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        return ResponseEntity.ok(olfactoryInspectionConverter.toOutputDTO(olfactoryInspectionService.create(olfactoryInspectionInputDTO)));
    }

    @Operation(summary = "Retorna uma lista com todas as percepções olfativas cadastradas")
    @GetMapping
    public ResponseEntity<List<OlfactoryInspectionOutputDTO>> getAllOlfactoryInspections() {
        return ResponseEntity.ok(olfactoryInspectionConverter.toOutputDTOList(olfactoryInspectionService.getAll()));
    }

    @Operation(summary = "Retorna uma percepção olfativa cadastrada pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<OlfactoryInspectionOutputDTO> getOlfactoryInspectionById(@PathVariable String id) {
        return ResponseEntity.ok(olfactoryInspectionConverter.toOutputDTO(olfactoryInspectionService.getById(UUID.fromString(id))));
    }

    @Operation(summary = "Atualiza uma percepção olfativa cadastrada pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<OlfactoryInspectionOutputDTO> updateOlfactoryInspection(@PathVariable String id,
                                          @Valid @RequestBody OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        return ResponseEntity.ok(olfactoryInspectionConverter
                .toOutputDTOUpdate(olfactoryInspectionService.update(UUID.fromString(id), olfactoryInspectionInputDTO),
                                UUID.fromString(id),
                                olfactoryInspectionConverter.toOutputDTO(olfactoryInspectionService.update(UUID.fromString(id),
                                olfactoryInspectionInputDTO))));
    }

    @Operation(summary = "Deleta uma percepção olfativa cadastrada pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOlfactoryInspection(@PathVariable String id) {
        olfactoryInspectionService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
