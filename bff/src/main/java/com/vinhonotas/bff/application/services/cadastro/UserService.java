package com.vinhonotas.bff.application.services.cadastro;

import com.vinhonotas.bff.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.UserOutputDTO;

import java.util.List;

public interface UserService {

    UserOutputDTO createUser(UserInputDTO userInputDTO);
    List<UserOutputDTO> getAllUser();
    UserOutputDTO getUserById(String id);
    UserOutputDTO getUserByName(String name);
    UserOutputDTO updateUser(String id, UserInputDTO userInputDTO);
    void deleteUser(String id);

}
