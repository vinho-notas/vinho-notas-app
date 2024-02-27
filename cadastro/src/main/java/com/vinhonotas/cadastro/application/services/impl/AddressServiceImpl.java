package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.AddressConverter;
import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.converters.StateConverter;
import com.vinhonotas.cadastro.application.services.AddressService;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.infrastructure.AddressRepository;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.infrastructure.StateRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressConverter addressConverter;
    private final StateConverter stateConverter;
    private final CountryConverter countryConverter;
    private final AddressRepository addressRepository;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressEntity create(AddressInputDTO addressInputDTO) {
        try {
            StateEntity state = stateRepository.findByUf(addressInputDTO.getUf().getUf());
            if (Objects.nonNull(state)) {
                addressInputDTO.setUf(stateConverter.convertToInputDTO(state));
            } else {
                throw new BadRequestException(MessagesConstants.STATE_NOT_FOUND_WITH_NAME + addressInputDTO.getUf().getUf());
            }

            CountryEntity country = countryRepository.findByCountryName(addressInputDTO.getCountry().getCountryName());
            if (Objects.nonNull(country)) {
                addressInputDTO.setCountry(countryConverter.convertToInputDTO(country));
            } else {
                throw new BadRequestException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME + addressInputDTO.getCountry().getCountryName());
            }

            AddressEntity addressEntity = addressConverter.convertToEntity(addressInputDTO);
            return addressRepository.save(addressEntity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_ADDRESS);
        }
    }

    @Override
    public List<AddressEntity> getAll() {
        List<AddressEntity> addressList = addressRepository.findAll();
        if (addressList.isEmpty()) {
            throw new BadRequestException(MessagesConstants.ADDRESS_NOT_FOUND);
        }
        return addressList;
    }

    @Override
    public AddressEntity getById(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.ADDRESS_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressEntity update(UUID id, AddressInputDTO addressInputDTO) {
        try {
            AddressEntity addressEntity = this.getById(id);
            return addressRepository.save(addressConverter.convertToEntityUpdate(addressEntity, id, addressInputDTO));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_ADDRESS_DATA);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<AddressEntity> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            throw new BadRequestException(MessagesConstants.ADDRESS_NOT_FOUND);
        }
        try {
            addressRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_ADDRESS_DATA);
        }
    }
}
