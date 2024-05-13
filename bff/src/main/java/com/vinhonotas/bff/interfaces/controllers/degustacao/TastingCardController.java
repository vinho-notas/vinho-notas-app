package com.vinhonotas.bff.interfaces.controllers.degustacao;

import com.vinhonotas.bff.application.services.degustacao.TastingCardService;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingCardInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingCardOutputDTO;
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
@RequestMapping("/api/v1/tasting-card")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@Tag(name = "Tasting Card", description = "Operações relacionadas a ficha de degustação do vinho")
public class TastingCardController {

    private final TastingCardService tastingCardService;

    @Operation(summary = "Cadastrar ficha de degustação")
    @PostMapping
    public ResponseEntity<TastingCardOutputDTO> createTastingCard(@Valid @RequestBody TastingCardInputDTO tastingCardInputDTO) {
        return ResponseEntity.ok(tastingCardService.createTastingCard(tastingCardInputDTO));
    }

    @Operation(summary = "Retorna uma lista com todas as fichas de degustação cadastradas")
    @GetMapping
    public ResponseEntity<List<TastingCardOutputDTO>> getAllTastingCards() {
        return ResponseEntity.ok(tastingCardService.getAllTastingCards());
    }

    @Operation(summary = "Retorna uma ficha de degustação cadastrada pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<TastingCardOutputDTO> getTastingCardById(@PathVariable("id") String id) {
        return ResponseEntity.ok(tastingCardService.getTastingCardById(id));
    }

    @Operation(summary = "Atualiza uma ficha de degustação cadastrada pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<TastingCardOutputDTO> updateTastingCard(@PathVariable ("id") String id, @Valid @RequestBody TastingCardInputDTO tastingCardInputDTO) {
        return ResponseEntity.ok(tastingCardService.updateTastingCard(id, tastingCardInputDTO));
    }

    @Operation(summary = "Deleta uma ficha de degustação cadastrada pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTastingCard(@PathVariable ("id") String id) {
        tastingCardService.deleteTastingCard(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta uma lista de fichas de degustação")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllTastingCards(@RequestBody List<String> ids) {
        ids.forEach(tastingCardService::deleteTastingCard);
        return ResponseEntity.noContent().build();
    }

}
