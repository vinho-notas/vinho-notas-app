package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import org.springframework.stereotype.Component;

@Component
public class CountryConverter {

    public CountryEntity toEntity(CountryInputDTO countryInputDTO) {
        return CountryEntity.builder()
                .countryName(countryInputDTO.getCountryName())
                .continentName(countryInputDTO.getContinentName())
                .build();
    }
}
