package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.AddressOutputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.StateOutputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressConverterTest {

    @InjectMocks
    private AddressConverter addressConverter;

    @Mock
    private StateConverter stateConverter;
    @Mock
    private CountryConverter countryConverter;

    private AddressInputDTO addressInputDTO;
    private AddressEntity addressEntity;
    private AddressOutputDTO addressOutputDTO;

    @BeforeEach
    void setUp() {
        addressInputDTO = createAddressInputDTO();
        addressEntity = createAddressEntity();
        addressOutputDTO = createAddressOutputDTO();
    }

    @Test
    @DisplayName("Teste de conversão de AddressInputDTO para AddressEntity")
    void testToEntity() {
        AddressEntity addressEntity = assertDoesNotThrow(()-> addressConverter.toEntity(addressInputDTO));
        assertNotNull(addressEntity);
        assertEquals(addressInputDTO.getAddressDescription(), addressEntity.getAddressDescription());
        assertEquals(addressInputDTO.getAddressNumber(), addressEntity.getAddressNumber());
        assertEquals(addressInputDTO.getComplement(), addressEntity.getComplement());
        assertEquals(addressInputDTO.getDistrict(), addressEntity.getDistrict());
        assertEquals(addressInputDTO.getZipCode(), addressEntity.getZipCode());
        assertEquals(addressInputDTO.getCity(), addressEntity.getCity());
        assertEquals(stateConverter.convertToEntity(addressInputDTO.getUf()), addressEntity.getUf());
        assertEquals(countryConverter.convertToEntity(addressInputDTO.getCountry()), addressEntity.getCountry());
        assertEquals(addressInputDTO.getPhoneNumber(), addressEntity.getPhoneNumber());
    }

    @Test
    @DisplayName("Teste de conversão para AddressEntityUpdate ")
    void testToEntityUpdate() {
        addressInputDTO.setAddressNumber(200);

        AddressEntity addressEntityUpdate = assertDoesNotThrow(()-> addressConverter.toEntityUpdate(addressEntity, addressEntity.getId(), addressInputDTO));
        assertNotNull(addressEntityUpdate);
        assertEquals(addressInputDTO.getAddressDescription(), addressEntityUpdate.getAddressDescription());
        assertEquals(200, addressEntityUpdate.getAddressNumber());
        assertEquals(addressInputDTO.getComplement(), addressEntityUpdate.getComplement());
        assertEquals(addressInputDTO.getDistrict(), addressEntityUpdate.getDistrict());
        assertEquals(addressInputDTO.getZipCode(), addressEntityUpdate.getZipCode());
        assertEquals(addressInputDTO.getCity(), addressEntityUpdate.getCity());
        assertEquals(stateConverter.convertToEntity(addressInputDTO.getUf()), addressEntityUpdate.getUf());
        assertEquals(countryConverter.convertToEntity(addressInputDTO.getCountry()), addressEntityUpdate.getCountry());
        assertEquals(addressInputDTO.getPhoneNumber(), addressEntityUpdate.getPhoneNumber());
    }

    @Test
    @DisplayName("Teste de conversão de AddressEntity para AddressOutputDTO")
    void testConvertToOutputDTO() {
        when(stateConverter.convertToOutputDTO(Mockito.any(StateEntity.class))).thenReturn(Mockito.mock(StateOutputDTO.class));
        when(countryConverter.convertToOutputDTO(Mockito.any(CountryEntity.class))).thenReturn(Mockito.mock(CountryOutputDTO.class));

        AddressOutputDTO addressOutput = assertDoesNotThrow(()-> addressConverter.convertToOutputDTO(addressEntity));
        assertNotNull(addressOutput);
        assertEquals(addressEntity.getId(), addressOutput.getId());
        assertEquals(addressEntity.getAddressDescription(), addressOutput.getAddressDescription());
        assertEquals(addressEntity.getAddressNumber(), addressOutput.getAddressNumber());
        assertEquals(addressEntity.getComplement(), addressOutput.getComplement());
        assertEquals(addressEntity.getDistrict(), addressOutput.getDistrict());
        assertEquals(addressEntity.getZipCode(), addressOutput.getZipCode());
        assertEquals(addressEntity.getCity(), addressOutput.getCity());
        assertEquals(addressEntity.getPhoneNumber(), addressOutput.getPhoneNumber());
    }

    @Test
    @DisplayName("Teste de conversão de List<AddressEntity> para List<AddressOutputDTO>")
    void testConvertToOutputDTOList() {
        when(stateConverter.convertToOutputDTO(Mockito.any(StateEntity.class))).thenReturn(Mockito.mock(StateOutputDTO.class));
        when(countryConverter.convertToOutputDTO(Mockito.any(CountryEntity.class))).thenReturn(Mockito.mock(CountryOutputDTO.class));

        List<AddressOutputDTO> list = assertDoesNotThrow(()-> addressConverter.convertToOutputDTOList(List.of(addressEntity)));
        assertNotNull(list);
        assertEquals(1, list.size());
        AddressOutputDTO addressOutput = list.get(0);
        assertEquals(addressEntity.getId(), addressOutput.getId());
        assertEquals(addressEntity.getAddressDescription(), addressOutput.getAddressDescription());
        assertEquals(addressEntity.getAddressNumber(), addressOutput.getAddressNumber());
        assertEquals(addressEntity.getComplement(), addressOutput.getComplement());
        assertEquals(addressEntity.getDistrict(), addressOutput.getDistrict());
        assertEquals(addressEntity.getZipCode(), addressOutput.getZipCode());
        assertEquals(addressEntity.getCity(), addressOutput.getCity());
        assertEquals(addressEntity.getPhoneNumber(), addressOutput.getPhoneNumber());
    }

    @Test
    @DisplayName("Teste de conversão de AddressOutputDTO para AddressOutputDTOUpdate")
    void testConvertToOutputDTOUpdate() {
        addressOutputDTO.setAddressNumber(200);

        AddressOutputDTO addressOutput = assertDoesNotThrow(()-> addressConverter.convertToOutputDTOUpdate(addressEntity, addressEntity.getId(), addressOutputDTO));
        assertNotNull(addressOutput);
        assertEquals(addressEntity.getId(), addressOutput.getId());
        assertEquals(addressOutputDTO.getAddressDescription(), addressOutput.getAddressDescription());
        assertEquals(200, addressOutput.getAddressNumber());
        assertEquals(addressOutputDTO.getComplement(), addressOutput.getComplement());
        assertEquals(addressOutputDTO.getDistrict(), addressOutput.getDistrict());
        assertEquals(addressOutputDTO.getZipCode(), addressOutput.getZipCode());
        assertEquals(addressOutputDTO.getCity(), addressOutput.getCity());
        assertEquals(addressOutputDTO.getUf(), addressOutput.getUf());
        assertEquals(addressOutputDTO.getCountry(), addressOutput.getCountry());
        assertEquals(addressOutputDTO.getPhoneNumber(), addressOutput.getPhoneNumber());
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

    private AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .id(UUID.fromString("f5d2e9e0-0b7e-4b1e-9b0a-0e9f5b9b6b1a"))
                .addressDescription("Rua addressDescription")
                .addressNumber(159)
                .complement("Ap 201")
                .district("district")
                .zipCode("00000-000")
                .city("Blumenau")
                .uf(Mockito.mock(StateEntity.class))
                .country(Mockito.mock(CountryEntity.class))
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
                .uf(createStateInputDTO())
                .country(createCountryInputDTO())
                .phoneNumber("0000000000")
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
                .continentName("America do Sul")
                .build();
    }

}