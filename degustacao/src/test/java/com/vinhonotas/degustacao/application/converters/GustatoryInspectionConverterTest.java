package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.GustatoryInspectionOutputDTO;
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
        assertEquals(gustatoryInspectionInputDTO.getBody(), entity.getBody());
        assertEquals(gustatoryInspectionInputDTO.getSweetness(), entity.getSweetness());
        assertEquals(gustatoryInspectionInputDTO.getTannin(), entity.getTannin());
        assertEquals(gustatoryInspectionInputDTO.getClassification(), entity.getClassification());
        assertEquals(gustatoryInspectionInputDTO.getAcidity(), entity.getAcidity());
        assertEquals(gustatoryInspectionInputDTO.getAlcohol(), entity.getAlcohol());
        assertEquals(gustatoryInspectionInputDTO.getPersistence(), entity.getPersistence());
        assertEquals(gustatoryInspectionInputDTO.getMaturity(), entity.getMaturity());
        assertEquals(gustatoryInspectionInputDTO.getTypicality(), entity.getTypicality());
    }

    @Test
    @DisplayName("Deve converter para entidade com atualização")
    void testToEntityUpdate() {
        gustatoryInspectionInputDTO.setAcidity(EnumAcidityType.VERY_ACIDIC);
        gustatoryInspectionInputDTO.setBody(EnumBodyType.LITTLE_BODY);

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
        assertEquals(gustatoryInspectionEntity.getBody(), outputDTO.getBody());
        assertEquals(gustatoryInspectionEntity.getSweetness(), outputDTO.getSweetness());
        assertEquals(gustatoryInspectionEntity.getTannin(), outputDTO.getTannin());
        assertEquals(gustatoryInspectionEntity.getClassification(), outputDTO.getClassification());
        assertEquals(gustatoryInspectionEntity.getAcidity(), outputDTO.getAcidity());
        assertEquals(gustatoryInspectionEntity.getAlcohol(), outputDTO.getAlcohol());
        assertEquals(gustatoryInspectionEntity.getPersistence(), outputDTO.getPersistence());
        assertEquals(gustatoryInspectionEntity.getMaturity(), outputDTO.getMaturity());
        assertEquals(gustatoryInspectionEntity.getTypicality(), outputDTO.getTypicality());
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
        assertEquals(gustatoryInspectionEntity.getBody(), list.get(0).getBody());
        assertEquals(gustatoryInspectionEntity.getSweetness(), list.get(0).getSweetness());
        assertEquals(gustatoryInspectionEntity.getTannin(), list.get(0).getTannin());
        assertEquals(gustatoryInspectionEntity.getClassification(), list.get(0).getClassification());
        assertEquals(gustatoryInspectionEntity.getAcidity(), list.get(0).getAcidity());
        assertEquals(gustatoryInspectionEntity.getAlcohol(), list.get(0).getAlcohol());
        assertEquals(gustatoryInspectionEntity.getPersistence(), list.get(0).getPersistence());
        assertEquals(gustatoryInspectionEntity.getMaturity(), list.get(0).getMaturity());
        assertEquals(gustatoryInspectionEntity.getTypicality(), list.get(0).getTypicality());
    }

    @Test
    @DisplayName("Deve converter para saída com atualização")
    void testToOutputDTOUpdate() {
        gustatoryInspectionOutputDTO.setAcidity(EnumAcidityType.VERY_ACIDIC);
        gustatoryInspectionOutputDTO.setBody(EnumBodyType.LITTLE_BODY);

        GustatoryInspectionOutputDTO outputDTO = assertDoesNotThrow(() -> gustatoryInspectionConverter
                .toOutputDTOUpdate(gustatoryInspectionEntity, gustatoryInspectionEntity.getId(), gustatoryInspectionOutputDTO));

        assertNotNull(outputDTO);
        assertEquals(EnumBodyType.LITTLE_BODY, outputDTO.getBody());
        assertEquals(EnumAcidityType.VERY_ACIDIC, outputDTO.getAcidity());
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
                .tastingCard(Mockito.mock(TastingCardEntity.class))
                .build();
    }

    private GustatoryInspectionOutputDTO createGustatoryInspectionOutputDTO() {
        return GustatoryInspectionOutputDTO.builder()
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
                .tastingCard(Mockito.mock(TastingCardEntity.class))
                .build();
    }

    private GustatoryInspectionInputDTO createGustatoryInspectionInputDTO() {
        return GustatoryInspectionInputDTO.builder()
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
                .tastingCard(Mockito.mock(TastingCardEntity.class))
                .build();
    }

}
