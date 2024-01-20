package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.CountryOutputDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Log4j2
class CountryConverterTest {

    @InjectMocks
    private CountryConverter countryConverter;

    private CountryEntity countryEntity;
    private CountryInputDTO countryInputDTO;
    private CountryOutputDTO countryOutputDTO;

    @BeforeEach
    void setUp() {
        log.info("Iniciando teste: ");

        countryEntity = createCountryEntity();
        countryInputDTO = createCountryInputDTO();
        countryOutputDTO = createCountryOutputDTO();
    }

    @Test
    @DisplayName("Teste de conversão de CountryInputDTO para CountryEntity")
    void testToEntity() {
        CountryEntity country = assertDoesNotThrow(() -> countryConverter.toEntity(countryInputDTO));
        assertNotNull(country);
        assertEquals(countryInputDTO.getCountryName(), country.getCountryName());
        assertEquals(countryInputDTO.getContinentName(), country.getContinentName());
    }

    @Test
    @DisplayName("Teste de conversão para CountryEntityUpdate ")
    void testToEntityUpdate() {
        countryInputDTO.setCountryName("Argentina");

        CountryEntity country = assertDoesNotThrow(()-> countryConverter.toEntityUpdate(countryEntity, countryEntity.getId(), countryInputDTO));
        assertNotNull(country);
        assertEquals("Argentina", country.getCountryName());
        assertEquals(countryInputDTO.getContinentName(), country.getContinentName());
    }

    @Test
    @DisplayName("Teste de conversão de CountryEntity para CountryOutputDTO")
    void testConvertToOutputDTO() {
        CountryOutputDTO countryOutput = assertDoesNotThrow(()-> countryConverter.convertToOutputDTO(countryEntity));
        assertNotNull(countryOutput);
        assertEquals(countryEntity.getId(), countryOutput.getId());
        assertEquals(countryEntity.getCountryName(), countryOutput.getCountryName());
        assertEquals(countryEntity.getContinentName(), countryOutput.getContinentName());
    }

    @Test
    @DisplayName("Teste de conversão de List<CountryEntity> para List<CountryOutputDTO>")
    void testConvertToOutputDTOList() {
        List<CountryOutputDTO> list = assertDoesNotThrow(()-> countryConverter.convertToOutputDTOList(List.of(countryEntity)));
        assertNotNull(list);
        assertEquals(1, list.size());
        CountryOutputDTO countryOutput = list.get(0);
        assertEquals(countryEntity.getId(), countryOutput.getId());
        assertEquals(countryEntity.getCountryName(), countryOutput.getCountryName());
        assertEquals(countryEntity.getContinentName(), countryOutput.getContinentName());
    }

    @Test
    @DisplayName("Teste de conversão de CountryOutputDTO para CountryOutputDTOUpdate")
    void testConvertToOutputDTOUpdate() {
        countryOutputDTO.setCountryName("Argentina");

        CountryOutputDTO countryOutput = assertDoesNotThrow(()-> countryConverter.convertToOutputDTOUpdate(countryEntity, countryEntity.getId(), countryOutputDTO));
        assertNotNull(countryOutput);
        assertEquals(countryEntity.getId(), countryOutput.getId());
        assertEquals("Argentina", countryOutput.getCountryName());
        assertEquals(countryOutputDTO.getContinentName(), countryOutput.getContinentName());
    }



    private CountryEntity createCountryEntity() {
        return CountryEntity.builder()
                .id(UUID.fromString("d0d7c2a0-0b7a-4e1e-8b7a-0b7a4e1e8b7a"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private CountryInputDTO createCountryInputDTO() {
        return CountryInputDTO.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private CountryOutputDTO createCountryOutputDTO() {
        return CountryOutputDTO.builder()
                .id(UUID.fromString("d0d7c2a0-0b7a-4e1e-8b7a-0b7a4e1e8b7a"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    @AfterEach
    void tearDownEach() {
        log.info("Finalizando teste");
    }


}