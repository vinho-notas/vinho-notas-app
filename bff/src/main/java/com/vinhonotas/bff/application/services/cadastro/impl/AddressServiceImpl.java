package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.cadastro.AddressService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.cadastro.AddressClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.AddressOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressClient addressClient;

    @Override
    public AddressOutputDTO createAddress(AddressInputDTO addressInputDTO) {
        try {
            return addressClient.createAddress(addressInputDTO);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<AddressOutputDTO> getAllAddress() {
        List<AddressOutputDTO> addresses = addressClient.getAllAddress();
        if (addresses.isEmpty()) {
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return addresses;
    }

    @Override
    public AddressOutputDTO getAddressById(String id) {
        AddressOutputDTO address = addressClient.getAddressById(id);
        if (Objects.isNull(address)) {
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return address;
    }

    @Override
    public AddressOutputDTO updateAddress(String id, AddressInputDTO addressInputDTO) {
        try {
            return addressClient.updateAddress(id, addressInputDTO);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deleteAddress(String id) {
        try {
            addressClient.deleteAddress(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }

}
