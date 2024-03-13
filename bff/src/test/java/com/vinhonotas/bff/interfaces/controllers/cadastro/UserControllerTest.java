package com.vinhonotas.bff.interfaces.controllers.cadastro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.cadastro.UserService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.domain.enums.EnumProfile;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.*;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.*;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    private UserInputDTO userInputDTO;
    private UserOutputDTO userOutputDTO;

    @BeforeEach
    void setUp() {
        userInputDTO = createUserInputDTO();
        userOutputDTO = createUserOutputDTO();
    }

    @Test
    @DisplayName("Deve criar um usuário")
    void testCreateUser() throws Exception {
        when(userService.createUser(userInputDTO)).thenReturn(userOutputDTO);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .andExpect(jsonPath("$.person.id").value("987efc9e-f787-4e83-bc88-bf1159230930"))
                .andExpect(jsonPath("$.person.name").value("Usuario Teste"))
                .andExpect(jsonPath("$.enumProfile").value("Enófilo"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.password").value("123456"));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar um usuário com dados inválidos")
    void testCreateUserWithInvalidData() throws Exception {
        when(userService.createUser(userInputDTO)).thenThrow(new BadRequestException(MessagesConstants.BAD_REQUEST));

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de usuários")
    void testGetAllUser() throws Exception {
        when(userService.getAllUser()).thenReturn(List.of(userOutputDTO));

        mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .andExpect(jsonPath("$[0].person.id").value("987efc9e-f787-4e83-bc88-bf1159230930"))
                .andExpect(jsonPath("$[0].person.name").value("Usuario Teste"))
                .andExpect(jsonPath("$[0].enumProfile").value("Enófilo"))
                .andExpect(jsonPath("$[0].email").value("email@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("123456"));
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar retornar uma lista de usuários vazia")
    void testGetAllUserWithEmptyList() throws Exception {
        when(userService.getAllUser()).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um usuário pelo id")
    void testGetUserById() throws Exception {
        when(userService.getUserById("8d39bcba-cb01-4103-b562-93c84a89c972")).thenReturn(userOutputDTO);

        mockMvc.perform(get("/api/v1/users/8d39bcba-cb01-4103-b562-93c84a89c972")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .andExpect(jsonPath("$.person.id").value("987efc9e-f787-4e83-bc88-bf1159230930"))
                .andExpect(jsonPath("$.person.name").value("Usuario Teste"))
                .andExpect(jsonPath("$.enumProfile").value("Enófilo"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.password").value("123456"));
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar retornar um usuário pelo id inexistente")
    void testGetUserByIdWithInvalidId() throws Exception {
        when(userService.getUserById("8d39bcba-cb01-4103-b562-93c84a89c972")).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get("/api/v1/users/8d39bcba-cb01-4103-b562-93c84a89c972")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um usuário pelo nome")
    void testGetUserByName() throws Exception {
        when(userService.getUserByName("Usuario Teste")).thenReturn(userOutputDTO);

        mockMvc.perform(get("/api/v1/users/name/Usuario Teste")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .andExpect(jsonPath("$.person.id").value("987efc9e-f787-4e83-bc88-bf1159230930"))
                .andExpect(jsonPath("$.person.name").value("Usuario Teste"))
                .andExpect(jsonPath("$.enumProfile").value("Enófilo"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.password").value("123456"));
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar retornar um usuário pelo nome inexistente")
    void testGetUserByNameWithInvalidName() throws Exception {
        when(userService.getUserByName("Usuario Teste")).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get("/api/v1/users/name/Usuario Teste")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    void testUpdateUser() throws Exception {
        when(userService.updateUser("8d39bcba-cb01-4103-b562-93c84a89c972", userInputDTO)).thenReturn(userOutputDTO);

        mockMvc.perform(put("/api/v1/users/8d39bcba-cb01-4103-b562-93c84a89c972")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .andExpect(jsonPath("$.person.id").value("987efc9e-f787-4e83-bc88-bf1159230930"))
                .andExpect(jsonPath("$.person.name").value("Usuario Teste"))
                .andExpect(jsonPath("$.enumProfile").value("Enófilo"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.password").value("123456"));
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar atualizar um usuário com dados inválidos")
    void testUpdateUserWithInvalidData() throws Exception {
        when(userService.updateUser("8d39bcba-cb01-4103-b562-93c84a89c972", userInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.BAD_REQUEST));

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
    @DisplayName("Deve lançar uma exceção ao tentar deletar um usuário com id inválido")
    void testDeleteUserWithInvalidId() throws Exception {
        mockMvc.perform(delete("/api/v1/users/8d39bcba-cb01-4103-b562-93c84a89c972")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private UserInputDTO createUserInputDTO() {
        return UserInputDTO.builder()
                .person(createPersonInputDTO())
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
                .uf(createStateInputDTO())
                .complement("Complemento Teste")
                .country(createCountryInputDTO())
                .district("Bairro Teste")
                .phoneNumber("123456789")
                .zipCode("12345678")
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

    private UserOutputDTO createUserOutputDTO() {
        return UserOutputDTO.builder()
                .id(UUID.fromString("8d39bcba-cb01-4103-b562-93c84a89c972"))
                .person(createPersonOutputDTO())
                .enumProfile(EnumProfile.OENOPHILE.getCode())
                .email("email@gmail.com")
                .password("123456")
                .build();
    }

    private PersonOutputDTO createPersonOutputDTO() {
        return PersonOutputDTO.builder()
                .id(UUID.fromString("987efc9e-f787-4e83-bc88-bf1159230930"))
                .name("Usuario Teste")
                .birthDate(LocalDate.of(1990, 10, 10))
                .document("12345678900")
                .address(createAddress())
                .build();
    }

    private AddressOutputDTO createAddress() {
        return AddressOutputDTO.builder()
                .id(UUID.fromString("c775e102-04e2-4e61-9f32-d78c1713ef03"))
                .addressDescription("Rua Teste")
                .addressNumber(123)
                .city("Cidade Teste")
                .uf(createStateOutputDTO())
                .complement("Complemento Teste")
                .country(createCountryOutputDTO())
                .district("Bairro Teste")
                .phoneNumber("123456789")
                .zipCode("12345678")
                .build();
    }

    private StateOutputDTO createStateOutputDTO() {
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

}