package com.vinhonotas.bff.interfaces.controllers.degustacao;

import com.vinhonotas.bff.application.services.degustacao.VisualInspectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visual-inspection")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class VisualInspectionController {

    private final VisualInspectionService visualInspectionService;

    @PostMapping
    public ResponseEntity<VisualInspectionOutputDTO> createVisualInspection(@Valid @RequestBody VisualInspectionInputDTO visualInspectionInputDTO) {
        return ResponseEntity.ok(visualInspectionService.createVisualInspection(visualInspectionInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<VisualInspectionOutputDTO>> getAllVisualInspections() {
        return ResponseEntity.ok(visualInspectionService.getAllVisualInspections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualInspectionOutputDTO> getVisualInspectionById(@PathVariable("id") String id) {
        return ResponseEntity.ok(visualInspectionService.getVisualInspectionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisualInspectionOutputDTO> updateVisualInspection(@PathVariable ("id") String id, @Valid @RequestBody VisualInspectionInputDTO visualInspectionInputDTO) {
        return ResponseEntity.ok(visualInspectionService.updateVisualInspection(id, visualInspectionInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisualInspection(@PathVariable ("id") String id) {
        visualInspectionService.deleteVisualInspection(id);
        return ResponseEntity.noContent().build();
    }

}
