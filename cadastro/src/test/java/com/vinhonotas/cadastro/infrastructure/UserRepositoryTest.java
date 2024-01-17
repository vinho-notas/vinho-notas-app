package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.*;
import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CountryRepository countryRepository;

    private PersonEntity personEnofilo;
    private PersonEntity personSommelier;
    private PersonEntity personParceiro;
    private UserEntity userEnofilo;
    private UserEntity userSommelier;
    private UserEntity userParceiro;
    private AddressEntity address;
    private StateEntity uf;
    private CountryEntity country;

    @BeforeEach
    void setUp() {
        country = createCountry();
        countryRepository.save(country);
        uf = createUf();
        stateRepository.save(uf);
        address = createAddress();
        addressRepository.save(address);
        personEnofilo = createPersonEnofilo();
        personRepository.save(personEnofilo);
        personSommelier = createPersonSomelier();
        personRepository.save(personSommelier);
        personParceiro = createPersonParceiro();
        personRepository.save(personParceiro);
        userEnofilo = createUserEnofilo();
        userSommelier = createUserSommerlier();
        userParceiro = createUserParceiro();
    }

    @Test
    @DisplayName("Deve salvar um usuário no banco de dados")
    void testSave() {
        UserEntity userEntitySaved = assertDoesNotThrow(() -> userRepository.save(userEnofilo));

        assertNotNull(userEntitySaved);
        assertEquals(personEnofilo, userEntitySaved.getPerson());
        assertEquals(EnumProfile.OENOPHILE, userEntitySaved.getProfile());
        assertEquals("person@enofilo.com", userEntitySaved.getEmail());
        assertEquals("123456", userEntitySaved.getPassword());
    }

    @Test
    @DisplayName("Deve retornar um usuário quando passado o id como parâmetro de busca")
    void testFindById() {
        UserEntity userEntitySaved = assertDoesNotThrow(() -> userRepository.save(userEnofilo));
        UserEntity userEntityFound = userRepository.findById(userEntitySaved.getId()).orElse(null);

        assertNotNull(userEntityFound);
        assertEquals(personEnofilo, userEntityFound.getPerson());
        assertEquals(EnumProfile.OENOPHILE, userEntityFound.getProfile());
        assertEquals("person@enofilo.com", userEntitySaved.getEmail());
        assertEquals("123456", userEntitySaved.getPassword());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todos os usuários salvos no banco")
    void testFindAll() {
        assertDoesNotThrow(() -> userRepository.save(userEnofilo));
        assertDoesNotThrow(() -> userRepository.save(userParceiro));
        assertDoesNotThrow(() -> userRepository.save(userSommelier));

        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    @DisplayName("Deve atualizar um usuário no banco de dados")
    void testUpdate() {
        UserEntity userEntitySaved = assertDoesNotThrow(() -> userRepository.save(userEnofilo));
        userEntitySaved.setProfile(EnumProfile.SOMMELIER);
        userEntitySaved.setEmail("new@email.com");
        userEntitySaved.setPassword("654321");

        UserEntity userEntityUpdated = assertDoesNotThrow(() -> userRepository.save(userEntitySaved));

        assertNotNull(userEntityUpdated);
        assertEquals(personEnofilo, userEntityUpdated.getPerson());
        assertEquals(EnumProfile.SOMMELIER, userEntityUpdated.getProfile());
        assertEquals("new@email.com", userEntityUpdated.getEmail());
        assertEquals("654321", userEntityUpdated.getPassword());
    }

    @Test
    @DisplayName("Deve deletar um usuário no banco de dados")
    void testDelete() {
        UserEntity userEntitySaved = assertDoesNotThrow(() -> userRepository.save(userEnofilo));
        assertDoesNotThrow(() -> userRepository.delete(userEntitySaved));

        assertEquals(0, userRepository.findAll().size());
    }

    private CountryEntity createCountry() {
        return CountryEntity.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    private StateEntity createUf() {
        return StateEntity.builder()
                .stateName("Santa Catarina")
                .uf("SC")
                .country(country)
                .build();
    }

    private AddressEntity createAddress() {
        return AddressEntity.builder()
                .street("Rua 3")
                .number(456)
                .complement("Complemento 1")
                .district("Bairro 1")
                .city("Cidade 1")
                .uf(uf)
                .phoneNumber("47999999999")
                .build();
    }

    private PersonEntity createPersonParceiro() {
        return PersonEntity.builder()
                .name("User Parceiro")
                .document("12345678900")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(address)
                .build();
    }

    private PersonEntity createPersonSomelier() {
        return PersonEntity.builder()
                .name("User Sommelier")
                .document("12345678900")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(address)
                .build();
    }

    private PersonEntity createPersonEnofilo() {
        return PersonEntity.builder()
                .name("User Enofilo")
                .document("12345678900")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(address)
                .build();
    }

    private UserEntity createUserEnofilo() {
        return UserEntity.builder()
                .person(personEnofilo)
                .profile(EnumProfile.OENOPHILE)
                .email("person@enofilo.com")
                .password("123456")
                .build();
    }

    private UserEntity createUserSommerlier() {
        return UserEntity.builder()
                .person(personSommelier)
                .profile(EnumProfile.SOMMELIER)
                .email("person@sommelier.com")
                .password("123456")
                .build();
    }

    private UserEntity createUserParceiro() {
        return UserEntity.builder()
                .person(personParceiro)
                .profile(EnumProfile.PARTNER)
                .email("person@parceiro.com")
                .password("123456")
                .build();
    }

}