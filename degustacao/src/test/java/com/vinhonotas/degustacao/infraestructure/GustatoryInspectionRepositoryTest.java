package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.domain.enums.EnumBodyType;
import com.vinhonotas.degustacao.domain.enums.EnumClassificationType;
import com.vinhonotas.degustacao.domain.enums.EnumSweetnessType;
import com.vinhonotas.degustacao.domain.enums.EnumTanninType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class GustatoryInspectionRepositoryTest {

    @Autowired
    private GustatoryInspectionRepository gustatoryInspectionRepository;

    private GustatoryInspectionEntity gustatoryInspectionEntity;

    @BeforeEach
    void setUp() {
        gustatoryInspectionEntity = createGustatoryInspectionEntity();
    }

    @Test
    @DisplayName("Deve salvar uma inspeção gustativa")
    void testDeveSalvarUmaInspecaoGustativa() {
        GustatoryInspectionEntity savedGustatoryInspection = assertDoesNotThrow(() -> gustatoryInspectionRepository.save(gustatoryInspectionEntity));

        assertNotNull(savedGustatoryInspection);
        assertEquals(gustatoryInspectionEntity.getId(), savedGustatoryInspection.getId());
        assertEquals(gustatoryInspectionEntity.getTastingData(), savedGustatoryInspection.getTastingData());
        assertEquals(gustatoryInspectionEntity.getWineTasted(), savedGustatoryInspection.getWineTasted());
        assertEquals(gustatoryInspectionEntity.getBody(), savedGustatoryInspection.getBody());
        assertEquals(gustatoryInspectionEntity.getSweetness(), savedGustatoryInspection.getSweetness());
        assertEquals(gustatoryInspectionEntity.getTannin(), savedGustatoryInspection.getTannin());
        assertEquals(gustatoryInspectionEntity.getClassification(), savedGustatoryInspection.getClassification());
    }

    @Test
    @DisplayName("Deve retornar uma lista de inspeções gustativas")
    void testDeveRetornarUmaListaDeInspecoesGustativas() {
        GustatoryInspectionEntity saved = assertDoesNotThrow(() -> gustatoryInspectionRepository.save(gustatoryInspectionEntity));

        List<GustatoryInspectionEntity> list = assertDoesNotThrow(() -> gustatoryInspectionRepository.findAll());
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(saved.getId(), list.get(0).getId());
    }

    @Test
    @DisplayName("Deve retornar uma inspeção gustativa por id")
    void testDeveRetornarUmaInspecaoGustativaPorId() {
        GustatoryInspectionEntity saved = assertDoesNotThrow(() -> gustatoryInspectionRepository.save(gustatoryInspectionEntity));

        Optional<GustatoryInspectionEntity> found = assertDoesNotThrow(() -> gustatoryInspectionRepository.findById(saved.getId()));
        assertNotNull(found);
        assertFalse(found.isEmpty());
        assertEquals(saved.getId(), found.get().getId());
    }

    @Test
    @DisplayName("Deve atualizar uma inspeção gustativa")
    void testDeveAtualizarUmaInspecaoGustativa() {
        GustatoryInspectionEntity saved = assertDoesNotThrow(() -> gustatoryInspectionRepository.save(gustatoryInspectionEntity));
        saved.setWineTasted("Wine Tasted Updated");
        saved.setBody(EnumBodyType.FULL_BODIED);
        saved.setSweetness(EnumSweetnessType.VERY_DRY);

        GustatoryInspectionEntity updated = assertDoesNotThrow(() -> gustatoryInspectionRepository.save(saved));

        assertNotNull(updated);
        assertEquals(saved.getId(), updated.getId());
        assertEquals("Wine Tasted Updated", updated.getWineTasted());
        assertEquals(EnumBodyType.FULL_BODIED, updated.getBody());
        assertEquals(EnumSweetnessType.VERY_DRY, updated.getSweetness());
    }

    @Test
    @DisplayName("Deve deletar uma inspeção gustativa")
    void testDeveDeletarUmaInspecaoGustativa() {
        GustatoryInspectionEntity saved = assertDoesNotThrow(() -> gustatoryInspectionRepository.save(gustatoryInspectionEntity));
        assertDoesNotThrow(() -> gustatoryInspectionRepository.delete(saved));

        Optional<GustatoryInspectionEntity> found = assertDoesNotThrow(() -> gustatoryInspectionRepository.findById(saved.getId()));
        assertNotNull(found);
        assertTrue(found.isEmpty());
    }

    private GustatoryInspectionEntity createGustatoryInspectionEntity() {
        return GustatoryInspectionEntity.builder()
                .id(UUID.fromString("f3b3f3e3-3f3b-3e3f-3b3f-3e3f3b3f3e3b"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .body(EnumBodyType.LITTLE_BODY)
                .sweetness(EnumSweetnessType.DRY)
                .tannin(EnumTanninType.LITTLE_TANIC)
                .classification(EnumClassificationType.GOOD)
                .build();
    }

}
