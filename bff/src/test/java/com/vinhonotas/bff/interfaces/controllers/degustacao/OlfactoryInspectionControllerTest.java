package com.vinhonotas.bff.interfaces.controllers.degustacao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.degustacao.OlfactoryInspectionService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.domain.enums.*;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.AromasInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.OlfactoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.AromasOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.OlfactoryInspectionOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
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

@WebMvcTest(controllers = OlfactoryInspectionController.class)
class OlfactoryInspectionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OlfactoryInspectionService olfactoryInspectionService;

    private OlfactoryInspectionInputDTO olfactoryInspectionInputDTO;
    private OlfactoryInspectionOutputDTO olfactoryInspectionOutputDTO;

    @BeforeEach
    void setUp() {
        olfactoryInspectionInputDTO = createOlafactoryInspectionInputDTO();
        olfactoryInspectionOutputDTO = createOlafactoryInspectionOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma percepção olfativa")
    void testCreate() throws Exception {
        when(olfactoryInspectionService.createOlfactoryInspection(olfactoryInspectionInputDTO)).thenReturn(olfactoryInspectionOutputDTO);

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
        when(olfactoryInspectionService.createOlfactoryInspection(olfactoryInspectionInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        mockMvc.perform(post("/api/v1/olfactory-inspection")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(olfactoryInspectionInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todas as percepções olfativas cadastradas")
    void testGetAll() throws Exception {
        when(olfactoryInspectionService.getAllOlfactoryInspections()).thenReturn(List.of(olfactoryInspectionOutputDTO));

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
        when(olfactoryInspectionService.getAllOlfactoryInspections())
                .thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get("/api/v1/olfactory-inspection")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar uma percepção olfativa cadastrada pelo id")
    void testGetById() throws Exception {
        when(olfactoryInspectionService.getOlfactoryInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .thenReturn(olfactoryInspectionOutputDTO);

        mockMvc.perform(get("/api/v1/olfactory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(olfactoryInspectionOutputDTO)));
    }

    @Test
    @DisplayName("Deve lançar exceção ao retornar uma percepção olfativa cadastrada pelo id")
    void testGetByIdThrowException() throws Exception {
        when(olfactoryInspectionService.getOlfactoryInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get("/api/v1/olfactory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve atualizar uma percepção olfativa cadastrada pelo id")
    void testUpdate() throws Exception {
        when(olfactoryInspectionService.updateOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", olfactoryInspectionInputDTO))
                .thenReturn(olfactoryInspectionOutputDTO);


        mockMvc.perform(put("/api/v1/olfactory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
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
        when(olfactoryInspectionService.updateOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", olfactoryInspectionInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        mockMvc.perform(put("/api/v1/olfactory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(olfactoryInspectionInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma percepção olfativa cadastrada pelo id")
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/olfactory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar uma percepção olfativa cadastrada pelo id")
    void testDeleteThrowException() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
                .when(olfactoryInspectionService).deleteOlfactoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
        mockMvc.perform(delete("/api/v1/olfactory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private OlfactoryInspectionOutputDTO createOlafactoryInspectionOutputDTO() {
        return OlfactoryInspectionOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE)
                .persistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .aromas(createAromasOutputDTO())
                .classification(EnumClassificationType.LITTLE)
                .build();
    }

    private OlfactoryInspectionInputDTO createOlafactoryInspectionInputDTO() {
        return OlfactoryInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE.getCode())
                .persistence(EnumPersistenceType.PERSISTENT.getCode())
                .quality(EnumQualityType.COMMON.getCode())
                .aromas(createAromasInputDTO())
                .classification(EnumClassificationType.LITTLE.getCode())
                .build();
    }

    private AromasOutputDTO createAromasOutputDTO() {
        return AromasOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3f3f3f3f3f3f"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .fruity(EnumFruityType.RASPBERRY)
                .dryFruits(EnumDryFruitsType.BRUNETTE)
                .florals(EnumFloralsType.CLOVE)
                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL)
                .minerals(EnumMineralsType.EARTH)
                .spices(EnumSpicesType.INDIAN_CLOVE)
                .animals(EnumAnimalsType.LEATHER)
                .empireumatics(EnumEmpireumaticsType.CARAMEL)
                .wood(EnumWoodType.SAWDUST)
                .chemicals(EnumChemicalsAndEtherealType.ACETONE)
                .lacteal(EnumLactealType.BUTTER)
                .sweets(EnumSweetsType.BULLET)
                .build();
    }

    private AromasInputDTO createAromasInputDTO() {
        return AromasInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .fruity(EnumFruityType.RASPBERRY.getCode())
                .dryFruits(EnumDryFruitsType.BRUNETTE.getCode())
                .florals(EnumFloralsType.CLOVE.getCode())
                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL.getCode())
                .minerals(EnumMineralsType.EARTH.getCode())
                .spices(EnumSpicesType.INDIAN_CLOVE.getCode())
                .animals(EnumAnimalsType.LEATHER.getCode())
                .empireumatics(EnumEmpireumaticsType.CARAMEL.getCode())
                .wood(EnumWoodType.SAWDUST.getCode())
                .chemicals(EnumChemicalsAndEtherealType.ACETONE.getCode())
                .lacteal(EnumLactealType.BUTTER.getCode())
                .sweets(EnumSweetsType.BULLET.getCode())
                .build();
    }

}