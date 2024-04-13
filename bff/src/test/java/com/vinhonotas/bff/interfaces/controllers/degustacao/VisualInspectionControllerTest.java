//package com.vinhonotas.bff.interfaces.controllers.degustacao;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.vinhonotas.bff.application.services.degustacao.VisualInspectionService;
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
//@WebMvcTest(controllers = VisualInspectionController.class)
//class VisualInspectionControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private VisualInspectionService visualInspectionService;
//
//    private VisualInspectionInputDTO inputDTO;
//    private VisualInspectionOutputDTO outputDTO;
//
//    @BeforeEach
//    void setUp() {
//        inputDTO = createVisualInspectionInputDTO();
//        outputDTO = createVisualInspectionOutputDTO();
//    }
//
//    @Test
//    @DisplayName("Deve criar uma percepção visual")
//    void testCreateVisualInspection() throws Exception {
//        when(visualInspectionService.createVisualInspection(inputDTO)).thenReturn(outputDTO);
//
//        mockMvc.perform(post("/api/v1/visual-inspection")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .json(objectMapper.writeValueAsString(outputDTO)));
//    }
//
//    @Test
//    @DisplayName("Deve lançar BadRequestException ao criar uma percepção visual")
//    void testCreateVisualInspectionBadRequestException() throws Exception {
//        when(visualInspectionService.createVisualInspection(inputDTO)).thenThrow(new BadRequestException(
//                MessagesConstants.ERROR_WHEN_SAVING));
//
//        mockMvc.perform(post("/api/v1/visual-inspection")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma lista com todas as percepções visuais cadastradas")
//    void testGetAllVisualInspections() throws Exception {
//        when(visualInspectionService.getAllVisualInspections()).thenReturn(List.of(outputDTO));
//
//        mockMvc.perform(get("/api/v1/visual-inspection")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .json(objectMapper.writeValueAsString(List.of(outputDTO))));
//    }
//
//    @Test
//    @DisplayName("Deve lançar BadRequestException ao retornar uma lista com todas as percepções visuais cadastradas")
//    void testGetAllVisualInspectionsBadRequestException() throws Exception {
//        when(visualInspectionService.getAllVisualInspections()).thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));
//
//        mockMvc.perform(get("/api/v1/visual-inspection")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma percepção visual cadastrada pelo id")
//    void testGetVisualInspectionById() throws Exception {
//        when(visualInspectionService.getVisualInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")).thenReturn(outputDTO);
//
//        mockMvc.perform(get("/api/v1/visual-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .json(objectMapper.writeValueAsString(outputDTO)));
//    }
//
//    @Test
//    @DisplayName("Deve lançar BadRequestException ao retornar uma percepção visual cadastrada pelo id")
//    void testGetVisualInspectionByIdBadRequestException() throws Exception {
//        when(visualInspectionService.getVisualInspectionById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
//                .thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));
//
//        mockMvc.perform(get("/api/v1/visual-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("Deve atualizar uma percepção visual cadastrada pelo id")
//    void testUpdateVisualInspection() throws Exception {
//        when(visualInspectionService.updateVisualInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO)).thenReturn(outputDTO);
//
//        mockMvc.perform(put("/api/v1/visual-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .json(objectMapper.writeValueAsString(outputDTO)));
//    }
//
//    @Test
//    @DisplayName("Deve lançar BadRequestException ao atualizar uma percepção visual cadastrada pelo id")
//    void testUpdateVisualInspectionBadRequestException() throws Exception {
//        when(visualInspectionService.updateVisualInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO))
//                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));
//
//        mockMvc.perform(put("/api/v1/visual-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @DisplayName("Deve deletar uma percepção visual cadastrada pelo id")
//    void testDeleteVisualInspection() throws Exception {
//        mockMvc.perform(delete("/api/v1/visual-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    @DisplayName("Deve lançar BadRequestException ao deletar uma percepção visual cadastrada pelo id")
//    void testDeleteVisualInspectionBadRequestException() throws Exception {
//        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
//                .when(visualInspectionService).deleteVisualInspection("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
//
//        mockMvc.perform(delete("/api/v1/visual-inspection/f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    private VisualInspectionOutputDTO createVisualInspectionOutputDTO() {
//        return VisualInspectionOutputDTO.builder()
//                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
//                .tastingData(LocalDate.now())
//                .wineTasted("Wine Tasted")
//                .clarity(EnumClarityType.VERY_CLEAR.getCode())
//                .brightness(EnumBrightnessType.VERY_BRIGHT.getCode())
//                .viscosity(EnumViscosityType.SLIPPERY.getCode())
//                .colorRed(EnumRedColorType.RUBY.getCode())
//                .colorWhite(EnumWhiteColorType.STRAW_YELLOW.getCode())
//                .colorRose(EnumRoseColorType.BROWN.getCode())
//                .classification(EnumClassificationType.LITTLE.getCode())
//                .build();
//    }
//
//    private VisualInspectionInputDTO createVisualInspectionInputDTO() {
//        return VisualInspectionInputDTO.builder()
//                .tastingData(LocalDate.now())
//                .wineTasted("Wine Tasted")
//                .clarity(EnumClarityType.VERY_CLEAR.getCode())
//                .brightness(EnumBrightnessType.VERY_BRIGHT.getCode())
//                .viscosity(EnumViscosityType.SLIPPERY.getCode())
//                .colorRed(EnumRedColorType.RUBY.getCode())
//                .colorWhite(EnumWhiteColorType.STRAW_YELLOW.getCode())
//                .colorRose(EnumRoseColorType.BROWN.getCode())
//                .classification(EnumClassificationType.LITTLE.getCode())
//                .build();
//    }
//
//}
