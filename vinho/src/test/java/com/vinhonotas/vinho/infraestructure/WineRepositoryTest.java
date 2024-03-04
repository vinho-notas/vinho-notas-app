package com.vinhonotas.vinho.infraestructure;

import com.vinhonotas.vinho.domain.entities.WineEntity;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class WineRepositoryTest {

    @Autowired
    private WineRepository wineRepository;

    private WineEntity redWine;
    private WineEntity whiteWine;

    @BeforeEach
    void setUp() {
        redWine = createRedWine();
        whiteWine = createWhiteWine();
    }

    @Test
    @DisplayName("Deve criar um vinho no banco de dados")
    void testCreateWine() {
        WineEntity wineSaved = assertDoesNotThrow(() -> wineRepository.save(redWine));

        assertNotNull(wineSaved);
        assertEquals(redWine.getId(), wineSaved.getId());
        assertEquals(redWine.getName(), wineSaved.getName());
        assertEquals(redWine.getPrice(), wineSaved.getPrice());
        assertEquals(redWine.getPurchaseLocation(), wineSaved.getPurchaseLocation());
        assertEquals(redWine.getPurchaseDate(), wineSaved.getPurchaseDate());
        assertEquals(redWine.getWineType(), wineSaved.getWineType());
        assertEquals(redWine.getWineClassification(), wineSaved.getWineClassification());
        assertEquals(redWine.getAlcoholContent(), wineSaved.getAlcoholContent());
        assertEquals(redWine.getVolumeMl(), wineSaved.getVolumeMl());
        assertEquals(redWine.getGrape(), wineSaved.getGrape());
        assertEquals(redWine.getWinery(), wineSaved.getWinery());
        assertEquals(redWine.getServiceTemperature(), wineSaved.getServiceTemperature());
        assertEquals(redWine.getHarvest(), wineSaved.getHarvest());
        assertEquals(redWine.getCountry(), wineSaved.getCountry());
        assertEquals(redWine.getGuardTime(), wineSaved.getGuardTime());
        assertEquals(redWine.getRegion(), wineSaved.getRegion());
        assertEquals(redWine.getMaturation(), wineSaved.getMaturation());
        assertEquals(redWine.getHarmonization(), wineSaved.getHarmonization());
    }

    @Test
    @DisplayName("Deve retornar uma lista de vinhos")
    void testReadAllWines() {
        WineEntity redWineSaved = assertDoesNotThrow(() -> wineRepository.save(redWine));
        WineEntity whiteWineSaved = assertDoesNotThrow(() -> wineRepository.save(whiteWine));

        List<WineEntity> wineList = assertDoesNotThrow(() -> wineRepository.findAll());
        assertNotNull(wineList);
        assertEquals(2, wineList.size());
        assertFalse(wineList.isEmpty());
        assertEquals(redWineSaved.getId(), wineList.get(0).getId());
        assertEquals(whiteWineSaved.getId(), wineList.get(1).getId());
    }

    @Test
    @DisplayName("Deve retornar um vinho pelo id")
    void testFindById() {
        WineEntity whiteWineSaved = assertDoesNotThrow(() -> wineRepository.save(whiteWine));

        WineEntity wineFound = assertDoesNotThrow(() -> wineRepository.findById(whiteWineSaved.getId()).orElseThrow());
        assertNotNull(wineFound);
        assertEquals(whiteWineSaved.getId(), wineFound.getId());
    }

    @Test
    @DisplayName("Deve atualizar um vinho")
    void testUpdate() {
        WineEntity whiteWineSaved = assertDoesNotThrow(() -> wineRepository.save(whiteWine));
        whiteWineSaved.setPrice(BigDecimal.valueOf(100.00));
        WineEntity wineUpdated = assertDoesNotThrow(() -> wineRepository.save(whiteWineSaved));

        assertNotNull(wineUpdated);
        assertEquals(whiteWineSaved.getId(), wineUpdated.getId());
        assertEquals(whiteWineSaved.getPrice(), wineUpdated.getPrice());
    }

    @Test
    @DisplayName("Deve deletar um vinho")
    void testDelete() {
        WineEntity whiteWineSaved = assertDoesNotThrow(() -> wineRepository.save(whiteWine));
        assertDoesNotThrow(() -> wineRepository.deleteById(whiteWineSaved.getId()));
        assertTrue(wineRepository.findAll().isEmpty());
    }

    private WineEntity createWhiteWine() {
        return WineEntity.builder()
                .id(UUID.fromString("468fc21e-f713-4974-9358-4a9547708ae4"))
                .name("468fc21e-f713-4974-9358-4a9547708ae4")
                .price(BigDecimal.valueOf(50.00))
                .purchaseLocation("www.wine.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.WHITEWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .alcoholContent("12.5")
                .volumeMl(750)
                .grape("Tempranillo Blanco (80.00%), Verdejo (20.00%)")
                .winery("Bodegas D. Mateos")
                .serviceTemperature("9.0")
                .harvest("2021")
                .country("Espanha")
                .guardTime("15 anos")
                .region("La Rioja")
                .maturation("10 meses em barricas de carvalho francês")
                .harmonization("Peixe assado na folha de bananeira, frutos do mar, arroz com brócolis, espetinho de camarão, massas, burrata, saladas.")
                .build();
    }

    private WineEntity createRedWine() {
        return WineEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .alcoholContent("12.5")
                .volumeMl(750)
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature("17.0")
                .harvest("2020")
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

}
