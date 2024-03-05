package com.vinhonotas.bff.application.services.avaliacao.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.avaliacao.PointScaleClient;
import com.vinhonotas.bff.domain.enums.EnumPointScale;
import com.vinhonotas.bff.interfaces.dtos.inputs.avaliacao.PointScaleInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.avaliacao.PointScaleOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointScaleServiceImplTest {

    @InjectMocks
    private PointScaleServiceImpl pointScaleServiceImpl;

    @Mock
    private PointScaleClient pointScaleClient;

    private PointScaleInputDTO pointScaleInputDTO;
    private PointScaleOutputDTO pointScaleOutputDTO;

    @BeforeEach
    void setUp() {
        pointScaleInputDTO = createPointScaleInputDTO();
        pointScaleOutputDTO = createPointScaleOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma avaliação")
    void testCreatePointScale() {
        when(pointScaleClient.createPointScale(pointScaleInputDTO)).thenReturn(pointScaleOutputDTO);

        PointScaleOutputDTO pointScale = assertDoesNotThrow(() -> pointScaleServiceImpl.createPointScale(pointScaleInputDTO));
        assertNotNull(pointScale);
        assertEquals(pointScaleOutputDTO.getId(), pointScale.getId());
        assertEquals(pointScaleOutputDTO.getWhatTasted(), pointScale.getWhatTasted());
        assertEquals(pointScaleOutputDTO.getWhenTasted(), pointScale.getWhenTasted());
        assertEquals(pointScaleOutputDTO.getWhatSaw(), pointScale.getWhatSaw());
        assertEquals(pointScaleOutputDTO.getWhatAromas(), pointScale.getWhatAromas());
        assertEquals(pointScaleOutputDTO.getWhatFlavors(), pointScale.getWhatFlavors());
        assertEquals(pointScaleOutputDTO.getWhatOpinion(), pointScale.getWhatOpinion());
        assertEquals(pointScaleOutputDTO.getPointScale(), pointScale.getPointScale());
        verify(pointScaleClient).createPointScale(pointScaleInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar uma avaliação")
    void testCreatePointScaleThrowBadRequestException() {
        when(pointScaleClient.createPointScale(pointScaleInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        Exception exception = assertThrows(Exception.class, () -> pointScaleServiceImpl.createPointScale(pointScaleInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(pointScaleClient).createPointScale(pointScaleInputDTO);
    }

    @Test
    @DisplayName("Deve retornar uma lista de avaliações")
    void testGetAllPointScale() {
        when(pointScaleClient.getAllPointScale()).thenReturn(List.of(pointScaleOutputDTO));

        List<PointScaleOutputDTO> pointScales = assertDoesNotThrow(() -> pointScaleServiceImpl.getAllPointScale());
        assertNotNull(pointScales);
        assertEquals(1, pointScales.size());
        assertEquals(pointScaleOutputDTO.getId(), pointScales.get(0).getId());
        assertEquals(pointScaleOutputDTO.getWhatTasted(), pointScales.get(0).getWhatTasted());
        assertEquals(pointScaleOutputDTO.getWhenTasted(), pointScales.get(0).getWhenTasted());
        assertEquals(pointScaleOutputDTO.getWhatSaw(), pointScales.get(0).getWhatSaw());
        assertEquals(pointScaleOutputDTO.getWhatAromas(), pointScales.get(0).getWhatAromas());
        assertEquals(pointScaleOutputDTO.getWhatFlavors(), pointScales.get(0).getWhatFlavors());
        assertEquals(pointScaleOutputDTO.getWhatOpinion(), pointScales.get(0).getWhatOpinion());
        assertEquals(pointScaleOutputDTO.getPointScale(), pointScales.get(0).getPointScale());
        verify(pointScaleClient).getAllPointScale();
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao retornar uma lista de avaliações")
    void testGetAllPointScaleThrowNotFoundException() {
        when(pointScaleClient.getAllPointScale()).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> pointScaleServiceImpl.getAllPointScale());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(pointScaleClient).getAllPointScale();
    }

    @Test
    @DisplayName("Deve retornar uma avaliação pelo id")
    void testGetPointScaleById() {
        when(pointScaleClient.getPointScaleById("ea1cd995-e8d4-4cb7-b446-ca1a233aacba")).thenReturn(pointScaleOutputDTO);

        PointScaleOutputDTO pointScale = assertDoesNotThrow(() -> pointScaleServiceImpl.getPointScaleById("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"));
        assertNotNull(pointScale);
        assertEquals(pointScaleOutputDTO.getId(), pointScale.getId());
        assertEquals(pointScaleOutputDTO.getWhatTasted(), pointScale.getWhatTasted());
        assertEquals(pointScaleOutputDTO.getWhenTasted(), pointScale.getWhenTasted());
        assertEquals(pointScaleOutputDTO.getWhatSaw(), pointScale.getWhatSaw());
        assertEquals(pointScaleOutputDTO.getWhatAromas(), pointScale.getWhatAromas());
        assertEquals(pointScaleOutputDTO.getWhatFlavors(), pointScale.getWhatFlavors());
        assertEquals(pointScaleOutputDTO.getWhatOpinion(), pointScale.getWhatOpinion());
        assertEquals(pointScaleOutputDTO.getPointScale(), pointScale.getPointScale());
        verify(pointScaleClient).getPointScaleById("ea1cd995-e8d4-4cb7-b446-ca1a233aacba");
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao retornar uma avaliação pelo id")
    void testGetPointScaleByIdThrowNotFoundException() {
        when(pointScaleClient.getPointScaleById("ea1cd995-e8d4-4cb7-b446-ca1a233aacba")).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> pointScaleServiceImpl
                .getPointScaleById("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"));

        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(pointScaleClient).getPointScaleById("ea1cd995-e8d4-4cb7-b446-ca1a233aacba");
    }

    @Test
    @DisplayName("Deve atualizar uma avaliação pelo id")
    void testUpdatePointScale() {
        when(pointScaleClient.updatePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba", pointScaleInputDTO)).thenReturn(pointScaleOutputDTO);

        PointScaleOutputDTO pointScale = assertDoesNotThrow(() -> pointScaleServiceImpl.updatePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba", pointScaleInputDTO));
        assertNotNull(pointScale);
        assertEquals(pointScaleOutputDTO.getId(), pointScale.getId());
        assertEquals(pointScaleOutputDTO.getWhatTasted(), pointScale.getWhatTasted());
        assertEquals(pointScaleOutputDTO.getWhenTasted(), pointScale.getWhenTasted());
        assertEquals(pointScaleOutputDTO.getWhatSaw(), pointScale.getWhatSaw());
        assertEquals(pointScaleOutputDTO.getWhatAromas(), pointScale.getWhatAromas());
        assertEquals(pointScaleOutputDTO.getWhatFlavors(), pointScale.getWhatFlavors());
        assertEquals(pointScaleOutputDTO.getWhatOpinion(), pointScale.getWhatOpinion());
        assertEquals(pointScaleOutputDTO.getPointScale(), pointScale.getPointScale());
        verify(pointScaleClient).updatePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba", pointScaleInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar uma avaliação pelo id")
    void testUpdatePointScaleThrowBadRequestException() {
        when(pointScaleClient.updatePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba", pointScaleInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        Exception exception = assertThrows(Exception.class, () -> pointScaleServiceImpl
                .updatePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba", pointScaleInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(pointScaleClient).updatePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba", pointScaleInputDTO);
    }

    @Test
    @DisplayName("Deve deletar uma avaliação pelo id")
    void testDeletePointScale() {
        assertDoesNotThrow(() -> pointScaleServiceImpl.deletePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"));
        verify(pointScaleClient).deletePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar uma avaliação pelo id")
    void testDeletePointScaleThrowBadRequestException() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING)).when(pointScaleClient)
                .deletePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba");

        Exception exception = assertThrows(Exception.class, () -> pointScaleServiceImpl.deletePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(pointScaleClient).deletePointScale("ea1cd995-e8d4-4cb7-b446-ca1a233aacba");
    }

        private PointScaleInputDTO createPointScaleInputDTO() {
        return PointScaleInputDTO.builder()
                .whatTasted("Faustino Rivero Ulecia Reserva Rioja DOCa, safra 2018, vinho tinto, seco, " +
                        "produzido e engarrafado por vinícola Loma Negra, em Vale Central, Chile.")
                .whenTasted("Em 28/10/2023, às 21:30h.")
                .whatSaw("Coloração amarelo dourado.")
                .whatAromas("Aroma de pimentão vermelho maduro.")
                .whatFlavors("Na boca boa acidez, lembrando frutas cítricas.")
                .whatOpinion("Muito suculento com final longo.")
                .pointScale(EnumPointScale.OUTSTANDING)
                .build();
    }

    private PointScaleOutputDTO createPointScaleOutputDTO() {
        return PointScaleOutputDTO.builder()
                .id(UUID.fromString("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"))
                .whatTasted("Faustino Rivero Ulecia Reserva Rioja DOCa, safra 2018, vinho tinto, seco, " +
                        "produzido e engarrafado por vinícola Loma Negra, em Vale Central, Chile.")
                .whenTasted("Em 28/10/2023, às 21:30h.")
                .whatSaw("Coloração amarelo dourado.")
                .whatAromas("Aroma de pimentão vermelho maduro.")
                .whatFlavors("Na boca boa acidez, lembrando frutas cítricas.")
                .whatOpinion("Muito suculento com final longo.")
                .pointScale(EnumPointScale.OUTSTANDING)
                .build();
    }

}
