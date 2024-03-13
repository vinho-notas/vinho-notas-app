package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.TastingConverter;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import com.vinhonotas.degustacao.domain.enums.EnumTastingType;
import com.vinhonotas.degustacao.infraestructure.TastingRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingInputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TastingServiceImplTest {

    @InjectMocks
    private TastingServiceImpl service;

    @Mock
    private TastingRepository repository;
    @Mock
    private TastingConverter converter;

    private TastingInputDTO inputDTO;
    private TastingEntity entity;

    @BeforeEach
    void setUp() {
        inputDTO = createTastingInputDTO();
        entity = createTastingEntity();
    }

    @Test
    @DisplayName("Deve criar uma degustação")
    void testCreate() {
        when(converter.toEntity(inputDTO)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);

        TastingEntity result = assertDoesNotThrow(() -> service.create(inputDTO));
        assertNotNull(result);
        assertEquals(entity.getTastingData(), result.getTastingData());
        assertEquals(entity.getTastingType(), result.getTastingType());
        assertEquals(entity.getTastingCards(), result.getTastingCards());

        verify(converter).toEntity(inputDTO);
        verify(repository).save(entity);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar uma degustação")
    void testCreateThrowBadRequestException() {
        when(converter.toEntity(inputDTO)).thenReturn(entity);
        when(repository.save(entity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_TASTING));

        Exception ex = assertThrows(Exception.class, () -> service.create(inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING_TASTING, ex.getMessage());

        verify(converter).toEntity(inputDTO);
        verify(repository).save(entity);
    }

    @Test
    @DisplayName("Deve retornar uma lista de degustações")
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(entity));

        var result = assertDoesNotThrow(() -> service.getAll());
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(entity.getTastingData(), result.stream().toList().get(0).getTastingData());
        assertEquals(entity.getTastingType(), result.stream().toList().get(0).getTastingType());
        assertEquals(entity.getTastingCards(), result.stream().toList().get(0).getTastingCards());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma lista de degustações")
    void testGetAllThrowBadRequestException() {
        when(repository.findAll()).thenReturn(List.of());

        Exception ex = assertThrows(Exception.class, () -> service.getAll());
        assertEquals(MessagesConstants.TASTING_NOT_FOUND, ex.getMessage());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Deve retornar uma degustação por id")
    void testGetById() {
        when(repository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));

        TastingEntity result = assertDoesNotThrow(() -> service.getById(entity.getId()));
        assertNotNull(result);
        assertEquals(entity.getTastingData(), result.getTastingData());
        assertEquals(entity.getTastingType(), result.getTastingType());
        assertEquals(entity.getTastingCards(), result.getTastingCards());
        verify(repository).findById(entity.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma degustação por id")
    void testGetByIdThrowBadRequestException() {
        when(repository.findById(entity.getId())).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () -> service.getById(entity.getId()));
        assertEquals(MessagesConstants.TASTING_NOT_FOUND, ex.getMessage());
        verify(repository).findById(entity.getId());
    }

    @Test
    @DisplayName("Deve atualizar uma degustação")
    void testUpdate() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(converter.toEntityUpdate(inputDTO, entity.getId(), entity)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);

        entity.setTastingData(LocalDate.now().minusDays(1));
        entity.setTastingType(EnumTastingType.BLIND);

        TastingEntity result = assertDoesNotThrow(() -> service.update(entity.getId(), inputDTO));
        assertNotNull(result);
        assertEquals(LocalDate.now().minusDays(1), result.getTastingData());
        assertEquals(EnumTastingType.BLIND, result.getTastingType());

        verify(repository).findById(entity.getId());
        verify(converter).toEntityUpdate(inputDTO, entity.getId(), entity);
        verify(repository).save(entity);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar uma degustação")
    void testUpdateThrowBadRequestException() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(converter.toEntityUpdate(inputDTO, entity.getId(), entity)).thenReturn(entity);
        when(repository.save(entity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_TASTING));

        Exception ex = assertThrows(Exception.class, () -> service.update(entity.getId(), inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING_TASTING, ex.getMessage());

        verify(repository).findById(entity.getId());
        verify(converter).toEntityUpdate(inputDTO, entity.getId(), entity);
        verify(repository).save(entity);
    }

    @Test
    @DisplayName("Deve deletar uma degustação")
    void testDelete() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        assertDoesNotThrow(() -> service.delete(entity.getId()));

        verify(repository).findById(entity.getId());
        verify(repository).deleteById(entity.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar uma degustação")
    void testDeleteThrowBadRequestException() {
        when(repository.findById(entity.getId())).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () -> service.delete(entity.getId()));
        assertEquals(MessagesConstants.TASTING_NOT_FOUND, ex.getMessage());
        verify(repository).findById(entity.getId());

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_TASTING)).when(repository).deleteById(entity.getId());

        ex = assertThrows(Exception.class, () -> service.delete(entity.getId()));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING_TASTING, ex.getMessage());
    }

    private TastingInputDTO createTastingInputDTO() {
        return TastingInputDTO.builder()
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE.getCode())
                .tastingCards(Set.of(TastingCardInputDTO.builder().build()))
                .build();
    }

    private TastingEntity createTastingEntity() {
        return TastingEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE)
                .tastingCards(Set.of(Mockito.mock(TastingCardEntity.class)))
                .build();
    }

}
