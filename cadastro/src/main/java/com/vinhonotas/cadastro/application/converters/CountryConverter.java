package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.CountryOutputDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class CountryConverter {

    public CountryEntity convertToEntity(CountryInputDTO countryInputDTO) {
        return CountryEntity.builder()
                .id(UUID.fromString(countryInputDTO.getId()))
                .countryName(countryInputDTO.getCountryName())
                .continentName(countryInputDTO.getContinentName())
                .userreg(countryInputDTO.getUserreg())
                .dthreg(LocalDateTime.now())
                .useralt(countryInputDTO.getUseralt())
                .dthalt(countryInputDTO.getDthalt())
                .build();
    }

    public CountryEntity convertToEntityUpdate(CountryEntity entity, UUID id, CountryInputDTO countryInputDTO) {
        return CountryEntity.builder()
                .id(id)
                .countryName(countryInputDTO.getCountryName() != null ? countryInputDTO.getCountryName() : entity.getCountryName())
                .continentName(countryInputDTO.getContinentName() != null ? countryInputDTO.getContinentName() : entity.getContinentName())
                .userreg(countryInputDTO.getUserreg() != null ? countryInputDTO.getUserreg() : entity.getUserreg())
                .dthreg(countryInputDTO.getDthreg() != null ? countryInputDTO.getDthreg() : entity.getDthreg())
                .useralt(countryInputDTO.getUseralt() != null ? countryInputDTO.getUseralt() : entity.getUseralt())
                .dthalt(LocalDateTime.now())
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

    public CountryInputDTO convertToInputDTO(CountryEntity country) {
        return CountryInputDTO.builder()
                .id(country.getId().toString())
                .countryName(country.getCountryName())
                .continentName(country.getContinentName())
                .build();
    }
}
