package com.vinhonotas.degustacao.interfaces.controllers;

import com.vinhonotas.degustacao.application.converters.GustatoryInspectionConverter;
import com.vinhonotas.degustacao.application.services.GustatoryInspectionService;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.GustatoryInspectionOutputDTO;
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
@RequestMapping(value = "/api/v1/gustatory-inspection")
@Tag(name = "Gustatory Inspection", description = "Operações relacionadas a percepção gustativa na degustação do vinho")
public class GustatoryInspectionController {

    private final GustatoryInspectionService gustatoryInspectionService;
    private final GustatoryInspectionConverter gustatoryInspectionConverter;

    @Operation(summary = "Cadastrar percepções gustativas")
    @PostMapping
    public ResponseEntity<GustatoryInspectionOutputDTO> createGustatoryInspection(@Valid @RequestBody GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        return ResponseEntity.ok(gustatoryInspectionConverter.toOutputDTO(gustatoryInspectionService.create(gustatoryInspectionInputDTO)));
    }

    @Operation(summary = "Retorna uma lista com todas as percepções gustativas cadastradas")
    @GetMapping
    public ResponseEntity<List<GustatoryInspectionOutputDTO>> getAllGustatoryInspections() {
        return ResponseEntity.ok(gustatoryInspectionConverter.toOutputDTOList(gustatoryInspectionService.getAll()));
    }

    @Operation(summary = "Retorna uma percepção gustativa cadastrada pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<GustatoryInspectionOutputDTO> getGustatoryInspectionById(@PathVariable String id) {
        return ResponseEntity.ok(gustatoryInspectionConverter.toOutputDTO(gustatoryInspectionService.getById(UUID.fromString(id))));
    }

    @Operation(summary = "Atualiza uma percepção gustativa cadastrada pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<GustatoryInspectionOutputDTO> updateGustatoryInspection(@PathVariable String id,
                                          @Valid @RequestBody GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        return ResponseEntity.ok(gustatoryInspectionConverter
                .toOutputDTOUpdate(gustatoryInspectionService.update(UUID.fromString(id), gustatoryInspectionInputDTO),
                                UUID.fromString(id),
                                gustatoryInspectionConverter.toOutputDTO(gustatoryInspectionService.update(UUID.fromString(id),
                                gustatoryInspectionInputDTO))));
    }

    @Operation(summary = "Deleta uma percepção gustativa cadastrada pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGustatoryInspection(@PathVariable String id) {
        gustatoryInspectionService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
