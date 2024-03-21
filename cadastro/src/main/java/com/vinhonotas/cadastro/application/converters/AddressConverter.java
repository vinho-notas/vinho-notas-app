package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.infrastructure.StateRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.AddressOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddressConverter {

    private final StateConverter stateConverter;
    private final CountryConverter countryConverter;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public AddressEntity convertToEntity(AddressInputDTO addressInputDTO) {
        StateEntity state = stateRepository.findByUf(addressInputDTO.getUf());
        CountryEntity country = countryRepository.findByCountryName(addressInputDTO.getCountry());
        return AddressEntity.builder()
                .addressDescription(addressInputDTO.getAddressDescription())
                .addressNumber(addressInputDTO.getAddressNumber())
                .complement(addressInputDTO.getComplement())
                .district(addressInputDTO.getDistrict())
                .zipCode(addressInputDTO.getZipCode())
                .city(addressInputDTO.getCity())
                .uf(state)
                .country(country)
                .phoneNumber(addressInputDTO.getPhoneNumber())
                .userreg(addressInputDTO.getUserreg())
                .dthreg(LocalDateTime.now())
                .useralt(addressInputDTO.getUseralt())
                .dthalt(addressInputDTO.getDthalt())
                .build();
    }

    public AddressEntity convertToEntityUpdate(AddressEntity entity, UUID id, AddressInputDTO addressInputDTO) {
        return AddressEntity.builder()
                .id(id)
                .addressDescription(addressInputDTO.getAddressDescription() != null ? addressInputDTO
                        .getAddressDescription() : entity.getAddressDescription())
                .addressNumber(addressInputDTO.getAddressNumber() != 0 ? addressInputDTO.getAddressNumber() : entity
                        .getAddressNumber())
                .complement(addressInputDTO.getComplement() != null ? addressInputDTO.getComplement() : entity
                        .getComplement())
                .district(addressInputDTO.getDistrict() != null ? addressInputDTO.getDistrict() : entity.getDistrict())
                .zipCode(addressInputDTO.getZipCode() != null ? addressInputDTO.getZipCode() : entity.getZipCode())
                .city(addressInputDTO.getCity() != null ? addressInputDTO.getCity() : entity.getCity())
                .uf(entity.getUf())
                .country(entity.getCountry())
                .phoneNumber(addressInputDTO.getPhoneNumber() != null ? addressInputDTO.getPhoneNumber() : entity
                        .getPhoneNumber())
                .userreg(addressInputDTO.getUserreg() != null ? addressInputDTO.getUserreg() : entity.getUserreg())
                .dthreg(addressInputDTO.getDthreg() != null ? addressInputDTO.getDthreg() : entity.getDthreg())
                .useralt(addressInputDTO.getUseralt() != null ? addressInputDTO.getUseralt() : entity.getUseralt())
                .dthalt(LocalDateTime.now())
                .build();
    }

    public AddressOutputDTO convertToOutputDTO(AddressEntity addressEntity) {
        return AddressOutputDTO.builder()
                .id(addressEntity.getId())
                .addressDescription(addressEntity.getAddressDescription())
                .addressNumber(addressEntity.getAddressNumber())
                .complement(addressEntity.getComplement())
                .district(addressEntity.getDistrict())
                .zipCode(addressEntity.getZipCode())
                .city(addressEntity.getCity())
                .uf(stateConverter.convertToOutputDTO(addressEntity.getUf()))
                .country(countryConverter.convertToOutputDTO(addressEntity.getCountry()))
                .phoneNumber(addressEntity.getPhoneNumber())
                .build();
    }

    public List<AddressOutputDTO> convertToOutputDTOList(List<AddressEntity> list) {
        return list.stream()
                .map(this::convertToOutputDTO)
                .toList();
    }

    public AddressOutputDTO convertToOutputDTOUpdate(AddressEntity addressEntity, UUID id, AddressOutputDTO addressOutputDTO) {
        return AddressOutputDTO.builder()
                .id(id)
                .addressDescription(addressOutputDTO.getAddressDescription() != null ? addressOutputDTO.getAddressDescription() : addressEntity.getAddressDescription())
                .addressNumber(addressOutputDTO.getAddressNumber() != 0 ? addressOutputDTO.getAddressNumber() : addressEntity.getAddressNumber())
                .complement(addressOutputDTO.getComplement() != null ? addressOutputDTO.getComplement() : addressEntity.getComplement())
                .district(addressOutputDTO.getDistrict() != null ? addressOutputDTO.getDistrict() : addressEntity.getDistrict())
                .zipCode(addressOutputDTO.getZipCode() != null ? addressOutputDTO.getZipCode() : addressEntity.getZipCode())
                .city(addressOutputDTO.getCity() != null ? addressOutputDTO.getCity() : addressEntity.getCity())
                .uf(addressOutputDTO.getUf() != null ? addressOutputDTO.getUf() : stateConverter.convertToOutputDTO(addressEntity.getUf()))
                .country(addressOutputDTO.getCountry() != null ? addressOutputDTO.getCountry() : countryConverter.convertToOutputDTO(addressEntity.getCountry()))
                .phoneNumber(addressOutputDTO.getPhoneNumber() != null ? addressOutputDTO.getPhoneNumber() : addressEntity.getPhoneNumber())
                .build();
    }

    public AddressInputDTO convertToInputDTO(AddressEntity address) {
        return AddressInputDTO.builder()
                .addressDescription(address.getAddressDescription())
                .addressNumber(address.getAddressNumber())
                .complement(address.getComplement())
                .district(address.getDistrict())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .uf(address.getUf().getUf())
                .country(address.getCountry().getCountryName())
                .phoneNumber(address.getPhoneNumber())
                .userreg(address.getUserreg())
                .dthreg(address.getDthreg())
                .useralt(address.getUseralt())
                .dthalt(address.getDthalt())
                .build();
    }
}
