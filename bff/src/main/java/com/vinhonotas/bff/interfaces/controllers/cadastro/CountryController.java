package com.vinhonotas.bff.interfaces.controllers.cadastro;

import com.vinhonotas.bff.application.services.cadastro.CountryService;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.CountryOutputDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RateLimiter(name = "rateLimiter")
@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@Tag(name = "Países", description = "Operações relacionadas a países")
public class CountryController {

    private final CountryService countryService;

    @Operation(summary = "Retorna todos os países")
    @GetMapping
    public ResponseEntity<List<CountryOutputDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @Operation(summary = "Retorna um país pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<CountryOutputDTO> getCountryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @Operation(summary = "Retorna um país pelo seu nome")
    @GetMapping("/name/{name}")
    public ResponseEntity<CountryOutputDTO> getCountryByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(countryService.getCountryByName(name));
    }

}
