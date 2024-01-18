package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.CountryInputDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
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
    void testCreateSuccess() {
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

    @Test
    @DisplayName("Teste de criação de país com exceção")
    void testCreateException() {
        when(countryConverter.toEntity(brasilInputDTO)).thenReturn(brasilEntity);
        when(countryRepository.save(brasilEntity)).thenThrow(RuntimeException.class);

        Exception exception = assertThrows(Exception.class, () -> countryService.create(brasilInputDTO));
        assertEquals("Erro ao gravar dados do país", exception.getMessage());
        verify(countryConverter, times(1)).toEntity(brasilInputDTO);
        verify(countryRepository, times(1)).save(brasilEntity);
    }

    @Test
    @DisplayName("Deve retornar uma lista de países")
    void testGetAll() {
        when(countryRepository.findAll()).thenReturn(List.of(brasilEntity));

        List<CountryEntity> list = assertDoesNotThrow(() -> countryService.getAll());
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("Brasil", list.get(0).getCountryName());
        assertEquals("América do Sul", list.get(0).getContinentName());
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), list.get(0).getId());
        verify(countryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar um país pelo id")
    void testGetById() {
        when(countryRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.of(brasilEntity));

        CountryEntity entity = assertDoesNotThrow(() -> countryService.getById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertNotNull(entity);
        assertEquals("Brasil", entity.getCountryName());
        assertEquals("América do Sul", entity.getContinentName());
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        verify(countryRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao buscar um país pelo id")
    void testGetByIdException() {
        when(countryRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> countryService.getById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertEquals("País não encontrado", exception.getMessage());
        verify(countryRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar um país pelo nome")
    void testGetByName() {
        when(countryRepository.findByCountryName("Brasil")).thenReturn(brasilEntity);

        CountryEntity entity = assertDoesNotThrow(() -> countryService.getByName("Brasil"));
        assertNotNull(entity);
        assertEquals("Brasil", entity.getCountryName());
        assertEquals("América do Sul", entity.getContinentName());
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        verify(countryRepository, times(1)).findByCountryName("Brasil");
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao buscar um país pelo nome")
    void testGetByNameException() {
        when(countryRepository.findByCountryName("Brasil")).thenThrow(IllegalArgumentException.class);

        Exception exception = assertThrows(Exception.class, () -> countryService.getByName("Brasil"));
        assertEquals("País não encontrado com o nome: Brasil", exception.getMessage());
        verify(countryRepository, times(1)).findByCountryName("Brasil");
    }

    @Test
    @DisplayName("Deve retornar uma lista de países pelo continente")
    void testGetByContinent() {
        when(countryRepository.findByContinentName("América do Sul")).thenReturn(List.of(brasilEntity));

        List<CountryEntity> list = assertDoesNotThrow(() -> countryService.getByContinent("América do Sul"));
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("Brasil", list.get(0).getCountryName());
        assertEquals("América do Sul", list.get(0).getContinentName());
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), list.get(0).getId());
        verify(countryRepository, times(1)).findByContinentName("América do Sul");
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao buscar uma lista de países pelo continente")
    void testGetByContinentException() {
        when(countryRepository.findByContinentName("América do Sul")).thenThrow(IllegalArgumentException.class);

        Exception exception = assertThrows(Exception.class, () -> countryService.getByContinent("América do Sul"));
        assertEquals("País não encontrado com o continente: América do Sul", exception.getMessage());
        verify(countryRepository, times(1)).findByContinentName("América do Sul");
    }

    @Test
    @DisplayName("Deve atualizar um país")
    void testUpdate() {
        when(countryRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.of(brasilEntity));
        when(countryConverter.toEntityUpdate(brasilEntity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), brasilInputDTO)).thenReturn(brasilEntity);
        when(countryRepository.save(brasilEntity)).thenReturn(brasilEntity);
        when(countryRepository.findByCountryName("Brasil")).thenReturn(brasilEntity);

        CountryEntity entity = assertDoesNotThrow(() -> countryService.update(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), brasilInputDTO));
        assertNotNull(entity);
        assertEquals("Brasil", entity.getCountryName());
        assertEquals("América do Sul", entity.getContinentName());
        assertEquals(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), entity.getId());
        verify(countryRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
        verify(countryConverter, times(1)).toEntityUpdate(brasilEntity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), brasilInputDTO);
        verify(countryRepository, times(1)).save(brasilEntity);
        verify(countryRepository, times(1)).findByCountryName("Brasil");
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao atualizar um país")
    void testUpdateException() {
        when(countryRepository.findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> countryService.update(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), brasilInputDTO));
        assertEquals("Erro ao atualizar dados do país", exception.getMessage());
        verify(countryRepository, times(1)).findById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
        verify(countryConverter, times(0)).toEntityUpdate(brasilEntity, UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"), brasilInputDTO);
        verify(countryRepository, times(0)).save(brasilEntity);
    }

    @Test
    @DisplayName("Deve deletar um país")
    void testDelete() {
        assertDoesNotThrow(() -> countryService.delete(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        verify(countryRepository, times(1)).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao deletar um país")
    void testDeleteException() {
        doThrow(IllegalArgumentException.class).when(countryRepository).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));

        Exception exception = assertThrows(Exception.class, () -> countryService.delete(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465")));
        assertEquals("Erro ao deletar país", exception.getMessage());
        verify(countryRepository, times(1)).deleteById(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"));
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

    @AfterEach
    void tearDownEach() {
        log.info("Finalizando teste");
    }


}