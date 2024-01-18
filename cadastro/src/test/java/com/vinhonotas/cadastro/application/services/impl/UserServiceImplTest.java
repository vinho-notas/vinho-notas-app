package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.UserConverter;
import com.vinhonotas.cadastro.domain.entities.*;
import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import com.vinhonotas.cadastro.infrastructure.UserRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;
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
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserConverter userConverter;

    private UserInputDTO inputDTO;
    private UserEntity entity;

    @BeforeEach
    void setUp() {
        log.info("Iniciando teste");

        inputDTO = createInputDTO();
        entity = createEntity();
    }

    @Test
    @DisplayName("Teste de criação de pessoa com sucesso")
    void testCreateSuccess() {
        when(userConverter.toEntity(inputDTO)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(entity);

        UserEntity entity = assertDoesNotThrow(() -> userService.create(inputDTO));
        assertNotNull(entity);
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        assertEquals("João", entity.getPerson().getName());
        assertEquals("email@email.com", entity.getEmail());
        assertEquals("123456", entity.getPassword());
        verify(userConverter, times(1)).toEntity(inputDTO);
        verify(userRepository, times(1)).save(entity);
    }

    @Test
    @DisplayName("Teste de criação de pessoa com exceção")
    void testCreateException() {
        when(userConverter.toEntity(inputDTO)).thenReturn(entity);
        when(userRepository.save(entity)).thenThrow(RuntimeException.class);

        Exception exception = assertThrows(Exception.class, () -> userService.create(inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING_USER, exception.getMessage());
        verify(userConverter, times(1)).toEntity(inputDTO);
        verify(userRepository, times(1)).save(entity);
    }

    @Test
    @DisplayName("Deve retornar uma lista de pessoas")
    void testGetAll() {
        when(userRepository.findAll()).thenReturn(List.of(entity));

        List<UserEntity> list = assertDoesNotThrow(() -> userService.getAll());
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), list.get(0).getId());
        assertEquals("João", list.get(0).getPerson().getName());
        assertEquals("email@email.com", list.get(0).getEmail());
        assertEquals("123456", list.get(0).getPassword());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar uma pessoa pelo id")
    void testGetById() {
        when(userRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.of(entity));

        UserEntity entity = assertDoesNotThrow(() -> userService.getById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertNotNull(entity);
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        assertEquals("João", entity.getPerson().getName());
        assertEquals("email@email.com", entity.getEmail());
        assertEquals("123456", entity.getPassword());
        verify(userRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao buscar uma pessoa pelo id")
    void testGetByIdException() {
        when(userRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> userService.getById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertEquals(MessagesConstants.USER_NOT_FOUND, exception.getMessage());
        verify(userRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar uma pessoa pelo nome")
    void testGetByName() {
        when(userRepository.findByPersonName("João")).thenReturn(entity);

        UserEntity entity = assertDoesNotThrow(() -> userService.getByName("João"));
        assertNotNull(entity);
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        assertEquals("João", entity.getPerson().getName());
        assertEquals("email@email.com", entity.getEmail());
        assertEquals("123456", entity.getPassword());
        verify(userRepository, times(1)).findByPersonName("João");
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao buscar uma pessoa pelo nome")
    void testGetByNameException() {
        when(userRepository.findByPersonName("João")).thenThrow(IllegalArgumentException.class);

        Exception exception = assertThrows(Exception.class, () -> userService.getByName("João"));
        assertEquals(MessagesConstants.USER_NOT_FOUND_WITH_NAME + "João", exception.getMessage());
        verify(userRepository, times(1)).findByPersonName("João");
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa")
    void testUpdate() {
        when(userRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.of(entity));
        when(userConverter.toEntityUpdate(entity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), inputDTO)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(entity);
        when(userRepository.findByPersonName("João")).thenReturn(entity);

        UserEntity entity = assertDoesNotThrow(() -> userService.update(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), inputDTO));
        assertNotNull(entity);
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        assertEquals("João", entity.getPerson().getName());
        assertEquals("email@email.com", entity.getEmail());
        assertEquals("123456", entity.getPassword());
        verify(userRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
        verify(userConverter, times(1)).toEntityUpdate(entity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), inputDTO);
        verify(userRepository, times(1)).save(entity);
        verify(userRepository, times(1)).findByPersonName("João");
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao atualizar uma pessoa")
    void testUpdateException() {
        when(userRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> userService.update(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), inputDTO));
        assertEquals(MessagesConstants.ERROR_UPDATE_USER_DATA, exception.getMessage());
        verify(userRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
        verify(userConverter, times(0)).toEntityUpdate(entity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), inputDTO);
        verify(userRepository, times(0)).save(entity);
    }

    @Test
    @DisplayName("Deve deletar uma pessoa")
    void testDelete() {
        assertDoesNotThrow(() -> userService.delete(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        verify(userRepository, times(1)).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao deletar uma pessoa")
    void testDeleteException() {
        doThrow(IllegalArgumentException.class).when(userRepository).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));

        Exception exception = assertThrows(Exception.class, () -> userService.delete(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertEquals(MessagesConstants.ERROR_DELETE_USER_DATA, exception.getMessage());
        verify(userRepository, times(1)).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    private UserEntity createEntity() {
        return UserEntity.builder()
                .id(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))
                .person(createPerson())
                .enumProfile(EnumProfile.OENOPHILE)
                .email("email@email.com")
                .password("123456")
                .build();
    }

    private UserInputDTO createInputDTO() {
        return UserInputDTO.builder()
                .person(createPerson())
                .enumProfile(EnumProfile.OENOPHILE)
                .email("email@email.com")
                .password("123456")
                .build();
    }

    private PersonEntity createPerson() {
        return PersonEntity.builder()
                .name("João")
                .document("12345678900")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(createAddressEntity())
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