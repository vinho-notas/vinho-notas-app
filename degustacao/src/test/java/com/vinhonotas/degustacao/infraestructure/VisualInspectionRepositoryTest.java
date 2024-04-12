//package com.vinhonotas.degustacao.infraestructure;
//
//import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
//import com.vinhonotas.degustacao.domain.enums.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest(properties = {
//        "spring.datasource.url=jdbc:h2:mem:testdb",
//        "spring.jpa.hibernate.ddl-auto=create-drop",
//        "spring.jpa.show-sql=true",
//        "spring.jpa.properties.hibernate.format_sql=true"
//})
//class VisualInspectionRepositoryTest {
//
//    @Autowired
//    private VisualInspectionRepository visualInspectionRepository;
//
//    private VisualInspectionEntity visualInspectionOne;
//    private VisualInspectionEntity visualInspectionTwo;
//
//    @BeforeEach
//    void setUp() {
//        visualInspectionOne = createVisualInspectionOne();
//        visualInspectionTwo = createVisualInspectionTwo();
//    }
//
//    @Test
//    @DisplayName("Deve criar uma inspeção visual")
//    void testCreate() {
//        VisualInspectionEntity visualInspectionSaved = assertDoesNotThrow(() -> visualInspectionRepository.save(visualInspectionOne));
//        assertNotNull(visualInspectionSaved);
//        assertEquals(visualInspectionOne.getId(), visualInspectionSaved.getId());
//        assertEquals(visualInspectionOne.getTastingData(), visualInspectionSaved.getTastingData());
//        assertEquals(visualInspectionOne.getWineTasted(), visualInspectionSaved.getWineTasted());
//        assertEquals(visualInspectionOne.getClarity(), visualInspectionSaved.getClarity());
//        assertEquals(visualInspectionOne.getBrightness(), visualInspectionSaved.getBrightness());
//        assertEquals(visualInspectionOne.getViscosity(), visualInspectionSaved.getViscosity());
//        assertEquals(visualInspectionOne.getColorWhite(), visualInspectionSaved.getColorWhite());
//        assertEquals(visualInspectionOne.getClassification(), visualInspectionSaved.getClassification());
//    }
//
//    @Test
//    @DisplayName("Deve listar todas as inspeções visuais")
//    void testReadAll() {
//        assertDoesNotThrow(() -> visualInspectionRepository.save(visualInspectionOne));
//        assertDoesNotThrow(() -> visualInspectionRepository.save(visualInspectionTwo));
//        List<VisualInspectionEntity> list = assertDoesNotThrow(() -> visualInspectionRepository.findAll());
//
//        assertNotNull(list);
//        assertEquals(2, list.size());
//        assertFalse(list.isEmpty());
//        assertEquals(visualInspectionOne.getId(), list.get(0).getId());
//        assertEquals(visualInspectionTwo.getId(), list.get(1).getId());
//    }
//
//    @Test
//    @DisplayName("Deve listar uma inspeção visual por id")
//    void testReadById() {
//        VisualInspectionEntity visualInspectionSaved = assertDoesNotThrow(() -> visualInspectionRepository.save(visualInspectionTwo));
//
//        Optional<VisualInspectionEntity> obj = assertDoesNotThrow(() -> visualInspectionRepository.findById(visualInspectionTwo.getId()));
//
//        assertNotNull(obj);
//        assertTrue(obj.isPresent());
//
//        assertEquals(visualInspectionTwo.getId(), obj.get().getId());
//        assertEquals(visualInspectionTwo.getTastingData(), obj.get().getTastingData());
//        assertEquals(visualInspectionTwo.getWineTasted(), obj.get().getWineTasted());
//        assertEquals(visualInspectionTwo.getClarity(), obj.get().getClarity());
//        assertEquals(visualInspectionTwo.getBrightness(), obj.get().getBrightness());
//        assertEquals(visualInspectionTwo.getViscosity(), obj.get().getViscosity());
//        assertEquals(visualInspectionTwo.getColorWhite(), obj.get().getColorWhite());
//        assertEquals(visualInspectionTwo.getClassification(), obj.get().getClassification());
//    }
//
//    @Test
//    @DisplayName("Deve atualizar uma inspeção visual")
//    void testUpdate() {
//        VisualInspectionEntity visualInspectionSaved = assertDoesNotThrow(() -> visualInspectionRepository.save(visualInspectionOne));
//        visualInspectionSaved.setWineTasted("Wine Tasted Updated");
//        visualInspectionSaved.setClassification(EnumClassificationType.LITTLE);
//
//        VisualInspectionEntity visualInspectionUpdated = assertDoesNotThrow(() -> visualInspectionRepository.save(visualInspectionSaved));
//        assertNotNull(visualInspectionUpdated);
//        assertEquals(visualInspectionSaved.getId(), visualInspectionUpdated.getId());
//        assertEquals("Wine Tasted Updated", visualInspectionUpdated.getWineTasted());
//        assertEquals(EnumClassificationType.LITTLE, visualInspectionUpdated.getClassification());
//    }
//
//    @Test
//    @DisplayName("Deve deletar uma inspeção visual")
//    void testDelete() {
//        VisualInspectionEntity visualInspectionSaved = assertDoesNotThrow(() -> visualInspectionRepository.save(visualInspectionOne));
//        assertDoesNotThrow(() -> visualInspectionRepository.deleteById(visualInspectionSaved.getId()));
//        Optional<VisualInspectionEntity> obj = assertDoesNotThrow(() -> visualInspectionRepository.findById(visualInspectionOne.getId()));
//
//        assertNotNull(obj);
//        assertTrue(obj.isEmpty());
//    }
//
//    private VisualInspectionEntity createVisualInspectionOne() {
//        return VisualInspectionEntity.builder()
//                .id(UUID.fromString("f5e7e3e4-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
//                .tastingData(LocalDate.now())
//                .wineTasted("wine Tasted One")
//                .clarity(EnumClarityType.CLEAR)
//                .brightness(EnumBrightnessType.BRIGHT)
//                .viscosity(EnumViscosityType.LITTLE_VISCOUS)
//                .colorWhite(EnumWhiteColorType.STRAW_YELLOW)
//                .classification(EnumClassificationType.GOOD)
//                .userreg("userreg")
//                .dthreg(LocalDateTime.now())
//                .useralt("userupd")
//                .dthalt(LocalDateTime.now())
//                .build();
//    }
//
//    private VisualInspectionEntity createVisualInspectionTwo() {
//        return VisualInspectionEntity.builder()
//                .id(UUID.fromString("f5e7e3e4-3e3e-4e3e-8e3e-4e4e4e4e4e4e"))
//                .tastingData(LocalDate.now())
//                .wineTasted("wine Tasted Two")
//                .clarity(EnumClarityType.VERY_CLEAR)
//                .brightness(EnumBrightnessType.VERY_BRIGHT)
//                .viscosity(EnumViscosityType.SLIPPERY)
//                .colorRed(EnumRedColorType.VIOLET)
//                .classification(EnumClassificationType.EXCELLENT)
//                .userreg("userreg")
//                .dthreg(LocalDateTime.now())
//                .useralt("userupd")
//                .dthalt(LocalDateTime.now())
//                .build();
//    }
//
//}
