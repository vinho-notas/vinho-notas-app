package com.vinhonotas.bff.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.cadastro.CountryService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CountryService countryService;

    private CountryOutputDTO brasil;

    @BeforeEach
    void setUp() {
        brasil = createBrasilOutputDTO();
    }

    @Test
    @DisplayName("Deve retornar uma lista de países")
    void testGetAllCountries() throws Exception {
        when(countryService.getAllCountries()).thenReturn(List.of(brasil));

        mockMvc.perform((get("/api/v1/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brasil))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$[0].countryName").value("Brasil"))
                .andExpect(jsonPath("$[0].continentName").value("América do Sul"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar retornar uma lista de países vazia")
    void testGetAllCountriesBadRequestException() throws Exception {
        when(countryService.getAllCountries()).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform((get("/api/v1/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brasil))))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um país pelo seu id")
    void testGetCountryById() throws Exception {
        when(countryService.getCountryById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")).thenReturn(brasil);

        mockMvc.perform((get("/api/v1/countries/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brasil))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.countryName").value("Brasil"))
                .andExpect(jsonPath("$.continentName").value("América do Sul"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar retornar um país pelo seu id")
    void testGetCountryByIdBadRequestException() throws Exception {
        when(countryService.getCountryById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform((get("/api/v1/countries/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brasil))))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um país pelo seu nome")
    void testGetCountryByName() throws Exception {
        when(countryService.getCountryByName("Brasil")).thenReturn(brasil);

        mockMvc.perform((get("/api/v1/countries/name/Brasil")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brasil))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.countryName").value("Brasil"))
                .andExpect(jsonPath("$.continentName").value("América do Sul"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar retornar um país pelo seu nome")
    void testGetCountryByNameBadRequestException() throws Exception {
        when(countryService.getCountryByName("Brasil"))
                .thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform((get("/api/v1/countries/name/Brasil")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brasil))))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private CountryOutputDTO createBrasilOutputDTO() {
        return CountryOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

}