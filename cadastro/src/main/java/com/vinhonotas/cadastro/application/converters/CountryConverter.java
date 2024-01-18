package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CountryConverter {

    public CountryEntity toEntity(CountryInputDTO countryInputDTO) {
        return CountryEntity.builder()
                .countryName(countryInputDTO.getCountryName())
                .continentName(countryInputDTO.getContinentName())
                .build();
    }

    public CountryEntity toEntityUpdate(CountryEntity entity, UUID id, CountryInputDTO countryInputDTO) {
        return CountryEntity.builder()
                .id(id)
                .countryName(countryInputDTO.getCountryName() != null ? countryInputDTO.getCountryName() : entity.getCountryName())
                .continentName(countryInputDTO.getContinentName() != null ? countryInputDTO.getContinentName() : entity.getContinentName())
                .build();
    }
}
