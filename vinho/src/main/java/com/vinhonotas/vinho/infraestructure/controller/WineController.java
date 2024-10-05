package com.vinhonotas.vinho.infraestructure.controller;

import com.vinhonotas.vinho.application.usecases.CreateWine;
import com.vinhonotas.vinho.application.usecases.RetrieveWineById;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.output.WineOutputDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineDomainMapper;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineEntityMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/wines")
@Tag(name = "Vinhos", description = "Operações relacionadas a vinhos")
public class WineController {

    private final CreateWine createWine;
    private final RetrieveWineById retrieveWineById;
    private final WineDomainMapper wineDomainMapper;
    private final WineEntityMapper wineEntityMapper;

    public WineController(CreateWine createWine, WineDomainMapper wineDomainMapper, WineEntityMapper wineEntityMapper, RetrieveWineById retrieveWineById){
        this.createWine = createWine;
        this.wineDomainMapper = wineDomainMapper;
        this.wineEntityMapper = wineEntityMapper;
        this.retrieveWineById = retrieveWineById;
    }

    @Operation(summary = "Cria um vinho")
    @PostMapping
    public ResponseEntity<WineOutputDTO> createWine(@Valid @RequestBody WineInputDTO wineInputDTO){
        log.info("createWine:: Recebendo requisição para criar um vinho com o input: {}", wineInputDTO);

        WineDomain wineDomain = wineDomainMapper.toWineDomain(wineInputDTO);
        WineEntity wineEntity = createWine.createWine(wineDomain);
        WineOutputDTO wineOutput = wineEntityMapper.toWineOutputDTO(wineEntity);

        log.info("createWine:: Vinho criado com sucesso: {}", wineOutput);
        return ResponseEntity.ok(wineOutput);
    }

    @Operation(summary = "Busca um vinho pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<WineOutputDTO> retrieveWineById(@PathVariable String id){
        log.info("retrieveWineById:: Recebedo requisição para retornar um vinho pelo id: {}", id);

        WineEntity wineEntity = retrieveWineById.retrieveWineById(id);
        WineOutputDTO wineOutput = wineEntityMapper.toWineOutputDTO(wineEntity);

        log.info("retrieveWineById:: Vinho retornado com sucesso: {}", wineOutput);
        return ResponseEntity.ok(wineOutput);
    }

}
