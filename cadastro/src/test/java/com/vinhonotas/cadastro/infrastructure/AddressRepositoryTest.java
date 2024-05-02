package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
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
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CountryRepository countryRepository;

    private StateEntity sc;
    private StateEntity pr;
    private CountryEntity brasil;

    @BeforeEach
    void setUp() {
        brasil = createCountry();
        sc = createStateSantaCatarina();
        pr = createStateParana();

        countryRepository.save(brasil);
        stateRepository.save(sc);
        stateRepository.save(pr);
    }



    @Test
    @DisplayName("Deve salvar um endereço no banco de dados")
    void testSave() {
        AddressEntity addressSc = createAddressSC();

        AddressEntity addressSaved = assertDoesNotThrow(() -> addressRepository.save(addressSc));

        assertNotNull(addressSaved);
        assertEquals("Rua 1", addressSaved.getAddressDescription());
        assertEquals(123, addressSaved.getAddressNumber());
        assertEquals("Casa", addressSaved.getComplement());
        assertEquals("Centro", addressSaved.getDistrict());
        assertEquals("Joinville", addressSaved.getCity());
        assertEquals(sc.getUf(), addressSaved.getUf().getUf());
        assertEquals("SC", addressSaved.getUf().getUf());
        assertEquals("(47) 99999-9999", addressSaved.getPhoneNumber());
    }

    @Test
    @DisplayName("Deve retornar um endereço quando passado o id como parâmetro de busca")
    void testFindById() {
        AddressEntity addressSc = createAddressSC();
        AddressEntity addressSaved = assertDoesNotThrow(() -> addressRepository.save(addressSc));
        AddressEntity addressFound = addressRepository.findById(addressSaved.getId()).orElse(null);

        assertNotNull(addressFound);
        assertEquals("Rua 1", addressFound.getAddressDescription());
        assertEquals(123, addressFound.getAddressNumber());
        assertEquals("Casa", addressFound.getComplement());
        assertEquals("Centro", addressFound.getDistrict());
        assertEquals("Joinville", addressFound.getCity());
        assertEquals(sc.getUf(), addressFound.getUf().getUf());
        assertEquals("SC", addressFound.getUf().getUf());
        assertEquals("(47) 99999-9999", addressFound.getPhoneNumber());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todos os endereços salvos no banco")
    void testFindAll() {
        AddressEntity addressSc = createAddressSC();
        AddressEntity addressPr = createAddressPR();

        assertDoesNotThrow(() -> addressRepository.save(addressSc));
        assertDoesNotThrow(() -> addressRepository.save(addressPr));
        assertEquals(2, addressRepository.findAll().size());
    }

    @Test
    @DisplayName("Deve alterar um endereço salvo no banco")
    void testUpdate() {
        AddressEntity addressSc = createAddressSC();
        AddressEntity addressSaved = assertDoesNotThrow(() -> addressRepository.save(addressSc));

        addressSaved.setAddressDescription("Rua 1 Alterado");
        addressSaved.setAddressNumber(1234);
        addressSaved.setComplement("Casa Alterado");
        addressSaved.setDistrict("Centro Alterado");
        addressSaved.setCity("Joinville Alterado");
        addressSaved.setUf(pr);
        addressSaved.setPhoneNumber("(47) 99999-9999 Alterado");

        AddressEntity addressUpdated = assertDoesNotThrow(() -> addressRepository.save(addressSaved));

        assertNotNull(addressUpdated);
        assertEquals("Rua 1 Alterado", addressUpdated.getAddressDescription());
        assertEquals(1234, addressUpdated.getAddressNumber());
        assertEquals("Casa Alterado", addressUpdated.getComplement());
        assertEquals("Centro Alterado", addressUpdated.getDistrict());
        assertEquals("Joinville Alterado", addressUpdated.getCity());
        assertEquals(pr.getUf(), addressUpdated.getUf().getUf());
        assertEquals("PR", addressUpdated.getUf().getUf());
        assertEquals("(47) 99999-9999 Alterado", addressUpdated.getPhoneNumber());
    }

    @Test
    @DisplayName("Deve deletar um endereço salvo no banco")
    void testDelete() {
        AddressEntity addressSc = createAddressSC();
        AddressEntity addressSaved = assertDoesNotThrow(() -> addressRepository.save(addressSc));

        assertDoesNotThrow(() -> addressRepository.delete(addressSaved));

        assertEquals(0, addressRepository.findAll().size());
    }

    private CountryEntity createCountry() {
        return CountryEntity.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .userreg("admin")
                .dthreg(LocalDateTime.now())
                .useralt(null)
                .dthalt(null)
                .build();
    }

    private AddressEntity createAddressPR() {
        return AddressEntity.builder()
                .addressDescription("Rua 2")
                .addressNumber(456)
                .complement("Casa")
                .district("Centro")
                .city("Curitiba")
                .zipCode("12345-123")
                .uf(pr)
                .country(brasil)
                .phoneNumber("(41) 99999-9999")
                .userreg("admin")
                .dthreg(LocalDateTime.now())
                .useralt(null)
                .dthalt(null)
                .build();
    }

    private AddressEntity createAddressSC() {
        return AddressEntity.builder()
                .addressDescription("Rua 1")
                .addressNumber(123)
                .complement("Casa")
                .district("Centro")
                .zipCode("12345-123")
                .city("Joinville")
                .uf(sc)
                .country(brasil)
                .phoneNumber("(47) 99999-9999")
                .userreg("admin")
                .dthreg(LocalDateTime.now())
                .useralt(null)
                .dthalt(null)
                .build();
    }

    private StateEntity createStateParana() {
        return StateEntity.builder()
                .stateName("Paraná")
                .uf("PR")
                .country(brasil)
                .userreg("admin")
                .dthreg(LocalDateTime.now())
                .useralt(null)
                .dthalt(null)
                .build();
    }

    private StateEntity createStateSantaCatarina() {
        return StateEntity.builder()
                .stateName("Santa Catarina")
                .uf("SC")
                .country(brasil)
                .userreg("admin")
                .dthreg(LocalDateTime.now())
                .useralt(null)
                .dthalt(null)
                .build();
    }


}