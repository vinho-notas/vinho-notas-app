package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.services.CountryService;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.CountryOutputDTO;
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
    public ResponseEntity<CountryOutputDTO> createCountry(@Valid @RequestBody CountryInputDTO countryInputDTO) {
        return ResponseEntity.ok(countryConverter.convertToOutputDTO(countryService.create(countryInputDTO)));
    }

    @GetMapping
    public ResponseEntity<List<CountryOutputDTO>> getAllCountries() {
        return ResponseEntity.ok(countryConverter.convertToOutputDTOList(countryService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryOutputDTO> getCountryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(countryConverter.convertToOutputDTO(countryService.getById(UUID.fromString(id))));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CountryOutputDTO> getCountryByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(countryConverter.convertToOutputDTO(countryService.getByName(name)));
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<CountryOutputDTO>> getCountryByContinent(@PathVariable("continent") String continent) {
        return ResponseEntity.ok(countryConverter.convertToOutputDTOList(countryService.getByContinent(continent)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryOutputDTO> updateCountry(@PathVariable("id") String id, @Valid @RequestBody CountryInputDTO countryInputDTO) {
        return ResponseEntity.ok(countryConverter.convertToOutputDTOUpdate(countryService.update(UUID.fromString(id),
                countryInputDTO), UUID.fromString(id), countryConverter.convertToOutputDTO(countryService.update(UUID.fromString(id), countryInputDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") String id) {
        countryService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
