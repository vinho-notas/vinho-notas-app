package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.DeleteWineRepository;
import com.vinhonotas.vinho.application.gateways.RetrieveWineByIdRepository;
import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.domain.entities.exceptions.WineNotFoundException;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class DeleteWineImplTest {
    private static final String ID = "123e4567-e89b-12d3-a456-426614174000";

    @InjectMocks
    private DeleteWineImpl deleteWineImpl;

    @Mock
    private DeleteWineRepository deleteWineRepository;
    @Mock
    private RetrieveWineByIdRepository retrieveWineByIdRepository;

    private WineEntity wineRetrieved;

    @BeforeEach
    void setUp() {
        wineRetrieved = createWineEntity();
    }
    @Test
    @DisplayName("Deve deletar um vinho pelo id")
    void deleteWineById() {
        Mockito.when(retrieveWineByIdRepository.retrieveWineById(ID)).thenReturn(wineRetrieved);

        assertDoesNotThrow(() -> deleteWineImpl.deleteWineById(UUID.fromString(ID)));
        assertNotNull(wineRetrieved);
        Mockito.verify(deleteWineRepository, Mockito.times(1)).deleteWineById(UUID.fromString(ID));
        Mockito.verify(retrieveWineByIdRepository, Mockito.times(1)).retrieveWineById(ID);
    }

    @Test
    @DisplayName("Deve retornar WineNotFoundException ao deletar um vinho pelo id")
    void deleteWineByIdErrorWineNotFoundException() {
        Mockito.when(retrieveWineByIdRepository.retrieveWineById(ID)).thenThrow(new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND));

        Exception exception = assertThrows(Exception.class, () -> deleteWineImpl.deleteWineById(UUID.fromString(ID)));
        assertEquals(MessagesConstants.ERROR_WINE_NOT_FOUND, exception.getMessage());
        Mockito.verify(deleteWineRepository, Mockito.never()).deleteWineById(UUID.fromString(ID));
        Mockito.verify(retrieveWineByIdRepository, Mockito.times(1)).retrieveWineById(ID);
    }

    @Test
    @DisplayName("Deve retornar BadRequestException ao deletar um vinho pelo id")
    void deleteWineByIdErrorBadRequestException() {
        Mockito.when(retrieveWineByIdRepository.retrieveWineById(ID)).thenReturn(wineRetrieved);
        doThrow(new BadRequestException(MessagesConstants.ERROR_DELETE_WINE)).when(deleteWineRepository).deleteWineById(UUID.fromString(ID));

        Exception exception = assertThrows(Exception.class, () -> deleteWineImpl.deleteWineById(UUID.fromString(ID)));
        assertEquals(MessagesConstants.ERROR_DELETE_WINE, exception.getMessage());
        Mockito.verify(deleteWineRepository, Mockito.times(1)).deleteWineById(UUID.fromString(ID));
        Mockito.verify(retrieveWineByIdRepository, Mockito.times(1)).retrieveWineById(ID);
    }

    @Test
    @DisplayName("wineRetrieved deve ser nulo")
    void deleteWineByIdErrorWineRetrievedNull() {
        Mockito.when(retrieveWineByIdRepository.retrieveWineById(ID)).thenReturn(null);

        assertDoesNotThrow(() -> deleteWineImpl.deleteWineById(UUID.fromString(ID)));
        Mockito.verify(deleteWineRepository, Mockito.never()).deleteWineById(UUID.fromString(ID));
        Mockito.verify(retrieveWineByIdRepository, Mockito.times(1)).retrieveWineById(ID);
    }


    private WineEntity createWineEntity() {
        return WineEntity.builder()
                .id(UUID.fromString(ID))
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
