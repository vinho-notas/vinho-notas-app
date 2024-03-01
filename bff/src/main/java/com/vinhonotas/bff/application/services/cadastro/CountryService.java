package com.vinhonotas.bff.application.services.cadastro;

import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;

import java.util.List;

public interface CountryService {

    List<CountryOutputDTO> getAllCountries();
    CountryOutputDTO getCountryById(String id);
    CountryOutputDTO getCountryByName(String name);

}
