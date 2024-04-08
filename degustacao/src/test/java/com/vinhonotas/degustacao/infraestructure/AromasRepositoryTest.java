package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.enums.*;
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
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=true",
        "spring.jpa.properties.hibernate.format_sql=true"
})
class AromasRepositoryTest {

    @Autowired
    private AromasRepository aromasRepository;

    private AromasEntity aromasEntity;

    @BeforeEach
    void setUp() {
        aromasEntity = createAromasEntity();
    }

    @Test
    @DisplayName("Deve criar um registro de aromas")
    void testCreateAromasEntity() {
        AromasEntity aromasSaved = assertDoesNotThrow(() -> aromasRepository.save(aromasEntity));
        assertNotNull(aromasSaved);
        assertEquals(aromasEntity.getId(), aromasSaved.getId());
        assertEquals(aromasEntity.getTastingData(), aromasSaved.getTastingData());
        assertEquals(aromasEntity.getWineTasted(), aromasSaved.getWineTasted());
        assertEquals(aromasEntity.getFruity(), aromasSaved.getFruity());
        assertEquals(aromasEntity.getFlorals(), aromasSaved.getFlorals());
        assertEquals(aromasEntity.getSpices(), aromasSaved.getSpices());
        assertEquals(aromasEntity.getWood(), aromasSaved.getWood());
    }

    @Test
    @DisplayName("Deve retornar uma lista de aromas")
    void testReadAllAromasEntity() {
        AromasEntity aromasSavedOne = assertDoesNotThrow(() -> aromasRepository.save(aromasEntity));
        AromasEntity aromasSavedTwo = assertDoesNotThrow(() -> aromasRepository.save(new AromasEntity()));
        List<AromasEntity> list = assertDoesNotThrow(() -> aromasRepository.findAll());

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Deve retornar um registro de aromas pelo id")
    void testFindById() {
        AromasEntity aromasSaved = assertDoesNotThrow(() -> aromasRepository.save(aromasEntity));

        Optional<AromasEntity> aromasFound = assertDoesNotThrow(() -> aromasRepository.findById(aromasSaved.getId()));
        assertNotNull(aromasFound);
        assertTrue(aromasFound.isPresent());
        assertEquals(aromasSaved.getId(), aromasFound.get().getId());
        assertEquals(aromasSaved.getTastingData(), aromasFound.get().getTastingData());
        assertEquals(aromasSaved.getWineTasted(), aromasFound.get().getWineTasted());
        assertEquals(aromasSaved.getFruity(), aromasFound.get().getFruity());
        assertEquals(aromasSaved.getFlorals(), aromasFound.get().getFlorals());
        assertEquals(aromasSaved.getSpices(), aromasFound.get().getSpices());
        assertEquals(aromasSaved.getWood(), aromasFound.get().getWood());
    }

@Test
    @DisplayName("Deve atualizar um registro de aromas")
    void testUpdateAromasEntity() {
        AromasEntity aromasSaved = assertDoesNotThrow(() -> aromasRepository.save(aromasEntity));

        aromasSaved.setWineTasted("Wine Tasted Updated");
        aromasSaved.setAnimals(EnumAnimalsType.LEATHER);

        AromasEntity aromasUpdated = assertDoesNotThrow(() -> aromasRepository.save(aromasSaved));
        assertNotNull(aromasUpdated);
        assertEquals(aromasSaved.getId(), aromasUpdated.getId());
        assertEquals("Wine Tasted Updated", aromasUpdated.getWineTasted());
        assertEquals(EnumAnimalsType.LEATHER, aromasUpdated.getAnimals());
    }

    @Test
    @DisplayName("Deve deletar um registro de aromas")
    void testDeleteAromasEntity() {
        AromasEntity aromasSaved = assertDoesNotThrow(() -> aromasRepository.save(aromasEntity));
        assertDoesNotThrow(() -> aromasRepository.delete(aromasSaved));

        Optional<AromasEntity> aromasFound = assertDoesNotThrow(() -> aromasRepository.findById(aromasSaved.getId()));
        assertNotNull(aromasFound);
        assertTrue(aromasFound.isEmpty());
    }

    private AromasEntity createAromasEntity() {
        return AromasEntity.builder()
                .id(UUID.fromString("f5d6e3e3-3e3e-3e3e-3e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .fruity(EnumFruityType.RASPBERRY)
                .florals(EnumFloralsType.CLOVE)
                .spices(EnumSpicesType.INDIAN_CLOVE)
                .wood(EnumWoodType.SAWDUST)
                .build();
    }

}
