package com.vinhonotas.bff.interfaces.controllers.degustacao;

import com.vinhonotas.bff.application.services.degustacao.TastingCardService;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingCardInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingCardOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasting-card")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class TastingCardController {

    private final TastingCardService tastingCardService;

    @PostMapping
    public ResponseEntity<TastingCardOutputDTO> createTastingCard(@Valid @RequestBody TastingCardInputDTO tastingCardInputDTO) {
        return ResponseEntity.ok(tastingCardService.createTastingCard(tastingCardInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<TastingCardOutputDTO>> getAllTastingCards() {
        return ResponseEntity.ok(tastingCardService.getAllTastingCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TastingCardOutputDTO> getTastingCardById(@PathVariable("id") String id) {
        return ResponseEntity.ok(tastingCardService.getTastingCardById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TastingCardOutputDTO> updateTastingCard(@PathVariable ("id") String id, @Valid @RequestBody TastingCardInputDTO tastingCardInputDTO) {
        return ResponseEntity.ok(tastingCardService.updateTastingCard(id, tastingCardInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTastingCard(@PathVariable ("id") String id) {
        tastingCardService.deleteTastingCard(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllTastingCards(@RequestBody List<String> ids) {
        ids.forEach(tastingCardService::deleteTastingCard);
        return ResponseEntity.noContent().build();
    }

}
