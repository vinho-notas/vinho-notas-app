package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.*;
import com.vinhonotas.degustacao.domain.enums.*;
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
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=true",
        "spring.jpa.properties.hibernate.format_sql=true"
})
class TastingCardRepositoryTest {

    @Autowired
    private TastingCardRepository tastingCardRepository;
    @Autowired
    private AromasRepository aromasRepository;
    @Autowired
    private OlfactoryInspectionRepository olfactoryInspectionRepository;
    @Autowired
    private VisualInspectionRepository visualInspectionRepository;
    @Autowired
    private GustatoryInspectionRepository gustatoryInspectionRepository;
    @Autowired
    private TastingRepository tastingRepository;

    private TastingCardEntity tastingCardEntity;
    private AromasEntity aromasEntity;
    private OlfactoryInspectionEntity olfactoryInspectionEntity;
    private VisualInspectionEntity visualInspectionEntity;
    private GustatoryInspectionEntity gustatoryInspectionEntity;
    private TastingEntity tastingEntity;

    @BeforeEach
    void setUp() {
        aromasEntity = createAromasEntity();
        aromasRepository.save(aromasEntity);

        olfactoryInspectionEntity = createOlfactoryInspectionEntity();
        olfactoryInspectionRepository.save(olfactoryInspectionEntity);

        visualInspectionEntity = createVisualInspectionEntity();
        visualInspectionRepository.save(visualInspectionEntity);

        gustatoryInspectionEntity = createGustatoryInspectionEntity();
        gustatoryInspectionRepository.save(gustatoryInspectionEntity);

        tastingEntity = createTastingEntity();
        tastingRepository.save(tastingEntity);

        tastingCardEntity = createTastingCardEntity();
    }


    @Test
    @DisplayName("Deve salvar um cartão de degustação")
    void testSaveTastingCard() {
        TastingCardEntity savedTastingCard = assertDoesNotThrow(() -> tastingCardRepository.save(tastingCardEntity));

        assertNotNull(savedTastingCard);
        assertEquals(tastingCardEntity.getId(), savedTastingCard.getId());
    }

    @Test
    @DisplayName("Deve retornar um cartão de degustação por id")
    void testFindByIdTastingCard() {
        assertDoesNotThrow(() -> tastingCardRepository.save(tastingCardEntity));
        Optional<TastingCardEntity> found = assertDoesNotThrow(() -> tastingCardRepository.findById(tastingCardEntity.getId()));
        assertNotNull(found);
        assertTrue(found.isPresent());
        assertEquals(tastingCardEntity.getId(), found.get().getId());
    }

    @Test
    @DisplayName("Deve alterar um cartão de degustação")
    void testUpdate() {
        TastingCardEntity savedTastingCard = assertDoesNotThrow(() -> tastingCardRepository.save(tastingCardEntity));
        savedTastingCard.setOpinion("Opinion Updated");

        TastingCardEntity updatedTastingCard = assertDoesNotThrow(() -> tastingCardRepository.save(savedTastingCard));

        assertNotNull(updatedTastingCard);
        assertEquals("Opinion Updated", updatedTastingCard.getOpinion());
    }

    @Test
    @DisplayName("Deve deletar um cartão de degustação")
    void testDelete() {
        TastingCardEntity savedTastingCard = assertDoesNotThrow(() -> tastingCardRepository.save(tastingCardEntity));
        assertDoesNotThrow(() -> tastingCardRepository.delete(savedTastingCard));

        Optional<TastingCardEntity> found = assertDoesNotThrow(() -> tastingCardRepository.findById(savedTastingCard.getId()));
        assertNotNull(found);
        assertTrue(found.isEmpty());
    }

    private GustatoryInspectionEntity createGustatoryInspectionEntity() {
        return GustatoryInspectionEntity.builder()
                .id(UUID.fromString("8942083a-3240-4d68-970f-c8349cc5e2f5"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .body(EnumBodyType.FULL_BODIED)
                .sweetness(EnumSweetnessType.SWEET)
                .tannin(EnumTanninType.LITTLE_TANIC)
                .classification(EnumClassificationType.LITTLE)
                .acidity(EnumAcidityType.ADEQUATE)
                .alcohol(EnumAlcoholType.LOW)
                .persistence(EnumPersistenceType.PERSISTENT)
                .maturity(EnumMaturityType.MATURE)
                .typicality(EnumTypicalityType.TYPICAL)
                .build();
    }

    private VisualInspectionEntity createVisualInspectionEntity() {
        return VisualInspectionEntity.builder()
                .id(UUID.fromString("3fe0f315-c704-49f8-8a71-a6a61002d2df"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .clarity(EnumClarityType.CLEAR)
                .brightness(EnumBrightnessType.BRIGHT)
                .viscosity(EnumViscosityType.VISCOUS)
                .colorRed(EnumRedColorType.RUBY)
                .classification(EnumClassificationType.LITTLE)
                .build();
    }

    private OlfactoryInspectionEntity createOlfactoryInspectionEntity() {
        return OlfactoryInspectionEntity.builder()
                .id(UUID.fromString("549cbf57-e4ad-496a-8e4c-ede70a799244"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .intensity(EnumIntensityType.INTENSE)
                .persistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .aromas(aromasEntity)
                .classification(EnumClassificationType.LITTLE)
                .build();
    }

    private TastingEntity createTastingEntity() {
        return TastingEntity.builder()
                .id(UUID.fromString("7a8ad3a6-2603-4aa2-a8d1-0b9d332bc32a"))
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.BLIND)
                .build();
    }

    private TastingCardEntity createTastingCardEntity() {
        return TastingCardEntity.builder()
                .id(UUID.fromString("06c95524-1c16-44bd-aee9-fd203620eede"))
                .visualInspection(visualInspectionEntity)
                .olfactoryInspection(olfactoryInspectionEntity)
                .gustatoryInspection(gustatoryInspectionEntity)
                .tasting(tastingEntity)
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .harvest("2021")
                .grapes("Grapes")
                .country("Country")
                .region("Region")
                .opinion("Opinion")
                .pointScale(EnumPointScale.CLASSIC)
                .build();
    }

    private AromasEntity createAromasEntity() {
        return AromasEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-7e7e7e7e7e7e"))
                .build();
    }

}
