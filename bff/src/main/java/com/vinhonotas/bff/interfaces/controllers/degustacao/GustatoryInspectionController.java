package com.vinhonotas.bff.interfaces.controllers.degustacao;

import com.vinhonotas.bff.application.services.degustacao.GustatoryInspectionService;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.GustatoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.GustatoryInspectionOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gustatory-inspection")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class GustatoryInspectionController {

    private final GustatoryInspectionService gustatoryInspectionService;

    @PostMapping
    public ResponseEntity<GustatoryInspectionOutputDTO> createGustatoryInspection(@Valid @RequestBody GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        return ResponseEntity.ok(gustatoryInspectionService.createGustatoryInspection(gustatoryInspectionInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<GustatoryInspectionOutputDTO>> getAllGustatoryInspections() {
        return ResponseEntity.ok(gustatoryInspectionService.getAllGustatoryInspections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GustatoryInspectionOutputDTO> getGustatoryInspectionById(@PathVariable("id") String id) {
        return ResponseEntity.ok(gustatoryInspectionService.getGustatoryInspectionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GustatoryInspectionOutputDTO> updateGustatoryInspection(@PathVariable ("id") String id,
                                                           @Valid @RequestBody GustatoryInspectionInputDTO gustatoryInspectionInputDTO) {
        return ResponseEntity.ok(gustatoryInspectionService.updateGustatoryInspection(id, gustatoryInspectionInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGustatoryInspection(@PathVariable ("id") String id) {
        gustatoryInspectionService.deleteGustatoryInspection(id);
        return ResponseEntity.noContent().build();
    }

}
