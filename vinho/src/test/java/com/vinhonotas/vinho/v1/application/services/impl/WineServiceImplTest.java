package com.vinhonotas.vinho.v1.application.services.impl;

import com.vinhonotas.vinho.v1.application.converters.WineConverter;
import com.vinhonotas.vinho.v1.application.services.impl.WineServiceImpl;
import com.vinhonotas.vinho.v1.domain.entities.WineEntity;
import com.vinhonotas.vinho.v1.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineType;
import com.vinhonotas.vinho.v1.infraestructure.WineRepository;
import com.vinhonotas.vinho.v1.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.vinho.v1.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WineServiceImplTest {

    @InjectMocks
    private WineServiceImpl wineServiceImpl;

    @Mock
    private WineRepository wineRepository;
    @Mock
    private WineConverter wineConverter;
    private WineInputDTO redWineInputDTO;
    private WineEntity redWineEntity;

    @BeforeEach
    void setUp() {
        redWineInputDTO = createRedWineInputDTO();
        redWineEntity = createRedWineEntity();
    }

    @Test
    void testCreate() {
        when(wineConverter.toEntity(redWineInputDTO)).thenReturn(redWineEntity);
        when(wineRepository.save(redWineEntity)).thenReturn(redWineEntity);

        WineEntity wineCreated = assertDoesNotThrow(() -> wineServiceImpl.create(redWineInputDTO));
        assertNotNull(wineCreated);
        assertEquals(redWineEntity.getId(), wineCreated.getId());
        verify(wineConverter, times(1)).toEntity(redWineInputDTO);
        verify(wineRepository, times(1)).save(redWineEntity);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar criar um vinho")
    void testCreateWithException() {
        when(wineRepository.save(redWineEntity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_CREATE_WINE));

        Exception exception = assertThrows(Exception.class, () -> wineServiceImpl.create(redWineInputDTO));
        assertEquals(MessagesConstants.ERROR_CREATE_WINE, exception.getMessage());
        verify(wineConverter, times(1)).toEntity(redWineInputDTO);
        verify(wineRepository, times(0)).save(redWineEntity);
    }

    @Test
    @DisplayName("Deve retornar uma lista de vinhos")
    void testGetAll() {
        when(wineRepository.findAll()).thenReturn(List.of(redWineEntity, redWineEntity));

        List<WineEntity> wineList = assertDoesNotThrow(() -> wineServiceImpl.getAll());
        assertNotNull(wineList);
        assertEquals(2, wineList.size());
        verify(wineRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar retornar uma lista de vinhos")
    void testGetAllWithException() {
        when(wineRepository.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(Exception.class, () -> wineServiceImpl.getAll());
        assertEquals(MessagesConstants.ERROR_WINE_NOT_FOUND, exception.getMessage());
        verify(wineRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar um vinho pelo id")
    void testGetById() {
        when(wineRepository.findById(redWineEntity.getId())).thenReturn(Optional.of(redWineEntity));

        WineEntity wineEntity = assertDoesNotThrow(() -> wineServiceImpl.getById(redWineEntity.getId()));
        assertNotNull(wineEntity);
        assertEquals(redWineEntity.getId(), wineEntity.getId());
        verify(wineRepository, times(1)).findById(redWineEntity.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar retornar um vinho pelo id")
    void testGetByIdWithException() {
        when(wineRepository.findById(redWineEntity.getId())).thenThrow(new BadRequestException(MessagesConstants.ERROR_WINE_NOT_FOUND));

        Exception exception = assertThrows(Exception.class, () -> wineServiceImpl.getById(redWineEntity.getId()));
        assertEquals(MessagesConstants.ERROR_WINE_NOT_FOUND, exception.getMessage());
        verify(wineRepository, times(1)).findById(redWineEntity.getId());
    }

    @Test
    @DisplayName("Deve atualizar um vinho")
    void testUpdate() {
        redWineEntity.setPrice(BigDecimal.valueOf(800.00));
        when(wineRepository.findById(redWineEntity.getId())).thenReturn(Optional.of(redWineEntity));
        when(wineConverter.toEntityUpdate(redWineEntity, redWineEntity.getId(), redWineInputDTO)).thenReturn(redWineEntity);
        when(wineRepository.save(redWineEntity)).thenReturn(redWineEntity);

        WineEntity wineUpdated = assertDoesNotThrow(() -> wineServiceImpl.update(redWineEntity.getId(), redWineInputDTO));
        assertNotNull(wineUpdated);
        assertEquals(BigDecimal.valueOf(800.00), wineUpdated.getPrice());
        verify(wineRepository, times(1)).findById(redWineEntity.getId());
        verify(wineConverter, times(1)).toEntityUpdate(redWineEntity, redWineEntity.getId(), redWineInputDTO);
        verify(wineRepository, times(1)).save(redWineEntity);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar atualizar um vinho")
    void testUpdateWithException() {
        when(wineRepository.findById(redWineEntity.getId())).thenReturn(Optional.of(redWineEntity));
        when(wineConverter.toEntityUpdate(redWineEntity, redWineEntity.getId(), redWineInputDTO)).thenReturn(redWineEntity);
        when(wineRepository.save(redWineEntity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_UPDATE_WINE_DATA));

        Exception exception = assertThrows(Exception.class, () -> wineServiceImpl.update(redWineEntity.getId(), redWineInputDTO));
        assertEquals(MessagesConstants.ERROR_UPDATE_WINE_DATA, exception.getMessage());
        verify(wineRepository, times(1)).findById(redWineEntity.getId());
        verify(wineConverter, times(1)).toEntityUpdate(redWineEntity, redWineEntity.getId(), redWineInputDTO);
        verify(wineRepository, times(1)).save(redWineEntity);
    }

    @Test
    @DisplayName("Deve deletar um vinho")
    void testDelete() {
        when(wineRepository.findById(redWineEntity.getId())).thenReturn(Optional.of(redWineEntity));
        doNothing().when(wineRepository).deleteById(redWineEntity.getId());

        assertDoesNotThrow(() -> wineServiceImpl.delete(redWineEntity.getId()));
        verify(wineRepository, times(1)).findById(redWineEntity.getId());
        verify(wineRepository, times(1)).deleteById(redWineEntity.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar um vinho")
    void testDeleteWithException() {
        when(wineRepository.findById(redWineEntity.getId())).thenReturn(Optional.of(redWineEntity));
        doThrow(new BadRequestException(MessagesConstants.ERROR_DELETE_WINE)).when(wineRepository).deleteById(redWineEntity.getId());

        Exception exception = assertThrows(Exception.class, () -> wineServiceImpl.delete(redWineEntity.getId()));
        assertEquals(MessagesConstants.ERROR_DELETE_WINE, exception.getMessage());
        verify(wineRepository, times(1)).findById(redWineEntity.getId());
        verify(wineRepository, times(1)).deleteById(redWineEntity.getId());
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando retornar uma lista de vinhos vazia ao chamar o método delete")
    void testDeleteWithExceptionWineNotFound() {
        when(wineRepository.findById(redWineEntity.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> wineServiceImpl.delete(redWineEntity.getId()));
        assertEquals(MessagesConstants.ERROR_WINE_NOT_FOUND, exception.getMessage());
        verify(wineRepository, times(1)).findById(redWineEntity.getId());
        verify(wineRepository, times(0)).deleteById(redWineEntity.getId());
    }


    private WineEntity createRedWineEntity() {
        return WineEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .alcoholContent("12.5")
                .volumeMl(750)
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature("17.0")
                .harvest("2020")
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

    private WineInputDTO createRedWineInputDTO() {
        return WineInputDTO.builder()
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE.getCode())
                .wineClassification(EnumWineClassification.DRYWINE.getCode())
                .alcoholContent("12.5")
                .volumeMl("750")
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature("17.0")
                .harvest("2020")
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

}