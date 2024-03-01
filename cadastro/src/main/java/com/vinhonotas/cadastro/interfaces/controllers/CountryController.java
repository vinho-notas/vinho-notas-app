package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.services.CountryService;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.CountryOutputDTO;
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
@RequestMapping(value = "/api/v1/countries")
@Tag(name = "Países", description = "Operações relacionadas a países")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class CountryController {

    private final CountryService countryService;
    private final CountryConverter countryConverter;

    @Operation(summary = "Cria um país")
    @PostMapping
    public ResponseEntity<CountryOutputDTO> createCountry(@Valid @RequestBody CountryInputDTO countryInputDTO) {
        return ResponseEntity.ok(countryConverter.convertToOutputDTO(countryService.create(countryInputDTO)));
    }

    @Operation(summary = "Retorna todos os países")
    @GetMapping
    public ResponseEntity<List<CountryOutputDTO>> getAllCountries() {
        return ResponseEntity.ok(countryConverter.convertToOutputDTOList(countryService.getAll()));
    }

    @Operation(summary = "Retorna um país pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<CountryOutputDTO> getCountryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(countryConverter.convertToOutputDTO(countryService.getById(UUID.fromString(id))));
    }

    @Operation(summary = "Retorna um país pelo seu nome")
    @GetMapping("/name/{name}")
    public ResponseEntity<CountryOutputDTO> getCountryByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(countryConverter.convertToOutputDTO(countryService.getByName(name)));
    }

    @Operation(summary = "Retorna uma lista de países pelo seu continente")
    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<CountryOutputDTO>> getCountryByContinent(@PathVariable("continent") String continent) {
        return ResponseEntity.ok(countryConverter.convertToOutputDTOList(countryService.getByContinent(continent)));
    }

    @Operation(summary = "Atualiza um país")
    @PutMapping("/{id}")
    public ResponseEntity<CountryOutputDTO> updateCountry(@PathVariable("id") String id, @Valid @RequestBody CountryInputDTO countryInputDTO) {
        return ResponseEntity.ok(countryConverter.convertToOutputDTOUpdate(countryService.update(UUID.fromString(id),
                countryInputDTO), UUID.fromString(id), countryConverter.convertToOutputDTO(countryService.update(UUID.fromString(id), countryInputDTO))));
    }

    @Operation(summary = "Deleta um país")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") String id) {
        countryService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
