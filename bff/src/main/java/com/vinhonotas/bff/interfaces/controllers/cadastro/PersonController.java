package com.vinhonotas.bff.interfaces.controllers.cadastro;

import com.vinhonotas.bff.application.services.cadastro.PersonService;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.EditPersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.PersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.PersonOutputDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RateLimiter(name = "rateLimiter")
@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@Tag(name = "Pessoas", description = "Operações relacionadas a pessoas")
public class PersonController {

    private final PersonService personService;

    @Operation(summary = "Cria uma pessoa")
    @PostMapping
    public ResponseEntity<PersonOutputDTO> createPerson(@Valid @RequestBody PersonInputDTO personInputDTO) {
                return ResponseEntity.ok(personService.createPerson(personInputDTO));
    }

    @Operation(summary = "Retorna todas as pessoas")
    @GetMapping
    public ResponseEntity<List<PersonOutputDTO>> getAllPerson() {
                return ResponseEntity.ok(personService.getAllPerson());
    }

    @Operation(summary = "Retorna uma pessoa pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> getPersonById(@PathVariable("id") String id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @Operation(summary = "Retorna uma pessoa pelo seu nome")
    @GetMapping("/name/{name}")
    public ResponseEntity<PersonOutputDTO> getPersonByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(personService.getPersonByName(name));
    }

    @Operation(summary = "Atualiza uma pessoa")
    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> updatePerson(@PathVariable("id") String id, @Valid @RequestBody EditPersonInputDTO editPersonInputDTO) {
        return ResponseEntity.ok(personService.updatePerson(id, editPersonInputDTO));
    }

    @Operation(summary = "Deleta uma pessoa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") String id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta uma lista de pessoas")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllPerson(@RequestBody List<String> ids) {
        ids.forEach(personService::deletePerson);
        return ResponseEntity.noContent().build();
    }

}
