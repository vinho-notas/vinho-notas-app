package com.vinhonotas.cadastro.application.services;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.EditPersonInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonEntity create(PersonInputDTO personInputDTO);
    List<PersonEntity> getAll();
    PersonEntity getById(UUID id);
    PersonEntity getByName(String name);
    PersonEntity update(UUID id, EditPersonInputDTO editPersonInputDTO);
    void delete(UUID id);
}
