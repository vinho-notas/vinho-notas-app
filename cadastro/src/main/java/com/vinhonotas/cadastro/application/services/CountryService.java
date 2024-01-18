package com.vinhonotas.cadastro.application.services;


import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;

import java.util.List;
import java.util.UUID;

public interface CountryService {

    public CountryEntity create(CountryInputDTO countryInputDTO);
    public List<CountryEntity> getAll();
    public CountryEntity getById(UUID id);
    public CountryEntity getByName(String name);
    public List<CountryEntity> getByContinent(String continent);
    public CountryEntity update(UUID id, CountryInputDTO countryInputDTO);
    public void delete(UUID id);
}
