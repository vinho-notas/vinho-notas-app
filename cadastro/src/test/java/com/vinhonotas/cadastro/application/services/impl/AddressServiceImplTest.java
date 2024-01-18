package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.AddressConverter;
import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.infrastructure.AddressRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Log4j2
class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private AddressConverter addressConverter;

    private AddressInputDTO addressInputDTO;
    private CountryEntity brasilEntity;
    private AddressEntity addressEntity;
    @BeforeEach
    void setUp() {
        log.info("Iniciando teste");
        brasilEntity = createBrasilEntity();
        addressInputDTO = createAddressInputDTO();
        addressEntity = createAddressEntity();
    }

    @Test
    @DisplayName("Teste de criação de endereço")
    void testCreateSucesso() {
        when(addressConverter.toEntity(addressInputDTO)).thenReturn(addressEntity);
        when(addressRepository.save(addressEntity)).thenReturn(addressEntity);
        AddressEntity address = assertDoesNotThrow(() -> addressServiceImpl.create(addressInputDTO));

        assertNotNull(address);
        assertEquals(addressEntity.getId(), address.getId());
        assertEquals(addressEntity.getAddressDescription(), address.getAddressDescription());
        assertEquals(addressEntity.getAddressNumber(), address.getAddressNumber());
        assertEquals(addressEntity.getComplement(), address.getComplement());
        assertEquals(addressEntity.getDistrict(), address.getDistrict());
        assertEquals(addressEntity.getZipCode(), address.getZipCode());
        assertEquals(addressEntity.getCity(), address.getCity());
        assertEquals(addressEntity.getUf(), address.getUf());
        assertEquals(addressEntity.getCountry(), address.getCountry());
        assertEquals(addressEntity.getPhoneNumber(), address.getPhoneNumber());
        verify(addressConverter, times(1)).toEntity(addressInputDTO);
        verify(addressRepository, times(1)).save(addressEntity);
    }

    @Test
    @DisplayName("Teste de criação de endereço com erro")
    void testCreateErro() {
        when(addressConverter.toEntity(addressInputDTO)).thenReturn(addressEntity);
        when(addressRepository.save(addressEntity)).thenThrow(new IllegalArgumentException());

        Exception exception = assertThrows(Exception.class, () -> addressServiceImpl.create(addressInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING_ADDRESS, exception.getMessage());
        verify(addressConverter, times(1)).toEntity(addressInputDTO);
        verify(addressRepository, times(1)).save(addressEntity);
    }

    @Test
    @DisplayName("Teste de busca de todos os endereços")
    void testGetAll() {
        when(addressRepository.findAll()).thenReturn(List.of(addressEntity));
        List<AddressEntity> addresses = assertDoesNotThrow(() -> addressServiceImpl.getAll());

        assertNotNull(addresses);
        assertFalse(addresses.isEmpty());
        assertEquals(1, addresses.size());
        assertEquals(addressEntity.getId(), addresses.get(0).getId());
        assertEquals(addressEntity.getAddressDescription(), addresses.get(0).getAddressDescription());
        assertEquals(addressEntity.getAddressNumber(), addresses.get(0).getAddressNumber());
        assertEquals(addressEntity.getComplement(), addresses.get(0).getComplement());
        assertEquals(addressEntity.getDistrict(), addresses.get(0).getDistrict());
        assertEquals(addressEntity.getZipCode(), addresses.get(0).getZipCode());
        assertEquals(addressEntity.getCity(), addresses.get(0).getCity());
        assertEquals(addressEntity.getUf(), addresses.get(0).getUf());
        assertEquals(addressEntity.getCountry(), addresses.get(0).getCountry());
        assertEquals(addressEntity.getPhoneNumber(), addresses.get(0).getPhoneNumber());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Teste de busca de endereço por id")
    void testGetByIdSucesso() {
        when(addressRepository.findById(addressEntity.getId())).thenReturn(Optional.of(addressEntity));
        AddressEntity address = assertDoesNotThrow(() -> addressServiceImpl.getById(addressEntity.getId()));

        assertNotNull(address);
        assertEquals(addressEntity.getId(), address.getId());
        assertEquals(addressEntity.getAddressDescription(), address.getAddressDescription());
        assertEquals(addressEntity.getAddressNumber(), address.getAddressNumber());
        assertEquals(addressEntity.getComplement(), address.getComplement());
        assertEquals(addressEntity.getDistrict(), address.getDistrict());
        assertEquals(addressEntity.getZipCode(), address.getZipCode());
        assertEquals(addressEntity.getCity(), address.getCity());
        assertEquals(addressEntity.getUf(), address.getUf());
        assertEquals(addressEntity.getCountry(), address.getCountry());
        assertEquals(addressEntity.getPhoneNumber(), address.getPhoneNumber());
        verify(addressRepository, times(1)).findById(addressEntity.getId());
    }

    @Test
    @DisplayName("Teste de busca de endereço por id com erro")
    void testGetByIdErro() {
        when(addressRepository.findById(addressEntity.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> addressServiceImpl.getById(addressEntity.getId()));
        assertEquals(MessagesConstants.ADDRESS_NOT_FOUND, exception.getMessage());
        verify(addressRepository, times(1)).findById(addressEntity.getId());
    }

    @Test
    @DisplayName("Teste de atualização de endereço")
    void testUpdateSucesso() {
        when(addressRepository.findById(addressEntity.getId())).thenReturn(Optional.of(addressEntity));
        when(addressConverter.toEntityUpdate(addressEntity, addressEntity.getId(), addressInputDTO)).thenReturn(addressEntity);
        when(addressRepository.save(addressEntity)).thenReturn(addressEntity);
        AddressEntity address = assertDoesNotThrow(() -> addressServiceImpl.update(addressEntity.getId(), addressInputDTO));

        assertNotNull(address);
        assertEquals(addressEntity.getId(), address.getId());
        assertEquals(addressEntity.getAddressDescription(), address.getAddressDescription());
        assertEquals(addressEntity.getAddressNumber(), address.getAddressNumber());
        assertEquals(addressEntity.getComplement(), address.getComplement());
        assertEquals(addressEntity.getDistrict(), address.getDistrict());
        assertEquals(addressEntity.getZipCode(), address.getZipCode());
        assertEquals(addressEntity.getCity(), address.getCity());
        assertEquals(addressEntity.getUf(), address.getUf());
        assertEquals(addressEntity.getCountry(), address.getCountry());
        assertEquals(addressEntity.getPhoneNumber(), address.getPhoneNumber());
        verify(addressRepository, times(1)).findById(addressEntity.getId());
        verify(addressConverter, times(1)).toEntityUpdate(addressEntity, addressEntity.getId(), addressInputDTO);
        verify(addressRepository, times(1)).save(addressEntity);
    }

    @Test
    @DisplayName("Teste de atualização de endereço com erro")
    void testUpdateErro() {
        when(addressRepository.findById(addressEntity.getId())).thenReturn(Optional.of(addressEntity));
        when(addressConverter.toEntityUpdate(addressEntity, addressEntity.getId(), addressInputDTO)).thenReturn(addressEntity);
        when(addressRepository.save(addressEntity)).thenThrow(new IllegalArgumentException());

        Exception exception = assertThrows(Exception.class, () -> addressServiceImpl.update(addressEntity.getId(), addressInputDTO));
        assertEquals(MessagesConstants.ERROR_UPDATE_ADDRESS_DATA, exception.getMessage());
        verify(addressRepository, times(1)).findById(addressEntity.getId());
        verify(addressConverter, times(1)).toEntityUpdate(addressEntity, addressEntity.getId(), addressInputDTO);
        verify(addressRepository, times(1)).save(addressEntity);
    }

    @Test
    @DisplayName("Teste deve deletar endereço")
    void testDeleteSucesso() {
        assertDoesNotThrow(() -> addressServiceImpl.delete(UUID.fromString("d0d7f9e0-0b7e-4b1e-8b7a-9b0b9b9b9b9b")));
        verify(addressRepository, times(1)).deleteById(UUID.fromString("d0d7f9e0-0b7e-4b1e-8b7a-9b0b9b9b9b9b"));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao deletar um endereço")
    void testDeleteErro() {
        doThrow(new IllegalArgumentException()).when(addressRepository).deleteById(UUID.fromString("d0d7f9e0-0b7e-4b1e-8b7a-9b0b9b9b9b9b"));

        Exception exception = assertThrows(Exception.class, () -> addressServiceImpl.delete(UUID.fromString("d0d7f9e0-0b7e-4b1e-8b7a-9b0b9b9b9b9b")));
        assertEquals(MessagesConstants.ERROR_DELETE_ADDRESS_DATA, exception.getMessage());
        verify(addressRepository, times(1)).deleteById(UUID.fromString("d0d7f9e0-0b7e-4b1e-8b7a-9b0b9b9b9b9b"));
    }


    private AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .id(UUID.fromString("d0d7f9e0-0b7e-4b1e-8b7a-9b0b9b9b9b9b"))
                .addressDescription("Rua dos Bobos")
                .addressNumber(0)
                .complement("Casa")
                .district("Centro")
                .zipCode("00000-000")
                .city("São Paulo")
                .uf(createSaoPauloEntity())
                .country(brasilEntity)
                .phoneNumber("11999999999")
                .build();
    }

    private AddressInputDTO createAddressInputDTO() {
        return AddressInputDTO.builder()
                .addressDescription("Rua dos Bobos")
                .addressNumber(0)
                .complement("Casa")
                .district("Centro")
                .zipCode("00000-000")
                .city("São Paulo")
                .uf(createSaoPauloEntity())
                .country(brasilEntity)
                .phoneNumber("11999999999")
                .build();
    }

    private StateEntity createSaoPauloEntity() {
        return StateEntity.builder()
                .stateName("São Paulo")
                .uf("SP")
                .country(brasilEntity)
                .build();
    }

    private CountryEntity createBrasilEntity() {
        return CountryEntity.builder()
                .countryName("Brasil")
                .continentName("América do Sul")
                .build();
    }

    @AfterEach
    void tearDownEach() {
        log.info("Finalizando teste");
    }

}