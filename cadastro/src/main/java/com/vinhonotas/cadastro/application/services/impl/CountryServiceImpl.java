package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.services.CountryService;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryConverter countryConverter;

    @Override
    public CountryEntity create(CountryInputDTO countryInputDTO) {
        try {
            CountryEntity countryEntity = countryConverter.toEntity(countryInputDTO);
            return countryRepository.save(countryEntity);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao gravar dados do país");
        }
    }

    @Override
    public List<CountryEntity> getAll() {
        //TODO: Implementar
        return null;
    }

    @Override
    public CountryEntity getById(UUID id) {
        //TODO: Implementar
        return null;
    }

    @Override
    public CountryEntity getByName(String name) {
        //TODO: Implementar
        return null;
    }

    @Override
    public List<CountryEntity> getByContinent(String continent) {
        //TODO: Implementar
        return null;
    }

    @Override
    public CountryEntity update(CountryEntity countryEntity) {
        //TODO: Implementar
        return null;
    }

    @Override
    public void delete(UUID id) {
        //TODO: Implementar
    }
}
