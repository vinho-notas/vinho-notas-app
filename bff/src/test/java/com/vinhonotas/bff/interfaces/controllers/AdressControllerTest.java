package com.vinhonotas.bff.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.cadastro.AddressService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.AddressOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.CountryOutputDTO;
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

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AddressController.class)
class AdressControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddressService addressService;

    private AddressInputDTO addressInputDTO;
    private AddressOutputDTO addressOutputDTO;

    @BeforeEach
    void setUp() {
        addressInputDTO = createAddressInputDTO();
        addressOutputDTO = createAddressOutputDTO();
    }

    @Test
    @DisplayName("Deve cadastrar um endereço")
    void testCreateAddress() throws Exception {
        when(addressService.createAddress(addressInputDTO)).thenReturn(addressOutputDTO);

        mockMvc.perform(post("/api/v1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.addressDescription").value("Rua 1"))
                .andExpect(jsonPath("$.addressNumber").value("1"))
                .andExpect(jsonPath("$.complement").value("Casa 1"))
                .andExpect(jsonPath("$.district").value("Bairro 1"))
                .andExpect(jsonPath("$.zipCode").value("00000-000"))
                .andExpect(jsonPath("$.city").value("Cidade 1"))
                .andExpect(jsonPath("$.uf.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .andExpect(jsonPath("$.phoneNumber").value("00000000000"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequest ao tentar cadastrar um endereço inválido")
    void testCreateAddressInvalid() throws Exception {
        when(addressService.createAddress(addressInputDTO)).thenThrow(new BadRequestException(MessagesConstants.BAD_REQUEST));

        mockMvc.perform(post("/api/v1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de endereços")
    void testGetAllAddress() throws Exception {
        when(addressService.getAllAddress()).thenReturn(List.of(addressOutputDTO));

        mockMvc.perform((get("/api/v1/address")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$[0].addressDescription").value("Rua 1"))
                .andExpect(jsonPath("$[0].addressNumber").value("1"))
                .andExpect(jsonPath("$[0].complement").value("Casa 1"))
                .andExpect(jsonPath("$[0].district").value("Bairro 1"))
                .andExpect(jsonPath("$[0].zipCode").value("00000-000"))
                .andExpect(jsonPath("$[0].city").value("Cidade 1"))
                .andExpect(jsonPath("$[0].uf.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .andExpect(jsonPath("$[0].phoneNumber").value("00000000000"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar retornar uma lista de endereços vazia")
    void testGetAllAddressBadRequestException() throws Exception {
        when(addressService.getAllAddress()).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform((get("/api/v1/address")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um endereço pelo seu id")
    void testGetAddressById() throws Exception {
        when(addressService.getAddressById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")).thenReturn(addressOutputDTO);

        mockMvc.perform((get("/api/v1/address/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.addressDescription").value("Rua 1"))
                .andExpect(jsonPath("$.addressNumber").value("1"))
                .andExpect(jsonPath("$.complement").value("Casa 1"))
                .andExpect(jsonPath("$.district").value("Bairro 1"))
                .andExpect(jsonPath("$.zipCode").value("00000-000"))
                .andExpect(jsonPath("$.city").value("Cidade 1"))
                .andExpect(jsonPath("$.uf.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .andExpect(jsonPath("$.phoneNumber").value("00000000000"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar retornar um endereço pelo seu id")
    void testGetAddressByIdBadRequestException() throws Exception {
        when(addressService.getAddressById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform((get("/api/v1/address/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar um endereço")
    void testUpdateAddress() throws Exception {
        when(addressService.updateAddress("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", addressInputDTO)).thenReturn(addressOutputDTO);

        mockMvc.perform(put("/api/v1/address/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.addressDescription").value("Rua 1"))
                .andExpect(jsonPath("$.addressNumber").value("1"))
                .andExpect(jsonPath("$.complement").value("Casa 1"))
                .andExpect(jsonPath("$.district").value("Bairro 1"))
                .andExpect(jsonPath("$.zipCode").value("00000-000"))
                .andExpect(jsonPath("$.city").value("Cidade 1"))
                .andExpect(jsonPath("$.uf.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .andExpect(jsonPath("$.phoneNumber").value("00000000000"));
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar atualizar um endereço inválido")
    void testUpdateAddressBadRequestException() throws Exception {
        when(addressService.updateAddress("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", addressInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.BAD_REQUEST));

        mockMvc.perform(put("/api/v1/address/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar um endereço")
    void testDeleteAddress() throws Exception {
        mockMvc.perform(delete("/api/v1/address/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar um BadRequestException ao tentar deletar um endereço inválido")
    void testDeleteAddressBadRequestException() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.BAD_REQUEST)).when(addressService)
                .deleteAddress("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
        
        mockMvc.perform(delete("/api/v1/address/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private AddressInputDTO createAddressInputDTO() {
        return AddressInputDTO.builder()
                .addressDescription("Rua 1")
                .addressNumber(1)
                .complement("Casa 1")
                .district("Bairro 1")
                .zipCode("00000-000")
                .city("Cidade 1")
                .uf(createStateInputDTO())
                .country(createCountryInputDTO())
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

    private AddressOutputDTO createAddressOutputDTO() {
        return AddressOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
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

    private StateOutputDTO createStateOutputDTO() {
        return StateOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .stateName("Santa Catarina")
                .uf("SC")
                .country(createCountryOutputDTO())
                .build();
    }

    private CountryOutputDTO createCountryOutputDTO() {
        return CountryOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

}
