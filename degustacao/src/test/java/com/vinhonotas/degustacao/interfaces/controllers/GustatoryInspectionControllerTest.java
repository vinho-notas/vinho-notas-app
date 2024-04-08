package com.vinhonotas.degustacao.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.degustacao.application.converters.GustatoryInspectionConverter;
import com.vinhonotas.degustacao.application.services.GustatoryInspectionService;
import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.GustatoryInspectionOutputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingCardOutputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

@WebMvcTest(controllers = GustatoryInspectionController.class)
class GustatoryInspectionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GustatoryInspectionService gustatoryInspectionService;
    @MockBean
    private GustatoryInspectionConverter gustatoryInspectionConverter;

    private GustatoryInspectionInputDTO inputDTO;
    private GustatoryInspectionOutputDTO outputDTO;
    private GustatoryInspectionEntity entity;

    @BeforeEach
    void setUp() {
        inputDTO = createGustatoryInspectionInputDTO();
        outputDTO = createGustatoryInspectionOutputDTO();
        entity = createGustatoryInspectionEntity();
    }

    @Test
    @DisplayName("Deve criar uma percepção gustativa")
    void testCreateGustatoryInspection() throws Exception {
        when(gustatoryInspectionService.create(inputDTO)).thenReturn(entity);
        when(gustatoryInspectionConverter.toOutputDTO(entity)).thenReturn(outputDTO);

        mockMvc.perform(post("/api/v1/gustatory-inspection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar uma percepção gustativa com dados inválidos")
    void testCreateGustatoryInspectionWithInvalidData() throws Exception {
        when(gustatoryInspectionService.create(inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_GUSTATORY_INSPECTION));

        mockMvc.perform(post("/api/v1/gustatory-inspection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todas as percepções gustativas cadastradas")
    void testGetAllGustatoryInspections() throws Exception {
        when(gustatoryInspectionService.getAll()).thenReturn(List.of(entity));
        when(gustatoryInspectionConverter.toOutputDTOList(List.of(entity))).thenReturn(List.of(outputDTO));

        mockMvc.perform(get("/api/v1/gustatory-inspection")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(outputDTO))));
    }

    @Test
    @DisplayName("Deve lançar exceção ao retornar uma lista com todas as percepções gustativas cadastradas")
    void testGetAllGustatoryInspectionsWithInvalidData() throws Exception {
        when(gustatoryInspectionService.getAll()).thenThrow(new BadRequestException(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND));

        mockMvc.perform(get("/api/v1/gustatory-inspection")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma percepção gustativa cadastrada pelo id")
    void testGetGustatoryInspectionById() throws Exception {
        when(gustatoryInspectionService.getById(entity.getId())).thenReturn(entity);
        when(gustatoryInspectionConverter.toOutputDTO(entity)).thenReturn(outputDTO);

        mockMvc.perform(get("/api/v1/gustatory-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar exceção ao retornar uma percepção gustativa cadastrada pelo id")
    void testGetGustatoryInspectionByIdWithInvalidData() throws Exception {
        when(gustatoryInspectionService.getById(entity.getId()))
                .thenThrow(new BadRequestException(MessagesConstants.GUSTATORY_INSPECTION_NOT_FOUND));

        mockMvc.perform(get("/api/v1/gustatory-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar uma percepção gustativa cadastrada pelo id")
    void testUpdateGustatoryInspection() throws Exception {
        when(gustatoryInspectionService.update(entity.getId(), inputDTO)).thenReturn(entity);
        when(gustatoryInspectionConverter.toOutputDTOUpdate(gustatoryInspectionService.update(entity.getId(), inputDTO),
                entity.getId(),
                gustatoryInspectionConverter.toOutputDTO(gustatoryInspectionService.update(entity.getId(), inputDTO))))
                .thenReturn(outputDTO);

        mockMvc.perform(put("/api/v1/gustatory-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar uma percepção gustativa cadastrada pelo id")
    void testUpdateGustatoryInspectionWithInvalidData() throws Exception {
        when(gustatoryInspectionService.update(entity.getId(), inputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_GUSTATORY_INSPECTION));

        mockMvc.perform(put("/api/v1/gustatory-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma percepção gustativa cadastrada pelo id")
    void testDeleteGustatoryInspection() throws Exception {
        mockMvc.perform(delete("/api/v1/gustatory-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar uma percepção gustativa cadastrada pelo id")
    void testDeleteGustatoryInspectionWithInvalidData() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_GUSTATORY_INSPECTION)).when(gustatoryInspectionService)
                .delete(entity.getId());
        mockMvc.perform(delete("/api/v1/gustatory-inspection/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private GustatoryInspectionOutputDTO createGustatoryInspectionOutputDTO() {
        return GustatoryInspectionOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .body(EnumBodyType.FULL_BODIED.getCode())
                .sweetness(EnumSweetnessType.VERY_DRY.getCode())
                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
                .classification(EnumClassificationType.LITTLE.getCode())
                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
                .alcohol(EnumAlcoholType.LOW.getCode())
                .persistence(EnumPersistenceType.SHORT.getCode())
                .maturity(EnumMaturityType.MATURE.getCode())
                .typicality(EnumTypicalityType.NOT_TYPICAL.getCode())
                .tastingCard(TastingCardOutputDTO.builder().build())
                .build();
    }

    private GustatoryInspectionInputDTO createGustatoryInspectionInputDTO() {
        return GustatoryInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .body(EnumBodyType.FULL_BODIED.getCode())
                .sweetness(EnumSweetnessType.VERY_DRY.getCode())
                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
                .classification(EnumClassificationType.LITTLE.getCode())
                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
                .alcohol(EnumAlcoholType.LOW.getCode())
                .persistence(EnumPersistenceType.SHORT.getCode())
                .maturity(EnumMaturityType.MATURE.getCode())
                .typicality(EnumTypicalityType.NOT_TYPICAL.getCode())
                .build();
    }

    private GustatoryInspectionEntity createGustatoryInspectionEntity() {
        return GustatoryInspectionEntity.builder()
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
                .build();
    }

}
