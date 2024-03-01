package com.vinhonotas.bff.client.cadastro;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "country", url = "${cadastro-api.url}", configuration = FeignConfig.class)
public interface CountryClient {

    @GetMapping("/countries")
    List<CountryOutputDTO> getAllCountries();

    @GetMapping("/countries/{id}")
    CountryOutputDTO getCountryById(@PathVariable("id") String id);

    @GetMapping("/countries/name/{name}")
    CountryOutputDTO getCountryByName(@PathVariable("name") String name);

}
