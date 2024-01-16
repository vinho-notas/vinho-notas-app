package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;
    private Country brasil;
    private Country argentina;
    @BeforeEach
    void setUp() {
        brasil = createCountryBrasil();
        argentina = createCountryArgentina();
    }

    @Test
    @DisplayName("Deve salvar um país")
    void testSave() {
        Country countrySaved = assertDoesNotThrow(() -> countryRepository.save(brasil));

        assertNotNull(countrySaved);
        assertEquals("Brasil", countrySaved.getCountryName());
        assertEquals("América do Sul", countrySaved.getContinentName());
    }

    @Test
    @DisplayName("Deve retornar um país salvo no banco ao passar o id como parâmetro de busca")
    void testFindById() {
        Country countrySaved = assertDoesNotThrow(() -> countryRepository.save(brasil));
        Country countryFound = countryRepository.findById(countrySaved.getId()).orElse(null);

        assertNotNull(countryFound);
        assertEquals("Brasil", countryFound.getCountryName());
        assertEquals("América do Sul", countryFound.getContinentName());
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
        Country countrySaved = assertDoesNotThrow(() -> countryRepository.save(brasil));
        countrySaved.setCountryName("Brasil Alterado");
        countrySaved.setContinentName("América do Sul Alterado");
        Country countryUpdated = assertDoesNotThrow(() -> countryRepository.save(countrySaved));

        assertNotNull(countryUpdated);
        assertEquals("Brasil Alterado", countryUpdated.getCountryName());
        assertEquals("América do Sul Alterado", countryUpdated.getContinentName());
    }

    @Test
    @DisplayName("Deve deletar um país salvo no banco")
    void testDelete() {
        Country countrySaved = assertDoesNotThrow(() -> countryRepository.save(brasil));
        assertDoesNotThrow(() -> countryRepository.delete(countrySaved));

        assertEquals(0, countryRepository.findAll().size());
    }

    private Country createCountryBrasil() {
        return Country.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private Country createCountryArgentina() {
        return Country.builder()
                .countryName("Argentina")
                .continentName("América do Sul")
                .build();
    }

}