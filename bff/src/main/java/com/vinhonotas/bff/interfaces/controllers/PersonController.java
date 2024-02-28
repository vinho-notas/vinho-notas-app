package com.vinhonotas.bff.interfaces.controllers;

import com.vinhonotas.bff.application.services.cadastro.PersonService;
import com.vinhonotas.bff.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.PersonOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonOutputDTO> createPerson(@Valid @RequestBody PersonInputDTO personInputDTO) {
        return ResponseEntity.ok(personService.createPerson(personInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<PersonOutputDTO>> getAllPerson() {
        return ResponseEntity.ok(personService.getAllPerson());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> getPersonById(@PathVariable("id") String id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PersonOutputDTO> getPersonByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(personService.getPersonByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> updatePerson(@PathVariable("id") String id, @Valid @RequestBody PersonInputDTO personInputDTO) {
        return ResponseEntity.ok(personService.updatePerson(id, personInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") String id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

}
