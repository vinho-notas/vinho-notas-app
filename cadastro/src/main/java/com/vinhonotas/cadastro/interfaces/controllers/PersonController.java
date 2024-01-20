package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.PersonConverter;
import com.vinhonotas.cadastro.application.services.PersonService;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.PersonOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonConverter personConverter;

    @PostMapping
    public ResponseEntity<PersonOutputDTO> createPerson(@Valid @RequestBody PersonInputDTO personInputDTO) {
        return ResponseEntity.ok(personConverter.convertToOutputDTO(personService.create(personInputDTO)));
    }

    @GetMapping
    public ResponseEntity<List<PersonOutputDTO>> getAllPerson() {
        return ResponseEntity.ok(personConverter.convertToOutputDTOList(personService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> getPersonById(@PathVariable("id") String id) {
        return ResponseEntity.ok(personConverter.convertToOutputDTO(personService.getById(UUID.fromString(id))));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PersonOutputDTO> getPersonByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(personConverter.convertToOutputDTO(personService.getByName(name)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> updatePerson(@PathVariable("id") String id, @Valid @RequestBody PersonInputDTO personInputDTO) {
        return ResponseEntity.ok(
                personConverter.convertToOutputDTOUpdate(
                        personService.update(UUID.fromString(id),
                        personInputDTO),
                        UUID.fromString(id),
                        personConverter.convertToOutputDTO(
                                personService.update(UUID.fromString(id), personInputDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") String id) {
        personService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
