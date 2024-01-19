package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.StateConverter;
import com.vinhonotas.cadastro.application.services.StateService;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.StateOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/states")
public class StateController {

    private final StateService stateService;
    private final StateConverter stateConverter;

    @PostMapping
    public ResponseEntity<StateOutputDTO> createState(@Valid @RequestBody StateInputDTO stateInputDTO) {
        return ResponseEntity.ok(stateConverter.convertToOutputDTO(stateService.create(stateInputDTO)));
    }

    @GetMapping
    public ResponseEntity<List<StateOutputDTO>> getAllStates() {
        return ResponseEntity.ok(stateConverter.convertToOutputDTOList(stateService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateOutputDTO> getStateById(@PathVariable("id") String id) {        ;
        return ResponseEntity.ok(stateConverter.convertToOutputDTO(stateService.getById(UUID.fromString(id))));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<StateOutputDTO> getStateByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(stateConverter.convertToOutputDTO(stateService.getByName(name)));
    }

    @GetMapping("/uf/{uf}")
    public ResponseEntity<StateOutputDTO> getStateByUf(@PathVariable("uf") String uf) {
        return ResponseEntity.ok(stateConverter.convertToOutputDTO(stateService.getByUf(uf)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateOutputDTO> updateState(@PathVariable("id") String id, @Valid @RequestBody StateInputDTO stateInputDTO) {
        return ResponseEntity.ok(stateConverter.convertToOutputDTOUpdate(stateService.update(UUID.fromString(id),
                stateInputDTO), UUID.fromString(id), stateConverter.convertToOutputDTO(stateService.update(UUID.fromString(id), stateInputDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable("id") String id) {
        stateService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
