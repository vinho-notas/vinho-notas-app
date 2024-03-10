package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CountryRepository countryRepository;

    private PersonEntity person1;
    private PersonEntity person2;
    private AddressEntity address1;
    private AddressEntity address2;
    private StateEntity uf;
    private CountryEntity country;

    @BeforeEach
    void setUp() {
        country = createCountry();
        countryRepository.save(country);
        uf = createState();
        stateRepository.save(uf);
        address1 = createAddress1();
        addressRepository.save(address1);
        address2 = createAddress2();
        addressRepository.save(address2);
        person1 = createPerson1();
        person2 = createPerson2();
    }

    @Test
    @DisplayName("Deve salvar uma pessoa no banco de dados")
    void testSave() {
        PersonEntity personEntitySaved = assertDoesNotThrow(() -> personRepository.save(person1));

        assertNotNull(personEntitySaved);
        assertEquals("Person 1", personEntitySaved.getName());
        assertEquals("00000000000", personEntitySaved.getDocument());
        assertEquals(LocalDate.of(1990, 1, 1), personEntitySaved.getBirthDate());
        assertEquals(address1, personEntitySaved.getAddress());
    }

    @Test
    @DisplayName("Deve retornar uma pessoa salva no banco ao passar o id como parâmetro de busca")
    void testFindById() {
        PersonEntity personEntitySaved = assertDoesNotThrow(() -> personRepository.save(person1));
        PersonEntity personEntityFound = personRepository.findById(personEntitySaved.getId()).orElse(null);

        assertNotNull(personEntityFound);
        assertEquals("Person 1", personEntityFound.getName());
        assertEquals("00000000000", personEntityFound.getDocument());
        assertEquals(LocalDate.of(1990, 1, 1), personEntityFound.getBirthDate());
        assertEquals(address1, personEntityFound.getAddress());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todas as pessoas salvas no banco")
    void testFindAll() {
        assertDoesNotThrow(() -> personRepository.save(person1));
        assertDoesNotThrow(() -> personRepository.save(person2));

        assertEquals(2, personRepository.findAll().size());
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa no banco de dados")
    void testUpdate() {
        PersonEntity personEntitySaved = assertDoesNotThrow(() -> personRepository.save(person1));
        personEntitySaved.setName("Person 1 Updated");
        personEntitySaved.setDocument("11111111111");
        personEntitySaved.setBirthDate(LocalDate.of(1991, 1, 1));
        personEntitySaved.setAddress(address2);

        PersonEntity personEntityUpdated = assertDoesNotThrow(() -> personRepository.save(personEntitySaved));

        assertNotNull(personEntityUpdated);
        assertEquals("Person 1 Updated", personEntityUpdated.getName());
        assertEquals("11111111111", personEntityUpdated.getDocument());
        assertEquals(LocalDate.of(1991, 1, 1), personEntityUpdated.getBirthDate());
        assertEquals(address2, personEntityUpdated.getAddress());
    }

    @Test
    @DisplayName("Deve deletar uma pessoa no banco de dados")
    void testDelete() {
        PersonEntity personEntitySaved = assertDoesNotThrow(() -> personRepository.save(person1));
        assertDoesNotThrow(() -> personRepository.delete(personEntitySaved));

        assertEquals(0, personRepository.findAll().size());
    }

    private CountryEntity createCountry() {
        return CountryEntity.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private AddressEntity createAddress1() {
        return AddressEntity.builder()
                .addressDescription("Rua 3")
                .addressNumber(456)
                .complement("Complemento 1")
                .district("Bairro 1")
                .zipCode("99999999")
                .city("Cidade 1")
                .uf(uf)
                .country(country)
                .phoneNumber("47999999999")
                .build();
    }

    private PersonEntity createPerson1() {
        return PersonEntity.builder()
                .name("Person 1")
                .document("00000000000")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(address1)
                .build();
    }

    private StateEntity createState() {
        return StateEntity.builder()
                .stateName("Santa Catarina")
                .uf("SC")
                .country(country)
                .build();
    }

    private AddressEntity createAddress2() {
        return AddressEntity.builder()
                .addressDescription("Rua 2")
                .addressNumber(123)
                .complement("Complemento 2")
                .district("Bairro 2")
                .zipCode("88888888")
                .city("Cidade 2")
                .uf(uf)
                .country(country)
                .phoneNumber("47999999999")
                .build();
    }

    private PersonEntity createPerson2() {
        return PersonEntity.builder()
                .name("Person 2")
                .document("12345678910")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(address2)
                .build();
    }

}