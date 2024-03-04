package com.vinhonotas.bff.application.services.cadastro;

import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.AddressOutputDTO;

import java.util.List;

public interface AddressService {

    AddressOutputDTO createAddress(AddressInputDTO addressInputDTO);
    List<AddressOutputDTO> getAllAddress();
    AddressOutputDTO getAddressById(String id);
    AddressOutputDTO updateAddress(String id, AddressInputDTO addressInputDTO);
    void deleteAddress(String id);

}
