package com.vinhonotas.cadastro.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.services.CountryService;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.interfaces.controllers.CountryController;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
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

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CountryController.class)
@Log4j2
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CountryService countryService;
    @MockBean
    private CountryConverter countryConverter;

    private CountryEntity brasilEntity;
    private CountryInputDTO brasilDTO;

    @BeforeEach
    void setUp() {
        log.info("Iniciando teste");

        brasilEntity = createBrasilEntity();
        brasilDTO = createBrasilDTO();
    }

    @Test
    @DisplayName("Deve criar um país")
    void testCreateCountry() throws Exception {
        when(countryService.create(brasilDTO)).thenReturn(brasilEntity);

        mockMvc.perform(post("/api/v1/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brasilDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.countryName").value("Brasil"))
                .andExpect(jsonPath("$.continentName").value("América do Sul"));
    }

    @Test
    @DisplayName("Deve retornar erro ao criar um país")
    void testCreateCountryError() throws Exception {
        when(countryService.create(brasilDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_COUNTRY));

        mockMvc.perform(post("/api/v1/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brasilDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de países")
    void testGetAllCountries() throws Exception {
        when(countryService.getAll()).thenReturn(List.of(brasilEntity));

        mockMvc.perform(get("/api/v1/countries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.[0].countryName").value("Brasil"))
                .andExpect(jsonPath("$.[0].continentName").value("América do Sul"));
    }

    @Test
    @DisplayName("Deve retornar um país pelo id")
    void testGetCountryById() throws Exception {
        when(countryService.getById(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))).thenReturn(brasilEntity);

        mockMvc.perform(get("/api/v1/countries/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.countryName").value("Brasil"))
                .andExpect(jsonPath("$.continentName").value("América do Sul"));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar um país pelo id")
    void testGetCountryByIdError() throws Exception {
        when(countryService.getById(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))).thenThrow(new BadRequestException(MessagesConstants.COUNTRY_NOT_FOUND));

        mockMvc.perform(get("/api/v1/countries/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um país pelo nome")
    void testGetCountryByName() throws Exception {
        when(countryService.getByName("Brasil")).thenReturn(brasilEntity);

        mockMvc.perform(get("/api/v1/countries/name/Brasil")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.countryName").value("Brasil"))
                .andExpect(jsonPath("$.continentName").value("América do Sul"));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar um país pelo nome")
    void testGetCountryByNameError() throws Exception {
        when(countryService.getByName("Brasil")).thenThrow(new BadRequestException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME + "Brasil"));

        mockMvc.perform(get("/api/v1/countries/name/Brasil")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de países pelo continente")
    void testGetCountryByContinent() throws Exception {
        when(countryService.getByContinent("América do Sul")).thenReturn(List.of(brasilEntity));

        mockMvc.perform(get("/api/v1/countries/continent/América do Sul")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.[0].countryName").value("Brasil"))
                .andExpect(jsonPath("$.[0].continentName").value("América do Sul"));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar uma lista de países pelo continente")
    void testGetCountryByContinentError() throws Exception {
        when(countryService.getByContinent("América do Sul")).thenThrow(new BadRequestException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_CONTINENT + "América do Sul"));

        mockMvc.perform(get("/api/v1/countries/continent/América do Sul")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar um país")
    void testUpdateCountry() throws Exception {
        CountryEntity entity = createBrasilEntity();
        entity.setCountryName("Argentina");
        when(countryService.update(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), brasilDTO)).thenReturn(entity);

        mockMvc.perform(put("/api/v1/countries/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brasilDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.countryName").value("Argentina"))
                .andExpect(jsonPath("$.continentName").value("América do Sul"));
    }

    @Test
    @DisplayName("Deve retornar erro ao atualizar um país")
    void testUpdateCountryError() throws Exception {
        when(countryService.update(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), brasilDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_UPDATE_COUNTRY_DATA));

        mockMvc.perform(put("/api/v1/countries/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brasilDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar um país")
    void testDeleteCountry() throws Exception {
        mockMvc.perform(delete("/api/v1/countries/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar erro ao deletar um país")
    void testDeleteCountryError() throws Exception {
        doThrow(BadRequestException.class).when(countryService).delete(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
        mockMvc.perform(delete("/api/v1/countries/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private CountryInputDTO createBrasilDTO() {
        return CountryInputDTO.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private CountryEntity createBrasilEntity() {
        return CountryEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    @AfterEach
    void tearDownEach() {
        log.info("Finalizando teste");
    }
}