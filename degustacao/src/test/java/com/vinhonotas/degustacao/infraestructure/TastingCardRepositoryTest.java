package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private TastingCardEntity tastingCardEntity;

    @BeforeEach
    void setUp() {
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

    private TastingCardEntity createTastingCardEntity() {
        return TastingCardEntity.builder()
                .id(UUID.fromString("06c95524-1c16-44bd-aee9-fd203620eede"))
                .wineTasted("Wine Tasted")
                .tastingData(LocalDate.now())
                .harvest("2021")
                .grapes("Grapes")
                .country("Country")
                .region("Region")
                .clarity(EnumClarityType.CLEAR)
                .brightness(EnumBrightnessType.BRIGHT)
                .viscosity(EnumViscosityType.VISCOUS)
                .colorRed(EnumRedColorType.RUBY)
                .colorWhite(EnumWhiteColorType.GOLDEN)
                .colorRose(EnumRoseColorType.BROWN)
                .visualInspectionClassification(EnumClassificationType.LITTLE)
                .intensity(EnumIntensityType.INTENSE)
                .olfactoryInspectionPersistence(EnumPersistenceType.PERSISTENT)
                .quality(EnumQualityType.COMMON)
                .fruity(EnumFruityType.BANANA)
                .dryFruits(EnumDryFruitsType.ALMODN)
                .florals(EnumFloralsType.ACACIA)
                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL)
                .minerals(EnumMineralsType.CHALK)
                .spices(EnumSpicesType.ANISE)
                .animals(EnumAnimalsType.HUNTING)
                .empireumatics(EnumEmpireumaticsType.CARAMEL)
                .wood(EnumWoodType.SAWDUST)
                .chemicals(EnumChemicalsAndEtherealType.ACETONE)
                .lacteal(EnumLactealType.BUTTER)
                .sweets(EnumSweetsType.BULLET)
                .olfactoryInspectionClassification(EnumClassificationType.EXCELLENT)
                .body(EnumBodyType.LITTLE_BODY)
                .sweetness(EnumSweetnessType.SWEET)
                .tannin(EnumTanninType.LITTLE_TANIC)
                .acidity(EnumAcidityType.LITTLE_ACID)
                .alcohol(EnumAlcoholType.LOW)
                .gustatoryInspectionPersistence(EnumPersistenceType.PERSISTENT)
                .maturity(EnumMaturityType.MATURE)
                .typicality(EnumTypicalityType.TYPICAL)
                .gustatoryInspectionClassification(EnumClassificationType.LITTLE)
                .opinion("Opinion")
                .pointScale(EnumPointScale.VERYGOOD)
                .dthreg(LocalDateTime.now())
                .userreg("User Reg")
                .build();
    }

}
