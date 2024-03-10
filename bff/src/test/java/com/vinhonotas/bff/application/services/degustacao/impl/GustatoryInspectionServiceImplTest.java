package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.degustacao.GustatoryInspectionClient;
import com.vinhonotas.bff.domain.enums.*;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.GustatoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.GustatoryInspectionOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingCardOutputDTO;
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
class GustatoryInspectionServiceImplTest {
    
    @InjectMocks
    private GustatoryInspectionServiceImpl service;
    
    @Mock
    private GustatoryInspectionClient client;
    
    private GustatoryInspectionInputDTO inputDTO;
    private GustatoryInspectionOutputDTO outputDTO;
    
    @BeforeEach
    void setUp() {
        inputDTO = createGustatoryInspectionInputDTO();
        outputDTO = createGustatoryInspectionOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma inspeção gustativa")
    void testCreate() {
        when(client.createGustatoryInspection(inputDTO)).thenReturn(outputDTO);

        GustatoryInspectionOutputDTO result = assertDoesNotThrow(() -> service.createGustatoryInspection(inputDTO));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getBody(), result.getBody());
        assertEquals(outputDTO.getSweetness(), result.getSweetness());
        assertEquals(outputDTO.getTannin(), result.getTannin());
        assertEquals(outputDTO.getClassification(), result.getClassification());
        assertEquals(outputDTO.getAcidity(), result.getAcidity());
        assertEquals(outputDTO.getAlcohol(), result.getAlcohol());
        assertEquals(outputDTO.getPersistence(), result.getPersistence());
        assertEquals(outputDTO.getMaturity(), result.getMaturity());
        assertEquals(outputDTO.getTypicality(), result.getTypicality());
        assertEquals(outputDTO.getTastingCard(), result.getTastingCard());
        verify(client).createGustatoryInspection(inputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar uma inspeção gustativa")
    void testCreateThrowBadRequestException() {
        when(client.createGustatoryInspection(inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        Exception exception = assertThrows(Exception.class, () -> service.createGustatoryInspection(inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(client).createGustatoryInspection(inputDTO);
    }

    @Test
    @DisplayName("Deve listar todas as inspeções gustativas")
    void testGetAll() {
        when(client.getAllGustatoryInspections()).thenReturn(List.of(outputDTO));

        List<GustatoryInspectionOutputDTO> result = assertDoesNotThrow(() -> service.getAllGustatoryInspections());
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(outputDTO.getId(), result.get(0).getId());
        assertEquals(outputDTO.getTastingData(), result.get(0).getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.get(0).getWineTasted());
        assertEquals(outputDTO.getBody(), result.get(0).getBody());
        assertEquals(outputDTO.getSweetness(), result.get(0).getSweetness());
        assertEquals(outputDTO.getTannin(), result.get(0).getTannin());
        assertEquals(outputDTO.getClassification(), result.get(0).getClassification());
        assertEquals(outputDTO.getAcidity(), result.get(0).getAcidity());
        assertEquals(outputDTO.getAlcohol(), result.get(0).getAlcohol());
        assertEquals(outputDTO.getPersistence(), result.get(0).getPersistence());
        assertEquals(outputDTO.getMaturity(), result.get(0).getMaturity());
        assertEquals(outputDTO.getTypicality(), result.get(0).getTypicality());
        assertEquals(outputDTO.getTastingCard(), result.get(0).getTastingCard());
        verify(client).getAllGustatoryInspections();
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao listar todas as inspeções gustativas")
    void testGetAllThrowNotFoundException() {
        when(client.getAllGustatoryInspections()).thenReturn(List.of());

        Exception exception = assertThrows(Exception.class, () -> service.getAllGustatoryInspections());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(client).getAllGustatoryInspections();
    }

    @Test
    @DisplayName("Deve buscar uma inspeção gustativa pelo id")
    void testGetById() {
        when(client.getGustatoryInspectionById(outputDTO.getId().toString())).thenReturn(outputDTO);

        GustatoryInspectionOutputDTO result = assertDoesNotThrow(() -> service.getGustatoryInspectionById(outputDTO.getId().toString()));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getBody(), result.getBody());
        assertEquals(outputDTO.getSweetness(), result.getSweetness());
        assertEquals(outputDTO.getTannin(), result.getTannin());
        assertEquals(outputDTO.getClassification(), result.getClassification());
        assertEquals(outputDTO.getAcidity(), result.getAcidity());
        assertEquals(outputDTO.getAlcohol(), result.getAlcohol());
        assertEquals(outputDTO.getPersistence(), result.getPersistence());
        assertEquals(outputDTO.getMaturity(), result.getMaturity());
        assertEquals(outputDTO.getTypicality(), result.getTypicality());
        assertEquals(outputDTO.getTastingCard(), result.getTastingCard());
        verify(client).getGustatoryInspectionById(outputDTO.getId().toString());
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao buscar uma inspeção gustativa pelo id")
    void testGetByIdThrowNotFoundException() {
        when(client.getGustatoryInspectionById(outputDTO.getId().toString())).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> service.getGustatoryInspectionById(outputDTO.getId().toString()));
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(client).getGustatoryInspectionById(outputDTO.getId().toString());
    }

    @Test
    @DisplayName("Deve atualizar uma inspeção gustativa")
    void testUpdate() {
        when(client.updateGustatoryInspection(outputDTO.getId().toString(), inputDTO)).thenReturn(outputDTO);

        GustatoryInspectionOutputDTO result = assertDoesNotThrow(() -> service.updateGustatoryInspection(outputDTO.getId().toString(), inputDTO));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getBody(), result.getBody());
        assertEquals(outputDTO.getSweetness(), result.getSweetness());
        assertEquals(outputDTO.getTannin(), result.getTannin());
        assertEquals(outputDTO.getClassification(), result.getClassification());
        assertEquals(outputDTO.getAcidity(), result.getAcidity());
        assertEquals(outputDTO.getAlcohol(), result.getAlcohol());
        assertEquals(outputDTO.getPersistence(), result.getPersistence());
        assertEquals(outputDTO.getMaturity(), result.getMaturity());
        assertEquals(outputDTO.getTypicality(), result.getTypicality());
        assertEquals(outputDTO.getTastingCard(), result.getTastingCard());
        verify(client).updateGustatoryInspection(outputDTO.getId().toString(), inputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar uma inspeção gustativa")
    void testUpdateThrowBadRequestException() {
        when(client.updateGustatoryInspection(outputDTO.getId().toString(), inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        Exception exception = assertThrows(Exception.class, () -> service.updateGustatoryInspection(outputDTO.getId().toString(), inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(client).updateGustatoryInspection(outputDTO.getId().toString(), inputDTO);
    }

    @Test
    @DisplayName("Deve deletar uma inspeção gustativa")
    void testDelete() {
        assertDoesNotThrow(() -> service.deleteGustatoryInspection(outputDTO.getId().toString()));
        verify(client).deleteGustatoryInspection(outputDTO.getId().toString());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar uma inspeção gustativa")
    void testDeleteThrowBadRequestException() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
                .when(client).deleteGustatoryInspection(outputDTO.getId().toString());

        Exception exception = assertThrows(Exception.class, () -> service.deleteGustatoryInspection(outputDTO.getId().toString()));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(client).deleteGustatoryInspection(outputDTO.getId().toString());
    }

    private GustatoryInspectionInputDTO createGustatoryInspectionInputDTO() {
        return GustatoryInspectionInputDTO.builder()
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

    private GustatoryInspectionOutputDTO createGustatoryInspectionOutputDTO() {
        return GustatoryInspectionOutputDTO.builder()
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
                .tastingCard(Mockito.mock(TastingCardOutputDTO.class))
                .build();
    }

}
