package com.vinhonotas.bff.interfaces.controllers.cadastro;

import com.vinhonotas.bff.application.services.cadastro.StateService;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.StateOutputDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RateLimiter(name = "rateLimiter")
@RestController
@RequestMapping("/api/v1/states")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@Tag(name = "Estados", description = "Operações relacionadas a estados")
public class StateController {

    private final StateService stateService;

    @Operation(summary = "Retorna todos os estados")
    @GetMapping
    public ResponseEntity<List<StateOutputDTO>> getAllStates(){
        return ResponseEntity.ok(stateService.getAllStates());
    }

    @Operation(summary = "Retorna um estado pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<StateOutputDTO> getStateById(@PathVariable("id") String id){
        return ResponseEntity.ok(stateService.getStateById(id));
    }

    @Operation(summary = "Retorna um estado pelo seu nome")
    @GetMapping("/name/{name}")
    public ResponseEntity<StateOutputDTO> getStateByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(stateService.getStateByName(name));
    }

    @Operation(summary = "Retorna um estado pela sua UF")
    @GetMapping("/uf/{uf}")
    public ResponseEntity<StateOutputDTO> getStateByUf(@PathVariable("uf") String uf) {
        return ResponseEntity.ok(stateService.getStateByUf(uf));
    }

}
