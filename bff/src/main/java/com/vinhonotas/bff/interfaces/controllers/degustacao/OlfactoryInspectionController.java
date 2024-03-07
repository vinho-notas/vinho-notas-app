package com.vinhonotas.bff.interfaces.controllers.degustacao;

import com.vinhonotas.bff.application.services.degustacao.OlfactoryInspectionService;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.OlfactoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.OlfactoryInspectionOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/olfactory-inspection")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class OlfactoryInspectionController {

    private final OlfactoryInspectionService olfactoryInspectionService;

    @PostMapping
    public ResponseEntity<OlfactoryInspectionOutputDTO> createOlafactoryInspection(@Valid @RequestBody OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        return ResponseEntity.ok(olfactoryInspectionService.createOlfactoryInspection(olfactoryInspectionInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<OlfactoryInspectionOutputDTO>> getAllOlfactoryInspections() {
        return ResponseEntity.ok(olfactoryInspectionService.getAllOlfactoryInspections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OlfactoryInspectionOutputDTO> getOlfactoryInspectionById(@PathVariable("id") String id) {
        return ResponseEntity.ok(olfactoryInspectionService.getOlfactoryInspectionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OlfactoryInspectionOutputDTO> updateOlfactoryInspection(@PathVariable ("id") String id, @Valid @RequestBody OlfactoryInspectionInputDTO olfactoryInspectionInputDTO) {
        return ResponseEntity.ok(olfactoryInspectionService.updateOlfactoryInspection(id, olfactoryInspectionInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOlfactoryInspection(@PathVariable ("id") String id) {
        olfactoryInspectionService.deleteOlfactoryInspection(id);
        return ResponseEntity.noContent().build();
    }

}
