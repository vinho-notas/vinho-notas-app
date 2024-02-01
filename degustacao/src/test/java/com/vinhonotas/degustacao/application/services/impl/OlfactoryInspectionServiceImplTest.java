package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.OlfactoryInspectionConverter;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.EnumClassificationType;
import com.vinhonotas.degustacao.domain.enums.EnumIntensityType;
import com.vinhonotas.degustacao.domain.enums.EnumPersistenceType;
import com.vinhonotas.degustacao.domain.enums.EnumQualityType;
import com.vinhonotas.degustacao.infraestructure.OlfactoryInspectionRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OlfactoryInspectionServiceImplTest {

    @InjectMocks
    private OlfactoryInspectionServiceImpl olfactoryInspectionService;

    @Mock
    private OlfactoryInspectionRepository olfactoryInspectionRepository;
    @Mock
    private OlfactoryInspectionConverter olfactoryInspectionConverter;

    private OlfactoryInspectionEntity olfactoryInspectionEntity;
    private OlfactoryInspectionInputDTO olfactoryInspectionInputDTO;

    @BeforeEach
    void setUp() {
        olfactoryInspectionEntity = createOlfactoryInspectionEntity();
        olfactoryInspectionInputDTO = createOlfactoryInspectionInputDTO();
    }

    @Test
    @DisplayName("Deve criar uma inspeção olfativa")
    void testCreate() {
        when(olfactoryInspectionConverter.toEntity(olfactoryInspectionInputDTO)).thenReturn(olfactoryInspectionEntity);
        when(olfactoryInspectionRepository.save(olfactoryInspectionEntity)).thenReturn(olfactoryInspectionEntity);

        OlfactoryInspectionEntity entity = assertDoesNotThrow(() -> olfactoryInspectionService.create(olfactoryInspectionInputDTO));

        assertNotNull(entity);
        assertEquals(olfactoryInspectionEntity.getId(), entity.getId());
        assertEquals(olfactoryInspectionEntity.getTastingData(), entity.getTastingData());
        assertEquals(olfactoryInspectionEntity.getWineTasted(), entity.getWineTasted());
        assertEquals(olfactoryInspectionEntity.getIntensity(), entity.getIntensity());
        assertEquals(olfactoryInspectionEntity.getPersistence(), entity.getPersistence());
        assertEquals(olfactoryInspectionEntity.getQuality(), entity.getQuality());
        assertEquals(olfactoryInspectionEntity.getAromas(), entity.getAromas());
        assertEquals(olfactoryInspectionEntity.getClassification(), entity.getClassification());
        assertEquals(olfactoryInspectionEntity.getTastingCard(), entity.getTastingCard());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar uma inspeção olfativa")
    void testCreateThrowBadRequestException() {
        when(olfactoryInspectionRepository.save(olfactoryInspectionEntity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_OLFACTORY_INSPECTION));

        Exception exception = assertThrows(Exception.class, () -> olfactoryInspectionService.create(olfactoryInspectionInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING_OLFACTORY_INSPECTION, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma lista de inspeções olfativas")
    void testGetAll() {
        when(olfactoryInspectionRepository.findAll()).thenReturn(List.of(olfactoryInspectionEntity));

        List<OlfactoryInspectionEntity> list = assertDoesNotThrow(() -> olfactoryInspectionService.getAll());
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(olfactoryInspectionEntity.getId(), list.get(0).getId());
        assertEquals(olfactoryInspectionEntity.getTastingData(), list.get(0).getTastingData());
        assertEquals(olfactoryInspectionEntity.getWineTasted(), list.get(0).getWineTasted());
        assertEquals(olfactoryInspectionEntity.getIntensity(), list.get(0).getIntensity());
        assertEquals(olfactoryInspectionEntity.getPersistence(), list.get(0).getPersistence());
        assertEquals(olfactoryInspectionEntity.getQuality(), list.get(0).getQuality());
        assertEquals(olfactoryInspectionEntity.getAromas(), list.get(0).getAromas());
        assertEquals(olfactoryInspectionEntity.getClassification(), list.get(0).getClassification());
        assertEquals(olfactoryInspectionEntity.getTastingCard(), list.get(0).getTastingCard());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma lista de inspeções olfativas")
    void testGetAllThrowBadRequestException() {
        when(olfactoryInspectionRepository.findAll()).thenReturn(List.of());

        Exception exception = assertThrows(Exception.class, () -> olfactoryInspectionService.getAll());
        assertEquals(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma inspeção olfativa por id")
    void testGetById() {
        when(olfactoryInspectionRepository.findById(olfactoryInspectionEntity.getId())).thenReturn(Optional.of(olfactoryInspectionEntity));

        OlfactoryInspectionEntity entity = assertDoesNotThrow(() -> olfactoryInspectionService.getById(olfactoryInspectionEntity.getId()));
        assertNotNull(entity);
        assertEquals(olfactoryInspectionEntity.getId(), entity.getId());
        assertEquals(olfactoryInspectionEntity.getTastingData(), entity.getTastingData());
        assertEquals(olfactoryInspectionEntity.getWineTasted(), entity.getWineTasted());
        assertEquals(olfactoryInspectionEntity.getIntensity(), entity.getIntensity());
        assertEquals(olfactoryInspectionEntity.getPersistence(), entity.getPersistence());
        assertEquals(olfactoryInspectionEntity.getQuality(), entity.getQuality());
        assertEquals(olfactoryInspectionEntity.getAromas(), entity.getAromas());
        assertEquals(olfactoryInspectionEntity.getClassification(), entity.getClassification());
        assertEquals(olfactoryInspectionEntity.getTastingCard(), entity.getTastingCard());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma inspeção olfativa por id")
    void testGetByIdThrowBadRequestException() {
        when(olfactoryInspectionRepository.findById(olfactoryInspectionEntity.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> olfactoryInspectionService.getById(olfactoryInspectionEntity.getId()));
        assertEquals(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Deve atualizar uma inspeção olfativa")
    void testUpdate() {
        olfactoryInspectionEntity.setWineTasted("Wine Tasted Updated");
        olfactoryInspectionEntity.setIntensity(EnumIntensityType.WEAK);

        when(olfactoryInspectionRepository.findById(olfactoryInspectionEntity.getId())).thenReturn(Optional.of(olfactoryInspectionEntity));
        when(olfactoryInspectionConverter.toEntityUpdate(olfactoryInspectionInputDTO, olfactoryInspectionEntity.getId(), olfactoryInspectionEntity)).thenReturn(olfactoryInspectionEntity);
        when(olfactoryInspectionRepository.save(olfactoryInspectionEntity)).thenReturn(olfactoryInspectionEntity);

        OlfactoryInspectionEntity entity = assertDoesNotThrow(() -> olfactoryInspectionService.update(olfactoryInspectionEntity.getId(), olfactoryInspectionInputDTO));
        assertNotNull(entity);
        assertEquals("Wine Tasted Updated", entity.getWineTasted());
        assertEquals(EnumIntensityType.WEAK, entity.getIntensity());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar uma inspeção olfativa")
    void testUpdateThrowBadRequestException() {
        when(olfactoryInspectionRepository.findById(olfactoryInspectionEntity.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> olfactoryInspectionService.update(olfactoryInspectionEntity.getId(), olfactoryInspectionInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING_OLFACTORY_INSPECTION, exception.getMessage());
    }

    @Test
    @DisplayName("Deve deletar uma inspeção olfativa")
    void testDelete() {
        when(olfactoryInspectionRepository.findById(olfactoryInspectionEntity.getId())).thenReturn(Optional.of(olfactoryInspectionEntity));

        assertDoesNotThrow(() -> olfactoryInspectionService.delete(olfactoryInspectionEntity.getId()));
        verify(olfactoryInspectionRepository).deleteById(olfactoryInspectionEntity.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar uma inspeção olfativa")
    void testDeleteThrowBadRequestException() {
        Exception exception = assertThrows(Exception.class, () -> olfactoryInspectionService.delete(olfactoryInspectionEntity.getId()));
        assertEquals(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND, exception.getMessage());

        when(olfactoryInspectionRepository.findById(olfactoryInspectionEntity.getId())).thenReturn(Optional.of(olfactoryInspectionEntity));
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_OLFACTORY_INSPECTION))
                .when(olfactoryInspectionRepository).deleteById(olfactoryInspectionEntity.getId());
        exception = assertThrows(Exception.class, () -> olfactoryInspectionService.delete(olfactoryInspectionEntity.getId()));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING_OLFACTORY_INSPECTION, exception.getMessage());

    }

    private OlfactoryInspectionInputDTO createOlfactoryInspectionInputDTO() {
        return OlfactoryInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE)
                .persistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .aromas(Mockito.mock(AromasEntity.class))
                .classification(EnumClassificationType.LITTLE)
                .tastingCard(Mockito.mock(TastingCardEntity.class))
                .build();
    }

    private OlfactoryInspectionEntity createOlfactoryInspectionEntity() {
        return OlfactoryInspectionEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE)
                .persistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .aromas(Mockito.mock(AromasEntity.class))
                .classification(EnumClassificationType.LITTLE)
                .tastingCard(Mockito.mock(TastingCardEntity.class))
                .build();
    }
}