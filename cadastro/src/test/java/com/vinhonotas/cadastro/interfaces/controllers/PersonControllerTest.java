package com.vinhonotas.cadastro.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.cadastro.application.converters.PersonConverter;
import com.vinhonotas.cadastro.application.services.PersonService;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.PersonOutputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
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
@Log4j2
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;
    @MockBean
    private PersonConverter personConverter;

    private PersonInputDTO personInputDTO;
    private PersonOutputDTO personOutputDTO;
    private AddressEntity addressEntity;
    private StateEntity stateEntity;
    private CountryEntity countryEntity;
    private PersonEntity personEntity;

    @BeforeEach
    void setUp() {
        log.info("Iniciando teste");

        countryEntity = createCountryEntity();
        stateEntity = createStateEntity();
        addressEntity = createAddressEntity();
        personInputDTO = createPersonInputDTO();
        personOutputDTO = createPersonOutputDTO();
        personEntity = createPersonEntity();

    }


    @Test
    @DisplayName("Deve criar uma pessoa")
    void testCreatePerson() throws Exception {
        when(personService.create(personInputDTO)).thenReturn(personEntity);
        when(personConverter.convertToOutputDTO(personService.create(personInputDTO))).thenReturn(personOutputDTO);

        mockMvc.perform(post("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$.name").value("Nome da pessoa"))
                .andExpect(jsonPath("$.document").value("12345678910"))
                .andExpect(jsonPath("$.birthDate").value("1990-01-01"))
                .andExpect(jsonPath("$.address.id").value("987efc9e-f787-4e83-bc88-bf1159230930"));
    }

    @Test
    @DisplayName("Deve retornar erro ao criar uma pessoa")
    void testCreatePersonError() throws Exception {
        when(personService.create(personInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_PERSON));

        mockMvc.perform(post("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de pessoas")
    void testGetAllPerson() throws Exception {
        when(personService.getAll()).thenReturn(List.of(personEntity));
        when(personConverter.convertToOutputDTOList(personService.getAll())).thenReturn(List.of(personOutputDTO));

        mockMvc.perform(get("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$[0].name").value("Nome da pessoa"))
                .andExpect(jsonPath("$[0].document").value("12345678910"))
                .andExpect(jsonPath("$[0].birthDate").value("1990-01-01"))
                .andExpect(jsonPath("$[0].address.id").value("987efc9e-f787-4e83-bc88-bf1159230930"));
    }

    @Test
    @DisplayName("Deve retornar uma pessoa pelo id")
    void testGetPersonById() throws Exception {
        when(personService.getById(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))).thenReturn(personEntity);
        when(personConverter.convertToOutputDTO(personService.getById(UUID.fromString("123e4567-e89b-12d3-a456-426614174000")))).thenReturn(personOutputDTO);

        mockMvc.perform(get("/api/v1/persons/{id}", "123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$.name").value("Nome da pessoa"))
                .andExpect(jsonPath("$.document").value("12345678910"))
                .andExpect(jsonPath("$.birthDate").value("1990-01-01"))
                .andExpect(jsonPath("$.address.id").value("987efc9e-f787-4e83-bc88-bf1159230930"));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar uma pessoa pelo id")
    void testGetPersonByIdError() throws Exception {
        when(personService.getById(UUID.fromString("123e4567-e89b-12d3-a456-426614174000")))
                .thenThrow(new BadRequestException(MessagesConstants.PERSON_NOT_FOUND));

        mockMvc.perform(get("/api/v1/persons/{id}", "123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma pessoa pelo nome")
    void testGetPersonByName() throws Exception {
        when(personService.getByName("Nome da pessoa")).thenReturn(personEntity);
        when(personConverter.convertToOutputDTO(personService.getByName("Nome da pessoa"))).thenReturn(personOutputDTO);

        mockMvc.perform(get("/api/v1/persons/name/{name}", "Nome da pessoa")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$.name").value("Nome da pessoa"))
                .andExpect(jsonPath("$.document").value("12345678910"))
                .andExpect(jsonPath("$.birthDate").value("1990-01-01"))
                .andExpect(jsonPath("$.address.id").value("987efc9e-f787-4e83-bc88-bf1159230930"));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar uma pessoa pelo nome")
    void testGetPersonByNameError() throws Exception {
        when(personService.getByName("Nome da pessoa"))
                .thenThrow(new BadRequestException(MessagesConstants.PERSON_NOT_FOUND_WITH_NAME + "Nome da pessoa"));

        mockMvc.perform(get("/api/v1/persons/name/{name}", "Nome da pessoa")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa")
    void testUpdatePerson() throws Exception {
        personOutputDTO.setName("Nome da pessoa atualizado");
        when(personService.update(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), personInputDTO)).thenReturn(personEntity);
        when(personConverter.convertToOutputDTOUpdate(personService.update(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), personInputDTO),
                UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), personConverter.convertToOutputDTO(personService.update(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), personInputDTO))))
                .thenReturn(personOutputDTO);

        mockMvc.perform(put("/api/v1/persons/{id}", "123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$.name").value("Nome da pessoa atualizado"))
                .andExpect(jsonPath("$.document").value("12345678910"))
                .andExpect(jsonPath("$.birthDate").value("1990-01-01"))
                .andExpect(jsonPath("$.address.id").value("987efc9e-f787-4e83-bc88-bf1159230930"));
    }

    @Test
    @DisplayName("Deve retornar erro ao atualizar uma pessoa")
    void testUpdatePersonError() throws Exception {
        when(personService.update(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), personInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_UPDATE_PERSON_DATA));

        mockMvc.perform(put("/api/v1/persons/{id}", "123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma pessoa")
    void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/api/v1/persons/{id}", "123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar erro ao deletar uma pessoa")
    void testDeletePersonError() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_DELETE_PERSON_DATA))
                .when(personService).delete(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));

        mockMvc.perform(delete("/api/v1/persons/{id}", "123e4567-e89b-12d3-a456-426614174000")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .name("Nome da pessoa")
                .document("12345678910")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(addressEntity)
                .build();
    }

    private PersonOutputDTO createPersonOutputDTO() {
        return PersonOutputDTO.builder()
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .name("Nome da pessoa")
                .document("12345678910")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(addressEntity)
                .build();
    }

    private CountryEntity createCountryEntity() {
        return CountryEntity.builder()
                .id(UUID.fromString("b2c2d8fd-ff0f-4a98-86e6-d2369a3b1dbf"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private StateEntity createStateEntity() {
        return StateEntity.builder()
                .id(UUID.fromString("ef15b193-a92d-4299-9695-c07e13d2f318"))
                .stateName("Nome do estado")
                .uf("UF")
                .country(countryEntity)
                .build();
    }

    private AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .id(UUID.fromString("987efc9e-f787-4e83-bc88-bf1159230930"))
                .addressDescription("Descrição do endereço")
                .addressNumber(123)
                .complement("Complemento da pessoa")
                .district("Bairro da pessoa")
                .city("Cidade da pessoa")
                .uf(stateEntity)
                .country(countryEntity)
                .zipCode("12345678")
                .phoneNumber("12345678910")
                .build();
    }

    private PersonInputDTO createPersonInputDTO() {
        return PersonInputDTO.builder()
                .name("Nome da pessoa")
                .document("12345678910")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(addressEntity)
                .build();
    }

    @AfterEach
    void tearDown() {
        log.info("Finalizando teste");
    }

}