package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.*;
import com.vinhonotas.degustacao.domain.enums.EnumPointScale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TastingCardRepositoryTest {

    @Autowired
    private TastingCardRepository tastingCardRepository;
    @Autowired
    private AromasRepository aromasRepository;

    private TastingCardEntity tastingCardEntity;
    private AromasEntity aromasEntity;

    @BeforeEach
    void setUp() {
        aromasEntity = createAromasEntity();
        aromasRepository.save(aromasEntity);
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
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .harvest("2021")
                .grapes("Grapes")
                .country("Country")
                .region("Region")
                .visualInspection(VisualInspectionEntity.builder().id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-4e4e4e4e4e4e")).build())
                .olfactoryInspection(OlfactoryInspectionEntity.builder()
                        .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-5e5e5e5e5e5e"))
                        .aromas(aromasEntity)
                        .build())
                .gustatoryInspection(GustatoryInspectionEntity.builder().id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-6e6e6e6e6e6e")).build())
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
