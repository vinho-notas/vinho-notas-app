//package com.vinhonotas.degustacao.application.services.impl;
//
//import com.vinhonotas.degustacao.application.converters.TastingCardConverter;
//import com.vinhonotas.degustacao.domain.entities.*;
//import com.vinhonotas.degustacao.domain.enums.EnumPointScale;
//import com.vinhonotas.degustacao.infraestructure.TastingCardRepository;
//import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
//import com.vinhonotas.degustacao.utils.MessagesConstants;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class TastingCardServiceImplTest {
//
//    @InjectMocks
//    private TastingCardServiceImpl tastingCardService;
//
//    @Mock
//    private TastingCardRepository tastingCardRepository;
//    @Mock
//    private TastingCardConverter tastingCardConverter;
//
//    private TastingCardEntity entity;
//    private TastingCardInputDTO inputDTO;
//
//    @BeforeEach
//    void setUp() {
//        entity = createTastingCardEntity();
//        inputDTO = createTastingCardInputDTO();
//    }
//
//    @Test
//    @DisplayName("Deve criar um cartão de degustação")
//    void testCreate() {
//        when(tastingCardConverter.toEntity(inputDTO)).thenReturn(entity);
//        when(tastingCardRepository.save(entity)).thenReturn(entity);
//
//        TastingCardEntity result = assertDoesNotThrow(() -> tastingCardService.create(inputDTO));
//
//        assertNotNull(result);
//        assertEquals(entity.getTastingData(), result.getTastingData());
//        assertEquals(entity.getWineTasted(), result.getWineTasted());
//        assertEquals(entity.getHarvest(), result.getHarvest());
//        assertEquals(entity.getGrapes(), result.getGrapes());
//        assertEquals(entity.getCountry(), result.getCountry());
//        assertEquals(entity.getRegion(), result.getRegion());
//        assertEquals(entity.getVisualInspection(), result.getVisualInspection());
//        assertEquals(entity.getOlfactoryInspection(), result.getOlfactoryInspection());
//        assertEquals(entity.getGustatoryInspection(), result.getGustatoryInspection());
//        assertEquals(entity.getOpinion(), result.getOpinion());
//        assertEquals(entity.getPointScale(), result.getPointScale());
//        assertEquals(entity.getTasting(), result.getTasting());
//
//        verify(tastingCardConverter, times(1)).toEntity(inputDTO);
//        verify(tastingCardRepository, times(1)).save(entity);
//    }
//
//    @Test
//    @DisplayName("Deve lançar uma exceção ao criar um cartão de degustação")
//    void testCreateThrowException() {
//        when(tastingCardConverter.toEntity(inputDTO)).thenReturn(entity);
//        when(tastingCardRepository.save(entity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_TASTING_CARD));
//
//        Exception ex = assertThrows(Exception.class, () -> tastingCardService.create(inputDTO));
//        assertEquals(MessagesConstants.ERROR_WHEN_SAVING_TASTING_CARD, ex.getMessage());
//
//        verify(tastingCardConverter, times(1)).toEntity(inputDTO);
//        verify(tastingCardRepository, times(1)).save(entity);
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma lista de cartões de degustação")
//    void testGetAll() {
//        when(tastingCardRepository.findAll()).thenReturn(List.of(entity));
//
//        var result = assertDoesNotThrow(() -> tastingCardService.getAll());
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        assertEquals(1, result.size());
//        assertEquals(entity.getTastingData(), result.stream().toList().get(0).getTastingData());
//        assertEquals(entity.getWineTasted(), result.stream().toList().get(0).getWineTasted());
//        assertEquals(entity.getHarvest(), result.stream().toList().get(0).getHarvest());
//        assertEquals(entity.getGrapes(), result.stream().toList().get(0).getGrapes());
//        assertEquals(entity.getCountry(), result.stream().toList().get(0).getCountry());
//        assertEquals(entity.getRegion(), result.stream().toList().get(0).getRegion());
//        assertEquals(entity.getVisualInspection(), result.stream().toList().get(0).getVisualInspection());
//        assertEquals(entity.getOlfactoryInspection(), result.stream().toList().get(0).getOlfactoryInspection());
//        assertEquals(entity.getGustatoryInspection(), result.stream().toList().get(0).getGustatoryInspection());
//        assertEquals(entity.getOpinion(), result.stream().toList().get(0).getOpinion());
//        assertEquals(entity.getPointScale(), result.stream().toList().get(0).getPointScale());
//        assertEquals(entity.getTasting(), result.stream().toList().get(0).getTasting());
//
//        verify(tastingCardRepository, times(1)).findAll();
//    }
//
//    @Test
//    @DisplayName("Deve lançar uma exceção ao retornar uma lista de cartões de degustação")
//    void testGetAllThrowException() {
//        when(tastingCardRepository.findAll()).thenReturn(List.of());
//
//        Exception ex = assertThrows(Exception.class, () -> tastingCardService.getAll());
//        assertEquals(MessagesConstants.TASTING_CARD_NOT_FOUND, ex.getMessage());
//
//        verify(tastingCardRepository, times(1)).findAll();
//    }
//
//    @Test
//    @DisplayName("Deve retornar um cartão de degustação por id")
//    void testGetById() {
//        when(tastingCardRepository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));
//
//        TastingCardEntity result = assertDoesNotThrow(() -> tastingCardService.getById(entity.getId()));
//
//        assertNotNull(result);
//        assertEquals(entity.getTastingData(), result.getTastingData());
//        assertEquals(entity.getWineTasted(), result.getWineTasted());
//        assertEquals(entity.getHarvest(), result.getHarvest());
//        assertEquals(entity.getGrapes(), result.getGrapes());
//        assertEquals(entity.getCountry(), result.getCountry());
//        assertEquals(entity.getRegion(), result.getRegion());
//        assertEquals(entity.getVisualInspection(), result.getVisualInspection());
//        assertEquals(entity.getOlfactoryInspection(), result.getOlfactoryInspection());
//        assertEquals(entity.getGustatoryInspection(), result.getGustatoryInspection());
//        assertEquals(entity.getOpinion(), result.getOpinion());
//        assertEquals(entity.getPointScale(), result.getPointScale());
//        assertEquals(entity.getTasting(), result.getTasting());
//
//        verify(tastingCardRepository, times(1)).findById(entity.getId());
//    }
//
//    @Test
//    @DisplayName("Deve lançar uma exceção ao retornar um cartão de degustação por id")
//    void testGetByIdThrowException() {
//        when(tastingCardRepository.findById(entity.getId())).thenReturn(java.util.Optional.empty());
//
//        Exception ex = assertThrows(Exception.class, () -> tastingCardService.getById(entity.getId()));
//        assertEquals(MessagesConstants.TASTING_CARD_NOT_FOUND, ex.getMessage());
//
//        verify(tastingCardRepository, times(1)).findById(entity.getId());
//    }
//
//    @Test
//    @DisplayName("Deve atualizar um cartão de degustação")
//    void testUpdate() {
//        when(tastingCardRepository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));
//        when(tastingCardConverter.toEntityUpdate(inputDTO, entity.getId(), entity)).thenReturn(entity);
//        when(tastingCardRepository.save(entity)).thenReturn(entity);
//
//        entity.setOpinion("New opinion");
//        entity.setHarvest("2000");
//
//        TastingCardEntity result = assertDoesNotThrow(() -> tastingCardService.update(entity.getId(), inputDTO));
//
//        assertNotNull(result);
//        assertEquals("2000", result.getHarvest());
//        assertEquals("New opinion", result.getOpinion());
//
//        verify(tastingCardRepository, times(1)).findById(entity.getId());
//        verify(tastingCardConverter, times(1)).toEntityUpdate(inputDTO, entity.getId(), entity);
//        verify(tastingCardRepository, times(1)).save(entity);
//    }
//
//    @Test
//    @DisplayName("Deve lançar uma exceção ao atualizar um cartão de degustação")
//    void testUpdateThrowException() {
//        when(tastingCardRepository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));
//        when(tastingCardConverter.toEntityUpdate(inputDTO, entity.getId(), entity)).thenReturn(entity);
//        when(tastingCardRepository.save(entity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_TASTING_CARD));
//
//        Exception ex = assertThrows(Exception.class, () -> tastingCardService.update(entity.getId(), inputDTO));
//        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING_TASTING_CARD, ex.getMessage());
//
//        verify(tastingCardRepository, times(1)).findById(entity.getId());
//        verify(tastingCardConverter, times(1)).toEntityUpdate(inputDTO, entity.getId(), entity);
//        verify(tastingCardRepository, times(1)).save(entity);
//    }
//
//    @Test
//    @DisplayName("Deve deletar um cartão de degustação")
//    void testDelete() {
//        when(tastingCardRepository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));
//        doNothing().when(tastingCardRepository).deleteById(entity.getId());
//
//        assertDoesNotThrow(() -> tastingCardService.delete(entity.getId()));
//
//        verify(tastingCardRepository, times(1)).findById(entity.getId());
//        verify(tastingCardRepository, times(1)).deleteById(entity.getId());
//    }
//
//    @Test
//    @DisplayName("Deve lançar uma exceção ao deletar um cartão de degustação")
//    void testDeleteThrowException() {
//        when(tastingCardRepository.findById(entity.getId())).thenReturn(Optional.empty());
//
//        Exception ex = assertThrows(Exception.class, () -> tastingCardService.delete(entity.getId()));
//        assertEquals(MessagesConstants.TASTING_CARD_NOT_FOUND, ex.getMessage());
//
//        verify(tastingCardRepository, times(1)).findById(entity.getId());
//
//        when(tastingCardRepository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));
//        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_TASTING_CARD)).when(tastingCardRepository).deleteById(entity.getId());
//
//        ex = assertThrows(Exception.class, () -> tastingCardService.delete(entity.getId()));
//        assertEquals(MessagesConstants.ERROR_WHEN_DELETING_TASTING_CARD, ex.getMessage());
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
//    private TastingCardEntity createTastingCardEntity() {
//        return TastingCardEntity.builder()
//                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
//                .tastingData(LocalDate.now())
//                .wineTasted("Wine Tasted")
//                .harvest("2020")
//                .grapes("Grapes")
//                .country("Chile")
//                .region("Vale Central")
//                .visualInspection(Mockito.mock(VisualInspectionEntity.class))
//                .olfactoryInspection(Mockito.mock(OlfactoryInspectionEntity.class))
//                .gustatoryInspection(Mockito.mock(GustatoryInspectionEntity.class))
//                .opinion("Opinion about the wine")
//                .pointScale(EnumPointScale.CLASSIC)
//                .tasting(Mockito.mock(TastingEntity.class))
//                .build();
//    }
//}