package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.AddressOutputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.CountryOutputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.PersonOutputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.StateOutputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonConverterTest {

    @InjectMocks
    private PersonConverter personConverter;

    @Mock
    private AddressConverter addressConverter;

    private PersonInputDTO personInputDTO;
    private PersonEntity personEntity;
    private PersonOutputDTO personOutputDTO;

    @BeforeEach
    void setUp() {
        personInputDTO = createPersonInputDTO();
        personEntity = createPersonEntity();
        personOutputDTO = createPersonOutputDTO();
    }

    @Test
    @DisplayName("Teste de conversão de PersonInputDTO para PersonEntity")
    void testToEntity() {
        PersonEntity personEntity = assertDoesNotThrow(() -> personConverter.convertToEntity(personInputDTO));
        assertNotNull(personEntity);
        assertEquals(personInputDTO.getName(), personEntity.getName());
        assertEquals(personInputDTO.getDocument(), personEntity.getDocument());
        assertEquals(personInputDTO.getBirthDate(), personEntity.getBirthDate());
        assertEquals(addressConverter.convertToEntity(personInputDTO.getAddress()), personEntity.getAddress());
    }

    @Test
    @DisplayName("Teste de conversão para PersonEntityUpdate ")
    void testToEntityUpdate() {
        personInputDTO.setName("Mauricio");

        PersonEntity entity = assertDoesNotThrow(() -> personConverter.convertToEntityUpdate(personEntity, personEntity.getId(), personInputDTO));
        assertNotNull(personEntity);
        assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), entity.getId());
        assertEquals("Mauricio", entity.getName());
        assertEquals("123456789", entity.getDocument());
        assertEquals(LocalDate.of(1995, 10, 10), entity.getBirthDate());
        assertEquals(addressConverter.convertToEntity(personInputDTO.getAddress()), entity.getAddress());
    }

    @Test
    @DisplayName("Teste de conversão de PersonEntity para PersonOutputDTO")
    void testConvertToOutputDTO() {
        PersonOutputDTO personOutput = assertDoesNotThrow(() -> personConverter.convertToOutputDTO(personEntity));
        assertNotNull(personOutput);
        assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), personOutput.getId());
        assertEquals("Vinicius", personOutput.getName());
        assertEquals("123456789", personOutput.getDocument());
        assertEquals(LocalDate.of(1995, 10, 10), personOutput.getBirthDate());
        assertEquals(addressConverter.convertToOutputDTO(personEntity.getAddress()), personOutput.getAddress());
    }

    @Test
    @DisplayName("Teste de conversão de List<PersonEntity> para List<PersonOutputDTO>")
    void testConvertToOutputDTOList() {
        List<PersonOutputDTO> personOutput = assertDoesNotThrow(() -> personConverter.convertToOutputDTOList(List.of(personEntity)));
        assertNotNull(personOutput);
        assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), personOutput.get(0).getId());
        assertEquals("Vinicius", personOutput.get(0).getName());
        assertEquals("123456789", personOutput.get(0).getDocument());
        assertEquals(LocalDate.of(1995, 10, 10), personOutput.get(0).getBirthDate());
        assertEquals(addressConverter.convertToOutputDTO(personEntity.getAddress()), personOutput.get(0).getAddress());
    }

    private PersonOutputDTO createPersonOutputDTO() {
        return PersonOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Vinicius")
                .document("123456789")
                .birthDate(LocalDate.of(1995, 10, 10))
                .address(createAddressOutputDTO())
                .build();
    }

    private AddressOutputDTO createAddressOutputDTO() {
        return AddressOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .addressDescription("Rua 1")
                .addressNumber(1)
                .complement("Casa 1")
                .district("Bairro 1")
                .zipCode("00000-000")
                .city("Cidade 1")
                .uf(Mockito.mock(StateOutputDTO.class))
                .country(Mockito.mock(CountryOutputDTO.class))
                .phoneNumber("00000000000")
                .build();
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Vinicius")
                .document("123456789")
                .birthDate(LocalDate.of(1995, 10, 10))
                .address(createAddressEntity())
                .build();
    }

    private AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .addressDescription("Rua 1")
                .addressNumber(1)
                .complement("Casa 1")
                .district("Bairro 1")
                .zipCode("00000-000")
                .city("Cidade 1")
                .uf(Mockito.mock(StateEntity.class))
                .country(Mockito.mock(CountryEntity.class))
                .phoneNumber("00000000000")
                .build();
    }

    private PersonInputDTO createPersonInputDTO() {
        return PersonInputDTO.builder()
                .id("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .name("Vinicius")
                .document("123456789")
                .birthDate(LocalDate.of(1995, 10, 10))
                .address(createAddressInputDTO())
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

}
