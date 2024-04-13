//package com.vinhonotas.bff.interfaces.controllers.degustacao;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.vinhonotas.bff.application.services.degustacao.GustatoryInspectionService;
//import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
//import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
//import com.vinhonotas.bff.domain.enums.*;
//import com.vinhonotas.bff.utils.MessagesConstants;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.UUID;
//
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = GustatoryInspectionController.class)
//class GustatoryInspectionControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private GustatoryInspectionService gustatoryInspectionService;
//
//    private GustatoryInspectionInputDTO inputDTO;
//    private GustatoryInspectionOutputDTO outputDTO;
//
//    @BeforeEach
//    void setUp() {
//        inputDTO = createGustatoryInspectionInputDTO();
//        outputDTO = createGustatoryInspectionOutputDTO();
//    }
//
//    @Test
//    @DisplayName("Deve criar uma percepção gustativa")
//    void testCreateGustatoryInspection() throws Exception {
//        when(gustatoryInspectionService.createGustatoryInspection(inputDTO)).thenReturn(outputDTO);
//
//        mockMvc.perform(post("/api/v1/gustatory-inspection")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
//    }
//
//    @Test
//    @DisplayName("Deve lançar exceção ao criar uma percepção gustativa com dados inválidos")
//    void testCreateGustatoryInspectionWithInvalidData() throws Exception {
//        when(gustatoryInspectionService.createGustatoryInspection(inputDTO))
//                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));
//
//        mockMvc.perform(post("/api/v1/gustatory-inspection")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma lista com todas as percepções gustativas cadastradas")
//    void testGetAllGustatoryInspections() throws Exception {
//        when(gustatoryInspectionService.getAllGustatoryInspections()).thenReturn(List.of(outputDTO));
//
//        mockMvc.perform(get("/api/v1/gustatory-inspection")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(List.of(outputDTO))));
//    }
//
//    @Test
//    @DisplayName("Deve lançar exceção ao retornar uma lista com todas as percepções gustativas cadastradas")
//    void testGetAllGustatoryInspectionsWithInvalidData() throws Exception {
//        when(gustatoryInspectionService.getAllGustatoryInspections())
//                .thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));
//
//        mockMvc.perform(get("/api/v1/gustatory-inspection")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma percepção gustativa cadastrada pelo id")
//    void testGetGustatoryInspectionById() throws Exception {
//        when(gustatoryInspectionService.getGustatoryInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")).thenReturn(outputDTO);
//
//        mockMvc.perform(get("/api/v1/gustatory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
//    }
//
//    @Test
//    @DisplayName("Deve lançar exceção ao retornar uma percepção gustativa cadastrada pelo id")
//    void testGetGustatoryInspectionByIdWithInvalidData() throws Exception {
//        when(gustatoryInspectionService.getGustatoryInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
//                .thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));
//
//        mockMvc.perform(get("/api/v1/gustatory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("Deve atualizar uma percepção gustativa cadastrada pelo id")
//    void testUpdateGustatoryInspection() throws Exception {
//        when(gustatoryInspectionService.updateGustatoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO))
//                .thenReturn(outputDTO);
//
//        mockMvc.perform(put("/api/v1/gustatory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
//    }
//
//    @Test
//    @DisplayName("Deve lançar exceção ao atualizar uma percepção gustativa cadastrada pelo id")
//    void testUpdateGustatoryInspectionWithInvalidData() throws Exception {
//        when(gustatoryInspectionService.updateGustatoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO))
//                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));
//
//        mockMvc.perform(put("/api/v1/gustatory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @DisplayName("Deve deletar uma percepção gustativa cadastrada pelo id")
//    void testDeleteGustatoryInspection() throws Exception {
//        mockMvc.perform(delete("/api/v1/gustatory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    @DisplayName("Deve lançar exceção ao deletar uma percepção gustativa cadastrada pelo id")
//    void testDeleteGustatoryInspectionWithInvalidData() throws Exception {
//        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING)).when(gustatoryInspectionService)
//                .deleteGustatoryInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
//
//        mockMvc.perform(delete("/api/v1/gustatory-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    private GustatoryInspectionOutputDTO createGustatoryInspectionOutputDTO() {
//        return GustatoryInspectionOutputDTO.builder()
//                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
//                .tastingData(LocalDate.now())
//                .wineTasted("Wine Tasted")
//                .body(EnumBodyType.FULL_BODIED.getCode())
//                .sweetness(EnumSweetnessType.VERY_DRY.getCode())
//                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
//                .classification(EnumClassificationType.LITTLE.getCode())
//                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
//                .alcohol(EnumAlcoholType.LOW.getCode())
//                .persistence(EnumPersistenceType.SHORT.getCode())
//                .maturity(EnumMaturityType.MATURE.getCode())
//                .typicality(EnumTypicalityType.NOT_TYPICAL.getCode())
//                .build();
//    }
//
//    private GustatoryInspectionInputDTO createGustatoryInspectionInputDTO() {
//        return GustatoryInspectionInputDTO.builder()
//                .tastingData(LocalDate.now())
//                .wineTasted("Wine Tasted")
//                .body(EnumBodyType.FULL_BODIED.getCode())
//                .sweetness(EnumSweetnessType.VERY_DRY.getCode())
//                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
//                .classification(EnumClassificationType.LITTLE.getCode())
//                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
//                .alcohol(EnumAlcoholType.LOW.getCode())
//                .persistence(EnumPersistenceType.SHORT.getCode())
//                .maturity(EnumMaturityType.MATURE.getCode())
//                .typicality(EnumTypicalityType.NOT_TYPICAL.getCode())
//                .build();
//    }
//
//}