package com.vinhonotas.cadastro.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.cadastro.application.converters.UserConverter;
import com.vinhonotas.cadastro.application.services.UserService;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.*;
import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.*;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.*;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;
    @MockBean
    private UserConverter userConverter;

    private AddressOutputDTO address;
    private PersonOutputDTO person;
    private UserEntity userEntity;
    private UserInputDTO userInputDTO;
    private UserOutputDTO userOutputDTO;

    @BeforeEach
    void setUp() {
        address = createAddress();
        person = createPerson();
        userEntity = createUserEntity();
        userInputDTO = createUserIntputDTO();
        userOutputDTO = createUserOutputDTO();
    }

    @Test
    @DisplayName("Deve criar um usuário")
    void testCreateUser() throws Exception {
        when(userService.create(any(UserInputDTO.class))).thenReturn(userEntity);
        when(userConverter.convertToOutputDTO(any(UserEntity.class))).thenReturn(userOutputDTO);
        userConverter.convertToOutputDTO(userService.create(userInputDTO));

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .andExpect(jsonPath("$.person.id").value("987efc9e-f787-4e83-bc88-bf1159230930"))
                .andExpect(jsonPath("$.enumProfile").value("Enófilo"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.password").value("123456"));
    }

    @Test
    @DisplayName("Deve retornar erro ao criar um usuário com email já existente")
    void testCreateUserWithExistingEmail() throws Exception {
        when(userService.create(any(UserInputDTO.class))).thenThrow(new BadRequestException(MessagesConstants.USER_ALREADY_EXISTS));

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de usuários")
    void testGetAllUser() throws Exception {
        when(userService.getAll()).thenReturn(List.of(userEntity));
        when(userConverter.convertToOutputDTOList(any(List.class))).thenReturn(List.of(userOutputDTO));
        userConverter.convertToOutputDTOList(userService.getAll());

        mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .andExpect(jsonPath("$[0].person.id").value("987efc9e-f787-4e83-bc88-bf1159230930"))
                .andExpect(jsonPath("$[0].enumProfile").value("Enófilo"))
                .andExpect(jsonPath("$[0].email").value("email@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("123456"));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar uma lista de usuários vazia")
    void testGetAllUserWithEmptyList() throws Exception {
        when(userService.getAll()).thenThrow(new BadRequestException(MessagesConstants.USERS_NOT_FOUND));

        mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um usuário pelo id")
    void testGetUserById() throws Exception {
        when(userService.getById(any(UUID.class))).thenReturn(userEntity);
        when(userConverter.convertToOutputDTO(any(UserEntity.class))).thenReturn(userOutputDTO);
        userConverter.convertToOutputDTO(userService.getById(UUID.fromString("8d39bcba-cb01-4103-b562-93c84a89c972")));

        mockMvc.perform(get("/api/v1/users/8d39bcba-cb01-4103-b562-93c84a89c972")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .andExpect(jsonPath("$.person.id").value("987efc9e-f787-4e83-bc88-bf1159230930"))
                .andExpect(jsonPath("$.enumProfile").value("Enófilo"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.password").value("123456"));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar um usuário pelo id inexistente")
    void testGetUserByIdWithNonexistentId() throws Exception {
        when(userService.getById(any(UUID.class))).thenThrow(new BadRequestException(MessagesConstants.USER_NOT_FOUND));

        mockMvc.perform(get("/api/v1/users/8d39bcba-cb01-4103-b562-93c84a89c972")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um usuário pelo nome")
    void testGetUserByName() throws Exception {
        when(userService.getByName(any(String.class))).thenReturn(userEntity);
        when(userConverter.convertToOutputDTO(any(UserEntity.class))).thenReturn(userOutputDTO);
        userConverter.convertToOutputDTO(userService.getByName("Usuario Teste"));

        mockMvc.perform(get("/api/v1/users/name/Usuario Teste")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .andExpect(jsonPath("$.person.id").value("987efc9e-f787-4e83-bc88-bf1159230930"))
                .andExpect(jsonPath("$.enumProfile").value("Enófilo"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.password").value("123456"));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar um usuário pelo nome inexistente")
    void testGetUserByNameWithNonexistentName() throws Exception {
        when(userService.getByName(any(String.class))).thenThrow(new BadRequestException(MessagesConstants.USER_NOT_FOUND_WITH_NAME + "Usuario Teste"));

        mockMvc.perform(get("/api/v1/users/name/Usuario Teste")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    void testUpdateUser() throws Exception {
        userOutputDTO.setPassword("444444");
        when(userService.update(any(UUID.class), any(UserInputDTO.class))).thenReturn(userEntity);
        when(userConverter.convertToOutputDTO(any(UserEntity.class))).thenReturn(userOutputDTO);
        when(userConverter.convertToOutputDTOUpdate(any(UserEntity.class), any(UUID.class), any(UserOutputDTO.class))).thenReturn(userOutputDTO);
        userConverter.convertToOutputDTOUpdate(userService.update(UUID.fromString("8d39bcba-cb01-4103-b562-93c84a89c972"), userInputDTO), UUID.fromString("8d39bcba-cb01-4103-b562-93c84a89c972"), userConverter.convertToOutputDTO(userService.update(UUID.fromString("8d39bcba-cb01-4103-b562-93c84a89c972"), userInputDTO)));

        mockMvc.perform(put("/api/v1/users/8d39bcba-cb01-4103-b562-93c84a89c972")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .andExpect(jsonPath("$.person.id").value("987efc9e-f787-4e83-bc88-bf1159230930"))
                .andExpect(jsonPath("$.enumProfile").value("Enófilo"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.password").value("444444"));
    }

    @Test
    @DisplayName("Deve retornar erro ao atualizar um usuário com id inexistente")
    void testUpdateUserWithNonexistentId() throws Exception {
        when(userService.update(any(UUID.class), any(UserInputDTO.class))).thenThrow(new BadRequestException(MessagesConstants.USER_NOT_FOUND));

        mockMvc.perform(put("/api/v1/users/8d39bcba-cb01-4103-b562-93c84a89c972")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInputDTO)))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar um usuário")
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/users/8d39bcba-cb01-4103-b562-93c84a89c972")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar erro ao deletar um usuário com id inexistente")
    void testDeleteUserWithNonexistentId() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.USER_NOT_FOUND)).when(userService).delete(any(UUID.class));

        mockMvc.perform(delete("/api/v1/users/8d39bcba-cb01-4103-b562-93c84a89c972")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    private AddressOutputDTO createAddress() {
        return AddressOutputDTO.builder()
                .id(UUID.fromString("c775e102-04e2-4e61-9f32-d78c1713ef03"))
                .addressDescription("Rua Teste")
                .addressNumber(123)
                .city("Cidade Teste")
                .uf(Mockito.mock(StateOutputDTO.class))
                .complement("Complemento Teste")
                .country(Mockito.mock(CountryOutputDTO.class))
                .district("Bairro Teste")
                .phoneNumber("123456789")
                .zipCode("12345678")
                .build();
    }

    private PersonOutputDTO createPerson() {
        return PersonOutputDTO.builder()
                .id(UUID.fromString("987efc9e-f787-4e83-bc88-bf1159230930"))
                .name("Usuario Teste")
                .birthDate(LocalDate.of(1990, 10, 10))
                .document("12345678900")
                .address(address)
                .build();
    }

    private UserEntity createUserEntity() {
        return UserEntity.builder()
                .id(UUID.fromString("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .person(createPersonEntity())
                .enumProfile(EnumProfile.OENOPHILE)
                .email("email@gmail.com")
                .password("123456")
                .build();
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
                .id(UUID.fromString("987efc9e-f787-4e83-bc88-bf1159230930"))
                .name("Usuario Teste")
                .birthDate(LocalDate.of(1990, 10, 10))
                .document("12345678900")
                .address(createAddressEntity())
                .build();
    }

    private AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .id(UUID.fromString("c775e102-04e2-4e61-9f32-d78c1713ef03"))
                .addressDescription("Rua Teste")
                .addressNumber(123)
                .city("Cidade Teste")
                .uf(Mockito.mock(StateEntity.class))
                .complement("Complemento Teste")
                .country(Mockito.mock(CountryEntity.class))
                .district("Bairro Teste")
                .phoneNumber("123456789")
                .zipCode("12345678")
                .build();
    }

    private UserInputDTO createUserIntputDTO() {
        return UserInputDTO.builder()
                .personId(createPersonInputDTO().getId())
                .enumProfile(EnumProfile.OENOPHILE.getCode())
                .email("email@gmail.com")
                .password("123456")
                .build();
    }

    private PersonInputDTO createPersonInputDTO() {
        return PersonInputDTO.builder()
                .name("Usuario Teste")
                .birthDate(LocalDate.of(1990, 10, 10))
                .document("12345678900")
                .address(createAddressInputDTO())
                .build();
    }

    private AddressInputDTO createAddressInputDTO() {
        return AddressInputDTO.builder()
                .addressDescription("Rua Teste")
                .addressNumber(123)
                .city("Cidade Teste")
                .uf("SC")
                .complement("Complemento Teste")
                .country("Brasil")
                .district("Bairro Teste")
                .phoneNumber("123456789")
                .zipCode("12345678")
                .build();
    }

    private UserOutputDTO createUserOutputDTO() {
        return UserOutputDTO.builder()
                .id(UUID.fromString("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .person(person)
                .enumProfile(EnumProfile.OENOPHILE.getCode())
                .email("email@gmail.com")
                .password("123456")
                .build();
    }

}