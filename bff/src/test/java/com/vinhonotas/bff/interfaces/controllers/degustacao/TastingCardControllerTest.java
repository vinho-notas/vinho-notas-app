package com.vinhonotas.bff.interfaces.controllers.degustacao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.degustacao.TastingCardService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.domain.enums.*;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingCardInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingCardOutputDTO;
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
import java.time.LocalDateTime;
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

    private TastingCardInputDTO inputDTO;
    private TastingCardOutputDTO outputDTO;

    @BeforeEach
    void setUp() {
        inputDTO = createTastingCardInputDTO();
        outputDTO = createTastingCardOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma ficha de degustação")
    void testCreateTastingCard() throws Exception {
        when(tastingCardService.createTastingCard(inputDTO)).thenReturn(outputDTO);

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
        when(tastingCardService.createTastingCard(inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        mockMvc.perform(post("/api/v1/tasting-card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todas as fichas de degustação cadastradas")
    void testGetAllTastingCards() throws Exception {
        when(tastingCardService.getAllTastingCards()).thenReturn(List.of(outputDTO));

        mockMvc.perform(get("/api/v1/tasting-card")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(outputDTO))));
    }

    @Test
    @DisplayName("Deve lançar erro ao retornar uma lista com todas as fichas de degustação cadastradas")
    void testGetAllTastingCardsError() throws Exception {
        when(tastingCardService.getAllTastingCards()).thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get("/api/v1/tasting-card")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar uma ficha de degustação cadastrada pelo id")
    void testGetTastingCardById() throws Exception {
        when(tastingCardService.getTastingCardById(outputDTO.getId().toString())).thenReturn(outputDTO);

        mockMvc.perform(get("/api/v1/tasting-card/" + outputDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar erro ao retornar uma ficha de degustação cadastrada pelo id")
    void testGetTastingCardByIdError() throws Exception {
        when(tastingCardService.getTastingCardById(outputDTO.getId().toString())).thenThrow(new NotFoundException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get("/api/v1/tasting-card/" + outputDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve atualizar uma ficha de degustação cadastrada pelo id")
    void testUpdateTastingCard() throws Exception {
        when(tastingCardService.updateTastingCard(outputDTO.getId().toString(), inputDTO)).thenReturn(outputDTO);

        mockMvc.perform(put("/api/v1/tasting-card/" + outputDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar erro ao atualizar uma ficha de degustação cadastrada pelo id")
    void testUpdateTastingCardError() throws Exception {
        when(tastingCardService.updateTastingCard(outputDTO.getId().toString(), inputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        mockMvc.perform(put("/api/v1/tasting-card/" + outputDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma ficha de degustação cadastrada pelo id")
    void testDeleteTastingCard() throws Exception {
        mockMvc.perform(delete("/api/v1/tasting-card/" + outputDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar erro ao deletar uma ficha de degustação cadastrada pelo id")
    void testDeleteTastingCardError() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING)).when(tastingCardService).deleteTastingCard(outputDTO.getId().toString());

        mockMvc.perform(delete("/api/v1/tasting-card/" + outputDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private TastingCardOutputDTO createTastingCardOutputDTO() {
        return TastingCardOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .wineTasted("Wine Tasted")
                .tastingData(LocalDate.now())
                .harvest("2021")
                .grapes("Grapes")
                .country("Country")
                .region("Region")
                .clarity(EnumClarityType.CLEAR.getCode())
                .brightness(EnumBrightnessType.BRIGHT.getCode())
                .viscosity(EnumViscosityType.VISCOUS.getCode())
                .colorRed(EnumRedColorType.RUBY.getCode())
                .colorWhite(EnumWhiteColorType.GOLDEN.getCode())
                .colorRose(EnumRoseColorType.BROWN.getCode())
                .visualInspectionClassification(EnumClassificationType.LITTLE.getCode())
                .intensity(EnumIntensityType.INTENSE.getCode())
                .olfactoryInspectionPersistence(EnumPersistenceType.PERSISTENT.getCode())
                .quality(EnumQualityType.COMMON.getCode())
                .fruity(EnumFruityType.BANANA.getCode())
                .dryFruits(EnumDryFruitsType.ALMODN.getCode())
                .florals(EnumFloralsType.ACACIA.getCode())
                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL.getCode())
                .minerals(EnumMineralsType.CHALK.getCode())
                .spices(EnumSpicesType.ANISE.getCode())
                .animals(EnumAnimalsType.HUNTING.getCode())
                .empireumatics(EnumEmpireumaticsType.CARAMEL.getCode())
                .wood(EnumWoodType.SAWDUST.getCode())
                .chemicals(EnumChemicalsAndEtherealType.ACETONE.getCode())
                .lacteal(EnumLactealType.BUTTER.getCode())
                .sweets(EnumSweetsType.BULLET.getCode())
                .olfactoryInspectionClassification(EnumClassificationType.EXCELLENT.getCode())
                .body(EnumBodyType.LITTLE_BODY.getCode())
                .sweetness(EnumSweetnessType.SWEET.getCode())
                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
                .alcohol(EnumAlcoholType.LOW.getCode())
                .gustatoryInspectionPersistence(EnumPersistenceType.PERSISTENT.getCode())
                .maturity(EnumMaturityType.MATURE.getCode())
                .typicality(EnumTypicalityType.TYPICAL.getCode())
                .gustatoryInspectionClassification(EnumClassificationType.LITTLE.getCode())
                .opinion("Opinion")
                .pointScale(EnumPointScale.VERYGOOD.getCode())
                .build();
    }

    private TastingCardInputDTO createTastingCardInputDTO() {
        return TastingCardInputDTO.builder()
                .wineTasted("Wine Tasted")
                .tastingData(LocalDate.now())
                .harvest("2021")
                .grapes("Grapes")
                .country("Country")
                .region("Region")
                .clarity(EnumClarityType.CLEAR.getCode())
                .brightness(EnumBrightnessType.BRIGHT.getCode())
                .viscosity(EnumViscosityType.VISCOUS.getCode())
                .colorRed(EnumRedColorType.RUBY.getCode())
                .colorWhite(EnumWhiteColorType.GOLDEN.getCode())
                .colorRose(EnumRoseColorType.BROWN.getCode())
                .visualInspectionClassification(EnumClassificationType.LITTLE.getCode())
                .intensity(EnumIntensityType.INTENSE.getCode())
                .olfactoryInspectionPersistence(EnumPersistenceType.PERSISTENT.getCode())
                .quality(EnumQualityType.COMMON.getCode())
                .fruity(EnumFruityType.BANANA.getCode())
                .dryFruits(EnumDryFruitsType.ALMODN.getCode())
                .florals(EnumFloralsType.ACACIA.getCode())
                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL.getCode())
                .minerals(EnumMineralsType.CHALK.getCode())
                .spices(EnumSpicesType.ANISE.getCode())
                .animals(EnumAnimalsType.HUNTING.getCode())
                .empireumatics(EnumEmpireumaticsType.CARAMEL.getCode())
                .wood(EnumWoodType.SAWDUST.getCode())
                .chemicals(EnumChemicalsAndEtherealType.ACETONE.getCode())
                .lacteal(EnumLactealType.BUTTER.getCode())
                .sweets(EnumSweetsType.BULLET.getCode())
                .olfactoryInspectionClassification(EnumClassificationType.EXCELLENT.getCode())
                .body(EnumBodyType.LITTLE_BODY.getCode())
                .sweetness(EnumSweetnessType.SWEET.getCode())
                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
                .alcohol(EnumAlcoholType.LOW.getCode())
                .gustatoryInspectionPersistence(EnumPersistenceType.PERSISTENT.getCode())
                .maturity(EnumMaturityType.MATURE.getCode())
                .typicality(EnumTypicalityType.TYPICAL.getCode())
                .gustatoryInspectionClassification(EnumClassificationType.LITTLE.getCode())
                .opinion("Opinion")
                .pointScale(EnumPointScale.VERYGOOD.getCode())
                .dthreg(LocalDateTime.now())
                .userreg("User Reg")
                .build();
    }

}
