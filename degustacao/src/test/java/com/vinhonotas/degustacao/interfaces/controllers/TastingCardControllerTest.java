package com.vinhonotas.degustacao.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.degustacao.application.converters.TastingCardConverter;
import com.vinhonotas.degustacao.application.services.TastingCardService;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
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
import java.time.LocalDateTime;
import java.util.Set;
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
        when(tastingCardService.getAll()).thenReturn(Set.of(entity));
        when(tastingCardConverter.toOutputDTO(entity)).thenReturn(outputDTO);

        mockMvc.perform(get("/api/v1/tasting-card")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Set.of(outputDTO))));
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
        when(tastingCardConverter.toOutputDTO(entity)).thenReturn(outputDTO);

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
        when(tastingCardService.update(entity.getId(), inputDTO)).thenThrow(new BadRequestException(
                MessagesConstants.ERROR_WHEN_UPDATING_TASTING_CARD));

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

    private TastingCardEntity createTastingCardEntity() {
        return TastingCardEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .wineTasted("Wine Tasted")
                .tastingData(LocalDate.now())
                .harvest("2021")
                .grapes("Grapes")
                .country("Country")
                .region("Region")
                .clarity(EnumClarityType.CLEAR)
                .brightness(EnumBrightnessType.BRIGHT)
                .viscosity(EnumViscosityType.VISCOUS)
                .colorRed(EnumRedColorType.RUBY)
                .colorWhite(EnumWhiteColorType.GOLDEN)
                .colorRose(EnumRoseColorType.BROWN)
                .visualInspectionClassification(EnumClassificationType.LITTLE)
                .intensity(EnumIntensityType.INTENSE)
                .olfactoryInspectionPersistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .fruity(EnumFruityType.BANANA)
                .dryFruits(EnumDryFruitsType.ALMODN)
                .florals(EnumFloralsType.ACACIA)
                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL)
                .minerals(EnumMineralsType.CHALK)
                .spices(EnumSpicesType.ANISE)
                .animals(EnumAnimalsType.HUNTING)
                .empireumatics(EnumEmpireumaticsType.CARAMEL)
                .wood(EnumWoodType.SAWDUST)
                .chemicals(EnumChemicalsAndEtherealType.ACETONE)
                .lacteal(EnumLactealType.BUTTER)
                .sweets(EnumSweetsType.BULLET)
                .olfactoryInspectionClassification(EnumClassificationType.EXCELLENT)
                .body(EnumBodyType.LITTLE_BODY)
                .sweetness(EnumSweetnessType.SWEET)
                .tannin(EnumTanninType.LITTLE_TANIC)
                .acidity(EnumAcidityType.LITTLE_ACID)
                .alcohol(EnumAlcoholType.LOW)
                .gustatoryInspectionPersistence(EnumPersistenceType.PERSISTENT)
                .maturity(EnumMaturityType.MATURE)
                .typicality(EnumTypicalityType.TYPICAL)
                .gustatoryInspectionClassification(EnumClassificationType.LITTLE)
                .opinion("Opinion")
                .pointScale(EnumPointScale.VERYGOOD)
                .dthreg(LocalDateTime.now())
                .userreg("User Reg")
                .build();
    }

}
