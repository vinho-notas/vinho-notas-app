package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.degustacao.OlfactoryInspectionClient;
import com.vinhonotas.bff.domain.enums.EnumClassificationType;
import com.vinhonotas.bff.domain.enums.EnumIntensityType;
import com.vinhonotas.bff.domain.enums.EnumPersistenceType;
import com.vinhonotas.bff.domain.enums.EnumQualityType;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.AromasInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.OlfactoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.AromasOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.OlfactoryInspectionOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OlfactoryInspectionServiceImplTest {

    @InjectMocks
    private OlfactoryInspectionServiceImpl olfactoryInspectionService;

    @Mock
    private OlfactoryInspectionClient olfactoryInspectionClient;

    private OlfactoryInspectionOutputDTO olfactoryInspectionOutputDTO;
    private OlfactoryInspectionInputDTO olfactoryInspectionInputDTO;

    @BeforeEach
    void setUp() {
        olfactoryInspectionOutputDTO = createOlfactoryInspectionOutputDTO();
        olfactoryInspectionInputDTO = createOlfactoryInspectionInputDTO();
    }

    @Test
    @DisplayName("Deve criar uma inspeção olfativa")
    void testCreate() {
        when(olfactoryInspectionClient.createOlafactoryInspection(olfactoryInspectionInputDTO)).thenReturn(olfactoryInspectionOutputDTO);

        OlfactoryInspectionOutputDTO response = assertDoesNotThrow(() -> olfactoryInspectionService
                .createOlfactoryInspection(olfactoryInspectionInputDTO));

        assertNotNull(response);
        assertEquals(olfactoryInspectionOutputDTO.getId(), response.getId());
        assertEquals(olfactoryInspectionOutputDTO.getTastingData(), response.getTastingData());
        assertEquals(olfactoryInspectionOutputDTO.getWineTasted(), response.getWineTasted());
        assertEquals(olfactoryInspectionOutputDTO.getIntensity(), response.getIntensity());
        assertEquals(olfactoryInspectionOutputDTO.getPersistence(), response.getPersistence());
        assertEquals(olfactoryInspectionOutputDTO.getQuality(), response.getQuality());
        assertEquals(olfactoryInspectionOutputDTO.getAromas(), response.getAromas());
        assertEquals(olfactoryInspectionOutputDTO.getClassification(), response.getClassification());
        verify(olfactoryInspectionClient).createOlafactoryInspection(olfactoryInspectionInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar uma inspeção olfativa")
    void testCreateThrowBadRequestException() {
        when(olfactoryInspectionClient.createOlafactoryInspection(olfactoryInspectionInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        Exception exception = assertThrows(Exception.class, () -> olfactoryInspectionService
                .createOlfactoryInspection(olfactoryInspectionInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(olfactoryInspectionClient).createOlafactoryInspection(olfactoryInspectionInputDTO);
    }

    @Test
    @DisplayName("Deve retornar uma lista de inspeções olfativas")
    void testGetAll() {
        when(olfactoryInspectionClient.getAllOlfactoryInspections()).thenReturn(List.of(olfactoryInspectionOutputDTO));

        List<OlfactoryInspectionOutputDTO> list = assertDoesNotThrow(() -> olfactoryInspectionService.getAllOlfactoryInspections());
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(olfactoryInspectionOutputDTO.getId(), list.get(0).getId());
        assertEquals(olfactoryInspectionOutputDTO.getTastingData(), list.get(0).getTastingData());
        assertEquals(olfactoryInspectionOutputDTO.getWineTasted(), list.get(0).getWineTasted());
        assertEquals(olfactoryInspectionOutputDTO.getIntensity(), list.get(0).getIntensity());
        assertEquals(olfactoryInspectionOutputDTO.getPersistence(), list.get(0).getPersistence());
        assertEquals(olfactoryInspectionOutputDTO.getQuality(), list.get(0).getQuality());
        assertEquals(olfactoryInspectionOutputDTO.getAromas(), list.get(0).getAromas());
        assertEquals(olfactoryInspectionOutputDTO.getClassification(), list.get(0).getClassification());
        verify(olfactoryInspectionClient).getAllOlfactoryInspections();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma lista de inspeções olfativas")
    void testGetAllThrowBadRequestException() {
        when(olfactoryInspectionClient.getAllOlfactoryInspections()).thenReturn(List.of());

        Exception exception = assertThrows(Exception.class, () -> olfactoryInspectionService.getAllOlfactoryInspections());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(olfactoryInspectionClient).getAllOlfactoryInspections();
    }

    @Test
    @DisplayName("Deve retornar uma inspeção olfativa por id")
    void testGetById() {
        when(olfactoryInspectionClient.getOlfactoryInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .thenReturn(olfactoryInspectionOutputDTO);

        OlfactoryInspectionOutputDTO response = assertDoesNotThrow(() -> olfactoryInspectionService
                .getOlfactoryInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertNotNull(response);
        assertEquals(olfactoryInspectionOutputDTO.getId(), response.getId());
        assertEquals(olfactoryInspectionOutputDTO.getTastingData(), response.getTastingData());
        assertEquals(olfactoryInspectionOutputDTO.getWineTasted(), response.getWineTasted());
        assertEquals(olfactoryInspectionOutputDTO.getIntensity(), response.getIntensity());
        assertEquals(olfactoryInspectionOutputDTO.getPersistence(), response.getPersistence());
        assertEquals(olfactoryInspectionOutputDTO.getQuality(), response.getQuality());
        assertEquals(olfactoryInspectionOutputDTO.getAromas(), response.getAromas());
        assertEquals(olfactoryInspectionOutputDTO.getClassification(), response.getClassification());
        verify(olfactoryInspectionClient).getOlfactoryInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma inspeção olfativa por id")
    void testGetByIdThrowBadRequestException() {
        when(olfactoryInspectionClient.getOlfactoryInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> olfactoryInspectionService
                .getOlfactoryInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(olfactoryInspectionClient).getOlfactoryInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve atualizar uma inspeção olfativa")
    void testUpdate() {
        when(olfactoryInspectionClient.updateOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", olfactoryInspectionInputDTO))
                .thenReturn(olfactoryInspectionOutputDTO);

        OlfactoryInspectionOutputDTO response = assertDoesNotThrow(() -> olfactoryInspectionService
                .updateOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", olfactoryInspectionInputDTO));
        assertNotNull(response);
        assertEquals(olfactoryInspectionOutputDTO.getId(), response.getId());
        assertEquals(olfactoryInspectionOutputDTO.getTastingData(), response.getTastingData());
        assertEquals(olfactoryInspectionOutputDTO.getWineTasted(), response.getWineTasted());
        assertEquals(olfactoryInspectionOutputDTO.getIntensity(), response.getIntensity());
        assertEquals(olfactoryInspectionOutputDTO.getPersistence(), response.getPersistence());
        assertEquals(olfactoryInspectionOutputDTO.getQuality(), response.getQuality());
        assertEquals(olfactoryInspectionOutputDTO.getAromas(), response.getAromas());
        assertEquals(olfactoryInspectionOutputDTO.getClassification(), response.getClassification());
        verify(olfactoryInspectionClient).updateOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", olfactoryInspectionInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar uma inspeção olfativa")
    void testUpdateThrowBadRequestException() {
        when(olfactoryInspectionClient.updateOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", olfactoryInspectionInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        Exception exception = assertThrows(Exception.class, () -> olfactoryInspectionService
                .updateOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", olfactoryInspectionInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(olfactoryInspectionClient).updateOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", olfactoryInspectionInputDTO);
    }

    @Test
    @DisplayName("Deve deletar uma inspeção olfativa")
    void testDelete() {
        assertDoesNotThrow(() -> olfactoryInspectionService.deleteOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        verify(olfactoryInspectionClient).deleteOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar uma inspeção olfativa")
    void testDeleteThrowBadRequestException() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
                .when(olfactoryInspectionClient).deleteOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");

        Exception exception = assertThrows(Exception.class, () -> olfactoryInspectionService
                .deleteOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(olfactoryInspectionClient).deleteOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");

    }

    private OlfactoryInspectionInputDTO createOlfactoryInspectionInputDTO() {
        return OlfactoryInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE.getCode())
                .persistence(EnumPersistenceType.PERSISTENT.getCode())
                .quality(EnumQualityType.COMMON.getCode())
                .aromas(Mockito.mock(AromasInputDTO.class))
                .classification(EnumClassificationType.LITTLE.getCode())
                .build();
    }

    private OlfactoryInspectionOutputDTO createOlfactoryInspectionOutputDTO() {
        return OlfactoryInspectionOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE)
                .persistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .aromas(Mockito.mock(AromasOutputDTO.class))
                .classification(EnumClassificationType.LITTLE)
                .build();
    }

}