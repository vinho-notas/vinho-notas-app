package com.vinhonotas.degustacao.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.degustacao.application.converters.VisualInspectionConverter;
import com.vinhonotas.degustacao.application.services.VisualInspectionService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.VisualInspectionOutputDTO;
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


@WebMvcTest(controllers = VisualInspectionController.class)
class VisualInspectionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VisualInspectionService visualInspectionService;
    @MockBean
    private VisualInspectionConverter visualInspectionConverter;

    private VisualInspectionEntity entity;
    private VisualInspectionInputDTO inputDTO;
    private VisualInspectionOutputDTO outputDTO;

    @BeforeEach
    void setUp() {
        entity = createVisualInspectionEntity();
        inputDTO = createVisualInspectionInputDTO();
        outputDTO = createVisualInspectionOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma percepção visual")
    void testCreateVisualInspection() throws Exception {
        when(visualInspectionService.create(inputDTO)).thenReturn(entity);
        when(visualInspectionConverter.toOutputDTO(entity)).thenReturn(outputDTO);

        mockMvc.perform(post("/api/v1/visual-inspection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar uma percepção visual")
    void testCreateVisualInspectionBadRequestException() throws Exception {
        when(visualInspectionService.create(inputDTO)).thenThrow(new BadRequestException(
                MessagesConstants.ERROR_WHEN_SAVING_VISUAL_INSPECTION));

        mockMvc.perform(post("/api/v1/visual-inspection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todas as percepções visuais cadastradas")
    void testGetAllVisualInspections() throws Exception {
        when(visualInspectionService.getAll()).thenReturn(List.of(entity));
        when(visualInspectionConverter.toOutputDTOList(List.of(entity))).thenReturn(List.of(outputDTO));

        mockMvc.perform(get("/api/v1/visual-inspection")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(List.of(outputDTO))));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma lista com todas as percepções visuais cadastradas")
    void testGetAllVisualInspectionsBadRequestException() throws Exception {
        when(visualInspectionService.getAll()).thenThrow(new BadRequestException(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND));

        mockMvc.perform(get("/api/v1/visual-inspection")
                .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma percepção visual cadastrada pelo id")
    void testGetVisualInspectionById() throws Exception {
        when(visualInspectionService.getById(entity.getId())).thenReturn(entity);
        when(visualInspectionConverter.toOutputDTO(entity)).thenReturn(outputDTO);

        mockMvc.perform(get("/api/v1/visual-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma percepção visual cadastrada pelo id")
    void testGetVisualInspectionByIdBadRequestException() throws Exception {
        when(visualInspectionService.getById(entity.getId())).thenThrow(new BadRequestException(MessagesConstants.VISUAL_INSPECTION_NOT_FOUND));

        mockMvc.perform(get("/api/v1/visual-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar uma percepção visual cadastrada pelo id")
    void testUpdateVisualInspection() throws Exception {
        when(visualInspectionService.update(entity.getId(), inputDTO)).thenReturn(entity);
        when(visualInspectionConverter.toOutputDTOUpdate(
                visualInspectionService.update(entity.getId(), inputDTO),
                entity.getId(),
                visualInspectionConverter.toOutputDTO(visualInspectionService.update(entity.getId(), inputDTO))))
                .thenReturn(outputDTO);

        mockMvc.perform(put("/api/v1/visual-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar uma percepção visual cadastrada pelo id")
    void testUpdateVisualInspectionBadRequestException() throws Exception {
        when(visualInspectionService.update(entity.getId(), inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_VISUAL_INSPECTION));

        mockMvc.perform(put("/api/v1/visual-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma percepção visual cadastrada pelo id")
    void testDeleteVisualInspection() throws Exception {
        mockMvc.perform(delete("/api/v1/visual-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar uma percepção visual cadastrada pelo id")
    void testDeleteVisualInspectionBadRequestException() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_VISUAL_INSPECTION))
                .when(visualInspectionService).delete(entity.getId());

        mockMvc.perform(delete("/api/v1/visual-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
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
                .tastingCard(Mockito.mock(TastingCardEntity.class))
                .build();
    }

    private VisualInspectionInputDTO createVisualInspectionInputDTO() {
        return VisualInspectionInputDTO.builder()
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

    private VisualInspectionEntity createVisualInspectionEntity() {
        return VisualInspectionEntity.builder()
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
