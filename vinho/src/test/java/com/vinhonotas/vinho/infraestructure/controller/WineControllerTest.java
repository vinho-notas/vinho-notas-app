package com.vinhonotas.vinho.infraestructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.vinho.application.usecases.CreateWine;
import com.vinhonotas.vinho.application.usecases.RetrieveWineById;
import com.vinhonotas.vinho.application.usecases.RetrieveWines;
import com.vinhonotas.vinho.application.usecases.UpdateWine;
import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.domain.entities.exceptions.WineNotFoundException;
import com.vinhonotas.vinho.domain.entities.wine.PurchaseInfo;
import com.vinhonotas.vinho.domain.entities.wine.WineDetails;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.domain.entities.wine.WineOrigin;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.PurchaseInfoDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineDetailsDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineOriginDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.output.WineOutputDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineDomainMapper;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineEntityMapper;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import com.vinhonotas.vinho.utils.EnumConverter;
import com.vinhonotas.vinho.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest(WineController.class)
class WineControllerTest {

    private final String BASE_URL = "/api/v1/wines";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateWine createWine;
    @MockBean
    private RetrieveWineById retrieveWineById;
    @MockBean
    private RetrieveWines retrieveWines;
    @MockBean
    private UpdateWine updateWine;
    @MockBean
    private WineDomainMapper wineDomainMapper;
    @MockBean
    private WineEntityMapper wineEntityMapper;

    private WineInputDTO wineInputDTO;
    private WineOutputDTO wineOutputDTO;
    private WineEntity wineEntity;
    private WineDomain wineDomain;

    @BeforeEach
    void setUp() {
        wineInputDTO = createWineInputDTO();
        wineOutputDTO = createWineOutputDTO();
        wineEntity = createWineEntity();
        wineDomain = createWineDomain();
    }

    @Test
    @DisplayName("Deve criar um vinho")
    void testCreateWine() throws Exception {
        when(wineDomainMapper.toWineDomain(wineInputDTO)).thenReturn(wineDomain);
        when(createWine.createWine(wineDomain)).thenReturn(wineEntity);
        when(wineEntityMapper.toWineOutputDTO(wineEntity)).thenReturn(wineOutputDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wineInputDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(wineOutputDTO)));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar criar um vinho")
    void testCreateWineBadRequest() throws Exception {
        when(wineDomainMapper.toWineDomain(wineInputDTO)).thenReturn(wineDomain);
        when(createWine.createWine(wineDomain)).thenThrow(new BadRequestException("Erro ao criar vinho"));

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wineInputDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Erro ao criar vinho"));
    }

    @Test
    @DisplayName("Deve retornar um vinho pelo id")
    void testRetrieveWineById() throws Exception {
        String id = "562aaf01-f0c6-4bd6-aa22-30b4596e217f";

        when(retrieveWineById.retrieveWineById(id)).thenReturn(wineEntity);
        when(wineEntityMapper.toWineOutputDTO(wineEntity)).thenReturn(wineOutputDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(wineOutputDTO)));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar retornar um vinho pelo id")
    void testRetrieveWineByIdBadRequest() throws Exception {
        String id = "562aaf01-f0c6-4bd6-aa22-30b4596e217f";

        when(retrieveWineById.retrieveWineById(id)).thenThrow(new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Nenhum vinho encontrado"));
    }

    @Test
    @DisplayName("Deve retornar uma lista de vinhos")
    void testRetrieveAllWines() throws Exception {
        when(retrieveWines.retrieveAllWines()).thenReturn(List.of(wineEntity));
        when(wineEntityMapper.toWineOutputDTOList(List.of(wineEntity))).thenReturn(List.of(wineOutputDTO));

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(List.of(wineOutputDTO))));
    }

    @Test
    @DisplayName("Deve lançar WineNotFoundException ao tentar retornar uma lista de vinhos")
    void testRetrieveAllWinesBadRequest() throws Exception {
        when(retrieveWines.retrieveAllWines()).thenThrow(new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Nenhum vinho encontrado"));
    }

    @Test
    @DisplayName("Deve atualizar um vinho pelo id")
    void testUpdateWine() throws Exception {
        String id = "562aaf01-f0c6-4bd6-aa22-30b4596e217f";

        when(wineDomainMapper.toWineDomain(wineInputDTO)).thenReturn(wineDomain);
        when(updateWine.updateWine(id, wineDomain)).thenReturn(wineEntity);
        when(wineEntityMapper.toWineOutputDTO(wineEntity)).thenReturn(wineOutputDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wineInputDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(wineOutputDTO)));
    }

    @Test
    @DisplayName("Deve lançar WineNotFoundException ao tentar atualizar um vinho pelo id")
    void testUpdateWineBadRequest() throws Exception {
        WineRepository wineRepository = Mockito.mock(WineRepository.class);
        String id = "562aaf01-f0c6-4bd6-aa22-30b4596e217f";

        when(wineDomainMapper.toWineDomain(wineInputDTO)).thenReturn(wineDomain);
        when(wineRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());
        when(updateWine.updateWine(id, wineDomain)).thenThrow(new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wineInputDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Nenhum vinho encontrado"));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar atualizar um vinho pelo id")
    void testUpdateWineBadRequestException() throws Exception {
        String id = "562aaf01-f0c6-4bd6-aa22-30b4596e217f";

        when(wineDomainMapper.toWineDomain(wineInputDTO)).thenReturn(wineDomain);
        when(updateWine.updateWine(id, wineDomain)).thenThrow(new BadRequestException(MessagesConstants.ERROR_UPDATE_WINE_DATA));

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wineInputDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Erro ao atualizar dados do vinho"));
    }

    private WineDomain createWineDomain() {
        return WineDomain.builder()
                .sku("Miliasso Barolo DOCG 2020", EnumWineType.REDWINE, EnumWineClassification.DRYWINE, "2020", "Italy")
                .name("Miliasso Barolo DOCG 2020")
                .wineDetails(WineDetails.builder()
                        .wineType(EnumWineType.REDWINE)
                        .wineClassification(EnumWineClassification.DRYWINE)
                        .alcoholContent("14.5%")
                        .volumeMl(750)
                        .grape("Nebbiolo")
                        .winery("Cantine Pover")
                        .serviceTemperature("16-18°C")
                        .build()
                )
                .purchaseInfo(PurchaseInfo.builder()
                        .price(new BigDecimal("150.00"))
                        .purchaseLocation("Vinhos do Mundo")
                        .purchaseDate(LocalDate.now())
                        .build()
                )
                .wineOrigin(WineOrigin.builder()
                        .country("Italy")
                        .region("Piemonte")
                        .harvest("2020")
                        .guardTime("10 years")
                        .maturation("24 months in oak barrels")
                        .harmonization("Red meats and mature cheeses")
                        .build()
                )
                .build();
    }

    private WineInputDTO createWineInputDTO() {
        return new WineInputDTO(
                "Miliasso Barolo DOCG 2020",
                new WineDetailsDTO(
                        EnumConverter.toString(EnumWineType.REDWINE),
                        EnumConverter.toString(EnumWineClassification.DRYWINE),
                        "14.5%",
                        "750",
                        "Nebbiolo",
                        "Cantine Pover",
                        "16-18°C"
                ),
                new PurchaseInfoDTO(
                        new BigDecimal("150.00"),
                        "Vinhos do Mundo",
                        LocalDate.now()
                ),
                new WineOriginDTO(
                        "Italy",
                        "Piemonte",
                        "2020",
                        "10 years",
                        "24 months in oak barrels",
                        "Red meats and mature cheeses"
                )
        );
    }

    private WineOutputDTO createWineOutputDTO() {
        return WineOutputDTO.builder()
                .id(UUID.fromString("562aaf01-f0c6-4bd6-aa22-30b4596e217f"))
                .sku("MiREDR2020It")
                .name("Miliasso Barolo DOCG 2020")
                .wineType(EnumConverter.toString(EnumWineType.REDWINE))
                .wineClassification(EnumConverter.toString(EnumWineClassification.DRYWINE))
                .alcoholContent("14.5%")
                .volumeMl("750")
                .grape("Nebbiolo")
                .winery("Cantine Pover")
                .serviceTemperature("16-18°C")
                .price(new BigDecimal("150.00"))
                .purchaseLocation("Vinhos do Mundo")
                .purchaseDate(LocalDate.now())
                .country("Italy")
                .region("Piemonte")
                .harvest("2020")
                .guardTime("10 years")
                .maturation("24 months in oak barrels")
                .harmonization("Red meats and mature cheeses")
                .build();
    }

    private WineEntity createWineEntity() {
        return WineEntity.builder()
                .id(UUID.fromString("562aaf01-f0c6-4bd6-aa22-30b4596e217f"))
                .sku("MiREDR2020It")
                .name("Miliasso Barolo DOCG 2020")
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .alcoholContent("14.5%")
                .volumeMl(750)
                .grape("Nebbiolo")
                .winery("Cantine Pover")
                .serviceTemperature("16-18°C")
                .price(new BigDecimal("150.00"))
                .purchaseLocation("Vinhos do Mundo")
                .purchaseDate(LocalDate.now())
                .country("Italy")
                .region("Piemonte")
                .harvest("2020")
                .guardTime("10 years")
                .maturation("24 months in oak barrels")
                .harmonization("Red meats and mature cheeses")
                .build();
    }

}
