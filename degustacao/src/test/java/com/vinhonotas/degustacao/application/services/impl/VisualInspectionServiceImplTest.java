package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.VisualInspectionConverter;
import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
import com.vinhonotas.degustacao.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.infraestructure.VisualInspectionRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;
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
class VisualInspectionServiceImplTest {

    @InjectMocks
    private VisualInspectionServiceImpl service;

    @Mock
    private VisualInspectionRepository repository;
    @Mock
    private VisualInspectionConverter converter;

    private VisualInspectionEntity entity;
    private VisualInspectionInputDTO inputDTO;

    @BeforeEach
    void setUp() {
        entity = createVisualInspectionEntity();
        inputDTO = createVisualInspectionInputDTO();
    }

    @Test
    @DisplayName("Deve criar uma inspeção visual")
    void testCreate(){
        when(converter.toEntity(inputDTO)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);

        VisualInspectionEntity result = assertDoesNotThrow(() -> service.create(inputDTO));
        assertNotNull(result);
        assertEquals(entity.getBrightness(), result.getBrightness());
        assertEquals(entity.getClarity(), result.getClarity());
        assertEquals(entity.getColorRed(), result.getColorRed());
        assertEquals(entity.getClassification(), result.getClassification());
        assertEquals(entity.getViscosity(), result.getViscosity());
        assertEquals(entity.getTastingData(), result.getTastingData());
        assertEquals(entity.getWineTasted(), result.getWineTasted());
        verify(converter).toEntity(inputDTO);
        verify(repository).save(entity);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar uma inspeção visual")
    void testCreateThrowsBadRequestException() {
        when(repository.save(entity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_VISUAL_INSPECTION));

        Exception exception = assertThrows(Exception.class, () -> service.create(inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING_VISUAL_INSPECTION, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma lista de inspeções visuais")
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(entity));

        List<VisualInspectionEntity> list = assertDoesNotThrow(() -> service.getAll());
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(entity.getBrightness(), list.get(0).getBrightness());
        assertEquals(entity.getClarity(), list.get(0).getClarity());
        assertEquals(entity.getColorRed(), list.get(0).getColorRed());
        assertEquals(entity.getClassification(), list.get(0).getClassification());
        assertEquals(entity.getViscosity(), list.get(0).getViscosity());
        assertEquals(entity.getTastingData(), list.get(0).getTastingData());
        assertEquals(entity.getWineTasted(), list.get(0).getWineTasted());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma lista de inspeções visuais")
    void testGetAllThrowsBadRequestException() {
        when(repository.findAll()).thenReturn(List.of());

        Exception exception = assertThrows(Exception.class, () -> service.getAll());
        assertEquals(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma inspeção visual por id")
    void testGetById() {
        when(repository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));

        VisualInspectionEntity result = assertDoesNotThrow(() -> service.getById(entity.getId()));
        assertNotNull(result);
        assertEquals(entity.getBrightness(), result.getBrightness());
        assertEquals(entity.getClarity(), result.getClarity());
        assertEquals(entity.getColorRed(), result.getColorRed());
        assertEquals(entity.getClassification(), result.getClassification());
        assertEquals(entity.getViscosity(), result.getViscosity());
        assertEquals(entity.getTastingData(), result.getTastingData());
        assertEquals(entity.getWineTasted(), result.getWineTasted());
        verify(repository).findById(entity.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma inspeção visual por id")
    void testGetByIdThrowsBadRequestException() {
        when(repository.findById(entity.getId())).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> service.getById(entity.getId()));
        assertEquals(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Deve atualizar uma inspeção visual")
    void testUpdate() {
        when(repository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));
        when(converter.toEntityUpdate(inputDTO, entity.getId(), entity)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);

        entity.setBrightness(EnumBrightnessType.LITTLE_BRIGHT);
        entity.setColorWhite(EnumWhiteColorType.AMBER);

        VisualInspectionEntity result = assertDoesNotThrow(() -> service.update(entity.getId(), inputDTO));
        assertNotNull(result);
        assertEquals(EnumBrightnessType.LITTLE_BRIGHT, result.getBrightness());
        assertEquals(EnumWhiteColorType.AMBER, result.getColorWhite());
        verify(repository).findById(entity.getId());
        verify(converter).toEntityUpdate(inputDTO, entity.getId(), entity);
        verify(repository).save(entity);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar uma inspeção visual")
    void testUpdateThrowsBadRequestException() {
        when(repository.findById(entity.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> service.update(entity.getId(), inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING_VISUAL_INSPECTION, exception.getMessage());
    }

    @Test
    @DisplayName("Deve deletar uma inspeção visual")
    void testDelete() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        assertDoesNotThrow(() -> service.delete(entity.getId()));
        verify(repository).findById(entity.getId());
        verify(repository).deleteById(entity.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar uma inspeção visual")
    void testDeleteThrowsBadRequestException() {
        Exception exception = assertThrows(Exception.class, () -> service.delete(entity.getId()));
        assertEquals(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND, exception.getMessage());

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_VISUAL_INSPECTION)).when(repository).deleteById(entity.getId());

        exception = assertThrows(Exception.class, () -> service.delete(entity.getId()));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING_VISUAL_INSPECTION, exception.getMessage());
    }

    private VisualInspectionInputDTO createVisualInspectionInputDTO() {
        return VisualInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .clarity(EnumClarityType.CLEAR.getCode())
                .brightness(EnumBrightnessType.OPAQUE.getCode())
                .viscosity(EnumViscosityType.VISCOUS.getCode())
                .colorRed(EnumRedColorType.RUBY.getCode())
                .classification(EnumClassificationType.LITTLE.getCode())
                .build();
    }

    private VisualInspectionEntity createVisualInspectionEntity() {
        return VisualInspectionEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .clarity(EnumClarityType.CLEAR)
                .brightness(EnumBrightnessType.OPAQUE)
                .viscosity(EnumViscosityType.VISCOUS)
                .colorRed(EnumRedColorType.RUBY)
                .classification(EnumClassificationType.LITTLE)
                .build();
    }

}
