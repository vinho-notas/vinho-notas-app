package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.PersonConverter;
import com.vinhonotas.cadastro.application.services.PersonService;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.PersonOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/persons")
@Tag(name = "Pessoas", description = "Operações relacionadas a pessoas")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class PersonController {

    private final PersonService personService;
    private final PersonConverter personConverter;

    @Operation(summary = "Cria uma pessoa")
    @PostMapping
    public ResponseEntity<PersonOutputDTO> createPerson(@Valid @RequestBody PersonInputDTO personInputDTO) {
        return ResponseEntity.ok(personConverter.convertToOutputDTO(personService.create(personInputDTO)));
    }

    @Operation(summary = "Retorna todas as pessoas")
    @GetMapping
    public ResponseEntity<List<PersonOutputDTO>> getAllPerson() {
        return ResponseEntity.ok(personConverter.convertToOutputDTOList(personService.getAll()));
    }

    @Operation(summary = "Retorna uma pessoa pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> getPersonById(@PathVariable("id") String id) {
        return ResponseEntity.ok(personConverter.convertToOutputDTO(personService.getById(UUID.fromString(id))));
    }

    @Operation(summary = "Retorna uma pessoa pelo seu nome")
    @GetMapping("/name/{name}")
    public ResponseEntity<PersonOutputDTO> getPersonByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(personConverter.convertToOutputDTO(personService.getByName(name)));
    }

    @Operation(summary = "Atualiza uma pessoa")
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

    @Operation(summary = "Deleta uma pessoa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") String id) {
        personService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
