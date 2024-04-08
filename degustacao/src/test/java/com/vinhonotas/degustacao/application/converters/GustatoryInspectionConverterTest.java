package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.GustatoryInspectionOutputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingCardOutputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GustatoryInspectionConverterTest {

    @InjectMocks
    private GustatoryInspectionConverter gustatoryInspectionConverter;

    private GustatoryInspectionInputDTO gustatoryInspectionInputDTO;
    private GustatoryInspectionOutputDTO gustatoryInspectionOutputDTO;
    private GustatoryInspectionEntity gustatoryInspectionEntity;

    @BeforeEach
    void setUp() {
        gustatoryInspectionInputDTO = createGustatoryInspectionInputDTO();
        gustatoryInspectionOutputDTO = createGustatoryInspectionOutputDTO();
        gustatoryInspectionEntity = createGustatoryInspectionEntity();
    }

    @Test
    @DisplayName("Deve converter para entidade")
    void testToEntity() {
        GustatoryInspectionEntity entity = assertDoesNotThrow(() -> gustatoryInspectionConverter.toEntity(gustatoryInspectionInputDTO));

        assertNotNull(entity);
        assertEquals(gustatoryInspectionInputDTO.getTastingData(), entity.getTastingData());
        assertEquals(gustatoryInspectionInputDTO.getWineTasted(), entity.getWineTasted());
        assertEquals(gustatoryInspectionInputDTO.getBody(), EnumConverter.toString(entity.getBody()));
        assertEquals(gustatoryInspectionInputDTO.getSweetness(), EnumConverter.toString(entity.getSweetness()));
        assertEquals(gustatoryInspectionInputDTO.getTannin(), EnumConverter.toString(entity.getTannin()));
        assertEquals(gustatoryInspectionInputDTO.getClassification(), EnumConverter.toString(entity.getClassification()));
        assertEquals(gustatoryInspectionInputDTO.getAcidity(), EnumConverter.toString(entity.getAcidity()));
        assertEquals(gustatoryInspectionInputDTO.getAlcohol(), EnumConverter.toString(entity.getAlcohol()));
        assertEquals(gustatoryInspectionInputDTO.getPersistence(), EnumConverter.toString(entity.getPersistence()));
        assertEquals(gustatoryInspectionInputDTO.getMaturity(), EnumConverter.toString(entity.getMaturity()));
        assertEquals(gustatoryInspectionInputDTO.getTypicality(), EnumConverter.toString(entity.getTypicality()));
    }

    @Test
    @DisplayName("Deve converter para entidade com atualização")
    void testToEntityUpdate() {
        gustatoryInspectionInputDTO.setAcidity(EnumAcidityType.VERY_ACIDIC.getCode());
        gustatoryInspectionInputDTO.setBody(EnumBodyType.LITTLE_BODY.getCode());

        GustatoryInspectionEntity entity = assertDoesNotThrow(() -> gustatoryInspectionConverter
                .toEntityUpdate(gustatoryInspectionInputDTO, gustatoryInspectionEntity.getId(), gustatoryInspectionEntity));

        assertNotNull(entity);
        assertEquals(EnumBodyType.LITTLE_BODY, entity.getBody());
        assertEquals(EnumAcidityType.VERY_ACIDIC, entity.getAcidity());
    }

    @Test
    @DisplayName("Deve converter para saída")
    void testToOutputDTO() {
        GustatoryInspectionOutputDTO outputDTO = assertDoesNotThrow(() -> gustatoryInspectionConverter.toOutputDTO(gustatoryInspectionEntity));

        assertNotNull(outputDTO);
        assertEquals(gustatoryInspectionEntity.getId(), outputDTO.getId());
        assertEquals(gustatoryInspectionEntity.getTastingData(), outputDTO.getTastingData());
        assertEquals(gustatoryInspectionEntity.getWineTasted(), outputDTO.getWineTasted());
        assertEquals(gustatoryInspectionEntity.getBody().getCode(), outputDTO.getBody());
        assertEquals(gustatoryInspectionEntity.getSweetness().getCode(), outputDTO.getSweetness());
        assertEquals(gustatoryInspectionEntity.getTannin().getCode(), outputDTO.getTannin());
        assertEquals(gustatoryInspectionEntity.getClassification().getCode(), outputDTO.getClassification());
        assertEquals(gustatoryInspectionEntity.getAcidity().getCode(), outputDTO.getAcidity());
        assertEquals(gustatoryInspectionEntity.getAlcohol().getCode(), outputDTO.getAlcohol());
        assertEquals(gustatoryInspectionEntity.getPersistence().getCode(), outputDTO.getPersistence());
        assertEquals(gustatoryInspectionEntity.getMaturity().getCode(), outputDTO.getMaturity());
        assertEquals(gustatoryInspectionEntity.getTypicality().getCode(), outputDTO.getTypicality());
    }

    @Test
    @DisplayName("Deve converter para uma lista de saída")
    void testToOutputDTOList() {
        List<GustatoryInspectionOutputDTO> list = assertDoesNotThrow(() -> gustatoryInspectionConverter.toOutputDTOList(List.of(gustatoryInspectionEntity)));

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(gustatoryInspectionEntity.getId(), list.get(0).getId());
        assertEquals(gustatoryInspectionEntity.getTastingData(), list.get(0).getTastingData());
        assertEquals(gustatoryInspectionEntity.getWineTasted(), list.get(0).getWineTasted());
        assertEquals(gustatoryInspectionEntity.getBody().getCode(), list.get(0).getBody());
        assertEquals(gustatoryInspectionEntity.getSweetness().getCode(), list.get(0).getSweetness());
        assertEquals(gustatoryInspectionEntity.getTannin().getCode(), list.get(0).getTannin());
        assertEquals(gustatoryInspectionEntity.getClassification().getCode(), list.get(0).getClassification());
        assertEquals(gustatoryInspectionEntity.getAcidity().getCode(), list.get(0).getAcidity());
        assertEquals(gustatoryInspectionEntity.getAlcohol().getCode(), list.get(0).getAlcohol());
        assertEquals(gustatoryInspectionEntity.getPersistence().getCode(), list.get(0).getPersistence());
        assertEquals(gustatoryInspectionEntity.getMaturity().getCode(), list.get(0).getMaturity());
        assertEquals(gustatoryInspectionEntity.getTypicality().getCode(), list.get(0).getTypicality());
    }

    @Test
    @DisplayName("Deve converter para saída com atualização")
    void testToOutputDTOUpdate() {
        gustatoryInspectionOutputDTO.setAcidity(EnumAcidityType.VERY_ACIDIC.getCode());
        gustatoryInspectionOutputDTO.setBody(EnumBodyType.LITTLE_BODY.getCode());

        GustatoryInspectionOutputDTO outputDTO = assertDoesNotThrow(() -> gustatoryInspectionConverter
                .toOutputDTOUpdate(gustatoryInspectionEntity, gustatoryInspectionEntity.getId(), gustatoryInspectionOutputDTO));

        assertNotNull(outputDTO);
        assertEquals(EnumBodyType.LITTLE_BODY.getCode(), outputDTO.getBody());
        assertEquals(EnumAcidityType.VERY_ACIDIC.getCode(), outputDTO.getAcidity());
    }

    private GustatoryInspectionEntity createGustatoryInspectionEntity() {
        return GustatoryInspectionEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .body(EnumBodyType.FULL_BODIED)
                .sweetness(EnumSweetnessType.VERY_DRY)
                .tannin(EnumTanninType.LITTLE_TANIC)
                .classification(EnumClassificationType.LITTLE)
                .acidity(EnumAcidityType.LITTLE_ACID)
                .alcohol(EnumAlcoholType.LOW)
                .persistence(EnumPersistenceType.SHORT)
                .maturity(EnumMaturityType.MATURE)
                .typicality(EnumTypicalityType.NOT_TYPICAL)
                .build();
    }

    private GustatoryInspectionOutputDTO createGustatoryInspectionOutputDTO() {
        return GustatoryInspectionOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .body(EnumBodyType.FULL_BODIED.getCode())
                .sweetness(EnumSweetnessType.VERY_DRY.getCode())
                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
                .classification(EnumClassificationType.LITTLE.getCode())
                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
                .alcohol(EnumAlcoholType.LOW.getCode())
                .persistence(EnumPersistenceType.SHORT.getCode())
                .maturity(EnumMaturityType.MATURE.getCode())
                .typicality(EnumTypicalityType.NOT_TYPICAL.getCode())
                .tastingCard(TastingCardOutputDTO.builder().build())
                .build();
    }

    private GustatoryInspectionInputDTO createGustatoryInspectionInputDTO() {
        return GustatoryInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .body(EnumBodyType.FULL_BODIED.getCode())
                .sweetness(EnumSweetnessType.VERY_DRY.getCode())
                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
                .classification(EnumClassificationType.LITTLE.getCode())
                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
                .alcohol(EnumAlcoholType.LOW.getCode())
                .persistence(EnumPersistenceType.SHORT.getCode())
                .maturity(EnumMaturityType.MATURE.getCode())
                .typicality(EnumTypicalityType.NOT_TYPICAL.getCode())
                .build();
    }

}
