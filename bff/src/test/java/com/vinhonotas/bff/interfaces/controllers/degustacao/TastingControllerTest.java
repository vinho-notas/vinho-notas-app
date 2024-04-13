//package com.vinhonotas.bff.interfaces.controllers.degustacao;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.vinhonotas.bff.application.services.degustacao.TastingService;
//import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
//import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
//import com.vinhonotas.bff.domain.enums.EnumPointScale;
//import com.vinhonotas.bff.domain.enums.EnumTastingType;
//import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.*;
//import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.*;
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
//import java.util.Set;
//import java.util.UUID;
//
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = TastingController.class)
//class TastingControllerTest {
//
//    private static final String URL = "/api/v1/tasting";
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private TastingService tastingService;
//
//    private TastingInputDTO inputDTO;
//    private TastingOutputDTO outputDTO;
//
//    @BeforeEach
//    void setUp() {
//        inputDTO = createTastingInputDTO();
//        outputDTO = createTastingOutputDTO();
//    }
//
//    @Test
//    @DisplayName("Deve criar uma degustação")
//    void testCreateTasting() throws Exception {
//        when(tastingService.createTasting(inputDTO)).thenReturn(outputDTO);
//
//        mockMvc.perform(post(URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(outputDTO.getId().toString()))
//                .andExpect(jsonPath("$.tastingData").value(outputDTO.getTastingData().toString()))
//                .andExpect(jsonPath("$.tastingType").value(outputDTO.getTastingType().toString()))
//                .andExpect(jsonPath("$.tastingCards").isArray());
//    }
//
//    @Test
//    @DisplayName("Deve retornar erro ao criar uma degustação")
//    void testCreateTastingThrowBadRequestException() throws Exception {
//        when(tastingService.createTasting(inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));
//
//        mockMvc.perform(post(URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma lista de degustações")
//    void testGetAllTastings() throws Exception {
//        when(tastingService.getAllTastings()).thenReturn(List.of(outputDTO));
//
//        mockMvc.perform(get(URL)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(outputDTO.getId().toString()))
//                .andExpect(jsonPath("$[0].tastingData").value(outputDTO.getTastingData().toString()))
//                .andExpect(jsonPath("$[0].tastingType").value(outputDTO.getTastingType().toString()))
//                .andExpect(jsonPath("$[0].tastingCards").isArray());
//    }
//
//    @Test
//    @DisplayName("Deve retornar um erro ao buscar uma lista de degustações")
//    void testGetAllTastingsThrowBadRequestException() throws Exception {
//        when(tastingService.getAllTastings()).thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));
//
//        mockMvc.perform(get(URL)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma degustação pelo id")
//    void testGetTastingById() throws Exception {
//        when(tastingService.getTastingById(outputDTO.getId().toString())).thenReturn(outputDTO);
//
//        mockMvc.perform(get(URL + "/" + outputDTO.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(outputDTO.getId().toString()))
//                .andExpect(jsonPath("$.tastingData").value(outputDTO.getTastingData().toString()))
//                .andExpect(jsonPath("$.tastingType").value(outputDTO.getTastingType().toString()))
//                .andExpect(jsonPath("$.tastingCards").isArray());
//    }
//
//    @Test
//    @DisplayName("Deve retornar um erro ao buscar uma degustação pelo id")
//    void testGetTastingByIdThrowBadRequestException() throws Exception {
//        when(tastingService.getTastingById(outputDTO.getId().toString())).thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));
//
//        mockMvc.perform(get(URL + "/" + outputDTO.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("Deve atualizar uma degustação pelo id")
//    void testUpdateTasting() throws Exception {
//        when(tastingService.updateTasting(outputDTO.getId().toString(), inputDTO)).thenReturn(outputDTO);
//
//        mockMvc.perform(put(URL + "/" + outputDTO.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(outputDTO.getId().toString()))
//                .andExpect(jsonPath("$.tastingData").value(outputDTO.getTastingData().toString()))
//                .andExpect(jsonPath("$.tastingType").value(outputDTO.getTastingType().toString()))
//                .andExpect(jsonPath("$.tastingCards").isArray());
//    }
//
//    @Test
//    @DisplayName("Deve retornar um erro ao atualizar uma degustação pelo id")
//    void testUpdateTastingThrowBadRequestException() throws Exception {
//        when(tastingService.updateTasting(outputDTO.getId().toString(), inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));
//
//        mockMvc.perform(put(URL + "/" + outputDTO.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(inputDTO)))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @DisplayName("Deve deletar uma degustação pelo id")
//    void testDeleteTasting() throws Exception {
//        mockMvc.perform(delete(URL + "/" + outputDTO.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    @DisplayName("Deve retornar um erro ao deletar uma degustação pelo id")
//    void testDeleteTastingThrowBadRequestException() throws Exception {
//        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING)).when(tastingService).deleteTasting(outputDTO.getId().toString());
//        mockMvc.perform(delete(URL + "/" + outputDTO.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    private TastingInputDTO createTastingInputDTO() {
//        return TastingInputDTO.builder()
//                .tastingData(LocalDate.now())
//                .tastingType(EnumTastingType.COMPARATIVE.getCode())
//                .tastingCards(Set.of(createTastingCardInputDTO()))
//                .build();
//    }
//
//    private TastingCardInputDTO createTastingCardInputDTO() {
//        return TastingCardInputDTO.builder()
//                .tastingData(LocalDate.now())
//                .wineTasted("Wine Tasted")
//                .harvest("2020")
//                .grapes("Grapes")
//                .country("Chile")
//                .region("Vale Central")
//                .visualInspection(VisualInspectionInputDTO.builder().build())
//                .olfactoryInspection(OlfactoryInspectionInputDTO.builder().build())
//                .gustatoryInspection(GustatoryInspectionInputDTO.builder().build())
//                .opinion("Opinion about the wine")
//                .pointScale(EnumPointScale.CLASSIC.getCode())
//                .build();
//    }
//
//    private TastingOutputDTO createTastingOutputDTO() {
//        return TastingOutputDTO.builder()
//                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
//                .tastingData(LocalDate.now())
//                .tastingType(EnumTastingType.COMPARATIVE.getCode())
//                .tastingCards(Set.of(createTastingCardOutputDTO()))
//                .build();
//    }
//
//    private TastingCardOutputDTO createTastingCardOutputDTO() {
//        return TastingCardOutputDTO.builder()
//                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3f3f3f3f3f3f"))
//                .tastingData(LocalDate.now())
//                .wineTasted("Wine Tasted")
//                .harvest("2020")
//                .grapes("Grapes")
//                .country("Chile")
//                .region("Vale Central")
//                .visualInspection(VisualInspectionOutputDTO.builder().build())
//                .olfactoryInspection(OlfactoryInspectionOutputDTO.builder().build())
//                .gustatoryInspection(GustatoryInspectionOutputDTO.builder().build())
//                .opinion("Opinion about the wine")
//                .pointScale(EnumPointScale.CLASSIC.name())
//                .build();
//    }
//
//}
