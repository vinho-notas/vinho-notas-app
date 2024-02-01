package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.VisualInspectionOutputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VisualInspectionConverterTest {

    @InjectMocks
    private VisualInspectionConverter visualInspectionConverter;

    private VisualInspectionInputDTO visualInspectionInputDTO;
    private VisualInspectionOutputDTO visualInspectionOutputDTO;
    private VisualInspectionEntity visualInspectionEntity;

    @BeforeEach
    void setUp() {
        visualInspectionInputDTO = createVisualInspectionInputDTO();
        visualInspectionOutputDTO = createVisualInspectionOutputDTO();
        visualInspectionEntity = createVisualInspectionEntity();
    }

    @Test
    @DisplayName("Deve converter em uma entidade")
    void testToEntity() {
        VisualInspectionEntity entity = assertDoesNotThrow(() -> visualInspectionConverter.toEntity(visualInspectionInputDTO));

        assertNotNull(entity);
        assertEquals(visualInspectionInputDTO.getTastingData(), entity.getTastingData());
        assertEquals(visualInspectionInputDTO.getWineTasted(), entity.getWineTasted());
        assertEquals(visualInspectionInputDTO.getClarity(), entity.getClarity());
        assertEquals(visualInspectionInputDTO.getBrightness(), entity.getBrightness());
        assertEquals(visualInspectionInputDTO.getViscosity(), entity.getViscosity());
        assertEquals(visualInspectionInputDTO.getColorRed(), entity.getColorRed());
        assertEquals(visualInspectionInputDTO.getColorWhite(), entity.getColorWhite());
        assertEquals(visualInspectionInputDTO.getColorRose(), entity.getColorRose());
        assertEquals(visualInspectionInputDTO.getClassification(), entity.getClassification());
    }

    @Test
    @DisplayName("Deve converter em uma entidade para atualização")
    void testToEntityUpdate() {
        visualInspectionInputDTO.setBrightness(EnumBrightnessType.OPAQUE);
        visualInspectionInputDTO.setViscosity(EnumViscosityType.VISCOUS);

        VisualInspectionEntity entity = assertDoesNotThrow(() -> visualInspectionConverter.toEntityUpdate(visualInspectionInputDTO,
                visualInspectionEntity.getId(), visualInspectionEntity));

        assertNotNull(entity);
        assertEquals(visualInspectionEntity.getId(), entity.getId());
        assertEquals(EnumBrightnessType.OPAQUE, entity.getBrightness());
        assertEquals(EnumViscosityType.VISCOUS, entity.getViscosity());
    }

    @Test
    @DisplayName("Deve converter em um DTO de saída")
    void testToOutputDTO() {
        VisualInspectionOutputDTO dto = assertDoesNotThrow(() -> visualInspectionConverter.toOutputDTO(visualInspectionEntity));

        assertNotNull(dto);
        assertEquals(visualInspectionEntity.getId(), dto.getId());
        assertEquals(visualInspectionEntity.getTastingData(), dto.getTastingData());
        assertEquals(visualInspectionEntity.getWineTasted(), dto.getWineTasted());
        assertEquals(visualInspectionEntity.getClarity(), dto.getClarity());
        assertEquals(visualInspectionEntity.getBrightness(), dto.getBrightness());
        assertEquals(visualInspectionEntity.getViscosity(), dto.getViscosity());
        assertEquals(visualInspectionEntity.getColorRed(), dto.getColorRed());
        assertEquals(visualInspectionEntity.getColorWhite(), dto.getColorWhite());
        assertEquals(visualInspectionEntity.getColorRose(), dto.getColorRose());
        assertEquals(visualInspectionEntity.getClassification(), dto.getClassification());
    }

    @Test
    @DisplayName("Deve converter em uma lista de DTO de saída")
    void testToOutputDTOList() {
        List<VisualInspectionOutputDTO> list = assertDoesNotThrow(() -> visualInspectionConverter.toOutputDTOList(List.of(visualInspectionEntity)));

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(visualInspectionEntity.getId(), list.get(0).getId());
        assertEquals(visualInspectionEntity.getTastingData(), list.get(0).getTastingData());
        assertEquals(visualInspectionEntity.getWineTasted(), list.get(0).getWineTasted());
        assertEquals(visualInspectionEntity.getClarity(), list.get(0).getClarity());
        assertEquals(visualInspectionEntity.getBrightness(), list.get(0).getBrightness());
        assertEquals(visualInspectionEntity.getViscosity(), list.get(0).getViscosity());
        assertEquals(visualInspectionEntity.getColorRed(), list.get(0).getColorRed());
        assertEquals(visualInspectionEntity.getColorWhite(), list.get(0).getColorWhite());
        assertEquals(visualInspectionEntity.getColorRose(), list.get(0).getColorRose());
        assertEquals(visualInspectionEntity.getClassification(), list.get(0).getClassification());
    }

    @Test
    @DisplayName("Deve converter em um DTO de saída para atualização")
    void testToOutputDTOUpdate() {
        visualInspectionOutputDTO.setBrightness(EnumBrightnessType.OPAQUE);
        visualInspectionOutputDTO.setViscosity(EnumViscosityType.VISCOUS);

        VisualInspectionOutputDTO dto = assertDoesNotThrow(() -> visualInspectionConverter.toOutputDTOUpdate(visualInspectionEntity,
                visualInspectionEntity.getId(), visualInspectionOutputDTO));

        assertNotNull(dto);
        assertEquals(visualInspectionEntity.getId(), dto.getId());
        assertEquals(EnumBrightnessType.OPAQUE, dto.getBrightness());
        assertEquals(EnumViscosityType.VISCOUS, dto.getViscosity());
    }

    private VisualInspectionEntity createVisualInspectionEntity() {
        return VisualInspectionEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .clarity(EnumClarityType.VERY_CLEAR)
                .brightness(EnumBrightnessType.VERY_BRIGHT)
                .viscosity(EnumViscosityType.SLIPPERY)
                .colorRed(EnumRedColorType.RUBY)
                .colorWhite(EnumWhiteColorType.STRAW_YELLOW)
                .colorRose(EnumRoseColorType.BROWN)
                .classification(EnumClassificationType.LITTLE)
                .tastingCard(Mockito.mock(TastingCardEntity.class))
                .build();
    }

    private VisualInspectionOutputDTO createVisualInspectionOutputDTO() {
        return VisualInspectionOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .clarity(EnumClarityType.VERY_CLEAR)
                .brightness(EnumBrightnessType.VERY_BRIGHT)
                .viscosity(EnumViscosityType.SLIPPERY)
                .colorRed(EnumRedColorType.RUBY)
                .colorWhite(EnumWhiteColorType.STRAW_YELLOW)
                .colorRose(EnumRoseColorType.BROWN)
                .classification(EnumClassificationType.LITTLE)
                .tastingCard(Mockito.mock(TastingCardEntity.class))
                .build();
    }

    private VisualInspectionInputDTO createVisualInspectionInputDTO() {
        return VisualInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .clarity(EnumClarityType.VERY_CLEAR)
                .brightness(EnumBrightnessType.VERY_BRIGHT)
                .viscosity(EnumViscosityType.SLIPPERY)
                .colorRed(EnumRedColorType.RUBY)
                .colorWhite(EnumWhiteColorType.STRAW_YELLOW)
                .colorRose(EnumRoseColorType.BROWN)
                .classification(EnumClassificationType.LITTLE)
                .tastingCard(Mockito.mock(TastingCardEntity.class))
                .build();
    }

}