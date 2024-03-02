package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.cadastro.CountryService;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.cadastro.CountryClient;
import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryClient countryClient;

    @Override
    public List<CountryOutputDTO> getAllCountries() {
        log.info("getAllCountries :: Listando todos os países");
        List<CountryOutputDTO> countries = countryClient.getAllCountries();
        if (countries.isEmpty()) {
            log.error("getAllCountries :: Ocorreu um erro ao listar os países: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return countries;
    }

    @Override
    public CountryOutputDTO getCountryById(String id) {
        log.info("getCountryById :: Buscando país pelo id: {}", id);
        CountryOutputDTO country = countryClient.getCountryById(id);
        if (Objects.isNull(country)) {
            log.error("getCountryById :: Ocorreu um erro ao buscar o país: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return country;
    }

    @Override
    public CountryOutputDTO getCountryByName(String name) {
        log.info("getCountryByName :: Buscando país pelo nome: {}", name);
        CountryOutputDTO country = countryClient.getCountryByName(name);
        if (Objects.isNull(country)) {
            log.error("getCountryByName :: Ocorreu um erro ao buscar o país: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return country;
    }

}
