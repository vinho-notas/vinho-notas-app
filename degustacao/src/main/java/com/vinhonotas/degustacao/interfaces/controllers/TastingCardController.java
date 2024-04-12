//package com.vinhonotas.degustacao.interfaces.controllers;
//
////import com.vinhonotas.degustacao.application.converters.TastingCardConverter;
//import com.vinhonotas.degustacao.application.services.TastingCardService;
//import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
//import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingCardOutputDTO;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Set;
//import java.util.UUID;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(value = "/api/v1/tasting-card")
//@Tag(name = "Tasting Card", description = "Operações relacionadas a ficha de degustação do vinho")
//@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
//public class TastingCardController {
//
//    private final TastingCardService tastingCardService;
////    private final TastingCardConverter tastingCardConverter;
//
//    @Operation(summary = "Cadastrar ficha de degustação")
//    @PostMapping
//    public ResponseEntity<TastingCardOutputDTO> createTastingCard(@Valid @RequestBody TastingCardInputDTO tastingCardInputDTO) {
//        return ResponseEntity.ok(tastingCardConverter.toOutputDTO(tastingCardService.create(tastingCardInputDTO)));
//    }
//
//    @Operation(summary = "Retorna uma lista com todas as fichas de degustação cadastradas")
//    @GetMapping
//    public ResponseEntity<Set<TastingCardOutputDTO>> getAllTastingCards() {
//        return ResponseEntity.ok(tastingCardConverter.toOutputDTOList(tastingCardService.getAll()));
//    }
//
//    @Operation(summary = "Retorna uma ficha de degustação cadastrada pelo id")
//    @GetMapping("/{id}")
//    public ResponseEntity<TastingCardOutputDTO> getTastingCardById(@PathVariable ("id") String id) {
//        return ResponseEntity.ok(tastingCardConverter.toOutputDTO(tastingCardService.getById(UUID.fromString(id))));
//    }
//
//    @Operation(summary = "Atualiza uma ficha de degustação cadastrada pelo id")
//    @PutMapping("/{id}")
//    public ResponseEntity<TastingCardOutputDTO> updateTastingCard(@PathVariable ("id") String id,
//                                          @Valid @RequestBody TastingCardInputDTO tastingCardInputDTO) {
//        return ResponseEntity.ok(tastingCardConverter
//                .toOutputDTOUpdate(tastingCardService.update(UUID.fromString(id), tastingCardInputDTO),
//                                UUID.fromString(id),
//                                tastingCardConverter.toOutputDTO(tastingCardService.update(UUID.fromString(id),
//                                tastingCardInputDTO))));
//    }
//
//    @Operation(summary = "Deleta uma ficha de degustação cadastrada pelo id")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTastingCard(@PathVariable ("id") String id) {
//        tastingCardService.delete(UUID.fromString(id));
//        return ResponseEntity.noContent().build();
//    }
//
//}
