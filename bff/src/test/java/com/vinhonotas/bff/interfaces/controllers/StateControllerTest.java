package com.vinhonotas.bff.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.cadastro.StateService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.CountryOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.StateOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

@WebMvcTest(controllers = StateController.class)
class StateControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StateService stateService;

    private StateOutputDTO santaCatarina;
    private StateOutputDTO parana;
    private StateOutputDTO rioGrandeDoSul;

    @BeforeEach
    void setUp() {
        santaCatarina = createSantaCatarina();
        parana = createParana();
        rioGrandeDoSul = createRioGrandeDoSul();
    }

    @Test
    @DisplayName("Deve retornar uma lista de estados")
    void testGetAllStates() throws Exception {
        when(stateService.getAllStates()).thenReturn(List.of(santaCatarina, parana, rioGrandeDoSul));

        mockMvc.perform((get("/api/v1/states")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$[0].stateName").value("Santa Catarina"))
                .andExpect(jsonPath("$[0].uf").value("SC"))
                .andExpect(jsonPath("$[1].id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22"))
                .andExpect(jsonPath("$[1].stateName").value("Paraná"))
                .andExpect(jsonPath("$[1].uf").value("PR"))
                .andExpect(jsonPath("$[2].id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a33"))
                .andExpect(jsonPath("$[2].stateName").value("Rio Grande do Sul"))
                .andExpect(jsonPath("$[2].uf").value("RS"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar retornar uma lista de estados vazia")
    void testGetAllStatesBadRequestException() throws Exception {
        when(stateService.getAllStates()).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform((get("/api/v1/states")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um estado pelo seu id")
    void testGetStateById() throws Exception {
        when(stateService.getStateById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")).thenReturn(santaCatarina);

        mockMvc.perform((get("/api/v1/states/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.stateName").value("Santa Catarina"))
                .andExpect(jsonPath("$.uf").value("SC"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar retornar um estado pelo seu id")
    void testGetStateByIdBadRequestException() throws Exception {
        when(stateService.getStateById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform((get("/api/v1/states/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um estado pelo seu nome")
    void testGetStateByName() throws Exception {
        when(stateService.getStateByName("Santa Catarina")).thenReturn(santaCatarina);

        mockMvc.perform((get("/api/v1/states/name/Santa Catarina")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.stateName").value("Santa Catarina"))
                .andExpect(jsonPath("$.uf").value("SC"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar retornar um estado pelo seu nome")
    void testGetStateByNameBadRequestException() throws Exception {
        when(stateService.getStateByName("Santa Catarina"))
                .thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform((get("/api/v1/states/name/Santa Catarina")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um estado pelo seu uf")
    void testGetStateByUf() throws Exception {
        when(stateService.getStateByUf("SC")).thenReturn(santaCatarina);

        mockMvc.perform((get("/api/v1/states/uf/SC")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.stateName").value("Santa Catarina"))
                .andExpect(jsonPath("$.uf").value("SC"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar retornar um estado pelo seu uf")
    void testGetStateByUfBadRequestException() throws Exception {
        when(stateService.getStateByUf("SC"))
                .thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform((get("/api/v1/states/uf/SC")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private StateOutputDTO createSantaCatarina() {
        return StateOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .stateName("Santa Catarina")
                .uf("SC")
                .country(Mockito.mock(CountryOutputDTO.class))
                .build();
    }

    private StateOutputDTO createParana() {
        return StateOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22"))
                .stateName("Paraná")
                .uf("PR")
                .country(Mockito.mock(CountryOutputDTO.class))
                .build();
    }

    private StateOutputDTO createRioGrandeDoSul() {
        return StateOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a33"))
                .stateName("Rio Grande do Sul")
                .uf("RS")
                .country(Mockito.mock(CountryOutputDTO.class))
                .build();
    }

}
