package com.vinhonotas.avaliacao.application.services.impl;

import com.vinhonotas.avaliacao.application.converters.PointScaleConverter;
import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import com.vinhonotas.avaliacao.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.avaliacao.domain.entities.exceptions.PointScaleNotFoundException;
import com.vinhonotas.avaliacao.domain.enums.EnumPointScale;
import com.vinhonotas.avaliacao.infraestructure.PointScaleRepository;
import com.vinhonotas.avaliacao.interfaces.dtos.inputs.PointScaleInputDTO;
import com.vinhonotas.avaliacao.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointScaleServiceImplTest {

    @InjectMocks
    private PointScaleServiceImpl pointScaleService;

    @Mock
    private PointScaleRepository pointScaleRepository;
    @Mock
    private PointScaleConverter pointScaleConverter;

    private PointScaleEntity pointScaleOne;
    private PointScaleEntity pointScaleTwo;
    private PointScaleEntity pointScaleThree;
    private PointScaleInputDTO pointScaleInputDTOOne;

    @BeforeEach
    void setUp() {
        pointScaleOne = createPointScaleOne();
        pointScaleTwo = createPointScaleTwo();
        pointScaleThree = createPointScaleThree();

        pointScaleInputDTOOne = createPointScaleInputDTOOne();
    }


    @Test
    @DisplayName("Deve criar um PointScaleEntity")
    void testCreate() {
        when(pointScaleConverter.toEntity(pointScaleInputDTOOne)).thenReturn(pointScaleOne);
        when(pointScaleRepository.save(pointScaleOne)).thenReturn(pointScaleOne);

        PointScaleEntity entity = assertDoesNotThrow(() -> pointScaleService.create(pointScaleInputDTOOne));
        assertNotNull(entity);
        assertEquals(pointScaleOne.getId(), entity.getId());
        verify(pointScaleConverter, times(1)).toEntity(pointScaleInputDTOOne);
        verify(pointScaleRepository, times(1)).save(pointScaleOne);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar um PointScaleEntity")
    void testCreateBadRequestException() {
        when(pointScaleConverter.toEntity(pointScaleInputDTOOne)).thenReturn(pointScaleOne);
        when(pointScaleRepository.save(pointScaleOne)).thenThrow(new BadRequestException(MessagesConstants.ERROR_CREATE_POINT_SCALE));

        Exception exception = assertThrows(Exception.class, () -> pointScaleService.create(pointScaleInputDTOOne));
        assertEquals(MessagesConstants.ERROR_CREATE_POINT_SCALE, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma lista de PointScaleEntity")
    void testGetAll() {
        when(pointScaleRepository.findAll()).thenReturn(List.of(pointScaleOne, pointScaleTwo, pointScaleThree));

        List<PointScaleEntity> list = assertDoesNotThrow(() -> pointScaleService.getAll());
        assertNotNull(list);
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
        verify(pointScaleRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao buscar uma lista de PointScaleEntity")
    void testGetAllBadRequestException() {
        when(pointScaleRepository.findAll()).thenThrow(new BadRequestException(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND));

        Exception exception = assertThrows(Exception.class, () -> pointScaleService.getAll());
        assertEquals(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar um PointScaleEntity ao se passar o id")
    void testGetById() {
        when(pointScaleRepository.findById(pointScaleOne.getId())).thenReturn(Optional.of(pointScaleOne));

        PointScaleEntity entity = assertDoesNotThrow(() -> pointScaleService.getById(pointScaleOne.getId()));
        assertNotNull(entity);
        assertEquals(pointScaleOne.getId(), entity.getId());
        verify(pointScaleRepository, times(1)).findById(pointScaleOne.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao buscar um PointScaleEntity ao se passar o id")
    void testGetByIdBadRequestException() {
        when(pointScaleRepository.findById(pointScaleOne.getId())).thenThrow(new PointScaleNotFoundException(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND));

        Exception exception = assertThrows(Exception.class, () -> pointScaleService.getById(pointScaleOne.getId()));
        assertEquals(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Deve atualizar um PointScaleEntity")
    void testUpdate() {
        pointScaleInputDTOOne.setPointScale(EnumPointScale.OUTSTANDING.getCode());

        when(pointScaleRepository.findById(pointScaleOne.getId())).thenReturn(Optional.of(pointScaleOne));
        when(pointScaleConverter.toEntityUpdate(pointScaleInputDTOOne, pointScaleOne.getId(), pointScaleOne)).thenReturn(pointScaleOne);
        when(pointScaleRepository.save(pointScaleOne)).thenReturn(pointScaleOne);


        PointScaleEntity pointScaleUpdated = assertDoesNotThrow(() -> pointScaleService.update(pointScaleOne.getId(), pointScaleInputDTOOne));
        assertNotNull(pointScaleUpdated);
        assertEquals(pointScaleOne.getId(), pointScaleUpdated.getId());
        assertEquals(pointScaleOne.getPointScale(), pointScaleUpdated.getPointScale());
        verify(pointScaleRepository, times(1)).findById(pointScaleOne.getId());
        verify(pointScaleConverter, times(1)).toEntityUpdate(pointScaleInputDTOOne, pointScaleOne.getId(), pointScaleOne);
        verify(pointScaleRepository, times(1)).save(pointScaleOne);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar um PointScaleEntity")
    void testUpdateBadRequestException() {
        when(pointScaleRepository.findById(pointScaleOne.getId())).thenReturn(Optional.of(pointScaleOne));
        when(pointScaleConverter.toEntityUpdate(pointScaleInputDTOOne, pointScaleOne.getId(), pointScaleOne)).thenReturn(pointScaleOne);
        when(pointScaleRepository.save(pointScaleOne)).thenThrow(new BadRequestException(MessagesConstants.ERROR_UPDATE_POINT_SCALE));

        Exception exception = assertThrows(Exception.class, () -> pointScaleService.update(pointScaleOne.getId(), pointScaleInputDTOOne));
        assertEquals(MessagesConstants.ERROR_UPDATE_POINT_SCALE, exception.getMessage());
    }

    @Test
    @DisplayName("Deve deletar um PointScaleEntity")
    void testDelete() {
        when(pointScaleRepository.findById(pointScaleOne.getId())).thenReturn(Optional.of(pointScaleOne));

        assertDoesNotThrow(() -> pointScaleService.delete(pointScaleOne.getId()));
        verify(pointScaleRepository, times(1)).deleteById(pointScaleOne.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar um PointScaleEntity")
    void testDeleteBadRequestException() {
        when(pointScaleRepository.findById(pointScaleOne.getId())).thenReturn(Optional.of(pointScaleOne));
        doThrow(new BadRequestException(MessagesConstants.ERROR_DELETE_POINT_SCALE)).when(pointScaleRepository).deleteById(pointScaleOne.getId());

        Exception exception = assertThrows(Exception.class, () -> pointScaleService.delete(pointScaleOne.getId()));
        assertEquals(MessagesConstants.ERROR_DELETE_POINT_SCALE, exception.getMessage());
    }

    private PointScaleInputDTO createPointScaleInputDTOOne() {
        return PointScaleInputDTO.builder()
                .whatTasted("Portada Winemaker's Selection, safra 2020, vinho tinto, seco, 12,5% de álcool, " +
                        "produzido e engarrafado por DFJ Vinhos, em Lisboa, Portugal.")
                .whenTasted("Em 15/09/2023, às 20:00h.")
                .whatSaw("Vinho de cor vermelho rubi, com reflexos violáceos, límpido, brilhante, com lágrimas finas, " +
                        "rápidas e abundantes.")
                .whatAromas("Aromas de frutas vermelhas maduras, como cereja e framboesa, com notas de especiarias, " +
                        "como pimenta preta, e de ervas, como tomilho.")
                .whatFlavors("Em boca, o vinho é seco, com acidez média, taninos médios, álcool médio, corpo médio, " +
                        "intensidade de sabor média, com sabores de frutas vermelhas maduras, como cereja e framboesa, " +
                        "com notas de especiarias, como pimenta preta, e de ervas, como tomilho, e final médio.")
                .whatOpinion("Vinho de boa qualidade, com boa complexidade, equilibrado, com boa intensidade de sabor, " +
                        "com boa persistência, com boa tipicidade, com boa harmonização, com boa relação qualidade/preço, " +
                        "com potencial de guarda de 3 anos, mas que pode ser consumido desde já.")
                .pointScale(EnumPointScale.VERYGOOD.getCode())
                .build();
    }

    private PointScaleEntity createPointScaleThree() {
        return PointScaleEntity.builder()
                .id(UUID.fromString("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"))
                .whatTasted("Faustino Rivero Ulecia Reserva Rioja DOCa, safra 2018, vinho tinto, seco, " +
                        "produzido e engarrafado por vinícola Loma Negra, em Vale Central, Chile.")
                .whenTasted("Em 28/10/2023, às 21:30h.")
                .whatSaw("Coloração amarelo dourado.")
                .whatAromas("Aroma de pimentão vermelho maduro.")
                .whatFlavors("Na boca boa acidez, lembrando frutas cítricas.")
                .whatOpinion("Muito suculento com final longo.")
                .pointScale(EnumPointScale.OUTSTANDING)
                .build();
    }

    private PointScaleEntity createPointScaleTwo() {
        return PointScaleEntity.builder()
                .id(UUID.fromString("468fc21e-f713-4974-9358-4a9547708ae4"))
                .whatTasted("Faustino Rivero Ulecia Reserva Rioja DOCa, safra 2018, vinho tinto, seco, 14% de álcool, " +
                        "produzido e engarrafado por Marqués del Atrio, em Rioja, Espanha.")
                .whenTasted("Em 28/10/2023, às 21:30h.")
                .whatSaw("Vermelho escuro , opaco e brilhante.")
                .whatAromas("Compota de frutas , tostado e chocolate..")
                .whatFlavors("Taninos suaves e bastante acidez. Corpo médio e persistente.")
                .whatOpinion("Mais um ótimo tinto da Rioja que degusto, ótimos aromas de frutas negras com toque de " +
                        "pimenta e tabaco, ótimo corpo e estrutura com tanicidade eacidez bem equilibradas, vinho bem agradável")
                .pointScale(EnumPointScale.GOOD)
                .build();
    }

    private PointScaleEntity createPointScaleOne() {
        return PointScaleEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .whatTasted("Portada Winemaker's Selection, safra 2020, vinho tinto, seco, 12,5% de álcool, " +
                        "produzido e engarrafado por DFJ Vinhos, em Lisboa, Portugal.")
                .whenTasted("Em 15/09/2023, às 20:00h.")
                .whatSaw("Vinho de cor vermelho rubi, com reflexos violáceos, límpido, brilhante, com lágrimas finas, " +
                        "rápidas e abundantes.")
                .whatAromas("Aromas de frutas vermelhas maduras, como cereja e framboesa, com notas de especiarias, " +
                        "como pimenta preta, e de ervas, como tomilho.")
                .whatFlavors("Em boca, o vinho é seco, com acidez média, taninos médios, álcool médio, corpo médio, " +
                        "intensidade de sabor média, com sabores de frutas vermelhas maduras, como cereja e framboesa, " +
                        "com notas de especiarias, como pimenta preta, e de ervas, como tomilho, e final médio.")
                .whatOpinion("Vinho de boa qualidade, com boa complexidade, equilibrado, com boa intensidade de sabor, " +
                        "com boa persistência, com boa tipicidade, com boa harmonização, com boa relação qualidade/preço, " +
                        "com potencial de guarda de 3 anos, mas que pode ser consumido desde já.")
                .pointScale(EnumPointScale.VERYGOOD)
                .build();
    }

}
