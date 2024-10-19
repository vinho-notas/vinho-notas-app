package com.vinhonotas.vinho.infraestructure.controller;

import com.vinhonotas.vinho.application.usecases.CreateWine;
import com.vinhonotas.vinho.application.usecases.RetrieveWineById;
import com.vinhonotas.vinho.application.usecases.RetrieveWines;
import com.vinhonotas.vinho.application.usecases.UpdateWine;
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

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/wines")
@Tag(name = "Vinhos", description = "Operações relacionadas a vinhos")
public class WineController {

    private final CreateWine createWine;
    private final RetrieveWineById retrieveWineById;
    private final RetrieveWines retrieveWines;
    private final UpdateWine updateWine;
    private final WineDomainMapper wineDomainMapper;
    private final WineEntityMapper wineEntityMapper;

    public WineController(CreateWine createWine, WineDomainMapper wineDomainMapper, WineEntityMapper wineEntityMapper,
                          RetrieveWineById retrieveWineById, RetrieveWines retrieveWines, UpdateWine updateWine){
        this.createWine = createWine;
        this.wineDomainMapper = wineDomainMapper;
        this.wineEntityMapper = wineEntityMapper;
        this.retrieveWineById = retrieveWineById;
        this.retrieveWines = retrieveWines;
        this.updateWine = updateWine;
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

    @Operation(summary = "Retorna uma lista de vinhos")
    @GetMapping
    public ResponseEntity<List<WineOutputDTO>> retrieveAllWines(){
        log.info("retrieveAllWines:: Recebendo requisição para retornar todos os vinhos");
        List<WineEntity> wineList = retrieveWines.retrieveAllWines();

        log.info("retrieveAllWines:: Lista de vinhos retornada com sucesso: {}", wineList);
        return ResponseEntity.ok(wineEntityMapper.toWineOutputDTOList(wineList));
    }

    @Operation(summary = "Atualiza um vinho pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<WineOutputDTO> updateWine(@PathVariable("id") String id, @Valid @RequestBody WineInputDTO wineInputDTO) {
        log.info("updateWine:: Recebendo requisição para atualizar um vinho pelo id: {}", id);

        WineDomain wineDomain = wineDomainMapper.toWineDomain(wineInputDTO);
        WineEntity wineUpdated = updateWine.updateWine(id, wineDomain);
        WineOutputDTO wineOutputDTO = wineEntityMapper.toWineOutputDTO(wineUpdated);

        log.info("updateWine:: Vinho atualizado com sucesso: {}", wineOutputDTO);
        return ResponseEntity.ok(wineOutputDTO);
    }

}
