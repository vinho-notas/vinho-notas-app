package com.vinhonotas.degustacao.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.degustacao.application.converters.OlfactoryInspectionConverter;
import com.vinhonotas.degustacao.application.services.OlfactoryInspectionService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import com.vinhonotas.degustacao.domain.enums.EnumClassificationType;
import com.vinhonotas.degustacao.domain.enums.EnumIntensityType;
import com.vinhonotas.degustacao.domain.enums.EnumPersistenceType;
import com.vinhonotas.degustacao.domain.enums.EnumQualityType;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.AromasOutputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.OlfactoryInspectionOutputDTO;
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

@WebMvcTest(controllers = OlfactoryInspectionController.class)
class OlfactoryInspectionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OlfactoryInspectionService olfactoryInspectionService;
    @MockBean
    private OlfactoryInspectionConverter olfactoryInspectionConverter;

    private OlfactoryInspectionEntity olfactoryInspectionEntity;
    private OlfactoryInspectionInputDTO olfactoryInspectionInputDTO;
    private OlfactoryInspectionOutputDTO olfactoryInspectionOutputDTO;

    @BeforeEach
    void setUp() {
        olfactoryInspectionEntity = createOlafactoryInspectionEntity();
        olfactoryInspectionInputDTO = createOlafactoryInspectionInputDTO();
        olfactoryInspectionOutputDTO = createOlafactoryInspectionOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma percepção olfativa")
    void testCreate() throws Exception {
        when(olfactoryInspectionService.create(olfactoryInspectionInputDTO)).thenReturn(olfactoryInspectionEntity);
        when(olfactoryInspectionConverter.toOutputDTO(olfactoryInspectionEntity)).thenReturn(olfactoryInspectionOutputDTO);

        mockMvc.perform(post("/api/v1/olfactory-inspection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(olfactoryInspectionInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(olfactoryInspectionOutputDTO)));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar uma percepção olfativa")
    void testCreateThrowException() throws Exception {
        when(olfactoryInspectionService.create(olfactoryInspectionInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_OLFACTORY_INSPECTION));

        mockMvc.perform(post("/api/v1/olfactory-inspection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(olfactoryInspectionInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todas as percepções olfativas cadastradas")
    void testGetAll() throws Exception {
        when(olfactoryInspectionService.getAll()).thenReturn(List.of(olfactoryInspectionEntity));
        when(olfactoryInspectionConverter.toOutputDTOList(List.of(olfactoryInspectionEntity))).thenReturn(List.of(olfactoryInspectionOutputDTO));

        mockMvc.perform(get("/api/v1/olfactory-inspection")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(List.of(olfactoryInspectionOutputDTO))));
    }

    @Test
    @DisplayName("Deve lançar exceção ao retornar uma lista com todas as percepções olfativas cadastradas")
    void testGetAllThrowException() throws Exception {
        when(olfactoryInspectionService.getAll()).thenThrow(new BadRequestException(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND));

        mockMvc.perform(get("/api/v1/olfactory-inspection")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma percepção olfativa cadastrada pelo id")
    void testGetById() throws Exception {
        when(olfactoryInspectionService.getById(olfactoryInspectionEntity.getId())).thenReturn(olfactoryInspectionEntity);
        when(olfactoryInspectionConverter.toOutputDTO(olfactoryInspectionEntity)).thenReturn(olfactoryInspectionOutputDTO);

        mockMvc.perform(get("/api/v1/olfactory-inspection/" + olfactoryInspectionEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(olfactoryInspectionOutputDTO)));
    }

    @Test
    @DisplayName("Deve lançar exceção ao retornar uma percepção olfativa cadastrada pelo id")
    void testGetByIdThrowException() throws Exception {
        when(olfactoryInspectionService.getById(olfactoryInspectionEntity.getId())).thenThrow(new BadRequestException(MessagesConstants.OLFACTORY_INSPECTION_NOT_FOUND));

        mockMvc.perform(get("/api/v1/olfactory-inspection/" + olfactoryInspectionEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar uma percepção olfativa cadastrada pelo id")
    void testUpdate() throws Exception {
        when(olfactoryInspectionService.update(olfactoryInspectionEntity.getId(), olfactoryInspectionInputDTO)).thenReturn(olfactoryInspectionEntity);
        when(olfactoryInspectionConverter.toOutputDTOUpdate(olfactoryInspectionService.update(olfactoryInspectionEntity.getId(), olfactoryInspectionInputDTO),
                olfactoryInspectionEntity.getId(),
                olfactoryInspectionConverter.toOutputDTO(olfactoryInspectionService.update(olfactoryInspectionEntity.getId(),
                        olfactoryInspectionInputDTO)))).thenReturn(olfactoryInspectionOutputDTO);

        mockMvc.perform(put("/api/v1/olfactory-inspection/" + olfactoryInspectionEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(olfactoryInspectionInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(olfactoryInspectionOutputDTO)));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar uma percepção olfativa cadastrada pelo id")
    void testUpdateThrowException() throws Exception {
        when(olfactoryInspectionService.update(olfactoryInspectionEntity.getId(), olfactoryInspectionInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_OLFACTORY_INSPECTION));

        mockMvc.perform(put("/api/v1/olfactory-inspection/" + olfactoryInspectionEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(olfactoryInspectionInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma percepção olfativa cadastrada pelo id")
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/olfactory-inspection/" + olfactoryInspectionEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar uma percepção olfativa cadastrada pelo id")
    void testDeleteThrowException() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_OLFACTORY_INSPECTION))
                .when(olfactoryInspectionService).delete(olfactoryInspectionEntity.getId());
        mockMvc.perform(delete("/api/v1/olfactory-inspection/" + olfactoryInspectionEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private OlfactoryInspectionOutputDTO createOlafactoryInspectionOutputDTO() {
        return OlfactoryInspectionOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE.getCode())
                .persistence(EnumPersistenceType.PERSISTENT.getCode())
                .quality(EnumQualityType.COMMON.getCode())
                .aromas(AromasOutputDTO.builder().build())
                .classification(EnumClassificationType.LITTLE.getCode())
                .tastingCard(TastingCardOutputDTO.builder().build())
                .build();
    }

    private OlfactoryInspectionInputDTO createOlafactoryInspectionInputDTO() {
        return OlfactoryInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE.getCode())
                .persistence(EnumPersistenceType.PERSISTENT.getCode())
                .quality(EnumQualityType.COMMON.getCode())
                .aromas(AromasInputDTO.builder().build())
                .classification(EnumClassificationType.LITTLE.getCode())
                .build();
    }

    private OlfactoryInspectionEntity createOlafactoryInspectionEntity() {
        return OlfactoryInspectionEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE)
                .persistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .aromas(Mockito.mock(AromasEntity.class))
                .classification(EnumClassificationType.LITTLE)
                .build();
    }

}
