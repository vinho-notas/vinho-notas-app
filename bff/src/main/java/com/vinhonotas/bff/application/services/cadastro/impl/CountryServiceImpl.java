package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.cadastro.CountryService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.cadastro.CountryClient;
import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryClient countryClient;

    @Override
    public List<CountryOutputDTO> getAllCountries() {
        List<CountryOutputDTO> countries = countryClient.getAllCountries();
        if (countries.isEmpty()) {
            throw new BadRequestException(MessagesConstants.COUNTRIES_NOT_FOUND);
        }
        return countries;
    }

    @Override
    public CountryOutputDTO getCountryById(String id) {
        CountryOutputDTO country = countryClient.getCountryById(id);
        if (Objects.isNull(country)) {
            throw new BadRequestException(MessagesConstants.COUNTRIES_NOT_FOUND);
        }
        return country;
    }

    @Override
    public CountryOutputDTO getCountryByName(String name) {
        CountryOutputDTO country = countryClient.getCountryByName(name);
        if (Objects.isNull(country)) {
            throw new BadRequestException(MessagesConstants.COUNTRIES_NOT_FOUND);
        }
        return country;
    }

}
