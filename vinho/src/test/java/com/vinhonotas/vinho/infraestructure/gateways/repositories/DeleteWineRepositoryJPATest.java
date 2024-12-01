package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import com.vinhonotas.vinho.utils.MessagesConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteWineRepositoryJPATest {

    @InjectMocks
    private DeleteWineRepositoryJPA deleteWineRepositoryJPA;

    @Mock
    private WineRepository wineRepository;

    @Test
    @DisplayName("Deve deletar um vinho no banco de dados")
    void testDeveDeletarUmVinhoNoBancoDeDados() {
        doNothing().when(wineRepository).deleteById(any(UUID.class));

        assertDoesNotThrow(() -> deleteWineRepositoryJPA.deleteWineById(UUID.randomUUID()));
        verify(wineRepository).deleteById(any(UUID.class));
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar deletar um vinho no banco de dados")
    void testDeveLancarUmaExcecaoAoDeletarUmVinhoNoBancoDeDados() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_DELETE_WINE)).when(wineRepository).deleteById(any(UUID.class));

        Exception exception = assertThrows(Exception.class, () -> deleteWineRepositoryJPA.deleteWineById(UUID.randomUUID()));
        assertEquals(MessagesConstants.ERROR_DELETE_WINE, exception.getMessage());
        verify(wineRepository).deleteById(any(UUID.class));
    }


}