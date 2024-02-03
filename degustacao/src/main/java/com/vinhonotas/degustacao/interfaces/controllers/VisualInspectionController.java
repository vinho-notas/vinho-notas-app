package com.vinhonotas.degustacao.interfaces.controllers;

import com.vinhonotas.degustacao.application.converters.VisualInspectionConverter;
import com.vinhonotas.degustacao.application.services.VisualInspectionService;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.VisualInspectionOutputDTO;
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
@RequestMapping(value = "/api/v1/visual-inspection")
@Tag(name = "Visual Inspection", description = "Operações relacionadas a percepção visual na degustação do vinho")
public class VisualInspectionController {

    private final VisualInspectionService visualInspectionService;
    private final VisualInspectionConverter visualInspectionConverter;

    @Operation(summary = "Cadastrar percepções visuais")
    @PostMapping
    public ResponseEntity<VisualInspectionOutputDTO> createVisualInspection(@Valid @RequestBody VisualInspectionInputDTO visualInspectionInputDTO) {
        return ResponseEntity.ok(visualInspectionConverter.toOutputDTO(visualInspectionService.create(visualInspectionInputDTO)));
    }

    @Operation(summary = "Retorna uma lista com todas as percepções visuais cadastradas")
    @GetMapping
    public ResponseEntity<List<VisualInspectionOutputDTO>> getAllVisualInspections() {
        return ResponseEntity.ok(visualInspectionConverter.toOutputDTOList(visualInspectionService.getAll()));
    }

    @Operation(summary = "Retorna uma percepção visual cadastrada pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<VisualInspectionOutputDTO> getVisualInspectionById(@PathVariable String id) {
        return ResponseEntity.ok(visualInspectionConverter.toOutputDTO(visualInspectionService.getById(UUID.fromString(id))));
    }

    @Operation(summary = "Atualiza uma percepção visual cadastrada pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<VisualInspectionOutputDTO> updateVisualInspection(@PathVariable String id,
                                          @Valid @RequestBody VisualInspectionInputDTO visualInspectionInputDTO) {
        return ResponseEntity.ok(visualInspectionConverter
                .toOutputDTOUpdate(visualInspectionService.update(UUID.fromString(id), visualInspectionInputDTO),
                                UUID.fromString(id),
                                visualInspectionConverter.toOutputDTO(visualInspectionService.update(UUID.fromString(id),
                                visualInspectionInputDTO))));
    }

    @Operation(summary = "Deleta uma percepção visual cadastrada pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisualInspection(@PathVariable String id) {
        visualInspectionService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
