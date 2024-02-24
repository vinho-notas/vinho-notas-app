package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.client.cadastro.CountryClient;
import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private CountryClient countryClient;

    private CountryOutputDTO countryOutputDTO;

    @BeforeEach
    void setUp() {
        countryOutputDTO = createCountryOutputDTO();
    }

    @Test
    @DisplayName("Deve retornar uma lista de países")
    void testGetAllCountries() {
        List<CountryOutputDTO> countries = new ArrayList<>();
        countries.add(countryOutputDTO);

        when(countryClient.getAllCountries()).thenReturn(countries);
        List<CountryOutputDTO> listResponse = assertDoesNotThrow(() -> countryService.getAllCountries());

        assertFalse(listResponse.isEmpty());
        assertEquals(1, listResponse.size());
        assertEquals(countryOutputDTO.getId(), listResponse.get(0).getId());
        assertEquals(countryOutputDTO.getCountryName(), listResponse.get(0).getCountryName());
        assertEquals(countryOutputDTO.getContinentName(), listResponse.get(0).getContinentName());
        verify(countryClient).getAllCountries();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar buscar todos os países e não encontrar nenhum")
    void testGetAllCountriesNotFound() {
        when(countryClient.getAllCountries()).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(Exception.class, () -> countryService.getAllCountries());

        assertEquals(MessagesConstants.COUNTRIES_NOT_FOUND, exception.getMessage());
        verify(countryClient).getAllCountries();
    }

    @Test
    @DisplayName("Deve retornar um país pelo id")
    void testGetCountryById() {
        when(countryClient.getCountryById("d0d7c2a0-0b7a-4e1e-8b7a-0b7a4e1e8b7a")).thenReturn(countryOutputDTO);
        CountryOutputDTO country = assertDoesNotThrow(() -> countryService.getCountryById("d0d7c2a0-0b7a-4e1e-8b7a-0b7a4e1e8b7a"));

        assertNotNull(country);
        assertEquals(countryOutputDTO.getId(), country.getId());
        assertEquals(countryOutputDTO.getCountryName(), country.getCountryName());
        assertEquals(countryOutputDTO.getContinentName(), country.getContinentName());

        verify(countryClient).getCountryById("d0d7c2a0-0b7a-4e1e-8b7a-0b7a4e1e8b7a");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar buscar um país pelo id e não encontrar")
    void testGetCountryByIdNotFound() {
        Exception exception = assertThrows(Exception.class, () -> countryService.getCountryById("d0d7c2a0-0b7a-4e1e-8b7a-0b7a4e1e8b7a"));

        assertEquals(MessagesConstants.COUNTRIES_NOT_FOUND, exception.getMessage());
        verify(countryClient).getCountryById("d0d7c2a0-0b7a-4e1e-8b7a-0b7a4e1e8b7a");
    }

    @Test
    @DisplayName("Deve retornar um país pelo nome")
    void testGetCountryByName() {
        when(countryClient.getCountryByName("Brasil")).thenReturn(countryOutputDTO);
        CountryOutputDTO country = assertDoesNotThrow(() -> countryService.getCountryByName("Brasil"));

        assertNotNull(country);
        assertEquals(countryOutputDTO.getId(), country.getId());
        assertEquals(countryOutputDTO.getCountryName(), country.getCountryName());
        assertEquals(countryOutputDTO.getContinentName(), country.getContinentName());

        verify(countryClient).getCountryByName("Brasil");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar buscar um país pelo nome e não encontrar")
    void testGetCountryByNameNotFound() {
        Exception exception = assertThrows(Exception.class, () -> countryService.getCountryByName("Brasil"));

        assertEquals(MessagesConstants.COUNTRIES_NOT_FOUND, exception.getMessage());
        verify(countryClient).getCountryByName("Brasil");
    }

    private CountryOutputDTO createCountryOutputDTO() {
        return CountryOutputDTO.builder()
                .id(UUID.fromString("d0d7c2a0-0b7a-4e1e-8b7a-0b7a4e1e8b7a"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

}
