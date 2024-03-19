package com.vinhonotas.cadastro.application.services;

import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserEntity create(UserInputDTO userInputDTO);
    List<UserEntity> getAll();
    UserEntity getById(UUID id);
    UserEntity getByName(String name);
    UserEntity update(UUID id, UserInputDTO userInputDTO);
    void delete(UUID id);
    UserEntity getByPersonId(UUID id);
}
