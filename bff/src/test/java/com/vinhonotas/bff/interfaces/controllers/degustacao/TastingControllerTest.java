package com.vinhonotas.bff.interfaces.controllers.degustacao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.degustacao.TastingService;
import com.vinhonotas.bff.domain.enums.EnumPointScale;
import com.vinhonotas.bff.domain.enums.EnumTastingType;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.*;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TastingController.class)
class TastingControllerTest {

    private static final String URL = "/api/v1/tasting";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TastingService tastingService;

    private TastingInputDTO inputDTO;
    private TastingOutputDTO outputDTO;

    @BeforeEach
    void setUp() {
        inputDTO = createTastingInputDTO();
        outputDTO = createTastingOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma degustação")
    void testCreateTasting() throws Exception {
        when(tastingService.createTasting(inputDTO)).thenReturn(outputDTO);

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(outputDTO.getId().toString()))
                .andExpect(jsonPath("$.tastingData").value(outputDTO.getTastingData().toString()))
                .andExpect(jsonPath("$.tastingType").value(outputDTO.getTastingType().toString()));
//                .andExpect(jsonPath("$.tastingCards").isArray());
    }

    private TastingInputDTO createTastingInputDTO() {
        return TastingInputDTO.builder()
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE)
                .tastingCards(Set.of(createTastingCardInputDTO()))
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
                .pointScale(EnumPointScale.CLASSIC)
                .build();
    }

    private TastingOutputDTO createTastingOutputDTO() {
        return TastingOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE)
                .tastingCards(Set.of(createTastingCardOutputDTO()))
                .build();
    }

    private TastingCardOutputDTO createTastingCardOutputDTO() {
        return TastingCardOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3f3f3f3f3f3f"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .harvest("2020")
                .grapes("Grapes")
                .country("Chile")
                .region("Vale Central")
                .visualInspection(VisualInspectionOutputDTO.builder().build())
                .olfactoryInspection(OlfactoryInspectionOutputDTO.builder().build())
                .gustatoryInspection(GustatoryInspectionOutputDTO.builder().build())
                .opinion("Opinion about the wine")
                .pointScale(EnumPointScale.CLASSIC)
                .build();
    }

}
