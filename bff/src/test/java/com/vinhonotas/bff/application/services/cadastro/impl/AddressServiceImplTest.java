package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.cadastro.AddressClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.EditAddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.AddressOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.CountryOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.StateOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;
    @Mock
    private AddressClient addressClient;

    private AddressOutputDTO addressOutputDTO;
    private AddressInputDTO addressInputDTO;
    private EditAddressInputDTO editAddressInputDTO;

    @BeforeEach
    void setUp() {
        addressOutputDTO = createAddressOutputDTO();
        addressInputDTO = createAddressInputDTO();
        editAddressInputDTO = createEditAddressInputDTO();
    }

    @Test
    @DisplayName("Deve criar um endereço")
    void testCreateAddress() {
        when(addressClient.createAddress(addressInputDTO)).thenReturn(addressOutputDTO);
        AddressOutputDTO response = assertDoesNotThrow(() -> addressService.createAddress(addressInputDTO));

        assertNotNull(response);
        assertEquals(addressOutputDTO.getId(), response.getId());
        assertEquals(addressOutputDTO.getAddressDescription(), response.getAddressDescription());
        assertEquals(addressOutputDTO.getAddressNumber(), response.getAddressNumber());
        assertEquals(addressOutputDTO.getComplement(), response.getComplement());
        assertEquals(addressOutputDTO.getDistrict(), response.getDistrict());
        assertEquals(addressOutputDTO.getZipCode(), response.getZipCode());
        assertEquals(addressOutputDTO.getCity(), response.getCity());
        assertEquals(addressOutputDTO.getUf(), response.getUf());
        assertEquals(addressOutputDTO.getCountry(), response.getCountry());
        assertEquals(addressOutputDTO.getPhoneNumber(), response.getPhoneNumber());
        verify(addressClient).createAddress(addressInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar um endereço")
    void testCreateAddressThrowBadRequestException() {
        when(addressClient.createAddress(addressInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        Exception exception = assertThrows(Exception.class, () -> addressService.createAddress(addressInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(addressClient).createAddress(addressInputDTO);
    }

    @Test
    @DisplayName("Deve retornar uma lista de endereços")
    void testGetAllAddress() {
        List<AddressOutputDTO> address = new ArrayList<>();
        address.add(addressOutputDTO);
        when(addressClient.getAllAddress()).thenReturn(address);

        List<AddressOutputDTO> list = assertDoesNotThrow(() -> addressService.getAllAddress());
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(addressOutputDTO.getId(), list.get(0).getId());
        assertEquals(addressOutputDTO.getAddressDescription(), list.get(0).getAddressDescription());
        assertEquals(addressOutputDTO.getAddressNumber(), list.get(0).getAddressNumber());
        assertEquals(addressOutputDTO.getComplement(), list.get(0).getComplement());
        assertEquals(addressOutputDTO.getDistrict(), list.get(0).getDistrict());
        assertEquals(addressOutputDTO.getZipCode(), list.get(0).getZipCode());
        assertEquals(addressOutputDTO.getCity(), list.get(0).getCity());
        assertEquals(addressOutputDTO.getUf(), list.get(0).getUf());
        assertEquals(addressOutputDTO.getCountry(), list.get(0).getCountry());
        assertEquals(addressOutputDTO.getPhoneNumber(), list.get(0).getPhoneNumber());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma lista de endereços")
    void testGetAllAddressThrowBadRequestException() {
        when(addressClient.getAllAddress()).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(Exception.class, () -> addressService.getAllAddress());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar um endereço por id")
    void testGetAddressById() {
        when(addressClient.getAddressById("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a")).thenReturn(addressOutputDTO);
        AddressOutputDTO response = assertDoesNotThrow(() -> addressService.getAddressById("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a"));

        assertNotNull(response);
        assertEquals(UUID.fromString("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a"), response.getId());
        assertEquals(addressOutputDTO.getAddressDescription(), response.getAddressDescription());
        assertEquals(addressOutputDTO.getAddressNumber(), response.getAddressNumber());
        assertEquals(addressOutputDTO.getComplement(), response.getComplement());
        assertEquals(addressOutputDTO.getDistrict(), response.getDistrict());
        assertEquals(addressOutputDTO.getZipCode(), response.getZipCode());
        assertEquals(addressOutputDTO.getCity(), response.getCity());
        assertEquals(addressOutputDTO.getUf(), response.getUf());
        assertEquals(addressOutputDTO.getCountry(), response.getCountry());
        assertEquals(addressOutputDTO.getPhoneNumber(), response.getPhoneNumber());
        verify(addressClient).getAddressById("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar um endereço por id")
    void testGetAddressByIdThrowBadRequestException() {
        when(addressClient.getAddressById("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a")).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> addressService.getAddressById("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a"));
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Deve atualizar um endereço")
    void testUpdateAddress() {
        addressOutputDTO.setAddressNumber(160);
        when(addressClient.updateAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a", editAddressInputDTO)).thenReturn(addressOutputDTO);
        AddressOutputDTO response = assertDoesNotThrow(() -> addressService.updateAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a", editAddressInputDTO));

        assertNotNull(response);
        assertEquals(UUID.fromString("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a"), response.getId());
        assertEquals(160, response.getAddressNumber());
        verify(addressClient).updateAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a", editAddressInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar um endereço")
    void testUpdateAddressThrowBadRequestException() {
        when(addressClient.updateAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a", editAddressInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        Exception exception = assertThrows(Exception.class, () -> addressService.updateAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a", editAddressInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(addressClient).updateAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a", editAddressInputDTO);
    }

    @Test
    @DisplayName("Deve deletar um endereço")
    void testDeleteAddress() {
        assertDoesNotThrow(() -> addressService.deleteAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a"));
        verify(addressClient).deleteAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar um endereço")
    void testDeleteAddressThrowBadRequestException() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
                .when(addressClient).deleteAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a");

        Exception exception = assertThrows(Exception.class, () -> addressService.deleteAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a"));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(addressClient).deleteAddress("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a");
    }

    private AddressOutputDTO createAddressOutputDTO() {
        return AddressOutputDTO.builder()
                .id(UUID.fromString("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a"))
                .addressDescription("Rua addressDescription")
                .addressNumber(159)
                .complement("Ap201")
                .district("district")
                .zipCode("00000-000")
                .city("Blumenau")
                .uf(Mockito.mock(StateOutputDTO.class))
                .country(Mockito.mock(CountryOutputDTO.class))
                .phoneNumber("0000000000")
                .build();
    }

    private EditAddressInputDTO createEditAddressInputDTO() {
        return EditAddressInputDTO.builder()
                .addressDescription("Rua addressDescription")
                .addressNumber(159)
                .complement("Ap 201")
                .district("district")
                .zipCode("00000-000")
                .city("Blumenau")
                .state("SC")
                .country("Brasil")
                .phoneNumber("0000000000")
                .build();
    }

    private AddressInputDTO createAddressInputDTO() {
        return AddressInputDTO.builder()
                .addressDescription("Rua addressDescription")
                .addressNumber(159)
                .complement("Ap 201")
                .district("district")
                .zipCode("00000-000")
                .city("Blumenau")
                .uf("SC")
                .country("Brasil")
                .phoneNumber("0000000000")
                .build();
    }

}