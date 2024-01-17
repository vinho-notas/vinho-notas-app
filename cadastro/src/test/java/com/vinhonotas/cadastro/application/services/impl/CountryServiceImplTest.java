package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Log4j2
@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @InjectMocks
    private CountryServiceImpl countryService;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private CountryConverter countryConverter;

    private CountryEntity brasilEntity;
    private CountryInputDTO brasilInputDTO;

    @BeforeEach
    void setUp() {
        log.info("Iniciando teste");

        brasilEntity = createBrasilEntity();
        brasilInputDTO = createBrasilInputDTO();
    }

    @Test
    @DisplayName("Teste de criação de país com sucesso")
    void createSuccess() {
        when(countryConverter.toEntity(brasilInputDTO)).thenReturn(brasilEntity);
        when(countryRepository.save(brasilEntity)).thenReturn(brasilEntity);

        CountryEntity entity = assertDoesNotThrow(() -> countryService.create(brasilInputDTO));
        assertNotNull(entity);
        assertEquals("Brasil", entity.getCountryName());
        assertEquals("América do Sul", entity.getContinentName());
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        verify(countryConverter, times(1)).toEntity(brasilInputDTO);
        verify(countryRepository, times(1)).save(brasilEntity);
    }

    //Testar criar país com exceção
    @Test
    @DisplayName("Teste de criação de país com exceção")
    void createException() {
        when(countryConverter.toEntity(brasilInputDTO)).thenReturn(brasilEntity);
        when(countryRepository.save(brasilEntity)).thenThrow(RuntimeException.class);

        Exception exception = assertThrows(Exception.class, () -> countryService.create(brasilInputDTO));
        assertEquals("Erro ao gravar dados do país", exception.getMessage());
        verify(countryConverter, times(1)).toEntity(brasilInputDTO);
        verify(countryRepository, times(1)).save(brasilEntity);
    }

    private CountryInputDTO createBrasilInputDTO() {
        return CountryInputDTO.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private CountryEntity createBrasilEntity() {
        return CountryEntity.builder()
                .id(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    @AfterAll
    static void tearDown() {
        log.info("Finalizando teste");
    }

}