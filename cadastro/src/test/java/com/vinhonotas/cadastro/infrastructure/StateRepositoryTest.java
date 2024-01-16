package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.Country;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StateRepositoryTest {

    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CountryRepository countryRepository;
    private StateEntity santaCatarina;
    private StateEntity parana;
    private Country country;

    @BeforeEach
    void setUp() {
        country = createCountryBrasil();
        santaCatarina = createStateSantaCatarina();
        parana = createStateParana();

        countryRepository.save(country);
    }

    @Test
    @DisplayName("Deve salvar um estado no banco de dados")
    void testSave() {
        StateEntity stateSaved = assertDoesNotThrow(() -> stateRepository.save(santaCatarina));

        assertNotNull(stateSaved);
        assertEquals("Santa Catarina", stateSaved.getStateName());
        assertEquals("SC", stateSaved.getUf());
        assertEquals(country.getCountryName(), stateSaved.getCountry().getCountryName());
    }

    @Test
    @DisplayName("Deve retornar um estado quando passado o id como parâmetro de busca")
    void testFindById() {
        StateEntity stateSaved = assertDoesNotThrow(() -> stateRepository.save(santaCatarina));
        StateEntity stateFound = stateRepository.findById(stateSaved.getId()).orElse(null);

        assertNotNull(stateFound);
        assertEquals("Santa Catarina", stateFound.getStateName());
        assertEquals("SC", stateFound.getUf());
        assertEquals(country.getCountryName(), stateFound.getCountry().getCountryName());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todos os estados salvos no banco")
    void testFindAll() {
        assertDoesNotThrow(() -> stateRepository.save(santaCatarina));
        assertDoesNotThrow(() -> stateRepository.save(parana));

        assertEquals(2, stateRepository.findAll().size());
    }

    @Test
    @DisplayName("Deve alterar um estado salvo no banco")
    void testUpdate() {
        StateEntity stateSaved = assertDoesNotThrow(() -> stateRepository.save(santaCatarina));
        stateSaved.setStateName("Santa Catarina Alterado");
        stateSaved.setUf("SC Alterado");
        StateEntity stateUpdated = assertDoesNotThrow(() -> stateRepository.save(stateSaved));

        assertNotNull(stateUpdated);
        assertEquals("Santa Catarina Alterado", stateUpdated.getStateName());
        assertEquals("SC Alterado", stateUpdated.getUf());
        assertEquals(country.getCountryName(), stateUpdated.getCountry().getCountryName());
    }

    @Test
    @DisplayName("Deve deletar um estado salvo no banco")
    void testDelete() {
        StateEntity stateSaved = assertDoesNotThrow(() -> stateRepository.save(santaCatarina));
        assertDoesNotThrow(() -> stateRepository.delete(stateSaved));

        assertEquals(0, stateRepository.findAll().size());
    }

    private StateEntity createStateParana() {
        return StateEntity.builder()
                .stateName("Paraná")
                .uf("PR")
                .country(country)
                .build();
    }

    private Country createCountryBrasil() {
        return Country.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private StateEntity createStateSantaCatarina() {
        return StateEntity.builder()
                .stateName("Santa Catarina")
                .uf("SC")
                .country(country)
                .build();
    }

}