package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=true",
        "spring.jpa.properties.hibernate.format_sql=true"
})
class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;
    private CountryEntity brasil;
    private CountryEntity argentina;
    @BeforeEach
    void setUp() {
        brasil = createCountryBrasil();
        argentina = createCountryArgentina();
    }

    @Test
    @DisplayName("Deve salvar um país")
    void testSave() {
        CountryEntity countryEntitySaved = assertDoesNotThrow(() -> countryRepository.save(brasil));

        assertNotNull(countryEntitySaved);
        assertEquals("Brasil", countryEntitySaved.getCountryName());
        assertEquals("América do Sul", countryEntitySaved.getContinentName());
    }

    @Test
    @DisplayName("Deve retornar um país salvo no banco ao passar o id como parâmetro de busca")
    void testFindById() {
        CountryEntity countryEntitySaved = assertDoesNotThrow(() -> countryRepository.save(brasil));
        CountryEntity countryEntityFound = countryRepository.findById(countryEntitySaved.getId()).orElse(null);

        assertNotNull(countryEntityFound);
        assertEquals("Brasil", countryEntityFound.getCountryName());
        assertEquals("América do Sul", countryEntityFound.getContinentName());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todos os países salvos no banco")
    void testFindAll() {
        assertDoesNotThrow(() -> countryRepository.save(brasil));
        assertDoesNotThrow(() -> countryRepository.save(argentina));

        assertEquals(2, countryRepository.findAll().size());
    }

    @Test
    @DisplayName("Deve alterar um país salvo no banco")
    void testUpdate() {
        CountryEntity countryEntitySaved = assertDoesNotThrow(() -> countryRepository.save(brasil));
        countryEntitySaved.setCountryName("Brasil Alterado");
        countryEntitySaved.setContinentName("América do Sul Alterado");
        CountryEntity countryEntityUpdated = assertDoesNotThrow(() -> countryRepository.save(countryEntitySaved));

        assertNotNull(countryEntityUpdated);
        assertEquals("Brasil Alterado", countryEntityUpdated.getCountryName());
        assertEquals("América do Sul Alterado", countryEntityUpdated.getContinentName());
    }

    @Test
    @DisplayName("Deve deletar um país salvo no banco")
    void testDelete() {
        CountryEntity countryEntitySaved = assertDoesNotThrow(() -> countryRepository.save(brasil));
        assertDoesNotThrow(() -> countryRepository.delete(countryEntitySaved));

        assertEquals(0, countryRepository.findAll().size());
    }

    private CountryEntity createCountryBrasil() {
        return CountryEntity.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .userreg("admin")
                .dthreg(LocalDateTime.now())
                .useralt(null)
                .dthalt(null)
                .build();
    }

    private CountryEntity createCountryArgentina() {
        return CountryEntity.builder()
                .countryName("Argentina")
                .continentName("América do Sul")
                .userreg("admin")
                .dthreg(LocalDateTime.now())
                .useralt(null)
                .dthalt(null)
                .build();
    }

}