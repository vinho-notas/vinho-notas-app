package com.vinhonotas.cadastro.application.services;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    public AddressEntity create(AddressInputDTO addressInputDTO);
    public List<AddressEntity> getAll();
    public AddressEntity getById(UUID id);
    public AddressEntity update(UUID id, AddressInputDTO addressInputDTO);
    public void delete(UUID id);

}
