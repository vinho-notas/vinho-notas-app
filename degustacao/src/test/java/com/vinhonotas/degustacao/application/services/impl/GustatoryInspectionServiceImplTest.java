package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.GustatoryInspectionConverter;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.infraestructure.GustatoryInspectionRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GustatoryInspectionServiceImplTest {
    
    @InjectMocks
    private GustatoryInspectionServiceImpl service;
    
    @Mock
    private GustatoryInspectionRepository repository;
    @Mock
    private GustatoryInspectionConverter converter;
    
    private GustatoryInspectionInputDTO inputDTO;
    private GustatoryInspectionEntity entity;

    @BeforeEach
    void setUp() {
        inputDTO = createGustatoryInspectionInputDTO();
        entity = createGustatoryInspectionEntity();        
    }

    @Test
    @DisplayName("Deve criar uma nova avaliação gustativa")
    void testCreate() {
        when(converter.toEntity(inputDTO)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);

        GustatoryInspectionEntity result = assertDoesNotThrow(() -> service.create(inputDTO));
        assertNotNull(result);
        assertEquals(entity.getTastingData(), result.getTastingData());
        assertEquals(entity.getWineTasted(), result.getWineTasted());
        assertEquals(entity.getBody(), result.getBody());
        assertEquals(entity.getSweetness(), result.getSweetness());
        assertEquals(entity.getTannin(), result.getTannin());
        assertEquals(entity.getClassification(), result.getClassification());
        assertEquals(entity.getAcidity(), result.getAcidity());
        assertEquals(entity.getAlcohol(), result.getAlcohol());
        assertEquals(entity.getPersistence(), result.getPersistence());
        assertEquals(entity.getMaturity(), result.getMaturity());
        assertEquals(entity.getTypicality(), result.getTypicality());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar criar uma nova avaliação gustativa")
    void testCreateThrowBadRequestException() {
        when(converter.toEntity(inputDTO)).thenReturn(entity);
        when(repository.save(entity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_GUSTATORY_INSPECTION));

        Exception ex = assertThrows(Exception.class, () -> service.create(inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING_GUSTATORY_INSPECTION, ex.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma lista de avaliações gustativas")
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(entity));

        List<GustatoryInspectionEntity> list = assertDoesNotThrow(() -> service.getAll());
        assertNotNull(list);
        assertEquals(entity.getTastingData(), list.get(0).getTastingData());
        assertEquals(entity.getWineTasted(), list.get(0).getWineTasted());
        assertEquals(entity.getBody(), list.get(0).getBody());
        assertEquals(entity.getSweetness(), list.get(0).getSweetness());
        assertEquals(entity.getTannin(), list.get(0).getTannin());
        assertEquals(entity.getClassification(), list.get(0).getClassification());
        assertEquals(entity.getAcidity(), list.get(0).getAcidity());
        assertEquals(entity.getAlcohol(), list.get(0).getAlcohol());
        assertEquals(entity.getPersistence(), list.get(0).getPersistence());
        assertEquals(entity.getMaturity(), list.get(0).getMaturity());
        assertEquals(entity.getTypicality(), list.get(0).getTypicality());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar retornar uma lista de avaliações gustativas")
    void testGetAllThrowBadRequestException() {
        when(repository.findAll()).thenReturn(List.of());

        Exception ex = assertThrows(Exception.class, () -> service.getAll());
        assertEquals(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND, ex.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma avaliação gustativa por id")
    void testGetById() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        GustatoryInspectionEntity result = assertDoesNotThrow(() -> service.getById(entity.getId()));
        assertNotNull(result);
        assertEquals(entity.getTastingData(), result.getTastingData());
        assertEquals(entity.getWineTasted(), result.getWineTasted());
        assertEquals(entity.getBody(), result.getBody());
        assertEquals(entity.getSweetness(), result.getSweetness());
        assertEquals(entity.getTannin(), result.getTannin());
        assertEquals(entity.getClassification(), result.getClassification());
        assertEquals(entity.getAcidity(), result.getAcidity());
        assertEquals(entity.getAlcohol(), result.getAlcohol());
        assertEquals(entity.getPersistence(), result.getPersistence());
        assertEquals(entity.getMaturity(), result.getMaturity());
        assertEquals(entity.getTypicality(), result.getTypicality());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar retornar uma avaliação gustativa por id")
    void testGetByIdThrowBadRequestException() {
        when(repository.findById(entity.getId())).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () -> service.getById(entity.getId()));
        assertEquals(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND, ex.getMessage());
    }

    @Test
    @DisplayName("Deve atualizar uma avaliação gustativa")
    void testUpdate() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(converter.toEntityUpdate(inputDTO, entity.getId(), entity)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);

        entity.setAcidity(EnumAcidityType.ADEQUATE);
        entity.setAlcohol(EnumAlcoholType.HIGH);

        GustatoryInspectionEntity result = assertDoesNotThrow(() -> service.update(entity.getId(), inputDTO));
        assertNotNull(result);
        assertEquals(EnumAcidityType.ADEQUATE, result.getAcidity());
        assertEquals(EnumAlcoholType.HIGH, result.getAlcohol());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar atualizar uma avaliação gustativa")
    void testUpdateThrowBadRequestException() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(converter.toEntityUpdate(inputDTO, entity.getId(), entity)).thenReturn(entity);
        when(repository.save(entity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_GUSTATORY_INSPECTION));

        Exception ex = assertThrows(Exception.class, () -> service.update(entity.getId(), inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING_GUSTATORY_INSPECTION, ex.getMessage());
    }

    @Test
    @DisplayName("Deve deletar uma avaliação gustativa")
    void testDelete() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        assertDoesNotThrow(() -> service.delete(entity.getId()));
        verify(repository, times(1)).deleteById(entity.getId());
        verify(repository, times(1)).findById(entity.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar deletar uma avaliação gustativa")
    void testDeleteThrowBadRequestException() {
        when(repository.findById(entity.getId())).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () -> service.delete(entity.getId()));
        assertEquals(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND, ex.getMessage());

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_GUSTATORY_INSPECTION)).when(repository).deleteById(entity.getId());

        ex = assertThrows(Exception.class, () -> service.delete(entity.getId()));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING_GUSTATORY_INSPECTION, ex.getMessage());
    }

    private GustatoryInspectionInputDTO createGustatoryInspectionInputDTO() {
        return GustatoryInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .body(EnumBodyType.FULL_BODIED.getCode())
                .sweetness(EnumSweetnessType.VERY_DRY.getCode())
                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
                .classification(EnumClassificationType.LITTLE.getCode())
                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
                .alcohol(EnumAlcoholType.LOW.getCode())
                .persistence(EnumPersistenceType.SHORT.getCode())
                .maturity(EnumMaturityType.MATURE.getCode())
                .typicality(EnumTypicalityType.NOT_TYPICAL.getCode())
                .build();
    }

    private GustatoryInspectionEntity createGustatoryInspectionEntity() {
        return GustatoryInspectionEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .body(EnumBodyType.FULL_BODIED)
                .sweetness(EnumSweetnessType.VERY_DRY)
                .tannin(EnumTanninType.LITTLE_TANIC)
                .classification(EnumClassificationType.LITTLE)
                .acidity(EnumAcidityType.LITTLE_ACID)
                .alcohol(EnumAlcoholType.LOW)
                .persistence(EnumPersistenceType.SHORT)
                .maturity(EnumMaturityType.MATURE)
                .typicality(EnumTypicalityType.NOT_TYPICAL)
                .build();
    }

}
