package com.vinhonotas.bff.interfaces.controllers.avaliacao;

import com.vinhonotas.bff.application.services.avaliacao.PointScaleService;
import com.vinhonotas.bff.interfaces.dtos.inputs.avaliacao.PointScaleInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.avaliacao.PointScaleOutputDTO;
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
@RequestMapping("/api/v1/point-scales")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@Tag(name = "Point Scales", description = "Operações relacionadas a avaliação de vinhos")
public class PointScaleController {

    private final PointScaleService pointScaleService;

    @Operation(summary = "Cria uma avaliação de vinho")
    @PostMapping
    public ResponseEntity<PointScaleOutputDTO> createPointScale(@Valid @RequestBody PointScaleInputDTO pointScaleInputDTO) {
        return ResponseEntity.ok(pointScaleService.createPointScale(pointScaleInputDTO));
    }

    @Operation(summary = "Retorna todas as avaliações de vinho")
    @GetMapping
    public ResponseEntity<List<PointScaleOutputDTO>> getAllPointScale() {
        return ResponseEntity.ok(pointScaleService.getAllPointScale());
    }

    @Operation(summary = "Retorna uma avaliação de vinho pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<PointScaleOutputDTO> getPointScaleById(@PathVariable("id") String id) {
        return ResponseEntity.ok(pointScaleService.getPointScaleById(id));
    }

    @Operation(summary = "Atualiza uma avaliação de vinho pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<PointScaleOutputDTO> updatePointScale(@PathVariable("id") String id, @Valid @RequestBody PointScaleInputDTO pointScaleInputDTO) {
        return ResponseEntity.ok(pointScaleService.updatePointScale(id, pointScaleInputDTO));
    }

    @Operation(summary = "Deleta uma avaliação de vinho pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePointScale(@PathVariable("id") String id) {
        pointScaleService.deletePointScale(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta uma lista de avaliação de vinho")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllPointScale(@RequestBody List<String> ids) {
        ids.forEach(pointScaleService::deletePointScale);
        return ResponseEntity.noContent().build();
    }

}
