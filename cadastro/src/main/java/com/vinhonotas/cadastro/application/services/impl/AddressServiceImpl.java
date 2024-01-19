package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.AddressConverter;
import com.vinhonotas.cadastro.application.services.AddressService;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.infrastructure.AddressRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressEntity create(AddressInputDTO addressInputDTO) {
        try {
            AddressEntity addressEntity = addressConverter.toEntity(addressInputDTO);
            return addressRepository.save(addressEntity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_ADDRESS);
        }
    }

    @Override
    public List<AddressEntity> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public AddressEntity getById(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.ADDRESS_NOT_FOUND));
    }

    @Override
    public AddressEntity update(UUID id, AddressInputDTO addressInputDTO) {
        try {
            AddressEntity addressEntity = this.getById(id);
            return addressRepository.save(addressConverter.toEntityUpdate(addressEntity, id, addressInputDTO));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_ADDRESS_DATA);
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            addressRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_ADDRESS_DATA);
        }
    }
}
