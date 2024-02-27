package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.services.CountryService;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryConverter countryConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CountryEntity create(CountryInputDTO countryInputDTO) {
        CountryEntity entity = countryRepository.findByCountryName(countryInputDTO.getCountryName());
        if (Objects.nonNull(entity)) {
            throw new BadRequestException(MessagesConstants.COUNTRY_ALREADY_EXISTS);
        }
        try {
            CountryEntity countryEntity = countryConverter.convertToEntity(countryInputDTO);
            return countryRepository.save(countryEntity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_COUNTRY);
        }
    }

    @Override
    public List<CountryEntity> getAll() {
        List<CountryEntity> entityList = countryRepository.findAll();
        if (entityList.isEmpty()) {
            throw new BadRequestException(MessagesConstants.COUNTRIES_NOT_FOUND);
        }
        return entityList;
    }

    @Override
    public CountryEntity getById(UUID id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.COUNTRY_NOT_FOUND));
    }

    @Override
    public CountryEntity getByName(String name) {
        CountryEntity country = countryRepository.findByCountryName(name);
        if (Objects.isNull(country)) {
            throw new BadRequestException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME + name);
        }
        return country;
    }

    @Override
    public List<CountryEntity> getByContinent(String continent) {
        List<CountryEntity> entityList = countryRepository.findByContinentName(continent);
        if (Objects.isNull(entityList) || entityList.isEmpty()) {
            throw new BadRequestException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_CONTINENT + continent);
        }
        return entityList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CountryEntity update(UUID id, CountryInputDTO countryInputDTO) {
        try {
            CountryEntity entity = this.getById(id);
            countryRepository.save(countryConverter.convertToEntityUpdate(entity, id, countryInputDTO));
            return countryRepository.findByCountryName(entity.getCountryName());
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_COUNTRY_DATA);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
            Optional<CountryEntity> entity = countryRepository.findById(id);
            if (entity.isEmpty()) {
                throw new BadRequestException(MessagesConstants.COUNTRY_NOT_FOUND);
            }
        try {
                countryRepository.deleteById(id);
            } catch (Exception e) {
                throw new BadRequestException(MessagesConstants.ERROR_DELETE_COUNTRY_DATA);
            }
    }
}
