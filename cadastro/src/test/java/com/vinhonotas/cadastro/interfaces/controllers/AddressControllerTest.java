package com.vinhonotas.cadastro.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.cadastro.application.converters.AddressConverter;
import com.vinhonotas.cadastro.application.services.AddressService;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.AddressOutputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.StateOutputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddressService addressService;
    @MockBean
    private AddressConverter addressConverter;

    private AddressEntity addressEntity;
    private AddressInputDTO addressInputDTO;
    private AddressOutputDTO addressOutputDTO;
    private StateEntity state;
    private CountryEntity country;

    @BeforeEach
    void setUp() {
        country = createCountryEntity();
        state = createStateEntity();
        addressEntity = createAddressEntity();
        addressInputDTO = createAddressInputDTO();
        addressOutputDTO = createAddressOutputDTO();
    }

    @Test
    @DisplayName("Deve criar um endereço")
    void testCreateAddress() throws Exception {
        when(addressService.create(addressInputDTO)).thenReturn(addressEntity);
        when(addressConverter.convertToOutputDTO(addressEntity)).thenReturn(addressOutputDTO);

        mockMvc.perform(post("/api/v1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(addressOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.addressDescription").value(addressOutputDTO.getAddressDescription()))
                .andExpect(jsonPath("$.addressNumber").value(addressOutputDTO.getAddressNumber()))
                .andExpect(jsonPath("$.complement").value(addressOutputDTO.getComplement()))
                .andExpect(jsonPath("$.district").value(addressOutputDTO.getDistrict()))
                .andExpect(jsonPath("$.zipCode").value(addressOutputDTO.getZipCode()))
                .andExpect(jsonPath("$.city").value(addressOutputDTO.getCity()))
                .andExpect(jsonPath("$.uf.id").value(addressOutputDTO.getUf().getId().toString()))
                .andExpect(jsonPath("$.country.id").value(addressOutputDTO.getCountry().getId().toString()))
                .andExpect(jsonPath("$.phoneNumber").value(addressOutputDTO.getPhoneNumber()));
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando cadastrar o endereço")
    void testCreateAddressException() throws Exception {
        when(addressService.create(addressInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_ADDRESS));

        mockMvc.perform(post("/api/v1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de endereços")
    void testGetAllAddress() throws Exception {
        when(addressService.getAll()).thenReturn(List.of(addressEntity));
        when(addressConverter.convertToOutputDTOList(List.of(addressEntity))).thenReturn(List.of(addressOutputDTO));

        mockMvc.perform(get("/api/v1/address")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(addressOutputDTO.getId().toString()))
                .andExpect(jsonPath("$[0].addressDescription").value(addressOutputDTO.getAddressDescription()))
                .andExpect(jsonPath("$[0].addressNumber").value(addressOutputDTO.getAddressNumber()))
                .andExpect(jsonPath("$[0].complement").value(addressOutputDTO.getComplement()))
                .andExpect(jsonPath("$[0].district").value(addressOutputDTO.getDistrict()))
                .andExpect(jsonPath("$[0].zipCode").value(addressOutputDTO.getZipCode()))
                .andExpect(jsonPath("$[0].city").value(addressOutputDTO.getCity()))
                .andExpect(jsonPath("$[0].uf.id").value(addressOutputDTO.getUf().getId().toString()))
                .andExpect(jsonPath("$[0].country.id").value(addressOutputDTO.getCountry().getId().toString()))
                .andExpect(jsonPath("$[0].phoneNumber").value(addressOutputDTO.getPhoneNumber()));
    }

    @Test
    @DisplayName("Deve retornar um endereço quando informado o id")
    void testGetAddressById() throws Exception {
        when(addressService.getById(addressEntity.getId())).thenReturn(addressEntity);
        when(addressConverter.convertToOutputDTO(addressEntity)).thenReturn(addressOutputDTO);

        mockMvc.perform(get("/api/v1/address/{id}", addressEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(addressOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.addressDescription").value(addressOutputDTO.getAddressDescription()))
                .andExpect(jsonPath("$.addressNumber").value(addressOutputDTO.getAddressNumber()))
                .andExpect(jsonPath("$.complement").value(addressOutputDTO.getComplement()))
                .andExpect(jsonPath("$.district").value(addressOutputDTO.getDistrict()))
                .andExpect(jsonPath("$.zipCode").value(addressOutputDTO.getZipCode()))
                .andExpect(jsonPath("$.city").value(addressOutputDTO.getCity()))
                .andExpect(jsonPath("$.uf.id").value(addressOutputDTO.getUf().getId().toString()))
                .andExpect(jsonPath("$.country.id").value(addressOutputDTO.getCountry().getId().toString()))
                .andExpect(jsonPath("$.phoneNumber").value(addressOutputDTO.getPhoneNumber()));
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando não encontrar o endereço pelo id")
    void testGetAddressByIdException() throws Exception {
        when(addressService.getById(addressEntity.getId())).thenThrow(new BadRequestException(MessagesConstants.ADDRESS_NOT_FOUND));

        mockMvc.perform(get("/api/v1/address/{id}", addressEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar um endereço")
    void testUpdateAddress() throws Exception {
        addressOutputDTO.setAddressDescription("Rua 2");

        when(addressService.update(addressEntity.getId(), addressInputDTO)).thenReturn(addressEntity);
        when(addressConverter.convertToOutputDTO(addressEntity)).thenReturn(addressOutputDTO);
        when(addressConverter.convertToOutputDTOUpdate(addressEntity, addressEntity.getId(), addressOutputDTO)).thenReturn(addressOutputDTO);

        mockMvc.perform(put("/api/v1/address/{id}", addressEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(addressOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.addressDescription").value("Rua 2"))
                .andExpect(jsonPath("$.addressNumber").value(addressOutputDTO.getAddressNumber()))
                .andExpect(jsonPath("$.complement").value(addressOutputDTO.getComplement()))
                .andExpect(jsonPath("$.district").value(addressOutputDTO.getDistrict()))
                .andExpect(jsonPath("$.zipCode").value(addressOutputDTO.getZipCode()))
                .andExpect(jsonPath("$.city").value(addressOutputDTO.getCity()))
                .andExpect(jsonPath("$.uf.id").value(addressOutputDTO.getUf().getId().toString()))
                .andExpect(jsonPath("$.country.id").value(addressOutputDTO.getCountry().getId().toString()))
                .andExpect(jsonPath("$.phoneNumber").value(addressOutputDTO.getPhoneNumber()));
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando não encontrar o endereço pelo id")
    void testUpdateAddressException() throws Exception {
        when(addressService.update(addressEntity.getId(), addressInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_UPDATE_ADDRESS_DATA));

        mockMvc.perform(put("/api/v1/address/{id}", addressEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar um endereço")
    void testDeleteAddress() throws Exception {
        mockMvc.perform(delete("/api/v1/address/{id}", addressEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando não encontrar o endereço pelo id")
    void testDeleteAddressException() throws Exception {
        doThrow(BadRequestException.class).when(addressService).delete(addressEntity.getId());

        mockMvc.perform(delete("/api/v1/address/{id}", addressEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private AddressOutputDTO createAddressOutputDTO() {
        return AddressOutputDTO.builder()
                .id(UUID.fromString("e50ae4ba-b799-4506-9efb-345a3f6556fa"))
                .addressDescription("Rua 1")
                .addressNumber(1)
                .complement("Casa 1")
                .district("Bairro 1")
                .zipCode("00000-000")
                .city("Cidade 1")
                .uf(createStateOutputDTO())
                .country(createCountryOutputDTO())
                .phoneNumber("00000000000")
                .build();
    }

    private CountryOutputDTO createCountryOutputDTO() {
        return CountryOutputDTO.builder()
                .id(UUID.fromString("e2adc688-5e7f-4edf-ae5e-7b6dcbb65e99"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private StateOutputDTO createStateOutputDTO() {
        return StateOutputDTO.builder()
                .id(UUID.fromString("1557c128-235d-4dff-800c-4b4b1a0693f3"))
                .stateName("Santa Catarina")
                .uf("SC")
                .country(createCountryOutputDTO())
                .build();
    }

    private AddressInputDTO createAddressInputDTO() {
        return AddressInputDTO.builder()
                .addressDescription("Rua 1")
                .addressNumber(1)
                .complement("Casa 1")
                .district("Bairro 1")
                .zipCode("00000-000")
                .city("Cidade 1")
                .uf("SC")
                .country("Brasil")
                .phoneNumber("00000000000")
                .build();
    }

    private StateInputDTO createStateInputDTO() {
        return StateInputDTO.builder()
                .stateName("Santa Catarina")
                .uf("SC")
                .country(createCountryInputDTO())
                .build();
    }

    private CountryInputDTO createCountryInputDTO() {
        return CountryInputDTO.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private CountryEntity createCountryEntity() {
        return CountryEntity.builder()
                .id(UUID.fromString("e2adc688-5e7f-4edf-ae5e-7b6dcbb65e99"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private StateEntity createStateEntity() {
        return StateEntity.builder()
                .id(UUID.fromString("1557c128-235d-4dff-800c-4b4b1a0693f3"))
                .stateName("Santa Catarina")
                .uf("SC")
                .country(country)
                .build();
    }

    private AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .id(UUID.fromString("e50ae4ba-b799-4506-9efb-345a3f6556fa"))
                .addressDescription("Rua 1")
                .addressNumber(1)
                .complement("Casa 1")
                .district("Bairro 1")
                .zipCode("00000-000")
                .city("Cidade 1")
                .uf(state)
                .country(country)
                .phoneNumber("00000000000")
                .build();
    }

}