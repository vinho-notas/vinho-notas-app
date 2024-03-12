package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.degustacao.VisualInspectionClient;
import com.vinhonotas.bff.domain.enums.*;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.VisualInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.VisualInspectionOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisualInspectionServiceImplTest {
    
    @InjectMocks
    private VisualInspectionServiceImpl service;
    
    @Mock
    private VisualInspectionClient client;
    
    private VisualInspectionInputDTO inputDTO;
    private VisualInspectionOutputDTO outputDTO;
    
    @BeforeEach
    void setUp() {
        inputDTO = createVisualInspectionInputDTO();
        outputDTO = createVisualInspectionOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma inspeção visual")
    void testCreate() {
        when(client.createVisualInspection(inputDTO)).thenReturn(outputDTO);

        VisualInspectionOutputDTO result = assertDoesNotThrow(() -> service.createVisualInspection(inputDTO));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getClarity(), result.getClarity());
        assertEquals(outputDTO.getBrightness(), result.getBrightness());
        assertEquals(outputDTO.getViscosity(), result.getViscosity());
        assertEquals(outputDTO.getColorRed(), result.getColorRed());
        assertEquals(outputDTO.getColorWhite(), result.getColorWhite());
        assertEquals(outputDTO.getColorRose(), result.getColorRose());
        assertEquals(outputDTO.getClassification(), result.getClassification());
        verify(client).createVisualInspection(inputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar uma inspeção visual")
    void testCreateThrowBadRequestException() {
        when(client.createVisualInspection(inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        Exception exception = assertThrows(Exception.class, () -> service.createVisualInspection(inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(client).createVisualInspection(inputDTO);
    }

    @Test
    @DisplayName("Deve listar todas as inspeções visuais")
    void testGetAll() {
        when(client.getAllVisualInspections()).thenReturn(List.of(outputDTO));

        var result = assertDoesNotThrow(() -> service.getAllVisualInspections());
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(outputDTO.getId(), result.get(0).getId());
        assertEquals(outputDTO.getTastingData(), result.get(0).getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.get(0).getWineTasted());
        assertEquals(outputDTO.getClarity(), result.get(0).getClarity());
        assertEquals(outputDTO.getBrightness(), result.get(0).getBrightness());
        assertEquals(outputDTO.getViscosity(), result.get(0).getViscosity());
        assertEquals(outputDTO.getColorRed(), result.get(0).getColorRed());
        assertEquals(outputDTO.getColorWhite(), result.get(0).getColorWhite());
        assertEquals(outputDTO.getColorRose(), result.get(0).getColorRose());
        assertEquals(outputDTO.getClassification(), result.get(0).getClassification());
        verify(client).getAllVisualInspections();
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao listar todas as inspeções visuais")
    void testGetAllThrowNotFoundException() {
        when(client.getAllVisualInspections()).thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));

        Exception exception = assertThrows(Exception.class, () -> service.getAllVisualInspections());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(client).getAllVisualInspections();
    }

    @Test
    @DisplayName("Deve buscar uma inspeção visual pelo id")
    void testGetById() {
        when(client.getVisualInspectionById(outputDTO.getId().toString())).thenReturn(outputDTO);

        var result = assertDoesNotThrow(() -> service.getVisualInspectionById(outputDTO.getId().toString()));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getClarity(), result.getClarity());
        assertEquals(outputDTO.getBrightness(), result.getBrightness());
        assertEquals(outputDTO.getViscosity(), result.getViscosity());
        assertEquals(outputDTO.getColorRed(), result.getColorRed());
        assertEquals(outputDTO.getColorWhite(), result.getColorWhite());
        assertEquals(outputDTO.getColorRose(), result.getColorRose());
        assertEquals(outputDTO.getClassification(), result.getClassification());
        verify(client).getVisualInspectionById(outputDTO.getId().toString());
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao buscar uma inspeção visual pelo id")
    void testGetByIdThrowNotFoundException() {
        when(client.getVisualInspectionById(outputDTO.getId().toString())).thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));

        Exception exception = assertThrows(Exception.class, () -> service.getVisualInspectionById(outputDTO.getId().toString()));
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(client).getVisualInspectionById(outputDTO.getId().toString());
    }

    @Test
    @DisplayName("Deve atualizar uma inspeção visual")
    void testUpdate() {
        when(client.updateVisualInspection(outputDTO.getId().toString(), inputDTO)).thenReturn(outputDTO);

        var result = assertDoesNotThrow(() -> service.updateVisualInspection(outputDTO.getId().toString(), inputDTO));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getClarity(), result.getClarity());
        assertEquals(outputDTO.getBrightness(), result.getBrightness());
        assertEquals(outputDTO.getViscosity(), result.getViscosity());
        assertEquals(outputDTO.getColorRed(), result.getColorRed());
        assertEquals(outputDTO.getColorWhite(), result.getColorWhite());
        assertEquals(outputDTO.getColorRose(), result.getColorRose());
        assertEquals(outputDTO.getClassification(), result.getClassification());
        verify(client).updateVisualInspection(outputDTO.getId().toString(), inputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar uma inspeção visual")
    void testUpdateThrowBadRequestException() {
        when(client.updateVisualInspection(outputDTO.getId().toString(), inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        Exception exception = assertThrows(Exception.class, () -> service.updateVisualInspection(outputDTO.getId().toString(), inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(client).updateVisualInspection(outputDTO.getId().toString(), inputDTO);
    }

    @Test
    @DisplayName("Deve deletar uma inspeção visual")
    void testDelete() {
        assertDoesNotThrow(() -> service.deleteVisualInspection(outputDTO.getId().toString()));
        verify(client).deleteVisualInspection(outputDTO.getId().toString());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar uma inspeção visual")
    void testDeleteThrowBadRequestException() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING)).when(client).deleteVisualInspection(outputDTO.getId().toString());

        Exception exception = assertThrows(Exception.class, () -> service.deleteVisualInspection(outputDTO.getId().toString()));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(client).deleteVisualInspection(outputDTO.getId().toString());
    }

    private VisualInspectionInputDTO createVisualInspectionInputDTO() {
        return VisualInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .clarity(EnumClarityType.VERY_CLEAR.getCode())
                .brightness(EnumBrightnessType.VERY_BRIGHT.getCode())
                .viscosity(EnumViscosityType.SLIPPERY.getCode())
                .colorRed(EnumRedColorType.RUBY.getCode())
                .colorWhite(EnumWhiteColorType.STRAW_YELLOW.getCode())
                .colorRose(EnumRoseColorType.BROWN.getCode())
                .classification(EnumClassificationType.LITTLE.getCode())
                .build();
    }

    private VisualInspectionOutputDTO createVisualInspectionOutputDTO() {
        return VisualInspectionOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .clarity(EnumClarityType.VERY_CLEAR)
                .brightness(EnumBrightnessType.VERY_BRIGHT)
                .viscosity(EnumViscosityType.SLIPPERY)
                .colorRed(EnumRedColorType.RUBY)
                .colorWhite(EnumWhiteColorType.STRAW_YELLOW)
                .colorRose(EnumRoseColorType.BROWN)
                .classification(EnumClassificationType.LITTLE)
                .build();
    }

}
