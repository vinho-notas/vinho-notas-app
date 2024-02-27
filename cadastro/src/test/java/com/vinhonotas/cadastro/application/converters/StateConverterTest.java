package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.StateOutputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StateConverterTest {

    @InjectMocks
    private StateConverter stateConverter;

    @Mock
    private CountryConverter countryConverter;

    private StateInputDTO stateInputDTO;
    private StateEntity stateEntity;
    private StateOutputDTO stateOutputDTO;

    @BeforeEach
    void setUp() {
        stateEntity = createStateEntity();
        stateInputDTO = createStateInputDTO();
        stateOutputDTO = createStateOutputDTO();
    }

    @Test
    @DisplayName("Teste de conversão de StateInputDTO para StateEntity")
    void testToEntity() {
        StateEntity state = assertDoesNotThrow(()-> stateConverter.toEntity(stateInputDTO));
        assertNotNull(stateEntity);
        assertEquals(stateInputDTO.getStateName(), state.getStateName());
        assertEquals(stateInputDTO.getUf(), state.getUf());
        assertEquals(stateInputDTO.getCountry(), state.getCountry());
    }

    @Test
    @DisplayName("Teste de conversão para StateEntityUpdate ")
    void testToEntityUpdate() {
        stateInputDTO.setStateName("Rio de Janeiro");

        StateEntity state = assertDoesNotThrow(()-> stateConverter.toEntityUpdate(stateEntity, stateEntity.getId(), stateInputDTO));
        assertNotNull(state);
        assertEquals("Rio de Janeiro", state.getStateName());
        assertEquals(stateInputDTO.getUf(), state.getUf());
        assertEquals(stateInputDTO.getCountry(), state.getCountry());
    }

    @Test
    @DisplayName("Teste de conversão de StateEntity para StateOutputDTO")
    void testConvertToOutputDTO() {
        when(countryConverter.convertToOutputDTO(Mockito.any(CountryEntity.class))).thenReturn(Mockito.mock(CountryOutputDTO.class));

        StateOutputDTO stateOutput = assertDoesNotThrow(()-> stateConverter.convertToOutputDTO(stateEntity));
        assertNotNull(stateOutputDTO);
        assertEquals(stateEntity.getId(), stateOutput.getId());
        assertEquals(stateEntity.getStateName(), stateOutput.getStateName());
        assertEquals(stateEntity.getUf(), stateOutput.getUf());
    }

    @Test
    @DisplayName("Teste de conversão de List<StateEntity> para List<StateOutputDTO>")
    void testConvertToOutputDTOList() {
        when(countryConverter.convertToOutputDTO(Mockito.any(CountryEntity.class))).thenReturn(Mockito.mock(CountryOutputDTO.class));

        List<StateOutputDTO> stateOutput = assertDoesNotThrow(()-> stateConverter.convertToOutputDTOList(List.of(stateEntity)));
        assertNotNull(stateOutputDTO);
        assertEquals(stateEntity.getId(), stateOutput.get(0).getId());
        assertEquals(stateEntity.getStateName(), stateOutput.get(0).getStateName());
        assertEquals(stateEntity.getUf(), stateOutput.get(0).getUf());
    }

    @Test
    @DisplayName("Teste de conversão de StateEntity para StateOutputDTOUpdate")
    void testConvertToOutputDTOUpdate() {
        stateOutputDTO.setStateName("Rio de Janeiro");

        StateOutputDTO stateOutput = assertDoesNotThrow(()-> stateConverter.convertToOutputDTOUpdate(stateEntity, stateEntity.getId(), stateOutputDTO));
        assertNotNull(stateOutputDTO);
        assertEquals(stateEntity.getId(), stateOutput.getId());
        assertEquals("Rio de Janeiro", stateOutput.getStateName());
        assertEquals(stateOutputDTO.getUf(), stateOutput.getUf());
        assertEquals(stateOutputDTO.getCountry(), stateOutput.getCountry());
    }

    private StateOutputDTO createStateOutputDTO() {
        return StateOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .stateName("São Paulo")
                .uf("SP")
                .country(Mockito.mock(CountryOutputDTO.class))
                .build();
    }

    private StateInputDTO createStateInputDTO() {
        return StateInputDTO.builder()
                .stateName("São Paulo")
                .uf("SP")
                .country(Mockito.mock(CountryEntity.class))
                .build();
    }

    private StateEntity createStateEntity() {
        return StateEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .stateName("São Paulo")
                .uf("SP")
                .country(Mockito.mock(CountryEntity.class))
                .build();
    }

}