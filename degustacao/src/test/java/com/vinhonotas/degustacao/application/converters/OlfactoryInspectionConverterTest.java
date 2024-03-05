package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.EnumClassificationType;
import com.vinhonotas.degustacao.domain.enums.EnumIntensityType;
import com.vinhonotas.degustacao.domain.enums.EnumPersistenceType;
import com.vinhonotas.degustacao.domain.enums.EnumQualityType;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.OlfactoryInspectionOutputDTO;
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
class OlfactoryInspectionConverterTest {

    @InjectMocks
    private OlfactoryInspectionConverter olfactoryInspectionConverter;

    private OlfactoryInspectionInputDTO olfactoryInspectionInputDTO;
    private OlfactoryInspectionOutputDTO olfactoryInspectionOutputDTO;
    private OlfactoryInspectionEntity olfactoryInspectionEntity;

    @BeforeEach
    void setUp() {
        olfactoryInspectionInputDTO = createOlfactoryInspectionInputDTO();
        olfactoryInspectionOutputDTO = createOlfactoryInspectionOutputDTO();
        olfactoryInspectionEntity = createOlfactoryInspectionEntity();
    }

    @Test
    @DisplayName("Deve converter para entidade")
    void testToEntity() {
        OlfactoryInspectionEntity entity = assertDoesNotThrow(() -> olfactoryInspectionConverter.toEntity(olfactoryInspectionInputDTO));

        assertNotNull(entity);
        assertEquals(olfactoryInspectionInputDTO.getTastingData(), entity.getTastingData());
        assertEquals(olfactoryInspectionInputDTO.getWineTasted(), entity.getWineTasted());
        assertEquals(olfactoryInspectionInputDTO.getIntensity(), entity.getIntensity());
        assertEquals(olfactoryInspectionInputDTO.getPersistence(), entity.getPersistence());
        assertEquals(olfactoryInspectionInputDTO.getQuality(), entity.getQuality());
        assertEquals(olfactoryInspectionInputDTO.getAromas(), entity.getAromas());
        assertEquals(olfactoryInspectionInputDTO.getClassification(), entity.getClassification());
    }

    @Test
    @DisplayName("Deve converter para entidade de atualização")
    void testToEntityUpdate() {
        olfactoryInspectionInputDTO.setWineTasted("Wine Tasted Updated");
        olfactoryInspectionInputDTO.setQuality(EnumQualityType.RUDE);

        OlfactoryInspectionEntity entity = assertDoesNotThrow(() -> olfactoryInspectionConverter.toEntityUpdate(
                olfactoryInspectionInputDTO, olfactoryInspectionEntity.getId(), olfactoryInspectionEntity));

        assertNotNull(entity);
        assertEquals("Wine Tasted Updated", entity.getWineTasted());
        assertEquals(EnumQualityType.RUDE, entity.getQuality());
    }

    @Test
    @DisplayName("Deve converter para DTO de saída")
    void testToOutputDTO() {
        OlfactoryInspectionOutputDTO outputDTO = assertDoesNotThrow(() -> olfactoryInspectionConverter.toOutputDTO(olfactoryInspectionEntity));

        assertNotNull(outputDTO);
        assertEquals(olfactoryInspectionEntity.getId(), outputDTO.getId());
        assertEquals(olfactoryInspectionEntity.getTastingData(), outputDTO.getTastingData());
        assertEquals(olfactoryInspectionEntity.getWineTasted(), outputDTO.getWineTasted());
        assertEquals(olfactoryInspectionEntity.getIntensity(), outputDTO.getIntensity());
        assertEquals(olfactoryInspectionEntity.getPersistence(), outputDTO.getPersistence());
        assertEquals(olfactoryInspectionEntity.getQuality(), outputDTO.getQuality());
        assertEquals(olfactoryInspectionEntity.getAromas(), outputDTO.getAromas());
        assertEquals(olfactoryInspectionEntity.getClassification(), outputDTO.getClassification());
    }

    @Test
    @DisplayName("Deve converter para lista de DTO de saída")
    void testToOutputDTOList() {
        List<OlfactoryInspectionOutputDTO> list = assertDoesNotThrow(() -> olfactoryInspectionConverter.toOutputDTOList(List.of(olfactoryInspectionEntity)));

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(olfactoryInspectionEntity.getId(), list.get(0).getId());
        assertEquals(olfactoryInspectionEntity.getTastingData(), list.get(0).getTastingData());
        assertEquals(olfactoryInspectionEntity.getWineTasted(), list.get(0).getWineTasted());
        assertEquals(olfactoryInspectionEntity.getIntensity(), list.get(0).getIntensity());
        assertEquals(olfactoryInspectionEntity.getPersistence(), list.get(0).getPersistence());
        assertEquals(olfactoryInspectionEntity.getQuality(), list.get(0).getQuality());
        assertEquals(olfactoryInspectionEntity.getAromas(), list.get(0).getAromas());
        assertEquals(olfactoryInspectionEntity.getClassification(), list.get(0).getClassification());
    }

    @Test
    @DisplayName("Deve converter para DTO de saída de atualização")
    void testToOutputDTOUpdate() {
        olfactoryInspectionOutputDTO.setPersistence(EnumPersistenceType.SHORT);
        olfactoryInspectionOutputDTO.setTastingData(LocalDate.now().minusDays(1));

        OlfactoryInspectionOutputDTO outputDTO = assertDoesNotThrow(() -> olfactoryInspectionConverter.toOutputDTOUpdate(
                olfactoryInspectionEntity, olfactoryInspectionEntity.getId(), olfactoryInspectionOutputDTO));

        assertNotNull(outputDTO);
        assertEquals(olfactoryInspectionEntity.getId(), outputDTO.getId());
        assertEquals(LocalDate.now().minusDays(1), outputDTO.getTastingData());
        assertEquals(EnumPersistenceType.SHORT, outputDTO.getPersistence());
    }

    private OlfactoryInspectionEntity createOlfactoryInspectionEntity() {
        return OlfactoryInspectionEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE)
                .persistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .aromas(Mockito.mock(AromasEntity.class))
                .classification(EnumClassificationType.LITTLE)
                .build();
    }

    private OlfactoryInspectionOutputDTO createOlfactoryInspectionOutputDTO() {
        return OlfactoryInspectionOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE)
                .persistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .aromas(new AromasEntity())
                .classification(EnumClassificationType.LITTLE)
                .tastingCard(new TastingCardEntity())
                .build();
    }

    private OlfactoryInspectionInputDTO createOlfactoryInspectionInputDTO() {
        return OlfactoryInspectionInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE)
                .persistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .aromas(new AromasEntity())
                .classification(EnumClassificationType.LITTLE)
                .build();
    }

}
