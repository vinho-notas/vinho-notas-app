package com.vinhonotas.vinho.infraestructure.controller;

import com.vinhonotas.vinho.application.usecases.*;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.output.WineOutputDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineDomainMapper;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineEntityMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Vinhos", description = "Operações relacionadas a vinhos")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/wines")
public class WineController {

    private final CreateWine createWine;
    private final RetrieveWineById retrieveWineById;
    private final RetrieveWines retrieveWines;
    private final UpdateWine updateWine;
    private final WineDomainMapper wineDomainMapper;
    private final WineEntityMapper wineEntityMapper;
    private final DeleteWine deleteWine;

    @Operation(summary = "Endpoint responsável por cadastrar um vinho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vinho cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = WineOutputDTO.class) )),
            @ApiResponse(responseCode = "400", description = "Erro na requisição",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<WineOutputDTO> createWine(@Valid @RequestBody WineInputDTO wineInputDTO){
        log.info("createWine:: Recebendo requisição para criar um vinho com o input: {}", wineInputDTO);

        WineDomain wineDomain = wineDomainMapper.toWineDomain(wineInputDTO);
        WineEntity wineEntity = createWine.createWine(wineDomain);
        WineOutputDTO wineOutput = wineEntityMapper.toWineOutputDTO(wineEntity);

        log.info("createWine:: Vinho criado com sucesso: {}", wineOutput);
        return ResponseEntity.ok(wineOutput);
    }

    @Operation(summary = "Endpoint responsável por buscar um vinho pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vinho retornado com sucesso",
                    content = @Content(schema = @Schema(implementation = WineOutputDTO.class) )),
            @ApiResponse(responseCode = "400", description = "Erro na requisição",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Vinho não encontrado",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<WineOutputDTO> retrieveWineById(@PathVariable String id){
        log.info("retrieveWineById:: Recebedo requisição para retornar um vinho pelo id: {}", id);

        WineEntity wineEntity = retrieveWineById.retrieveWineById(id);
        WineOutputDTO wineOutput = wineEntityMapper.toWineOutputDTO(wineEntity);

        log.info("retrieveWineById:: Vinho retornado com sucesso: {}", wineOutput);
        return ResponseEntity.ok(wineOutput);
    }

    @Operation(summary = "Entpoint responsável por retornar uma lista de vinhos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vinhos retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = WineOutputDTO.class) )),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<List<WineOutputDTO>> retrieveAllWines(){
        log.info("retrieveAllWines:: Recebendo requisição para retornar todos os vinhos");
        List<WineEntity> wineList = retrieveWines.retrieveAllWines();

        log.info("retrieveAllWines:: Lista de vinhos retornada com sucesso: {}", wineList);
        return ResponseEntity.ok(wineEntityMapper.toWineOutputDTOList(wineList));
    }

    @Operation(summary = "Endpoint responsável por atualizar um vinho pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vinho atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = WineOutputDTO.class) )),
            @ApiResponse(responseCode = "400", description = "Erro na requisição",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Vinho não encontrado",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<WineOutputDTO> updateWine(@PathVariable("id") String id, @Valid @RequestBody WineInputDTO wineInputDTO) {
        log.info("updateWine:: Recebendo requisição para atualizar um vinho pelo id: {}, com os dados: {}", id, wineInputDTO);

        WineEntity wineUpdated = updateWine.updateWine(id, wineInputDTO);
        WineOutputDTO wineOutputDTO = wineEntityMapper.toWineOutputDTO(wineUpdated);

        log.info("updateWine:: Vinho atualizado com sucesso: {}", wineOutputDTO);
        return ResponseEntity.ok(wineOutputDTO);
    }

    @Operation(summary = "Endpoitn responsável por deletar um vinho pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vinho deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Vinho não encontrado",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWine(@PathVariable("id") String id) {
        log.info("deleteWine:: Recebendo requisição para deletar um vinho pelo id: {}", id);

        deleteWine.deleteWineById(UUID.fromString(id));

        log.info("deleteWine:: Vinho deletado com sucesso");
        return ResponseEntity.noContent().build();
    }

}
