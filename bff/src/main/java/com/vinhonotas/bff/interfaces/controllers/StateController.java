package com.vinhonotas.bff.interfaces.controllers;

import com.vinhonotas.bff.application.services.cadastro.StateService;
import com.vinhonotas.bff.interfaces.dtos.outputs.StateOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/states")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    @GetMapping
    public ResponseEntity<List<StateOutputDTO>> getAllStates(){
        return ResponseEntity.ok(stateService.getAllStates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateOutputDTO> getStateById(@PathVariable("id") String id){
        return ResponseEntity.ok(stateService.getStateById(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<StateOutputDTO> getStateByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(stateService.getStateByName(name));
    }

    @GetMapping("/uf/{uf}")
    public ResponseEntity<StateOutputDTO> getStateByUf(@PathVariable("uf") String uf) {
        return ResponseEntity.ok(stateService.getStateByUf(uf));
    }

}
