package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddressConverter {

    public AddressEntity toEntity(AddressInputDTO addressInputDTO) {
        return AddressEntity.builder()
                .addressDescription(addressInputDTO.getAddressDescription())
                .addressNumber(addressInputDTO.getAddressNumber())
                .complement(addressInputDTO.getComplement())
                .district(addressInputDTO.getDistrict())
                .zipCode(addressInputDTO.getZipCode())
                .city(addressInputDTO.getCity())
                .uf(addressInputDTO.getUf())
                .country(addressInputDTO.getCountry())
                .phoneNumber(addressInputDTO.getPhoneNumber())
                .build();
    }

    public AddressEntity toEntityUpdate(AddressEntity entity, UUID id, AddressInputDTO addressInputDTO) {
        return AddressEntity.builder()
                .id(id)
                .addressDescription(addressInputDTO.getAddressDescription() != null ? addressInputDTO.getAddressDescription() : entity.getAddressDescription())
                .addressNumber(addressInputDTO.getAddressNumber() != 0 ? addressInputDTO.getAddressNumber() : entity.getAddressNumber())
                .complement(addressInputDTO.getComplement() != null ? addressInputDTO.getComplement() : entity.getComplement())
                .district(addressInputDTO.getDistrict() != null ? addressInputDTO.getDistrict() : entity.getDistrict())
                .zipCode(addressInputDTO.getZipCode() != null ? addressInputDTO.getZipCode() : entity.getZipCode())
                .city(addressInputDTO.getCity() != null ? addressInputDTO.getCity() : entity.getCity())
                .uf(addressInputDTO.getUf() != null ? addressInputDTO.getUf() : entity.getUf())
                .country(addressInputDTO.getCountry() != null ? addressInputDTO.getCountry() : entity.getCountry())
                .phoneNumber(addressInputDTO.getPhoneNumber() != null ? addressInputDTO.getPhoneNumber() : entity.getPhoneNumber())
                .build();
    }
}
