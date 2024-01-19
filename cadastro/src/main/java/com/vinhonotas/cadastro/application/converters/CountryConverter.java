package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.CountryOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public CountryOutputDTO convertToOutputDTO(CountryEntity country) {
        return CountryOutputDTO.builder()
                .id(country.getId())
                .countryName(country.getCountryName())
                .continentName(country.getContinentName())
                .build();
    }

    public List<CountryOutputDTO> convertToOutputDTOList(List<CountryEntity> list) {
        return list.stream()
                .map(this::convertToOutputDTO)
                .toList();
    }

    public CountryOutputDTO convertToOutputDTOUpdate(CountryEntity update, UUID uuid, CountryOutputDTO countryOutputDTO) {
        return CountryOutputDTO.builder()
                .id(uuid)
                .countryName(countryOutputDTO.getCountryName() != null ? countryOutputDTO.getCountryName() : update.getCountryName())
                .continentName(countryOutputDTO.getContinentName() != null ? countryOutputDTO.getContinentName() : update.getContinentName())
                .build();
    }
}
