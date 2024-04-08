package com.vinhonotas.avaliacao.interfaces.controllers;

import com.vinhonotas.avaliacao.application.converters.PointScaleConverter;
import com.vinhonotas.avaliacao.application.services.PointScaleService;
import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import com.vinhonotas.avaliacao.interfaces.dtos.inputs.PointScaleInputDTO;
import com.vinhonotas.avaliacao.interfaces.dtos.outputs.PointScaleOutputDTO;
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
@RequestMapping(value = "/api/v1/point-scales")
@Tag(name = "Point Scales", description = "Operações relacionadas a avaliação de vinhos")
public class PointScaleController {

    private final PointScaleService pointScaleService;
    private final PointScaleConverter pointScaleConverter;

    @Operation(summary = "Cria uma avaliação de vinho")
    @PostMapping
    public ResponseEntity<PointScaleOutputDTO> createPointScale(@Valid @RequestBody PointScaleInputDTO pointScaleInputDTO) {
        return ResponseEntity.ok(pointScaleConverter.toOutputDTO(pointScaleService.create(pointScaleInputDTO)));
    }

    @Operation(summary = "Retorna todas as avaliações de vinho")
    @GetMapping
    public ResponseEntity<List<PointScaleOutputDTO>> getAllPointScale() {
        return ResponseEntity.ok(pointScaleConverter.toOutputDTOList(pointScaleService.getAll()));
    }

    @Operation(summary = "Retorna uma avaliação de vinho pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<PointScaleOutputDTO> getPointScaleById(@PathVariable("id") String id) {
        return ResponseEntity.ok(pointScaleConverter.toOutputDTO(pointScaleService.getById(UUID.fromString(id))));
    }

    @Operation(summary = "Atualiza uma avaliação de vinho pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<PointScaleOutputDTO> updatePointScale(@PathVariable("id") String id, @Valid @RequestBody PointScaleInputDTO pointScaleInputDTO) {
        PointScaleEntity pointScaleUpdated = pointScaleService.update(UUID.fromString(id), pointScaleInputDTO);
        PointScaleOutputDTO pointScaleOutputDTO = pointScaleConverter.toOutputDTO(pointScaleUpdated);
        return ResponseEntity.ok(pointScaleConverter.toOutputDTOUpdate(pointScaleUpdated, UUID.fromString(id), pointScaleOutputDTO));
    }

    @Operation(summary = "Deleta uma avaliação de vinho pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePointScale(@PathVariable("id") String id) {
        pointScaleService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
