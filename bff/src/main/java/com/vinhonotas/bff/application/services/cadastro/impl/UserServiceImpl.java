package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.cadastro.UserService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.cadastro.UserClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.UserOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;

    @Override
    public UserOutputDTO createUser(UserInputDTO userInputDTO) {
        try {
            return userClient.createUser(userInputDTO);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_USER);
        }
    }

    @Override
    public List<UserOutputDTO> getAllUser() {
        List<UserOutputDTO> users = userClient.getAllUser();
        if (users.isEmpty()) {
            throw new BadRequestException(MessagesConstants.USERS_NOT_FOUND);
        }
        return users;
    }

    @Override
    public UserOutputDTO getUserById(String id) {
        UserOutputDTO user = userClient.getUserById(id);
        if (Objects.isNull(user)) {
            throw new BadRequestException(MessagesConstants.USERS_NOT_FOUND);
        }
        return user;
    }

    @Override
    public UserOutputDTO getUserByName(String name) {
        UserOutputDTO user = userClient.getUserByName(name);
        if (Objects.isNull(user)) {
            throw new BadRequestException(MessagesConstants.USERS_NOT_FOUND);
        }
        return user;
    }

    @Override
    public UserOutputDTO updateUser(String id, UserInputDTO userInputDTO) {
        try {
            return userClient.updateUser(id, userInputDTO);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_USER_DATA);
        }
    }

    @Override
    public void deleteUser(String id) {
        try {
            userClient.deleteUser(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_USER);
        }

    }
}
