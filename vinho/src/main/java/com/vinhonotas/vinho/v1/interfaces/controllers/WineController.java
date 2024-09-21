//package com.vinhonotas.vinho.v1.interfaces.controllers;
//
//import com.vinhonotas.vinho.v1.application.converters.WineConverter;
//import com.vinhonotas.vinho.v1.application.services.WineService;
//import com.vinhonotas.vinho.v1.domain.entities.WineEntity;
//import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
//import com.vinhonotas.vinho.v1.interfaces.dtos.outputs.WineOutputDTO;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(value = "/api/v1/wines")
//@Tag(name = "Vinhos", description = "Operações relacionadas a vinhos")
//public class WineController {
//
//    private final WineService wineService;
//    private final WineConverter wineConverter;
//
//    @Operation(summary = "Cria um vinho")
//    @PostMapping
//    public ResponseEntity<WineOutputDTO> createWine(@Valid @RequestBody WineInputDTO wineInputDTO){
//        return ResponseEntity.ok(wineConverter.toOutputDTO(wineService.create(wineInputDTO)));
//    }
//
//    @Operation(summary = "Retorna todos os vinhos")
//    @GetMapping
//    public ResponseEntity<List<WineOutputDTO>> getAllWines() {
//        return ResponseEntity.ok(wineConverter.toOutputDTOList(wineService.getAll()));
//    }
//
//    @Operation(summary = "Retorna um vinho pelo id")
//    @GetMapping("/{id}")
//    public ResponseEntity<WineOutputDTO> getWineById(@PathVariable("id") String id) {
//        return ResponseEntity.ok(wineConverter.toOutputDTO(wineService.getById(UUID.fromString(id))));
//    }
//
//    @Operation(summary = "Atualiza um vinho pelo id")
//    @PutMapping("/{id}")
//    public ResponseEntity<WineOutputDTO> updateWine(@PathVariable("id") String id, @Valid @RequestBody WineInputDTO wineInputDTO) {
//        WineEntity wineUpdated = wineService.update(UUID.fromString(id), wineInputDTO);
//        WineOutputDTO wineOutputDTO = wineConverter.toOutputDTO(wineUpdated);
//        return ResponseEntity.ok(wineConverter.toOutputDTOUpdate(wineUpdated, UUID.fromString(id), wineOutputDTO));
//    }
//
//    @Operation(summary = "Deleta um vinho pelo id")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteWine(@PathVariable("id") String id) {
//        wineService.delete(UUID.fromString(id));
//        return ResponseEntity.noContent().build();
//    }
//}
