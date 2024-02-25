package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.client.cadastro.StateClient;
import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.StateOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StateServiceImplTest {

    @InjectMocks
    private StateServiceImpl stateService;
    @Mock
    private StateClient stateClient;

    private StateOutputDTO stateOutputDTO;

    @BeforeEach
    void setUp() {
        stateOutputDTO = createStateOutputDTO();
    }

    @Test
    @DisplayName("Deve retornar uma lista de estados")
    void testGetAllStates() {
        List<StateOutputDTO> states = new ArrayList<>();
        states.add(stateOutputDTO);

        when(stateClient.getAllStates()).thenReturn(states);

        List<StateOutputDTO> list = assertDoesNotThrow(() -> stateService.getAllStates());

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(stateOutputDTO.getId(), list.get(0).getId());
        assertEquals(stateOutputDTO.getStateName(), list.get(0).getStateName());
        assertEquals(stateOutputDTO.getUf(), list.get(0).getUf());
        assertEquals(stateOutputDTO.getCountry(), list.get(0).getCountry());
        verify(stateClient).getAllStates();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar buscar todos os estados e não encontrar nenhum")
    void testGetAllStatesNotFound() {
        when(stateClient.getAllStates()).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(Exception.class, () -> stateService.getAllStates());

        assertEquals(MessagesConstants.STATES_NOT_FOUND, exception.getMessage());
        verify(stateClient).getAllStates();
    }

    @Test
    @DisplayName("Deve retornar um estado por id")
    void testGetStateById() {
        when(stateClient.getStateById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")).thenReturn(stateOutputDTO);
        StateOutputDTO response = assertDoesNotThrow(() -> stateService.getStateById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));

        assertNotNull(response);
        assertEquals(stateOutputDTO.getId(), response.getId());
        assertEquals(stateOutputDTO.getStateName(), response.getStateName());
        assertEquals(stateOutputDTO.getUf(), response.getUf());
        assertEquals(stateOutputDTO.getCountry(), response.getCountry());
        verify(stateClient).getStateById(Mockito.anyString());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar buscar um estado pelo id e não encontrar")
    void testGetStateByIdNotFound() {
        Exception exception = assertThrows(Exception.class, () -> stateService.getStateById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));

        assertEquals(MessagesConstants.STATES_NOT_FOUND, exception.getMessage());
        verify(stateClient).getStateById(Mockito.anyString());
    }

    @Test
    @DisplayName("Deve retornar um estado pelo nome")
    void testGetStateByName() {
        when(stateClient.getStateByName("São Paulo")).thenReturn(stateOutputDTO);
        StateOutputDTO response = assertDoesNotThrow(() -> stateService.getStateByName("São Paulo"));

        assertNotNull(response);
        assertEquals(stateOutputDTO.getId(), response.getId());
        assertEquals(stateOutputDTO.getStateName(), response.getStateName());
        assertEquals(stateOutputDTO.getUf(), response.getUf());
        assertEquals(stateOutputDTO.getCountry(), response.getCountry());
        verify(stateClient).getStateByName(Mockito.anyString());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar buscar um estado pelo nome e não encontrar")
    void testGetStateByNameNotFound() {
        Exception exception = assertThrows(Exception.class, () -> stateService.getStateByName("São Paulo"));

        assertEquals(MessagesConstants.STATES_NOT_FOUND, exception.getMessage());
        verify(stateClient).getStateByName(Mockito.anyString());
    }

    @Test
    @DisplayName("Deve retornar um estado pela sigla")
    void testGetStateByUf() {
        when(stateClient.getStateByUf("SP")).thenReturn(stateOutputDTO);
        StateOutputDTO response = assertDoesNotThrow(() -> stateService.getStateByUf("SP"));

        assertNotNull(response);
        assertEquals(stateOutputDTO.getId(), response.getId());
        assertEquals(stateOutputDTO.getStateName(), response.getStateName());
        assertEquals(stateOutputDTO.getUf(), response.getUf());
        assertEquals(stateOutputDTO.getCountry(), response.getCountry());
        verify(stateClient).getStateByUf(Mockito.anyString());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar buscar um estado pela sigla e não encontrar")
    void testGetStateByUfNotFound() {
        Exception exception = assertThrows(Exception.class, () -> stateService.getStateByUf("SP"));

        assertEquals(MessagesConstants.STATES_NOT_FOUND, exception.getMessage());
        verify(stateClient).getStateByUf(Mockito.anyString());
    }

    private StateOutputDTO createStateOutputDTO() {
        return StateOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .stateName("São Paulo")
                .uf("SP")
                .country(Mockito.mock(CountryOutputDTO.class))
                .build();
    }

}
