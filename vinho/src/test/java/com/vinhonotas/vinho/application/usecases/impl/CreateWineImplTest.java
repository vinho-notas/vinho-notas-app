//package com.vinhonotas.vinho.application.usecases.impl;
//
//import com.vinhonotas.vinho.application.gateways.CreateWineRepository;
//import com.vinhonotas.vinho.domain.entities.wine.PurchaseInfo;
//import com.vinhonotas.vinho.domain.entities.wine.WineDetails;
//import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
//import com.vinhonotas.vinho.domain.entities.wine.WineOrigin;
//import com.vinhonotas.vinho.infraestructure.controller.dtos.input.PurchaseInfoDTO;
//import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineDetailsDTO;
//import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
//import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineOriginDTO;
//import com.vinhonotas.vinho.v1.domain.entities.exceptions.BadRequestException;
//import com.vinhonotas.vinho.v1.domain.enums.EnumWineClassification;
//import com.vinhonotas.vinho.v1.domain.enums.EnumWineType;
//import com.vinhonotas.vinho.v1.utils.MessagesConstants;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Slf4j
//@ExtendWith(MockitoExtension.class)
//class CreateWineImplTest {
//
//    @InjectMocks
//    private CreateWineImpl createWineImpl;
//
//    @Mock
//    private CreateWineRepository createWineRepository;
//
//    @Test
//    void testCreateWineSuccess() {
//        WineDetailsDTO details = new WineDetailsDTO(
//                EnumWineType.REDWINE,
//                EnumWineClassification.DRYWINE,
//                "13%",
//                750,
//                "Cabernet Sauvignon",
//                "DFJ Vinhos",
//                "16°C");
//
//        PurchaseInfoDTO purchaseInfo = new PurchaseInfoDTO(
//                BigDecimal.valueOf(50.00),
//                "Supermercado ABC",
//                LocalDate.now());
//        WineOriginDTO origin = new WineOriginDTO(
//                "Portugal",
//                "Lisboa",
//                "2020",
//                "1 ano",
//                "1 mês em garrafa",
//                "Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com " +
//                        "carnes de caça, queijos de massa dura, com longa maturação."
//        );
//        WineDetails wineDetails = new WineDetails(
//                EnumWineType.REDWINE,
//                EnumWineClassification.DRYWINE,
//                "13%",
//                750,
//                "Cabernet Sauvignon",
//                "DFJ Vinhos",
//                "16°C");
//
//        PurchaseInfo info = new PurchaseInfo(BigDecimal.valueOf(50.00), "Supermercado ABC", LocalDate.now());
//        WineOrigin wineOrigin = new WineOrigin("Portugal", "Lisboa", "2020", "1 ano", "1 mês em garrafa",
//                "Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com " +
//                        "carnes de caça, queijos de massa dura, com longa maturação.");
//        WineDomain wineDomain = new WineDomain("", "Portada Winemaker's Selection 2020", wineDetails, info, wineOrigin);
//
//        Mockito.when(createWineRepository.createWine(Mockito.any(WineInputDTO.class))).thenReturn(wineDomain);
//
//        WineInputDTO wine = new WineInputDTO("Portada Winemaker's Selection 2020", details, purchaseInfo, origin);
//
//        WineDomain wineCreated = assertDoesNotThrow(() -> createWineImpl.createWine(wine));
//        assertNotNull(wineCreated);
//
//        log.info("createWine :: Vinho criado: {}", wineCreated);
//
//    }
//
//    @Test
//    void testCreateWithException() {
//        WineInputDTO wine = new WineInputDTO("Portada Winemaker's Selection 2020", null, null, null);
//        Mockito.when(createWineRepository.createWine(wine)).thenThrow(new BadRequestException(MessagesConstants.ERROR_CREATE_WINE));
//
//        BadRequestException ex = assertThrows(BadRequestException.class, () -> createWineImpl.createWine(wine));
//        assertEquals(MessagesConstants.ERROR_CREATE_WINE, ex.getMessage());
//        Mockito.verify(createWineRepository, Mockito.times(1)).createWine(wine);
//    }
//}