package com.vinhonotas.cadastro.application.services;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    AddressEntity create(AddressInputDTO addressInputDTO);
    List<AddressEntity> getAll();
    AddressEntity getById(UUID id);
    AddressEntity update(UUID id, AddressInputDTO addressInputDTO);
    void delete(UUID id);

}
