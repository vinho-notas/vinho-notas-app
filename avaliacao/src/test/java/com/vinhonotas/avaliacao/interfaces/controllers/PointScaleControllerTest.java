package com.vinhonotas.avaliacao.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.avaliacao.application.converters.PointScaleConverter;
import com.vinhonotas.avaliacao.application.services.PointScaleService;
import com.vinhonotas.avaliacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import com.vinhonotas.avaliacao.domain.enums.EnumPointScale;
import com.vinhonotas.avaliacao.interfaces.dtos.inputs.PointScaleInputDTO;
import com.vinhonotas.avaliacao.interfaces.dtos.outputs.PointScaleOutputDTO;
import com.vinhonotas.avaliacao.utils.MessagesConstants;
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
    @MockBean
    private PointScaleConverter pointScaleConverter;
    
    private PointScaleInputDTO pointScaleInputDTO;
    private PointScaleOutputDTO pointScaleOutputDTO;
    private PointScaleEntity pointScaleEntity;

    @BeforeEach
    void setUp() {
        pointScaleInputDTO = createPointScaleInputDTO();
        pointScaleOutputDTO = createPointScaleOutputDTO();
        pointScaleEntity = createPointScaleEntity();
    }

    @Test
    @DisplayName("Deve criar uma avaliação de vinho")
    void testCreatePointScale() throws Exception {
        when(pointScaleService.create(pointScaleInputDTO)).thenReturn(pointScaleEntity);
        when(pointScaleConverter.toOutputDTO(pointScaleEntity)).thenReturn(pointScaleOutputDTO);

        mockMvc.perform(post("/api/v1/point-scales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pointScaleInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"));
    }

    @Test
    @DisplayName("Deve retornar erro ao criar uma avaliação de vinho")
    void testCreatePointScaleError() throws Exception {
        when(pointScaleService.create(pointScaleInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_CREATE_POINT_SCALE));

        mockMvc.perform(post("/api/v1/point-scales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pointScaleInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de avaliações de vinho")
    void testGetAllPointScale() throws Exception {
        when(pointScaleService.getAll()).thenReturn(List.of(pointScaleEntity, pointScaleEntity));
        when(pointScaleConverter.toOutputDTOList(List.of(pointScaleEntity, pointScaleEntity))).thenReturn(List.of(pointScaleOutputDTO, pointScaleOutputDTO));

        mockMvc.perform(get("/api/v1/point-scales")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar uma lista de avaliações de vinho")
    void testGetAllPointScaleError() throws Exception {
        when(pointScaleService.getAll()).thenThrow(new BadRequestException(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND));

        mockMvc.perform(get("/api/v1/point-scales")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma avaliação de vinho pelo id")
    void testGetPointScaleById() throws Exception {
        when(pointScaleService.getById(UUID.fromString("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"))).thenReturn(pointScaleEntity);
        when(pointScaleConverter.toOutputDTO(pointScaleEntity)).thenReturn(pointScaleOutputDTO);

        mockMvc.perform(get("/api/v1/point-scales/{id}", "ea1cd995-e8d4-4cb7-b446-ca1a233aacba")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar uma avaliação de vinho pelo id")
    void testGetPointScaleByIdError() throws Exception {
        when(pointScaleService.getById(UUID.fromString("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"))).thenThrow(
                new BadRequestException(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND));

        mockMvc.perform(get("/api/v1/point-scales/{id}", "ea1cd995-e8d4-4cb7-b446-ca1a233aacba")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar uma avaliação de vinho pelo id")
    void testUpdatePointScale() throws Exception {
        pointScaleOutputDTO.setPointScale(EnumPointScale.NOTRECOMMENDED);

        when(pointScaleService.update(UUID.fromString("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"), pointScaleInputDTO)).thenReturn(pointScaleEntity);
        when(pointScaleConverter.toOutputDTO(pointScaleEntity)).thenReturn(pointScaleOutputDTO);
        when(pointScaleConverter.toOutputDTOUpdate(pointScaleEntity, UUID.fromString("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"),
                pointScaleOutputDTO)).thenReturn(pointScaleOutputDTO);

        mockMvc.perform(put("/api/v1/point-scales/{id}", "ea1cd995-e8d4-4cb7-b446-ca1a233aacba")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pointScaleInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"))
                .andExpect(jsonPath("$.pointScale").value(EnumPointScale.NOTRECOMMENDED.toString()));
    }

    @Test
    @DisplayName("Deve retornar erro ao atualizar uma avaliação de vinho pelo id")
    void testUpdatePointScaleError() throws Exception {
        when(pointScaleService.update(UUID.fromString("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"), pointScaleInputDTO)).thenThrow(
                new BadRequestException(MessagesConstants.ERROR_UPDATE_POINT_SCALE));

        mockMvc.perform(put("/api/v1/point-scales/{id}", "ea1cd995-e8d4-4cb7-b446-ca1a233aacba")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pointScaleInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma avaliação de vinho pelo id")
    void testDeletePointScale() throws Exception {
        mockMvc.perform(delete("/api/v1/point-scales/{id}", "ea1cd995-e8d4-4cb7-b446-ca1a233aacba")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar erro ao deletar uma avaliação de vinho pelo id")
    void testDeletePointScaleError() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_DELETE_POINT_SCALE)).when(pointScaleService).delete(
                UUID.fromString("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"));
        mockMvc.perform(delete("/api/v1/point-scales/{id}", "ea1cd995-e8d4-4cb7-b446-ca1a233aacba")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private PointScaleEntity createPointScaleEntity() {
        return PointScaleEntity.builder()
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
}
