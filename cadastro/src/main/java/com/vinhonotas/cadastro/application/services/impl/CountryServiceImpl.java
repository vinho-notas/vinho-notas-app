package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.services.CountryService;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.exceptions.CountryAlreadyExistsException;
import com.vinhonotas.cadastro.domain.entities.exceptions.CountryNotFoundException;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryConverter countryConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CountryEntity create(CountryInputDTO countryInputDTO) {
        log.info("create :: Registrando um país com os dados: {}", countryInputDTO.toString());
        CountryEntity entity = countryRepository.findByCountryName(countryInputDTO.getCountryName());
        if (Objects.nonNull(entity)) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.COUNTRY_ALREADY_EXISTS);
            throw new CountryAlreadyExistsException(MessagesConstants.COUNTRY_ALREADY_EXISTS);
        }
        try {
            CountryEntity countryEntity = countryConverter.convertToEntity(countryInputDTO);
            return countryRepository.save(countryEntity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.ERROR_WHEN_SAVING_COUNTRY, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_COUNTRY);
        }
    }

    @Override
    public List<CountryEntity> getAll() {
        log.info("getAll :: Listando todos os países");
        List<CountryEntity> entityList = countryRepository.findAll();
        if (entityList.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao buscar os países: {}", MessagesConstants.COUNTRIES_NOT_FOUND);
            throw new CountryNotFoundException(MessagesConstants.COUNTRIES_NOT_FOUND);
        }
        return entityList;
    }

    @Override
    public CountryEntity getById(UUID id) {
        log.info("getById :: Buscando país pelo id: {}", id.toString());
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(MessagesConstants.COUNTRY_NOT_FOUND));
    }

    @Override
    public CountryEntity getByName(String name) {
        log.info("getByName :: Buscando país pelo nome: {}", name);
        CountryEntity country = countryRepository.findByCountryName(name);
        if (Objects.isNull(country)) {
            log.error("getByName :: Ocorreu um erro: {}", MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME + name);
            throw new CountryNotFoundException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME + name);
        }
        return country;
    }

    @Override
    public List<CountryEntity> getByContinent(String continent) {
        log.info("getByContinent :: Buscando país pelo continente: {}", continent);
        List<CountryEntity> entityList = countryRepository.findByContinentName(continent);
        if (Objects.isNull(entityList) || entityList.isEmpty()) {
            log.error("getByContinent :: Ocorreu um erro ao buscar os países: {}", MessagesConstants.COUNTRY_NOT_FOUND_WITH_CONTINENT + continent);
            throw new CountryNotFoundException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_CONTINENT + continent);
        }
        return entityList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CountryEntity update(UUID id, CountryInputDTO countryInputDTO) {
        log.info("update :: Atualizando país com os dados: {}", countryInputDTO.toString());
        try {
            CountryEntity entity = this.getById(id);
            countryRepository.save(countryConverter.convertToEntityUpdate(entity, id, countryInputDTO));
            return countryRepository.findByCountryName(entity.getCountryName());
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {}", MessagesConstants.ERROR_UPDATE_COUNTRY_DATA, e);
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_COUNTRY_DATA);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando país com o id: {}", id.toString());
            Optional<CountryEntity> entity = countryRepository.findById(id);
            if (entity.isEmpty()) {
                log.error("delete :: Ocorreu um erro: {}", MessagesConstants.COUNTRY_NOT_FOUND);
                throw new CountryNotFoundException(MessagesConstants.COUNTRY_NOT_FOUND);
            }
        try {
                countryRepository.deleteById(id);
            } catch (Exception e) {
                log.error("delete :: Ocorreu um erro: {}", MessagesConstants.ERROR_DELETE_COUNTRY_DATA, e);
                throw new BadRequestException(MessagesConstants.ERROR_DELETE_COUNTRY_DATA);
            }
    }

}
