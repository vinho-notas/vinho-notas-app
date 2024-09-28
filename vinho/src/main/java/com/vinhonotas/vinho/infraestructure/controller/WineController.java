package com.vinhonotas.vinho.infraestructure.controller;

import com.vinhonotas.vinho.application.usecases.CreateWine;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.output.WineOutputDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineDomainMapper;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineEntityMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/wines")
@Tag(name = "Vinhos", description = "Operações relacionadas a vinhos")
public class WineController {

    private final CreateWine createWine;
    private final WineDomainMapper wineDomainMapper;
    private final WineEntityMapper wineEntityMapper;

    public WineController(CreateWine createWine, WineDomainMapper wineDomainMapper, WineEntityMapper wineEntityMapper){
        this.createWine = createWine;
        this.wineDomainMapper = wineDomainMapper;
        this.wineEntityMapper = wineEntityMapper;
    }

    @Operation(summary = "Cria um vinho")
    @PostMapping
    public ResponseEntity<WineOutputDTO> createWine(@Valid @RequestBody WineInputDTO wineInputDTO){
        WineDomain wineDomain = wineDomainMapper.toWineDomain(wineInputDTO);
        WineEntity wineEntity = createWine.createWine(wineDomain);
        WineOutputDTO wineOutput = wineEntityMapper.toWineOutputDTO(wineEntity);
        return ResponseEntity.ok(wineOutput);
    }
}
