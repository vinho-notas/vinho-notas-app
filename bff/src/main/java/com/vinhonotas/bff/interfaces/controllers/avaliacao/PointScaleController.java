package com.vinhonotas.bff.interfaces.controllers.avaliacao;

import com.vinhonotas.bff.application.services.avaliacao.PointScaleService;
import com.vinhonotas.bff.interfaces.dtos.inputs.avaliacao.PointScaleInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.avaliacao.PointScaleOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/point-scales")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class PointScaleController {

    private final PointScaleService pointScaleService;

    @PostMapping
    public ResponseEntity<PointScaleOutputDTO> createPointScale(@Valid @RequestBody PointScaleInputDTO pointScaleInputDTO) {
        return ResponseEntity.ok(pointScaleService.createPointScale(pointScaleInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<PointScaleOutputDTO>> getAllPointScale() {
        return ResponseEntity.ok(pointScaleService.getAllPointScale());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointScaleOutputDTO> getPointScaleById(@PathVariable("id") String id) {
        return ResponseEntity.ok(pointScaleService.getPointScaleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PointScaleOutputDTO> updatePointScale(@PathVariable("id") String id, @Valid @RequestBody PointScaleInputDTO pointScaleInputDTO) {
        return ResponseEntity.ok(pointScaleService.updatePointScale(id, pointScaleInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePointScale(@PathVariable("id") String id) {
        pointScaleService.deletePointScale(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllPointScale(@RequestBody List<String> ids) {
        ids.forEach(pointScaleService::deletePointScale);
        return ResponseEntity.noContent().build();
    }

}
