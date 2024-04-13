package com.vinhonotas.degustacao.interfaces.controllers;

//import com.vinhonotas.degustacao.application.converters.TastingCardConverter;

import com.vinhonotas.degustacao.application.converters.TastingCardConverter;
import com.vinhonotas.degustacao.application.services.TastingCardService;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingCardOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tasting-card")
@Tag(name = "Tasting Card", description = "Operações relacionadas a ficha de degustação do vinho")
public class TastingCardController {

    private final TastingCardService tastingCardService;
    private final TastingCardConverter tastingCardConverter;

    @Operation(summary = "Cadastrar ficha de degustação")
    @PostMapping
    public ResponseEntity<TastingCardOutputDTO> createTastingCard(@Valid @RequestBody TastingCardInputDTO tastingCardInputDTO) {
        TastingCardEntity tastingCardEntity = tastingCardService.create(tastingCardInputDTO);
        TastingCardOutputDTO outputDTO = tastingCardConverter.toOutputDTO(tastingCardEntity);
        return ResponseEntity.ok(outputDTO);
    }

    @Operation(summary = "Retorna uma lista com todas as fichas de degustação cadastradas")
    @GetMapping
    public ResponseEntity<Set<TastingCardOutputDTO>> getAllTastingCards() {
        Set<TastingCardEntity> entityList = tastingCardService.getAll();
        Set<TastingCardOutputDTO> outputDTOList = tastingCardConverter.toOutputDTOList(entityList);
        return ResponseEntity.ok(outputDTOList);
    }

    @Operation(summary = "Retorna uma ficha de degustação cadastrada pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<TastingCardOutputDTO> getTastingCardById(@PathVariable("id") String id) {
        TastingCardEntity entity = tastingCardService.getById(UUID.fromString(id));
        TastingCardOutputDTO outputDTO = tastingCardConverter.toOutputDTO(entity);
        return ResponseEntity.ok(outputDTO);
    }

    @Operation(summary = "Atualiza uma ficha de degustação cadastrada pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<TastingCardOutputDTO> updateTastingCard(@PathVariable ("id") String id,
                                          @Valid @RequestBody TastingCardInputDTO tastingCardInputDTO) {

        TastingCardEntity updated = tastingCardService.update(UUID.fromString(id), tastingCardInputDTO);

        TastingCardOutputDTO outputDTO = tastingCardConverter.toOutputDTO(updated);

        return ResponseEntity.ok(outputDTO);
    }

    @Operation(summary = "Deleta uma ficha de degustação cadastrada pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTastingCard(@PathVariable ("id") String id) {
        tastingCardService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
