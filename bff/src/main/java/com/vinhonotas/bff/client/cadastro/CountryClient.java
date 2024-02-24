package com.vinhonotas.bff.client.cadastro;

import com.vinhonotas.bff.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "country", url = "${cadastro-api.url}")
public interface CountryClient {

    @PostMapping
    CountryOutputDTO createCountry(@Valid @RequestBody CountryInputDTO countryInputDTO);

    @GetMapping
    List<CountryOutputDTO> getAllCountries();

    @GetMapping("/{id}")
    CountryOutputDTO getCountryById(@PathVariable("id") String id);

    @GetMapping("/name/{name}")
    CountryOutputDTO getCountryByName(@PathVariable("name") String name);

    @GetMapping("/continent/{continent}")
    List<CountryOutputDTO> getCountryByContinent(@PathVariable("continent") String continent);

    CountryOutputDTO updateCountry(@PathVariable("id") String id, @Valid @RequestBody CountryInputDTO countryInputDTO);

    @DeleteMapping("/{id}")
    Void deleteCountry(@PathVariable("id") String id);

}
