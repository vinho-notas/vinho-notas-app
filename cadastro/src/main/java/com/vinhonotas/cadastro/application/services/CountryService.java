package com.vinhonotas.cadastro.application.services;


import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;

import java.util.List;
import java.util.UUID;

public interface CountryService {

    CountryEntity create(CountryInputDTO countryInputDTO);
    List<CountryEntity> getAll();
    CountryEntity getById(UUID id);
    CountryEntity getByName(String name);
    List<CountryEntity> getByContinent(String continent);
    CountryEntity update(UUID id, CountryInputDTO countryInputDTO);
    void delete(UUID id);
}
