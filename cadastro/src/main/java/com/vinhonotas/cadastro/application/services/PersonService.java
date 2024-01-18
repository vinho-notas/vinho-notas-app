package com.vinhonotas.cadastro.application.services;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    public PersonEntity create(PersonInputDTO personInputDTO);
    public List<PersonEntity> getAll();
    public PersonEntity getById(UUID id);
    public PersonEntity getByName(String name);
    public PersonEntity update(UUID id, PersonInputDTO personInputDTO);
    public void delete(UUID id);
}
