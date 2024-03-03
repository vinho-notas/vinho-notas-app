package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import com.vinhonotas.degustacao.domain.enums.EnumTastingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class TastingRepositoryTest {

    @Autowired
    private TastingRepository tastingRepository;

    private TastingEntity tastingEntity;
    private Set<TastingCardEntity> tastingCards;
    @BeforeEach
    void setUp() {
        tastingEntity = createTastingEntity();
        tastingCards = Set.of(new TastingCardEntity());
    }

    @Test
    @DisplayName("Deve cadastrar uma degustação")
    void testCreate() {
        TastingEntity tasting = assertDoesNotThrow(() -> tastingRepository.save(tastingEntity));

        assertNotNull(tasting);
        assertEquals(tastingEntity.getId(), tasting.getId());
        assertEquals(tastingEntity.getTastingData(), tasting.getTastingData());
        assertEquals(tastingEntity.getTastingType(), tasting.getTastingType());
        assertEquals(tastingEntity.getTastingCards(), tasting.getTastingCards());
    }

    @Test
    @DisplayName("Deve retornar uma degustação por id")
    void testFindById() {
        TastingEntity tastingSaved = assertDoesNotThrow(() -> tastingRepository.save(tastingEntity));

        Optional<TastingEntity> found = assertDoesNotThrow(() -> tastingRepository.findById(tastingSaved.getId()));

        assertNotNull(found);
        assertTrue(found.isPresent());
        assertEquals(tastingEntity.getId(), found.get().getId());
        assertEquals(tastingEntity.getTastingData(), found.get().getTastingData());
        assertEquals(tastingEntity.getTastingType(), found.get().getTastingType());
        assertEquals(tastingEntity.getTastingCards(), found.get().getTastingCards());
    }

    @Test
    @DisplayName("Deve atualizar uma degustação")
    void testUpdate() {
        TastingEntity tastingSaved = assertDoesNotThrow(() -> tastingRepository.save(tastingEntity));

        tastingSaved.setTastingType(EnumTastingType.VERTICAL);

        TastingEntity tastingUpdated = assertDoesNotThrow(() -> tastingRepository.save(tastingSaved));

        assertNotNull(tastingUpdated);
        assertEquals(tastingSaved.getId(), tastingUpdated.getId());
        assertEquals(EnumTastingType.VERTICAL, tastingUpdated.getTastingType());
    }

    @Test
    @DisplayName("Deve deletar uma degustação")
    void testDelete() {
        TastingEntity tastingSaved = assertDoesNotThrow(() -> tastingRepository.save(tastingEntity));

        assertDoesNotThrow(() -> tastingRepository.delete(tastingSaved));

        Optional<TastingEntity> found = assertDoesNotThrow(() -> tastingRepository.findById(tastingSaved.getId()));

        assertNotNull(found);
        assertTrue(found.isEmpty());
    }

    private TastingEntity createTastingEntity() {
        return TastingEntity.builder()
                .id(UUID.fromString("f5f3e3e3-3e3e-3e3e-3e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE)
                .tastingCards(tastingCards)
                .build();
    }

}
