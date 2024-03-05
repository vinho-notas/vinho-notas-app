package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.EnumClassificationType;
import com.vinhonotas.degustacao.domain.enums.EnumIntensityType;
import com.vinhonotas.degustacao.domain.enums.EnumPersistenceType;
import com.vinhonotas.degustacao.domain.enums.EnumQualityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class OlfactoryInspectionRepositoryTest {

    @Autowired
    private OlfactoryInspectionRepository olfactoryInspectionRepository;

    private OlfactoryInspectionEntity olfactoryInspectionEntity;
    private AromasEntity aromasEntity;
    private TastingCardEntity tastingCardEntity;

    @BeforeEach
    void setUp() {
        olfactoryInspectionEntity = createOlfactoryInspectionEntity();
        aromasEntity = createAromasEntity();
        tastingCardEntity = createTastingCardEntity();
    }

    @Test
    @DisplayName("Deve criar um registro de inspeção olfativa")
    void testCreateOlfactoryInspectionEntity() {
        OlfactoryInspectionEntity olfactoryInspectionSaved = assertDoesNotThrow(() -> olfactoryInspectionRepository.save(olfactoryInspectionEntity));
        assertNotNull(olfactoryInspectionSaved);
        assertEquals(olfactoryInspectionEntity.getId(), olfactoryInspectionSaved.getId());
        assertEquals(olfactoryInspectionEntity.getTastingData(), olfactoryInspectionSaved.getTastingData());
        assertEquals(olfactoryInspectionEntity.getWineTasted(), olfactoryInspectionSaved.getWineTasted());
        assertEquals(olfactoryInspectionEntity.getIntensity(), olfactoryInspectionSaved.getIntensity());
        assertEquals(olfactoryInspectionEntity.getPersistence(), olfactoryInspectionSaved.getPersistence());
        assertEquals(olfactoryInspectionEntity.getQuality(), olfactoryInspectionSaved.getQuality());
        assertEquals(olfactoryInspectionEntity.getAromas(), olfactoryInspectionSaved.getAromas());
        assertEquals(olfactoryInspectionEntity.getClassification(), olfactoryInspectionSaved.getClassification());
    }

    @Test
    @DisplayName("Deve retornar um registro de inspeção olfativa pelo id")
    void testFindById() {
        OlfactoryInspectionEntity olfactoryInspectionSaved = assertDoesNotThrow(() -> olfactoryInspectionRepository.save(olfactoryInspectionEntity));

        Optional<OlfactoryInspectionEntity> olfactoryInspection = assertDoesNotThrow(() -> olfactoryInspectionRepository.findById(olfactoryInspectionSaved.getId()));

        assertTrue(olfactoryInspection.isPresent());
        assertEquals(olfactoryInspectionSaved.getId(), olfactoryInspection.get().getId());
        assertEquals(olfactoryInspectionSaved.getTastingData(), olfactoryInspection.get().getTastingData());
        assertEquals(olfactoryInspectionSaved.getWineTasted(), olfactoryInspection.get().getWineTasted());
        assertEquals(olfactoryInspectionSaved.getIntensity(), olfactoryInspection.get().getIntensity());
        assertEquals(olfactoryInspectionSaved.getPersistence(), olfactoryInspection.get().getPersistence());
        assertEquals(olfactoryInspectionSaved.getQuality(), olfactoryInspection.get().getQuality());
        assertEquals(olfactoryInspectionSaved.getAromas(), olfactoryInspection.get().getAromas());
        assertEquals(olfactoryInspectionSaved.getClassification(), olfactoryInspection.get().getClassification());
    }

    @Test
    @DisplayName("Deve atualizar um registro de inspeção olfativa")
    void testUpdateOlfactoryInspectionEntity() {
        OlfactoryInspectionEntity olfactoryInspectionSaved = assertDoesNotThrow(() -> olfactoryInspectionRepository.save(olfactoryInspectionEntity));
        olfactoryInspectionSaved.setWineTasted("Wine Tasted Updated");
        olfactoryInspectionSaved.setQuality(EnumQualityType.COMMON);
        olfactoryInspectionSaved.setClassification(EnumClassificationType.LITTLE);

        OlfactoryInspectionEntity olfactoryInspectionUpdated = assertDoesNotThrow(() -> olfactoryInspectionRepository.save(olfactoryInspectionSaved));

        assertNotNull(olfactoryInspectionUpdated);
        assertEquals("Wine Tasted Updated", olfactoryInspectionUpdated.getWineTasted());
        assertEquals(EnumQualityType.COMMON, olfactoryInspectionUpdated.getQuality());
        assertEquals(EnumClassificationType.LITTLE, olfactoryInspectionUpdated.getClassification());
    }

    @Test
    @DisplayName("Deve deletar um registro de inspeção olfativa")
    void testDeleteOlfactoryInspectionEntity() {
        OlfactoryInspectionEntity olfactoryInspectionSaved = assertDoesNotThrow(() -> olfactoryInspectionRepository.save(olfactoryInspectionEntity));
        assertDoesNotThrow(() -> olfactoryInspectionRepository.delete(olfactoryInspectionSaved));

        Optional<OlfactoryInspectionEntity> olfactoryInspection = assertDoesNotThrow(() -> olfactoryInspectionRepository.findById(olfactoryInspectionSaved.getId()));
        assertTrue(olfactoryInspection.isEmpty());
    }

    private OlfactoryInspectionEntity createOlfactoryInspectionEntity() {
        return OlfactoryInspectionEntity.builder()
                .id(UUID.fromString("f5d4b3d3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE)
                .persistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.THIN)
                .aromas(aromasEntity)
                .classification(EnumClassificationType.GOOD)
                .build();
    }

    private TastingCardEntity createTastingCardEntity() {
        return TastingCardEntity.builder()
                .id(UUID.fromString("af717432-58a4-4b2d-be3b-c28ca0915b57"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .build();
    }

    private AromasEntity createAromasEntity() {
        return AromasEntity.builder()
                .id(UUID.fromString("9ce11266-41a2-4fda-8447-641a6edda6a6"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .build();
    }

}
