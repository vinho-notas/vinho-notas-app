package com.vinhonotas.cadastro.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.cadastro.application.converters.StateConverter;
import com.vinhonotas.cadastro.application.services.StateService;
import com.vinhonotas.cadastro.configuration.security.SecurityFilter;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.StateOutputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = StateController.class)
class StateControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private StateService stateService;
    @MockBean
    private StateConverter stateConverter;
    @MockBean
    private SecurityFilter securityFilter;

    private StateInputDTO stateInputDTO;
    private StateEntity stateEntity;
    private StateOutputDTO stateOutputDTO;
    private CountryEntity country;

    @BeforeEach
    void setUp() {
        country = createCountry();
        stateInputDTO = createStateInputDTO();
        stateEntity = createStateEntity();
        stateOutputDTO = createOutputDTO();
    }

    @Test
    @DisplayName("Deve criar um estado")
    void testCreateState() throws Exception {
        when(stateService.create(stateInputDTO)).thenReturn(stateEntity);
        when(stateConverter.convertToOutputDTO(stateEntity)).thenReturn(stateOutputDTO);

        mockMvc.perform(post("/api/v1/states")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stateInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(stateEntity.getId().toString()))
                .andExpect(jsonPath("$.stateName").value(stateEntity.getStateName()))
                .andExpect(jsonPath("$.uf").value(stateEntity.getUf()))
                .andExpect(jsonPath("$.country.id").value(stateEntity.getCountry().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um erro ao criar um estado")
    void testCreateStateError() throws Exception {
        when(stateService.create(stateInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_STATE));

        mockMvc.perform(post("/api/v1/states")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stateInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de estados")
    void testGetAllStates() throws Exception {
        when(stateService.getAll()).thenReturn(List.of(stateEntity));
        when(stateConverter.convertToOutputDTOList(List.of(stateEntity))).thenReturn(List.of(stateOutputDTO));

        mockMvc.perform(get("/api/v1/states")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(stateEntity.getId().toString()))
                .andExpect(jsonPath("$[0].stateName").value(stateEntity.getStateName()))
                .andExpect(jsonPath("$[0].uf").value(stateEntity.getUf()))
                .andExpect(jsonPath("$[0].country.id").value(stateEntity.getCountry().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um estado pelo id")
    void testGetStateById() throws Exception {
        when(stateService.getById(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))).thenReturn(stateEntity);
        when(stateConverter.convertToOutputDTO(stateEntity)).thenReturn(stateOutputDTO);

        mockMvc.perform(get("/api/v1/states/{id}", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(stateEntity.getId().toString()))
                .andExpect(jsonPath("$.stateName").value(stateEntity.getStateName()))
                .andExpect(jsonPath("$.uf").value(stateEntity.getUf()))
                .andExpect(jsonPath("$.country.id").value(stateEntity.getCountry().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um erro ao buscar um estado pelo id")
    void testGetStateByIdError() throws Exception {
        when(stateService.getById(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")))
                .thenThrow(new BadRequestException(MessagesConstants.STATE_NOT_FOUND));

        mockMvc.perform(get("/api/v1/states/{id}", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um estado pelo nome")
    void testGetStateByName() throws Exception {
        when(stateService.getByName("São Paulo")).thenReturn(stateEntity);
        when(stateConverter.convertToOutputDTO(stateEntity)).thenReturn(stateOutputDTO);

        mockMvc.perform(get("/api/v1/states/name/{name}", "São Paulo")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(stateEntity.getId().toString()))
                .andExpect(jsonPath("$.stateName").value(stateEntity.getStateName()))
                .andExpect(jsonPath("$.uf").value(stateEntity.getUf()))
                .andExpect(jsonPath("$.country.id").value(stateEntity.getCountry().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um erro ao buscar um estado pelo nome")
    void testGetStateByNameError() throws Exception {
        when(stateService.getByName("São Paulo"))
                .thenThrow(new BadRequestException(MessagesConstants.STATE_NOT_FOUND_WITH_NAME + "São Paulo"));

        mockMvc.perform(get("/api/v1/states/name/{name}", "São Paulo")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um estado pela UF")
    void testGetStateByUf() throws Exception {
        when(stateService.getByUf("SP")).thenReturn(stateEntity);
        when(stateConverter.convertToOutputDTO(stateEntity)).thenReturn(stateOutputDTO);

        mockMvc.perform(get("/api/v1/states/uf/{uf}", "SP")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(stateEntity.getId().toString()))
                .andExpect(jsonPath("$.stateName").value(stateEntity.getStateName()))
                .andExpect(jsonPath("$.uf").value(stateEntity.getUf()))
                .andExpect(jsonPath("$.country.id").value(stateEntity.getCountry().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um erro ao buscar um estado pela UF")
    void testGetStateByUfError() throws Exception {
        when(stateService.getByUf("SP"))
                .thenThrow(new BadRequestException(MessagesConstants.STATE_NOT_FOUND_WITH_UF + "SP"));

        mockMvc.perform(get("/api/v1/states/uf/{uf}", "SP")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar um estado")
    void testUpdateState() throws Exception {
        stateOutputDTO.setStateName("Rio de Janeiro");
        when(stateService.update(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), stateInputDTO)).thenReturn(stateEntity);
        when(stateConverter.convertToOutputDTO(stateEntity)).thenReturn(stateOutputDTO);
        when(stateConverter.convertToOutputDTOUpdate(stateEntity, UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), stateOutputDTO))
                .thenReturn(stateOutputDTO);

        mockMvc.perform(put("/api/v1/states/{id}", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stateInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(stateEntity.getId().toString()))
                .andExpect(jsonPath("$.stateName").value("Rio de Janeiro"))
                .andExpect(jsonPath("$.uf").value(stateEntity.getUf()))
                .andExpect(jsonPath("$.country.id").value(stateEntity.getCountry().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um erro ao atualizar um estado")
    void testUpdateStateError() throws Exception {
        when(stateService.update(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), stateInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_UPDATE_STATE_DATA));

        mockMvc.perform(put("/api/v1/states/{id}", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stateInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar um estado")
    void testDeleteState() throws Exception {
        mockMvc.perform(delete("/api/v1/states/{id}", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar um erro ao deletar um estado")
    void testDeleteStateError() throws Exception {
        doThrow(BadRequestException.class).when(stateService)
                .delete(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
        mockMvc.perform(delete("/api/v1/states/{id}", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private StateOutputDTO createOutputDTO() {
        return StateOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .stateName("São Paulo")
                .uf("SP")
                .country(createCountryOutputDTO())
                .build();
    }

    private CountryOutputDTO createCountryOutputDTO() {
        return CountryOutputDTO.builder()
                .id(UUID.fromString("e50ae4ba-b799-4506-9efb-345a3f6556fa"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private StateEntity createStateEntity() {
        return StateEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .stateName("São Paulo")
                .uf("SP")
                .country(country)
                .build();
    }

    private StateInputDTO createStateInputDTO() {
        return StateInputDTO.builder()
                .stateName("São Paulo")
                .uf("SP")
                .country(createCountryInputDTO())
                .build();
    }

    private CountryInputDTO createCountryInputDTO() {
        return CountryInputDTO.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private CountryEntity createCountry() {
        return CountryEntity.builder()
                .id(UUID.fromString("e50ae4ba-b799-4506-9efb-345a3f6556fa"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

}