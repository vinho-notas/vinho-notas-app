package com.vinhonotas.cadastro.application.services;

import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public UserEntity create(UserInputDTO userInputDTO);
    public List<UserEntity> getAll();
    public UserEntity getById(UUID id);
    public UserEntity getByName(String name);
    public UserEntity update(UUID id, UserInputDTO userInputDTO);
    public void delete(UUID id);
}
