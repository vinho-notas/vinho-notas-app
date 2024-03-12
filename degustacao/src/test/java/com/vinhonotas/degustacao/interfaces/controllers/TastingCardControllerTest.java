package com.vinhonotas.degustacao.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.degustacao.application.converters.TastingCardConverter;
import com.vinhonotas.degustacao.application.services.TastingCardService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.*;
import com.vinhonotas.degustacao.domain.enums.EnumPointScale;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingCardOutputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TastingCardController.class)
class TastingCardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TastingCardService tastingCardService;
    @MockBean
    private TastingCardConverter tastingCardConverter;

    private TastingCardEntity entity;
    private TastingCardInputDTO inputDTO;
    private TastingCardOutputDTO outputDTO;

    @BeforeEach
    void setUp() {
        entity = createTastingCardEntity();
        inputDTO = createTastingCardInputDTO();
        outputDTO = createTastingCardOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma ficha de degustação")
    void testCreateTastingCard() throws Exception {
        when(tastingCardService.create(inputDTO)).thenReturn(entity);
        when(tastingCardConverter.toOutputDTO(entity)).thenReturn(outputDTO);

        mockMvc.perform(post("/api/v1/tasting-card")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve retornar erro ao criar uma ficha de degustação")
    void testCreateTastingCardError() throws Exception {
        when(tastingCardService.create(inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_TASTING_CARD));

        mockMvc.perform(post("/api/v1/tasting-card")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todas as fichas de degustação cadastradas")
    void testGetAllTastingCards() throws Exception {
        when(tastingCardService.getAll()).thenReturn(List.of(entity));
        when(tastingCardConverter.toOutputDTOList(List.of(entity))).thenReturn(List.of(outputDTO));

        mockMvc.perform(get("/api/v1/tasting-card")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(outputDTO))));
    }

    @Test
    @DisplayName("Deve lançar erro ao retornar uma lista com todas as fichas de degustação cadastradas")
    void testGetAllTastingCardsError() throws Exception {
        when(tastingCardService.getAll()).thenThrow(new BadRequestException(MessagesConstants.TASTING_CARD_NOT_FOUND));

        mockMvc.perform(get("/api/v1/tasting-card")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma ficha de degustação cadastrada pelo id")
    void testGetTastingCardById() throws Exception {
        when(tastingCardService.getById(entity.getId())).thenReturn(entity);
        when(tastingCardConverter.toOutputDTO(entity)).thenReturn(outputDTO);

        mockMvc.perform(get("/api/v1/tasting-card/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar erro ao retornar uma ficha de degustação cadastrada pelo id")
    void testGetTastingCardByIdError() throws Exception {
        when(tastingCardService.getById(entity.getId())).thenThrow(new BadRequestException(MessagesConstants.TASTING_CARD_NOT_FOUND));

        mockMvc.perform(get("/api/v1/tasting-card/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar uma ficha de degustação cadastrada pelo id")
    void testUpdateTastingCard() throws Exception {
        when(tastingCardService.update(entity.getId(), inputDTO)).thenReturn(entity);
        when(tastingCardConverter.toOutputDTOUpdate(
                tastingCardService.update(entity.getId(), inputDTO),
                entity.getId(),
                tastingCardConverter.toOutputDTO(tastingCardService.update(entity.getId(), inputDTO))))
                .thenReturn(outputDTO);

        mockMvc.perform(put("/api/v1/tasting-card/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar erro ao atualizar uma ficha de degustação cadastrada pelo id")
    void testUpdateTastingCardError() throws Exception {
        when(tastingCardService.update(entity.getId(), inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_TASTING_CARD));

        mockMvc.perform(put("/api/v1/tasting-card/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma ficha de degustação cadastrada pelo id")
    void testDeleteTastingCard() throws Exception {
        mockMvc.perform(delete("/api/v1/tasting-card/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar erro ao deletar uma ficha de degustação cadastrada pelo id")
    void testDeleteTastingCardError() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_TASTING_CARD)).when(tastingCardService).delete(entity.getId());

        mockMvc.perform(delete("/api/v1/tasting-card/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
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
                .visualInspection(Mockito.mock(VisualInspectionEntity.class))
                .olfactoryInspection(Mockito.mock(OlfactoryInspectionEntity.class))
                .gustatoryInspection(Mockito.mock(GustatoryInspectionEntity.class))
                .opinion("Opinion about the wine")
                .pointScale(EnumPointScale.CLASSIC)
                .tasting(Mockito.mock(TastingEntity.class))
                .build();
    }

    private TastingCardInputDTO createTastingCardInputDTO() {
        return TastingCardInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .harvest("2020")
                .grapes("Grapes")
                .country("Chile")
                .region("Vale Central")
                .visualInspection(VisualInspectionInputDTO.builder().build())
                .olfactoryInspection(OlfactoryInspectionInputDTO.builder().build())
                .gustatoryInspection(GustatoryInspectionInputDTO.builder().build())
                .opinion("Opinion about the wine")
                .pointScale(EnumPointScale.CLASSIC.getCode())
                .build();
    }

    private TastingCardEntity createTastingCardEntity() {
        return TastingCardEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .harvest("2020")
                .grapes("Grapes")
                .country("Chile")
                .region("Vale Central")
                .visualInspection(Mockito.mock(VisualInspectionEntity.class))
                .olfactoryInspection(Mockito.mock(OlfactoryInspectionEntity.class))
                .gustatoryInspection(Mockito.mock(GustatoryInspectionEntity.class))
                .opinion("Opinion about the wine")
                .pointScale(EnumPointScale.CLASSIC)
                .tasting(Mockito.mock(TastingEntity.class))
                .build();
    }

}
