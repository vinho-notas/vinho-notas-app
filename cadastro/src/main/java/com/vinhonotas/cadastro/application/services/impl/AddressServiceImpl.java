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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
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
        log.info("create :: Registrando um endereço com os dados: {}", addressInputDTO.toString());
        try {
            StateEntity state = stateRepository.findByUf(addressInputDTO.getUf().getUf());
            if (Objects.nonNull(state)) {
                addressInputDTO.setUf(stateConverter.convertToInputDTO(state));
            } else {
                log.error("create :: Ocorreu um erro: {}", MessagesConstants.STATE_NOT_FOUND_WITH_NAME + addressInputDTO.getUf().getUf());
                throw new BadRequestException(MessagesConstants.STATE_NOT_FOUND_WITH_NAME + addressInputDTO.getUf().getUf());
            }

            CountryEntity country = countryRepository.findByCountryName(addressInputDTO.getCountry().getCountryName());
            if (Objects.nonNull(country)) {
                addressInputDTO.setCountry(countryConverter.convertToInputDTO(country));
            } else {
                log.error("create :: Ocorreu um erro: {}", MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME + addressInputDTO.getCountry().getCountryName());
                throw new BadRequestException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME + addressInputDTO.getCountry().getCountryName());
            }

            AddressEntity addressEntity = addressConverter.convertToEntity(addressInputDTO);
            return addressRepository.save(addressEntity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.ERROR_WHEN_SAVING_ADDRESS, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_ADDRESS);
        }
    }

    @Override
    public List<AddressEntity> getAll() {
        log.info("getAll :: Listando todos os endereços");
        List<AddressEntity> addressList = addressRepository.findAll();
        if (addressList.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao buscar os endereços: {}", MessagesConstants.ADDRESS_NOT_FOUND);
            throw new BadRequestException(MessagesConstants.ADDRESS_NOT_FOUND);
        }
        return addressList;
    }

    @Override
    public AddressEntity getById(UUID id) {
        log.info("getById :: Buscando endereço pelo id: {}", id.toString());
        return addressRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.ADDRESS_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressEntity update(UUID id, AddressInputDTO addressInputDTO) {
        log.info("update :: Atualizando endereço com os dados: {}", addressInputDTO.toString());
        try {
            AddressEntity addressEntity = this.getById(id);
            return addressRepository.save(addressConverter.convertToEntityUpdate(addressEntity, id, addressInputDTO));
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {}", MessagesConstants.ERROR_UPDATE_ADDRESS_DATA, e);
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_ADDRESS_DATA);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando endereço com o id: {}", id.toString());
        Optional<AddressEntity> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            log.error("delete :: Ocorreu um erro ao deletar o endereço: {}", MessagesConstants.ADDRESS_NOT_FOUND);
            throw new BadRequestException(MessagesConstants.ADDRESS_NOT_FOUND);
        }
        try {
            addressRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro ao deletar o endereço: {}", MessagesConstants.ERROR_DELETE_ADDRESS_DATA, e);
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_ADDRESS_DATA);
        }
    }

}
