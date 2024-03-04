package com.vinhonotas.bff.application.services.cadastro;

import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.UserInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.UserOutputDTO;

import java.util.List;

public interface UserService {

    UserOutputDTO createUser(UserInputDTO userInputDTO);
    List<UserOutputDTO> getAllUser();
    UserOutputDTO getUserById(String id);
    UserOutputDTO getUserByName(String name);
    UserOutputDTO updateUser(String id, UserInputDTO userInputDTO);
    void deleteUser(String id);

}
