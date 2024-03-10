package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.cadastro.AddressService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.cadastro.AddressClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.AddressOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressClient addressClient;

    @Override
    public AddressOutputDTO createAddress(AddressInputDTO addressInputDTO) {
        log.info("createAddress :: Registrando um endereço com os dados: {}", addressInputDTO.toString());
        try {
            return addressClient.createAddress(addressInputDTO);
        } catch (Exception e) {
            log.error("createAddress :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<AddressOutputDTO> getAllAddress() {
        log.info("getAllAddress :: Listando todos os endereços");
        List<AddressOutputDTO> addresses = addressClient.getAllAddress();
        if (addresses.isEmpty()) {
            log.error("getAllAddress :: Ocorreu um erro ao listar os endereços: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return addresses;
    }

    @Override
    public AddressOutputDTO getAddressById(String id) {
        log.info("getAddressById :: Buscando endereço pelo id: {}", id);
        AddressOutputDTO address = addressClient.getAddressById(id);
        if (Objects.isNull(address)) {
            log.error("getAddressById :: Ocorreu um erro ao buscar o endereço: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return address;
    }

    @Override
    public AddressOutputDTO updateAddress(String id, AddressInputDTO addressInputDTO) {
        log.info("updateAddress :: Atualizando endereço com os dados: {}", addressInputDTO.toString());
        try {
            return addressClient.updateAddress(id, addressInputDTO);
        } catch (Exception e) {
            log.error("updateAddress :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deleteAddress(String id) {
        log.info("deleteAddress :: Deletando endereço pelo id: {}", id);
        try {
            addressClient.deleteAddress(id);
        } catch (Exception e) {
            log.error("deleteAddress :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }

}
