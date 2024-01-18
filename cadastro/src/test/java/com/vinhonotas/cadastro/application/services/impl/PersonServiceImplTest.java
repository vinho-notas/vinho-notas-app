package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.PersonConverter;
import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.infrastructure.PersonRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Log4j2
class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonConverter personConverter;

    private PersonInputDTO inputDTO;
    private PersonEntity entity;

    @BeforeEach
    void setUp() {
        log.info("Iniciando teste");

        inputDTO = createInputDTO();
        entity = createEntity();
    }

    @Test
    @DisplayName("Teste de criação de pessoa com sucesso")
    void testCreateSuccess() {
        when(personConverter.toEntity(inputDTO)).thenReturn(entity);
        when(personRepository.save(entity)).thenReturn(entity);

        PersonEntity entity = assertDoesNotThrow(() -> personService.create(inputDTO));
        assertNotNull(entity);
        assertEquals("João", entity.getName());
        assertEquals("12345678900", entity.getDocument());
        assertEquals(LocalDate.of(1990, 1, 1), entity.getBirthDate());
        assertEquals("Rua 3", entity.getAddress().getAddressDescription());
        verify(personConverter, times(1)).toEntity(inputDTO);
        verify(personRepository, times(1)).save(entity);
    }

    @Test
    @DisplayName("Teste de criação de pessoa com exceção")
    void testCreateException() {
        when(personConverter.toEntity(inputDTO)).thenReturn(entity);
        when(personRepository.save(entity)).thenThrow(RuntimeException.class);

        Exception exception = assertThrows(Exception.class, () -> personService.create(inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING_PERSON, exception.getMessage());
        verify(personConverter, times(1)).toEntity(inputDTO);
        verify(personRepository, times(1)).save(entity);
    }

    @Test
    @DisplayName("Deve retornar uma lista de pessoas")
    void testGetAll() {
        when(personRepository.findAll()).thenReturn(List.of(entity));

        List<PersonEntity> list = assertDoesNotThrow(() -> personService.getAll());
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("João", list.get(0).getName());
        assertEquals("12345678900", list.get(0).getDocument());
        assertEquals(LocalDate.of(1990, 1, 1), list.get(0).getBirthDate());
        assertEquals("Rua 3", list.get(0).getAddress().getAddressDescription());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar uma pessoa pelo id")
    void testGetById() {
        when(personRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.of(entity));

        PersonEntity entity = assertDoesNotThrow(() -> personService.getById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertNotNull(entity);
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        assertEquals("João", entity.getName());
        assertEquals("12345678900", entity.getDocument());
        assertEquals(LocalDate.of(1990, 1, 1), entity.getBirthDate());
        assertEquals("Rua 3", entity.getAddress().getAddressDescription());
        verify(personRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao buscar uma pessoa pelo id")
    void testGetByIdException() {
        when(personRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> personService.getById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertEquals(MessagesConstants.PERSON_NOT_FOUND, exception.getMessage());
        verify(personRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar uma pessoa pelo nome")
    void testGetByName() {
        when(personRepository.findByName("João")).thenReturn(entity);

        PersonEntity entity = assertDoesNotThrow(() -> personService.getByName("João"));
        assertNotNull(entity);
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        assertEquals("João", entity.getName());
        assertEquals("12345678900", entity.getDocument());
        assertEquals(LocalDate.of(1990, 1, 1), entity.getBirthDate());
        assertEquals("Rua 3", entity.getAddress().getAddressDescription());
        verify(personRepository, times(1)).findByName("João");
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao buscar uma pessoa pelo nome")
    void testGetByNameException() {
        when(personRepository.findByName("João")).thenThrow(IllegalArgumentException.class);

        Exception exception = assertThrows(Exception.class, () -> personService.getByName("João"));
        assertEquals(MessagesConstants.PERSON_NOT_FOUND_WITH_NAME + "João", exception.getMessage());
        verify(personRepository, times(1)).findByName("João");
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa")
    void testUpdate() {
        when(personRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.of(entity));
        when(personConverter.toEntityUpdate(entity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), inputDTO)).thenReturn(entity);
        when(personRepository.save(entity)).thenReturn(entity);
        when(personRepository.findByName("João")).thenReturn(entity);

        PersonEntity entity = assertDoesNotThrow(() -> personService.update(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), inputDTO));
        assertNotNull(entity);
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        assertEquals("João", entity.getName());
        assertEquals("12345678900", entity.getDocument());
        assertEquals(LocalDate.of(1990, 1, 1), entity.getBirthDate());
        assertEquals("Rua 3", entity.getAddress().getAddressDescription());
        verify(personRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
        verify(personConverter, times(1)).toEntityUpdate(entity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), inputDTO);
        verify(personRepository, times(1)).save(entity);
        verify(personRepository, times(1)).findByName("João");
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao atualizar uma pessoa")
    void testUpdateException() {
        when(personRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> personService.update(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), inputDTO));
        assertEquals(MessagesConstants.ERROR_UPDATE_PERSON_DATA, exception.getMessage());
        verify(personRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
        verify(personConverter, times(0)).toEntityUpdate(entity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), inputDTO);
        verify(personRepository, times(0)).save(entity);
    }

    @Test
    @DisplayName("Deve deletar uma pessoa")
    void testDelete() {
        assertDoesNotThrow(() -> personService.delete(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        verify(personRepository, times(1)).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao deletar uma pessoa")
    void testDeleteException() {
        doThrow(IllegalArgumentException.class).when(personRepository).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));

        Exception exception = assertThrows(Exception.class, () -> personService.delete(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertEquals(MessagesConstants.ERROR_DELETE_PERSON_DATA, exception.getMessage());
        verify(personRepository, times(1)).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    private PersonEntity createEntity() {
        return PersonEntity.builder()
                .id(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))
                .name("João")
                .document("12345678900")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(createAddressEntity())
                .build();
    }

    private PersonInputDTO createInputDTO() {
        return PersonInputDTO.builder()
                .name("João")
                .document("12345678900")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(createAddressInputDTO())
                .build();
    }

    private AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .id(UUID.fromString("a231dd0c-5f78-4db0-98b5-3c04f6178bf9"))
                .addressDescription("Rua 3")
                .addressNumber(456)
                .complement("Complemento 1")
                .district("Bairro 1")
                .zipCode("99999999")
                .city("Cidade 1")
                .uf(createUf())
                .country(createCountry())
                .phoneNumber("47999999999")
                .build();
    }

    private AddressEntity createAddressInputDTO() {
        return AddressEntity.builder()
                .addressDescription("Rua 3")
                .addressNumber(456)
                .complement("Complemento 1")
                .district("Bairro 1")
                .zipCode("99999999")
                .city("Cidade 1")
                .uf(createUfInputDTO())
                .country(createCountryInputDTO())
                .phoneNumber("47999999999")
                .build();
    }

    private StateEntity createUf() {
        return StateEntity.builder()
                .id(UUID.fromString("8e8447f1-ce6c-47a3-9cea-4f70446049d9"))
                .stateName("Santa Catarina")
                .uf("SC")
                .country(createCountry())
                .build();
    }

    private StateEntity createUfInputDTO() {
        return StateEntity.builder()
                .stateName("Santa Catarina")
                .uf("SC")
                .country(createCountryInputDTO())
                .build();
    }

    private CountryEntity createCountry() {
        return CountryEntity.builder()
                .id(UUID.fromString("2cb051aa-5beb-4678-82cb-af44490c16af"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private CountryEntity createCountryInputDTO() {
        return CountryEntity.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    @AfterEach
    void tearDownEach() {
        log.info("Finalizando teste");
    }

}