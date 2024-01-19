package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.services.CountryService;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/countries")
public class CountryController {

    private final CountryService countryService;
    private final CountryConverter countryConverter;

    @PostMapping
    public ResponseEntity<CountryEntity> createCountry(@Valid @RequestBody CountryInputDTO countryInputDTO) {
        return ResponseEntity.ok(countryService.create(countryInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<CountryEntity>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryEntity> getCountryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(countryService.getById(UUID.fromString(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CountryEntity> getCountryByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(countryService.getByName(name));
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<CountryEntity>> getCountryByContinent(@PathVariable("continent") String continent) {
        return ResponseEntity.ok(countryService.getByContinent(continent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryEntity> updateCountry(@PathVariable("id") String id, @Valid @RequestBody CountryInputDTO countryInputDTO) {
        return ResponseEntity.ok(countryService.update(UUID.fromString(id), countryInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") String id) {
        countryService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
