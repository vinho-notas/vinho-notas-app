package com.vinhonotas.bff.interfaces.controllers;

import com.vinhonotas.bff.application.services.cadastro.CountryService;
import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryOutputDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryOutputDTO> getCountryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @GetMapping("/name/{name}")
    public CountryOutputDTO getCountryByName(@PathVariable("name") String name) {
        return countryService.getCountryByName(name);
    }


}
