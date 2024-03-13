package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.degustacao.TastingClient;
import com.vinhonotas.bff.domain.enums.EnumTastingType;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingCardInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingCardOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingOutputDTO;
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
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TastingServiceImplTest {

    @InjectMocks
    private TastingServiceImpl tastingService;

    @Mock
    private TastingClient tastingClient;

    private TastingInputDTO tastingInputDTO;
    private TastingOutputDTO tastingOutputDTO;

    @BeforeEach
    void setUp() {
        tastingInputDTO = createTastingInputDTO();
        tastingOutputDTO = createTastingOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma degustação")
    void testCreateTasting() {
        when(tastingClient.createTasting(tastingInputDTO)).thenReturn(tastingOutputDTO);

        TastingOutputDTO result = assertDoesNotThrow(() -> tastingService.createTasting(tastingInputDTO));
        assertNotNull(result);
        assertEquals(tastingOutputDTO.getId(), result.getId());
        assertEquals(tastingOutputDTO.getTastingData(), result.getTastingData());
        assertEquals(tastingOutputDTO.getTastingType(), result.getTastingType());
        assertEquals(tastingOutputDTO.getTastingCards(), result.getTastingCards());
        verify(tastingClient).createTasting(tastingInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar uma degustação")
    void testCreateTastingThrowBadRequestException() {
        when(tastingClient.createTasting(tastingInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        Exception exception = assertThrows(Exception.class, () -> tastingService.createTasting(tastingInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(tastingClient).createTasting(tastingInputDTO);
    }

    @Test
    @DisplayName("Deve listar todas as degustações")
    void testGetAllTastings() {
        when(tastingClient.getAllTastings()).thenReturn(List.of(tastingOutputDTO));

        List<TastingOutputDTO> list = assertDoesNotThrow(() -> tastingService.getAllTastings());
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(tastingOutputDTO.getId(), list.get(0).getId());
        assertEquals(tastingOutputDTO.getTastingData(), list.get(0).getTastingData());
        assertEquals(tastingOutputDTO.getTastingType(), list.get(0).getTastingType());
        assertEquals(tastingOutputDTO.getTastingCards(), list.get(0).getTastingCards());
        verify(tastingClient).getAllTastings();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao listar todas as degustações")
    void testGetAllTastingsThrowBadRequestException() {
        when(tastingClient.getAllTastings()).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        Exception exception = assertThrows(Exception.class, () -> tastingService.getAllTastings());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(tastingClient).getAllTastings();
    }

    @Test
    @DisplayName("Deve buscar uma degustação pelo id")
    void testGetTastingById() {
        when(tastingClient.getTastingById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")).thenReturn(tastingOutputDTO);

        TastingOutputDTO result = assertDoesNotThrow(() -> tastingService.getTastingById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertNotNull(result);
        assertEquals("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", result.getId().toString());
        assertEquals(tastingOutputDTO.getTastingData(), result.getTastingData());
        assertEquals(tastingOutputDTO.getTastingType(), result.getTastingType());
        assertEquals(tastingOutputDTO.getTastingCards(), result.getTastingCards());
        verify(tastingClient).getTastingById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao buscar uma degustação pelo id")
    void testGetTastingByIdThrowBadRequestException() {
        when(tastingClient.getTastingById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        Exception exception = assertThrows(Exception.class, () -> tastingService.getTastingById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(tastingClient).getTastingById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve atualizar uma degustação")
    void testUpdateTasting() {
        when(tastingClient.updateTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", tastingInputDTO)).thenReturn(tastingOutputDTO);

        TastingOutputDTO result = assertDoesNotThrow(() -> tastingService.updateTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", tastingInputDTO));
        assertNotNull(result);
        assertEquals("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", result.getId().toString());
        assertEquals(tastingOutputDTO.getTastingData(), result.getTastingData());
        assertEquals(tastingOutputDTO.getTastingType(), result.getTastingType());
        assertEquals(tastingOutputDTO.getTastingCards(), result.getTastingCards());
        verify(tastingClient).updateTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", tastingInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar uma degustação")
    void testUpdateTastingThrowBadRequestException() {
        when(tastingClient.updateTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", tastingInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        Exception exception = assertThrows(Exception.class, () -> tastingService.updateTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", tastingInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(tastingClient).updateTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", tastingInputDTO);
    }

    @Test
    @DisplayName("Deve deletar uma degustação")
    void testDeleteTasting() {
        assertDoesNotThrow(() -> tastingService.deleteTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));

        verify(tastingClient).deleteTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar uma degustação")
    void testDeleteTastingThrowBadRequestException() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
                .when(tastingClient).deleteTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");

        Exception exception = assertThrows(Exception.class, () -> tastingService.deleteTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(tastingClient).deleteTasting("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    private TastingInputDTO createTastingInputDTO() {
        return TastingInputDTO.builder()
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE.getCode())
                .tastingCards(Set.of(Mockito.mock(TastingCardInputDTO.class)))
                .build();
    }

    private TastingOutputDTO createTastingOutputDTO() {
        return TastingOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE.getCode())
                .tastingCards(Set.of(Mockito.mock(TastingCardOutputDTO.class)))
                .build();
    }

}
