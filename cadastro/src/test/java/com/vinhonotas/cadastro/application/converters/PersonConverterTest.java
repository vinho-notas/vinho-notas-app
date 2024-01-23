package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.PersonOutputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
        PersonEntity personEntity = assertDoesNotThrow(() -> personConverter.toEntity(personInputDTO));
        assertNotNull(personEntity);
        assertEquals(personInputDTO.getName(), personEntity.getName());
        assertEquals(personInputDTO.getDocument(), personEntity.getDocument());
        assertEquals(personInputDTO.getBirthDate(), personEntity.getBirthDate());
        assertEquals(personInputDTO.getAddress(), personEntity.getAddress());
    }

    @Test
    @DisplayName("Teste de conversão para PersonEntityUpdate ")
    void testToEntityUpdate() {
        personInputDTO.setName("Mauricio");

        PersonEntity entity = assertDoesNotThrow(() -> personConverter.toEntityUpdate(personEntity, personEntity.getId(), personInputDTO));
        assertNotNull(personEntity);
        assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), entity.getId());
        assertEquals("Mauricio", entity.getName());
        assertEquals("123456789", entity.getDocument());
        assertEquals(LocalDate.of(1995, 10, 10), entity.getBirthDate());
        assertEquals(personInputDTO.getAddress(), entity.getAddress());
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
        assertEquals(personEntity.getAddress(), personOutput.getAddress());
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
        assertEquals(personEntity.getAddress(), personOutput.get(0).getAddress());
    }

    @Test
    @DisplayName("Teste de conversão de PersonEntity para PersonOutputDTOUpdate")
    void testConvertToOutputDTOUpdate() {
        personEntity.setName("Mauricio");
        PersonOutputDTO personOutput = assertDoesNotThrow(() -> personConverter.convertToOutputDTOUpdate(personEntity, personEntity.getId(), personOutputDTO));
        assertNotNull(personOutput);
        assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), personOutput.getId());
        assertEquals("Mauricio", personOutput.getName());
        assertEquals("123456789", personOutput.getDocument());
        assertEquals(LocalDate.of(1995, 10, 10), personOutput.getBirthDate());
        assertEquals(personEntity.getAddress(), personOutput.getAddress());
    }

    private PersonOutputDTO createPersonOutputDTO() {
        return PersonOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Vinicius")
                .document("123456789")
                .birthDate(LocalDate.of(1995, 10, 10))
                .address(Mockito.mock(AddressEntity.class))
                .build();
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Vinicius")
                .document("123456789")
                .birthDate(LocalDate.of(1995, 10, 10))
                .address(Mockito.mock(AddressEntity.class))
                .build();
    }

    private PersonInputDTO createPersonInputDTO() {
        return PersonInputDTO.builder()
                .name("Vinicius")
                .document("123456789")
                .birthDate(LocalDate.of(1995, 10, 10))
                .address(Mockito.mock(AddressEntity.class))
                .build();
    }

}