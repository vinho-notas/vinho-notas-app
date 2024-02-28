package com.vinhonotas.bff.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.cadastro.PersonService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.AddressOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.PersonOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.StateOutputDTO;
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

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    private PersonInputDTO personInputDTO;
    private PersonOutputDTO personOutputDTO;

    @BeforeEach
    void setUp() {
        personInputDTO = createPersonInputDTO();
        personOutputDTO = createPersonOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma pessoa")
    void testCreatePerson() throws Exception {
        when(personService.createPerson(personInputDTO)).thenReturn(personOutputDTO);

        mockMvc.perform(post("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(personOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(personOutputDTO.getName()))
                .andExpect(jsonPath("$.document").value(personOutputDTO.getDocument()))
                .andExpect(jsonPath("$.birthDate").value(personOutputDTO.getBirthDate().toString()))
                .andExpect(jsonPath("$.address.id").value(personOutputDTO.getAddress().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um BadRequest ao tentar criar uma pessoa com dados inválidos")
    void testCreatePersonWithInvalidData() throws Exception {
        when(personService.createPerson(personInputDTO)).thenThrow(new BadRequestException(MessagesConstants.BAD_REQUEST));

        mockMvc.perform(post("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de pessoas")
    void testGetAllPerson() throws Exception {
        when(personService.getAllPerson()).thenReturn(List.of(personOutputDTO));

        mockMvc.perform(get("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(personOutputDTO.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(personOutputDTO.getName()))
                .andExpect(jsonPath("$[0].document").value(personOutputDTO.getDocument()))
                .andExpect(jsonPath("$[0].birthDate").value(personOutputDTO.getBirthDate().toString()))
                .andExpect(jsonPath("$[0].address.id").value(personOutputDTO.getAddress().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um BadRequest ao tentar retornar uma lista de pessoas vazia")
    void testGetAllPersonWithEmptyList() throws Exception {
        when(personService.getAllPerson()).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma pessoa pelo id")
    void testGetPersonById() throws Exception {
        when(personService.getPersonById("123e4567-e89b-12d3-a456-426614174000")).thenReturn(personOutputDTO);

        mockMvc.perform(get("/api/v1/persons/123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(personOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(personOutputDTO.getName()))
                .andExpect(jsonPath("$.document").value(personOutputDTO.getDocument()))
                .andExpect(jsonPath("$.birthDate").value(personOutputDTO.getBirthDate().toString()))
                .andExpect(jsonPath("$.address.id").value(personOutputDTO.getAddress().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um BadRequest ao tentar retornar uma pessoa pelo id inexistente")
    void testGetPersonByIdWithInvalidId() throws Exception {
        when(personService.getPersonById("123e4567-e89b-12d3-a456-426614174000"))
                .thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get("/api/v1/persons/123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma pessoa pelo nome")
    void testGetPersonByName() throws Exception {
        when(personService.getPersonByName("Nome da pessoa")).thenReturn(personOutputDTO);

        mockMvc.perform(get("/api/v1/persons/name/Nome da pessoa")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(personOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(personOutputDTO.getName()))
                .andExpect(jsonPath("$.document").value(personOutputDTO.getDocument()))
                .andExpect(jsonPath("$.birthDate").value(personOutputDTO.getBirthDate().toString()))
                .andExpect(jsonPath("$.address.id").value(personOutputDTO.getAddress().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um BadRequest ao tentar retornar uma pessoa pelo nome inexistente")
    void testGetPersonByNameWithInvalidName() throws Exception {
        when(personService.getPersonByName("Nome da pessoa")).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get("/api/v1/persons/name/Nome da pessoa")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa")
    void testUpdatePerson() throws Exception {
        when(personService.updatePerson("123e4567-e89b-12d3-a456-426614174000", personInputDTO)).thenReturn(personOutputDTO);

        mockMvc.perform(put("/api/v1/persons/123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(personOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(personOutputDTO.getName()))
                .andExpect(jsonPath("$.document").value(personOutputDTO.getDocument()))
                .andExpect(jsonPath("$.birthDate").value(personOutputDTO.getBirthDate().toString()))
                .andExpect(jsonPath("$.address.id").value(personOutputDTO.getAddress().getId().toString()));
    }

    @Test
    @DisplayName("Deve retornar um BadRequest ao tentar atualizar uma pessoa com dados inválidos")
    void testUpdatePersonWithInvalidData() throws Exception {
        when(personService.updatePerson("123e4567-e89b-12d3-a456-426614174000", personInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.BAD_REQUEST));

        mockMvc.perform(put("/api/v1/persons/123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma pessoa")
    void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/api/v1/persons/123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar um BadRequest ao tentar deletar uma pessoa com id inexistente")
    void testDeletePersonWithInvalidId() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.NOT_FOUND))
                .when(personService).deletePerson("123e4567-e89b-12d3-a456-426614174000");

        mockMvc.perform(delete("/api/v1/persons/123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    private PersonInputDTO createPersonInputDTO() {
        return PersonInputDTO.builder()
                .name("Nome da pessoa")
                .document("12345678910")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(createAddressInputDTO())
                .build();
    }

    private AddressInputDTO createAddressInputDTO() {
        return AddressInputDTO.builder()
                .addressDescription("Descrição do endereço")
                .addressNumber(123)
                .complement("Complemento da pessoa")
                .district("Bairro da pessoa")
                .city("Cidade da pessoa")
                .uf(createStateInputDTO())
                .country(createCountryInputDTO())
                .zipCode("12345678")
                .phoneNumber("12345678910")
                .build();
    }

    private CountryInputDTO createCountryInputDTO() {
        return CountryInputDTO.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private StateInputDTO createStateInputDTO() {
        return StateInputDTO.builder()
                .stateName("Nome do estado")
                .uf("UF")
                .country(createCountryInputDTO())
                .build();
    }

    private PersonOutputDTO createPersonOutputDTO() {
        return PersonOutputDTO.builder()
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .name("Nome da pessoa")
                .document("12345678910")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(createAddressOutputDTO())
                .build();
    }

    private AddressOutputDTO createAddressOutputDTO() {
        return AddressOutputDTO.builder()
                .id(UUID.fromString("987efc9e-f787-4e83-bc88-bf1159230930"))
                .addressDescription("Descrição do endereço")
                .addressNumber(123)
                .complement("Complemento da pessoa")
                .district("Bairro da pessoa")
                .city("Cidade da pessoa")
                .uf(createStateOutputDTO())
                .country(createCountryOutputDTO())
                .zipCode("12345678")
                .phoneNumber("12345678910")
                .build();
    }

    private CountryOutputDTO createCountryOutputDTO() {
        return CountryOutputDTO.builder()
                .id(UUID.fromString("b2c2d8fd-ff0f-4a98-86e6-d2369a3b1dbf"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private StateOutputDTO createStateOutputDTO() {
        return StateOutputDTO.builder()
                .id(UUID.fromString("ef15b193-a92d-4299-9695-c07e13d2f318"))
                .stateName("Nome do estado")
                .uf("UF")
                .country(createCountryOutputDTO())
                .build();
    }

}
