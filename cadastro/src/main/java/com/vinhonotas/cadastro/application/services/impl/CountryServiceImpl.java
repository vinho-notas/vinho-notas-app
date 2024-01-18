package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.services.CountryService;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryConverter countryConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        return countryRepository.findAll();
    }

    @Override
    public CountryEntity getById(UUID id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("País não encontrado"));
    }

    @Override
    public CountryEntity getByName(String name) {
        try {
            return countryRepository.findByCountryName(name);
        } catch (Exception e) {
            throw new IllegalArgumentException("País não encontrado com o nome: " + name);
        }
    }

    @Override
    public List<CountryEntity> getByContinent(String continent) {
        try {
            return countryRepository.findByContinentName(continent);
        } catch (Exception e) {
            throw new IllegalArgumentException("País não encontrado com o continente: " + continent);
        }
    }

    @Override
    public CountryEntity update(UUID id, CountryInputDTO countryInputDTO) {
        try {
            CountryEntity entity = this.getById(id);
            countryRepository.save(countryConverter.toEntityUpdate(entity, id, countryInputDTO));
            return countryRepository.findByCountryName(entity.getCountryName());
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao atualizar dados do país");
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            countryRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao deletar país");
        }
    }
}
