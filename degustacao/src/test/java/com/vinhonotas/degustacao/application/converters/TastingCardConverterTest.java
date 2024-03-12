package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.*;
import com.vinhonotas.degustacao.domain.enums.EnumPointScale;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingCardInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingCardOutputDTO;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TastingCardConverterTest {

    @InjectMocks
    private TastingCardConverter tastingCardConverter;

    @Mock
    private VisualInspectionConverter visualInspectionConverter;
    @Mock
    private OlfactoryInspectionConverter olfactoryInspectionConverter;
    @Mock
    private GustatoryInspectionConverter gustatoryInspectionConverter;

    private TastingCardEntity tastingCardEntity;
    private TastingCardInputDTO tastingCardInputDTO;
    private TastingCardOutputDTO tastingCardOutputDTO;

    @BeforeEach
    void setUp() {
        tastingCardEntity = createTastingCardEntity();
        tastingCardInputDTO = createTastingCardInputDTO();
        tastingCardOutputDTO = createTastingCardOutputDTO();
    }

    @Test
    @DisplayName("Deve converter para uma entidade")
    void testToEntity() {
        TastingCardEntity entity = assertDoesNotThrow(() -> tastingCardConverter.toEntity(tastingCardInputDTO));

        assertNotNull(entity);
        assertEquals(tastingCardInputDTO.getTastingData(), entity.getTastingData());
        assertEquals(tastingCardInputDTO.getWineTasted(), entity.getWineTasted());
        assertEquals(tastingCardInputDTO.getHarvest(), entity.getHarvest());
        assertEquals(tastingCardInputDTO.getGrapes(), entity.getGrapes());
        assertEquals(tastingCardInputDTO.getCountry(), entity.getCountry());
        assertEquals(tastingCardInputDTO.getRegion(), entity.getRegion());
        assertEquals(visualInspectionConverter.toEntity(tastingCardInputDTO.getVisualInspection()), entity.getVisualInspection());
        assertEquals(olfactoryInspectionConverter.toEntity(tastingCardInputDTO.getOlfactoryInspection()), entity.getOlfactoryInspection());
        assertEquals(gustatoryInspectionConverter.toEntity(tastingCardInputDTO.getGustatoryInspection()), entity.getGustatoryInspection());
        assertEquals(tastingCardInputDTO.getOpinion(), entity.getOpinion());
        assertEquals(tastingCardInputDTO.getPointScale(), entity.getPointScale().getCode());
    }

    @Test
    @DisplayName("Deve converter para uma entidade de atualização")
    void testToEntityUpdate() {
        tastingCardInputDTO.setCountry("Argentina");
        tastingCardInputDTO.setPointScale(EnumPointScale.VERYGOOD.getCode());

        TastingCardEntity entity = assertDoesNotThrow(() -> tastingCardConverter.toEntityUpdate(tastingCardInputDTO,
                tastingCardEntity.getId(), tastingCardEntity));

        assertNotNull(entity);
        assertEquals("Argentina", entity.getCountry());
        assertEquals(EnumPointScale.VERYGOOD, entity.getPointScale());
    }

    @Test
    @DisplayName("Deve converter para um DTO de saída")
    void testToOutputDTO() {
        TastingCardOutputDTO outputDTO = assertDoesNotThrow(() -> tastingCardConverter.toOutputDTO(tastingCardEntity));

        assertNotNull(outputDTO);
        assertEquals(tastingCardEntity.getId(), outputDTO.getId());
        assertEquals(tastingCardEntity.getTastingData(), outputDTO.getTastingData());
        assertEquals(tastingCardEntity.getWineTasted(), outputDTO.getWineTasted());
        assertEquals(tastingCardEntity.getHarvest(), outputDTO.getHarvest());
        assertEquals(tastingCardEntity.getGrapes(), outputDTO.getGrapes());
        assertEquals(tastingCardEntity.getCountry(), outputDTO.getCountry());
        assertEquals(tastingCardEntity.getRegion(), outputDTO.getRegion());
        assertEquals(tastingCardEntity.getVisualInspection(), outputDTO.getVisualInspection());
        assertEquals(tastingCardEntity.getOlfactoryInspection(), outputDTO.getOlfactoryInspection());
        assertEquals(tastingCardEntity.getGustatoryInspection(), outputDTO.getGustatoryInspection());
        assertEquals(tastingCardEntity.getOpinion(), outputDTO.getOpinion());
        assertEquals(tastingCardEntity.getPointScale(), outputDTO.getPointScale());
        assertEquals(tastingCardEntity.getTasting(), outputDTO.getTasting());
    }

    @Test
    @DisplayName("Deve converter para uma lista de DTOs de saída")
    void testToOutputDTOList() {
        List<TastingCardOutputDTO> list = assertDoesNotThrow(() -> tastingCardConverter.toOutputDTOList(List.of(tastingCardEntity)));

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(tastingCardEntity.getId(), list.get(0).getId());
        assertEquals(tastingCardEntity.getTastingData(), list.get(0).getTastingData());
        assertEquals(tastingCardEntity.getWineTasted(), list.get(0).getWineTasted());
        assertEquals(tastingCardEntity.getHarvest(), list.get(0).getHarvest());
        assertEquals(tastingCardEntity.getGrapes(), list.get(0).getGrapes());
        assertEquals(tastingCardEntity.getCountry(), list.get(0).getCountry());
        assertEquals(tastingCardEntity.getRegion(), list.get(0).getRegion());
        assertEquals(tastingCardEntity.getVisualInspection(), list.get(0).getVisualInspection());
        assertEquals(tastingCardEntity.getOlfactoryInspection(), list.get(0).getOlfactoryInspection());
        assertEquals(tastingCardEntity.getGustatoryInspection(), list.get(0).getGustatoryInspection());
        assertEquals(tastingCardEntity.getOpinion(), list.get(0).getOpinion());
        assertEquals(tastingCardEntity.getPointScale(), list.get(0).getPointScale());
        assertEquals(tastingCardEntity.getTasting(), list.get(0).getTasting());
    }

    @Test
    @DisplayName("Deve converter para um DTO de atualização de saída")
    void testToOutputDTOUpdate() {
        tastingCardOutputDTO.setGrapes("50% Cabernet Sauvignon, 40% Montepulciano, 10% Merlot");
        tastingCardOutputDTO.setHarvest("2019");

        TastingCardOutputDTO outputDTO = assertDoesNotThrow(() -> tastingCardConverter.toOutputDTOUpdate(tastingCardEntity,
                tastingCardEntity.getId(), tastingCardOutputDTO));

        assertNotNull(outputDTO);
        assertEquals("2019", outputDTO.getHarvest());
        assertEquals("50% Cabernet Sauvignon, 40% Montepulciano, 10% Merlot", outputDTO.getGrapes());
    }


    private TastingCardOutputDTO createTastingCardOutputDTO() {
        return TastingCardOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .harvest("2020")
                .grapes("Grapes")
                .country("Chile")
                .region("Vale Central")
                .visualInspection(Mockito.mock(VisualInspectionEntity.class))
                .olfactoryInspection(Mockito.mock(OlfactoryInspectionEntity.class))
                .gustatoryInspection(Mockito.mock(GustatoryInspectionEntity.class))
                .opinion("Opinion about the wine")
                .pointScale(EnumPointScale.CLASSIC)
                .tasting(Mockito.mock(TastingEntity.class))
                .build();
    }

    private TastingCardInputDTO createTastingCardInputDTO() {
        return TastingCardInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .harvest("2020")
                .grapes("Grapes")
                .country("Chile")
                .region("Vale Central")
                .visualInspection(VisualInspectionInputDTO.builder().build())
                .olfactoryInspection(OlfactoryInspectionInputDTO.builder().build())
                .gustatoryInspection(GustatoryInspectionInputDTO.builder().build())
                .opinion("Opinion about the wine")
                .pointScale(EnumPointScale.CLASSIC.getCode())
                .build();
    }

    private TastingCardEntity createTastingCardEntity() {
        return TastingCardEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .harvest("2020")
                .grapes("Grapes")
                .country("Chile")
                .region("Vale Central")
                .visualInspection(Mockito.mock(VisualInspectionEntity.class))
                .olfactoryInspection(Mockito.mock(OlfactoryInspectionEntity.class))
                .gustatoryInspection(Mockito.mock(GustatoryInspectionEntity.class))
                .opinion("Opinion about the wine")
                .pointScale(EnumPointScale.CLASSIC)
                .tasting(Mockito.mock(TastingEntity.class))
                .build();
    }

}
