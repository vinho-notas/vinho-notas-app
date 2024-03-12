package com.vinhonotas.bff.interfaces.controllers.avaliacao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.avaliacao.PointScaleService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.domain.enums.EnumPointScale;
import com.vinhonotas.bff.interfaces.dtos.inputs.avaliacao.PointScaleInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.avaliacao.PointScaleOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PointScaleController.class)
class PointScaleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PointScaleService pointScaleService;

    private final String URL = "/api/v1/point-scales";
    private PointScaleInputDTO pointScaleInputDTO;
    private PointScaleOutputDTO pointScaleOutputDTO;

    @BeforeEach
    void setUp() {
        pointScaleInputDTO = createPointScaleInputDTO();
        pointScaleOutputDTO = createPointScaleOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma avaliação de vinho")
    void testCreatePointScale() throws Exception {
        when(pointScaleService.createPointScale(pointScaleInputDTO)).thenReturn(pointScaleOutputDTO);

        mockMvc.perform(post(URL)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(pointScaleInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pointScaleOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.whatTasted").value(pointScaleOutputDTO.getWhatTasted()))
                .andExpect(jsonPath("$.whenTasted").value(pointScaleOutputDTO.getWhenTasted()))
                .andExpect(jsonPath("$.whatSaw").value(pointScaleOutputDTO.getWhatSaw()))
                .andExpect(jsonPath("$.whatAromas").value(pointScaleOutputDTO.getWhatAromas()))
                .andExpect(jsonPath("$.whatFlavors").value(pointScaleOutputDTO.getWhatFlavors()))
                .andExpect(jsonPath("$.whatOpinion").value(pointScaleOutputDTO.getWhatOpinion()))
                .andExpect(jsonPath("$.pointScale").value(EnumPointScale.OUTSTANDING.name()));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao criar uma avaliação de vinho")
    void testCreatePointScaleException() throws Exception {
        when(pointScaleService.createPointScale(pointScaleInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pointScaleInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar todas as avaliações de vinho")
    void testGetAllPointScale() throws Exception {
        when(pointScaleService.getAllPointScale()).thenReturn(List.of(pointScaleOutputDTO));

        mockMvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(pointScaleOutputDTO.getId().toString()))
                .andExpect(jsonPath("$[0].whatTasted").value(pointScaleOutputDTO.getWhatTasted()))
                .andExpect(jsonPath("$[0].whenTasted").value(pointScaleOutputDTO.getWhenTasted()))
                .andExpect(jsonPath("$[0].whatSaw").value(pointScaleOutputDTO.getWhatSaw()))
                .andExpect(jsonPath("$[0].whatAromas").value(pointScaleOutputDTO.getWhatAromas()))
                .andExpect(jsonPath("$[0].whatFlavors").value(pointScaleOutputDTO.getWhatFlavors()))
                .andExpect(jsonPath("$[0].whatOpinion").value(pointScaleOutputDTO.getWhatOpinion()))
                .andExpect(jsonPath("$[0].pointScale").value(EnumPointScale.OUTSTANDING.name()));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao retornar todas as avaliações de vinho")
    void testGetAllPointScaleException() throws Exception {
        when(pointScaleService.getAllPointScale()).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma avaliação de vinho pelo id")
    void testGetPointScaleById() throws Exception {
        when(pointScaleService.getPointScaleById(pointScaleOutputDTO.getId().toString())).thenReturn(pointScaleOutputDTO);

        mockMvc.perform(get(URL + "/" + pointScaleOutputDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pointScaleOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.whatTasted").value(pointScaleOutputDTO.getWhatTasted()))
                .andExpect(jsonPath("$.whenTasted").value(pointScaleOutputDTO.getWhenTasted()))
                .andExpect(jsonPath("$.whatSaw").value(pointScaleOutputDTO.getWhatSaw()))
                .andExpect(jsonPath("$.whatAromas").value(pointScaleOutputDTO.getWhatAromas()))
                .andExpect(jsonPath("$.whatFlavors").value(pointScaleOutputDTO.getWhatFlavors()))
                .andExpect(jsonPath("$.whatOpinion").value(pointScaleOutputDTO.getWhatOpinion()))
                .andExpect(jsonPath("$.pointScale").value(EnumPointScale.OUTSTANDING.name()));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao retornar uma avaliação de vinho pelo id")
    void testGetPointScaleByIdException() throws Exception {
        when(pointScaleService.getPointScaleById(pointScaleOutputDTO.getId().toString()))
                .thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get(URL + "/" + pointScaleOutputDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar uma avaliação de vinho pelo id")
    void testUpdatePointScale() throws Exception {
        when(pointScaleService.updatePointScale(pointScaleOutputDTO.getId().toString(), pointScaleInputDTO))
                .thenReturn(pointScaleOutputDTO);

        mockMvc.perform(put(URL + "/" + pointScaleOutputDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pointScaleInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pointScaleOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.whatTasted").value(pointScaleOutputDTO.getWhatTasted()))
                .andExpect(jsonPath("$.whenTasted").value(pointScaleOutputDTO.getWhenTasted()))
                .andExpect(jsonPath("$.whatSaw").value(pointScaleOutputDTO.getWhatSaw()))
                .andExpect(jsonPath("$.whatAromas").value(pointScaleOutputDTO.getWhatAromas()))
                .andExpect(jsonPath("$.whatFlavors").value(pointScaleOutputDTO.getWhatFlavors()))
                .andExpect(jsonPath("$.whatOpinion").value(pointScaleOutputDTO.getWhatOpinion()))
                .andExpect(jsonPath("$.pointScale").value(EnumPointScale.OUTSTANDING.name()));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao atualizar uma avaliação de vinho pelo id")
    void testUpdatePointScaleException() throws Exception {
        when(pointScaleService.updatePointScale(pointScaleOutputDTO.getId().toString(), pointScaleInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        mockMvc.perform(put(URL + "/" + pointScaleOutputDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pointScaleInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma avaliação de vinho pelo id")
    void testDeletePointScale() throws Exception {
        mockMvc.perform(delete(URL + "/" + pointScaleOutputDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao deletar uma avaliação de vinho pelo id")
    void testDeletePointScaleException() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
                .when(pointScaleService).deletePointScale(pointScaleOutputDTO.getId().toString());

        mockMvc.perform(delete(URL + "/" + pointScaleOutputDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
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
                .pointScale(EnumPointScale.OUTSTANDING.getCode())
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
