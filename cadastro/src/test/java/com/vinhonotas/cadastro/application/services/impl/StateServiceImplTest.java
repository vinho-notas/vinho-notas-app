package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.converters.StateConverter;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.infrastructure.StateRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StateServiceImplTest {

    @InjectMocks
    private StateServiceImpl stateService;
    @Mock
    private StateRepository stateRepository;
    @Mock
    private StateConverter stateConverter;
    @Mock
    private CountryConverter countryConverter;
    @Mock
    private CountryRepository countryRepository;

    private StateEntity scEntity;
    private StateInputDTO scInputDTO;

    @BeforeEach
    void setUp() {
        scEntity = createSCEntity();
        scInputDTO = createSCInputDTO();
    }

    @Test
    @DisplayName("Teste de criação de estado com sucesso")
    void testCreateSuccess() {
        when(stateConverter.convertToEntity(scInputDTO)).thenReturn(scEntity);
        when(stateRepository.save(scEntity)).thenReturn(scEntity);
        when(stateRepository.findByStateName(scInputDTO.getStateName())).thenReturn(null);
        when(countryRepository.findByCountryName(scInputDTO.getCountry().getCountryName())).thenReturn(createBrasilEntity());

        StateEntity entity = assertDoesNotThrow(() -> stateService.create(scInputDTO));
        assertNotNull(entity);
        assertEquals("Santa Catarina", entity.getStateName());
        assertEquals("SC", entity.getUf());
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        assertEquals("Brasil", entity.getCountry().getCountryName());
        verify(stateConverter, times(1)).convertToEntity(scInputDTO);
        verify(stateRepository, times(1)).save(scEntity);
    }

    @Test
    @DisplayName("Teste de criação de estado com exceção")
    void testCreateException() {
        Exception exception = assertThrows(Exception.class, () -> stateService.create(scInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING_STATE, exception.getMessage());
        verify(stateConverter, times(0)).convertToEntity(scInputDTO);
        verify(stateRepository, times(0)).save(scEntity);
        verify(stateRepository, times(1)).findByStateName(scInputDTO.getStateName());
        verify(countryRepository, times(1)).findByCountryName(scInputDTO.getCountry().getCountryName());
    }

    @Test
    @DisplayName("Deve retornar uma lista de estados")
    void testGetAll() {
        when(stateRepository.findAll()).thenReturn(List.of(scEntity));

        List<StateEntity> list = assertDoesNotThrow(() -> stateService.getAll());
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), list.get(0).getId());
        assertEquals("Santa Catarina", list.get(0).getStateName());
        assertEquals("SC", list.get(0).getUf());
        assertEquals("Brasil", list.get(0).getCountry().getCountryName());
        verify(stateRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar um estado pelo id")
    void testGetById() {
        when(stateRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.of(scEntity));

        StateEntity entity = assertDoesNotThrow(() -> stateService.getById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertNotNull(entity);
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        assertEquals("Santa Catarina", entity.getStateName());
        assertEquals("SC", entity.getUf());
        assertEquals("Brasil", entity.getCountry().getCountryName());
        verify(stateRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao buscar um estado pelo id")
    void testGetByIdException() {
        when(stateRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> stateService.getById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertEquals(MessagesConstants.STATE_NOT_FOUND, exception.getMessage());
        verify(stateRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar um estado pelo nome")
    void testGetByName() {
        when(stateRepository.findByStateName("Santa Catarina")).thenReturn(scEntity);

        StateEntity entity = assertDoesNotThrow(() -> stateService.getByName("Santa Catarina"));
        assertNotNull(entity);
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        assertEquals("Santa Catarina", entity.getStateName());
        assertEquals("SC", entity.getUf());
        assertEquals("Brasil", entity.getCountry().getCountryName());
        verify(stateRepository, times(1)).findByStateName("Santa Catarina");
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao buscar um estado pelo nome")
    void testGetByNameException() {
        when(stateRepository.findByStateName("Santa Catarina")).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> stateService.getByName("Santa Catarina"));
        assertEquals(MessagesConstants.STATE_NOT_FOUND_WITH_NAME + "Santa Catarina", exception.getMessage());
        verify(stateRepository, times(1)).findByStateName("Santa Catarina");
    }

    @Test
    @DisplayName("Deve retornar uma lista de estados pela UF")
    void testGetByUf() {
        when(stateRepository.findByUf("SC")).thenReturn(scEntity);

        StateEntity state = assertDoesNotThrow(() -> stateService.getByUf("SC"));
        assertNotNull(state);
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), state.getId());
        assertEquals("Santa Catarina", state.getStateName());
        assertEquals("SC", state.getUf());
        assertEquals("Brasil", state.getCountry().getCountryName());
        verify(stateRepository, times(1)).findByUf("SC");
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao buscar uma lista de estados pela UF")
    void testGetByUfException() {
        when(stateRepository.findByUf("SC")).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> stateService.getByUf("SC"));
        assertEquals(MessagesConstants.STATE_NOT_FOUND_WITH_UF + "SC", exception.getMessage());
        verify(stateRepository, times(1)).findByUf("SC");
    }

    @Test
    @DisplayName("Deve atualizar um estado")
    void testUpdate() {
        when(stateRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.of(scEntity));
        when(stateConverter.convertToEntityUpdate(scEntity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), scInputDTO)).thenReturn(scEntity);
        when(stateRepository.save(scEntity)).thenReturn(scEntity);
        when(stateRepository.findByStateName("Santa Catarina")).thenReturn(scEntity);

        StateEntity entity = assertDoesNotThrow(() -> stateService.update(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), scInputDTO));
        assertNotNull(entity);
        assertEquals("Santa Catarina", entity.getStateName());
        assertEquals("SC", entity.getUf());
        assertEquals("Brasil", entity.getCountry().getCountryName());
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        verify(stateRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
        verify(stateConverter, times(1)).convertToEntityUpdate(scEntity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), scInputDTO);
        verify(stateRepository, times(1)).save(scEntity);
        verify(stateRepository, times(1)).findByStateName("Santa Catarina");
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao atualizar um estado")
    void testUpdateException() {
        when(stateRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> stateService.update(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), scInputDTO));
        assertEquals(MessagesConstants.ERROR_UPDATE_STATE_DATA, exception.getMessage());
        verify(stateRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
        verify(stateConverter, times(0)).convertToEntityUpdate(scEntity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), scInputDTO);
        verify(stateRepository, times(0)).save(scEntity);
    }

    @Test
    @DisplayName("Deve deletar um estado")
    void testDelete() {
        when(stateRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.of(scEntity));
        assertDoesNotThrow(() -> stateService.delete(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        verify(stateRepository, times(1)).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao deletar um estado")
    void testDeleteException() {
        doThrow(BadRequestException.class).when(stateRepository).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
        when(stateRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.of(scEntity));

        Exception exception = assertThrows(Exception.class, () -> stateService.delete(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertEquals(MessagesConstants.ERROR_DELETE_STATE_DATA, exception.getMessage());
        verify(stateRepository, times(1)).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    private StateInputDTO createSCInputDTO() {
        return StateInputDTO.builder()
                .stateName("Santa Catarina")
                .uf("SC")
                .country(createBrasilInputDTO())
                .build();
    }

    private CountryInputDTO createBrasilInputDTO() {
        return CountryInputDTO.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private CountryEntity createBrasilEntity() {
        return CountryEntity.builder()
                .id(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private StateEntity createSCEntity() {
        return StateEntity.builder()
                .id(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))
                .stateName("Santa Catarina")
                .uf("SC")
                .country(createBrasilEntity())
                .build();
    }

}