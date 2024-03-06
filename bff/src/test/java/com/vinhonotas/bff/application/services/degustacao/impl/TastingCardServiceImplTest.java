package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.degustacao.TastingCardClient;
import com.vinhonotas.bff.domain.enums.EnumPointScale;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.GustatoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.OlfactoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingCardInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.VisualInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.GustatoryInspectionOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.OlfactoryInspectionOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingCardOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.VisualInspectionOutputDTO;
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
class TastingCardServiceImplTest {

    @InjectMocks
    private TastingCardServiceImpl service;

    @Mock
    private TastingCardClient client;

    private TastingCardInputDTO inputDTO;
    private TastingCardOutputDTO outputDTO;

    @BeforeEach
    void setUp() {
        inputDTO = createTastingCardInputDTO();
        outputDTO = createTastingCardOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma ficha de degustação")
    void testCreateTastingCard() {
        when(client.createTastingCard(inputDTO)).thenReturn(outputDTO);

        TastingCardOutputDTO result = assertDoesNotThrow(() -> service.createTastingCard(inputDTO));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getHarvest(), result.getHarvest());
        assertEquals(outputDTO.getGrapes(), result.getGrapes());
        assertEquals(outputDTO.getCountry(), result.getCountry());
        assertEquals(outputDTO.getRegion(), result.getRegion());
        assertEquals(outputDTO.getVisualInspection(), result.getVisualInspection());
        assertEquals(outputDTO.getOlfactoryInspection(), result.getOlfactoryInspection());
        assertEquals(outputDTO.getGustatoryInspection(), result.getGustatoryInspection());
        assertEquals(outputDTO.getOpinion(), result.getOpinion());
        assertEquals(outputDTO.getPointScale(), result.getPointScale());
        verify(client).createTastingCard(inputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar criar uma ficha de degustação")
    void testCreateTastingCardThrowBadRequestException() {
        when(client.createTastingCard(inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.createTastingCard(inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(client).createTastingCard(inputDTO);
    }

    @Test
    @DisplayName("Deve listar todas as fichas de degustação")
    void testGetAllTastingCards() {
        when(client.getAllTastingCards()).thenReturn(List.of(outputDTO));

        List<TastingCardOutputDTO> result = assertDoesNotThrow(() -> service.getAllTastingCards());
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(outputDTO.getId(), result.get(0).getId());
        assertEquals(outputDTO.getTastingData(), result.get(0).getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.get(0).getWineTasted());
        assertEquals(outputDTO.getHarvest(), result.get(0).getHarvest());
        assertEquals(outputDTO.getGrapes(), result.get(0).getGrapes());
        assertEquals(outputDTO.getCountry(), result.get(0).getCountry());
        assertEquals(outputDTO.getRegion(), result.get(0).getRegion());
        assertEquals(outputDTO.getVisualInspection(), result.get(0).getVisualInspection());
        assertEquals(outputDTO.getOlfactoryInspection(), result.get(0).getOlfactoryInspection());
        assertEquals(outputDTO.getGustatoryInspection(), result.get(0).getGustatoryInspection());
        assertEquals(outputDTO.getOpinion(), result.get(0).getOpinion());
        assertEquals(outputDTO.getPointScale(), result.get(0).getPointScale());
        verify(client).getAllTastingCards();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar listar todas as fichas de degustação")
    void testGetAllTastingCardsThrowBadRequestException() {
        when(client.getAllTastingCards()).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.getAllTastingCards());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(client).getAllTastingCards();
    }

    @Test
    @DisplayName("Deve buscar uma ficha de degustação pelo id")
    void testGetTastingCardById() {
        when(client.getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")).thenReturn(outputDTO);

        TastingCardOutputDTO result = assertDoesNotThrow(() -> service.getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getHarvest(), result.getHarvest());
        assertEquals(outputDTO.getGrapes(), result.getGrapes());
        assertEquals(outputDTO.getCountry(), result.getCountry());
        assertEquals(outputDTO.getRegion(), result.getRegion());
        assertEquals(outputDTO.getVisualInspection(), result.getVisualInspection());
        assertEquals(outputDTO.getOlfactoryInspection(), result.getOlfactoryInspection());
        assertEquals(outputDTO.getGustatoryInspection(), result.getGustatoryInspection());
        assertEquals(outputDTO.getOpinion(), result.getOpinion());
        assertEquals(outputDTO.getPointScale(), result.getPointScale());
        verify(client).getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar buscar uma ficha de degustação pelo id")
    void testGetTastingCardByIdThrowBadRequestException() {
        when(client.getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(client).getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve atualizar uma ficha de degustação pelo id")
    void testUpdateTastingCard() {
        when(client.updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO)).thenReturn(outputDTO);

        TastingCardOutputDTO result = assertDoesNotThrow(() -> service.updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getHarvest(), result.getHarvest());
        assertEquals(outputDTO.getGrapes(), result.getGrapes());
        assertEquals(outputDTO.getCountry(), result.getCountry());
        assertEquals(outputDTO.getRegion(), result.getRegion());
        assertEquals(outputDTO.getVisualInspection(), result.getVisualInspection());
        assertEquals(outputDTO.getOlfactoryInspection(), result.getOlfactoryInspection());
        assertEquals(outputDTO.getGustatoryInspection(), result.getGustatoryInspection());
        assertEquals(outputDTO.getOpinion(), result.getOpinion());
        assertEquals(outputDTO.getPointScale(), result.getPointScale());
        verify(client).updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar atualizar uma ficha de degustação pelo id")
    void testUpdateTastingCardThrowBadRequestException() {
        when(client.updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(client).updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO);
    }

    @Test
    @DisplayName("Deve deletar uma ficha de degustação pelo id")
    void testDeleteTastingCard() {
        assertDoesNotThrow(() -> service.deleteTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        verify(client).deleteTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar deletar uma ficha de degustação pelo id")
    void testDeleteTastingCardThrowBadRequestException() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING)).when(client).deleteTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.deleteTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(client).deleteTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    private TastingCardInputDTO createTastingCardInputDTO() {
        return TastingCardInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .harvest("2020")
                .grapes("Grapes")
                .country("Chile")
                .region("Vale Central")
                .visualInspection(Mockito.mock(VisualInspectionInputDTO.class))
                .olfactoryInspection(Mockito.mock(OlfactoryInspectionInputDTO.class))
                .gustatoryInspection(Mockito.mock(GustatoryInspectionInputDTO.class))
                .opinion("Opinion about the wine")
                .pointScale(EnumPointScale.CLASSIC)
                .build();
    }

    private TastingCardOutputDTO createTastingCardOutputDTO() {
        return TastingCardOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .harvest("2020")
                .grapes("Grapes")
                .country("Chile")
                .region("Vale Central")
                .visualInspection(Mockito.mock(VisualInspectionOutputDTO.class))
                .olfactoryInspection(Mockito.mock(OlfactoryInspectionOutputDTO.class))
                .gustatoryInspection(Mockito.mock(GustatoryInspectionOutputDTO.class))
                .opinion("Opinion about the wine")
                .pointScale(EnumPointScale.CLASSIC)
                .build();
    }

}