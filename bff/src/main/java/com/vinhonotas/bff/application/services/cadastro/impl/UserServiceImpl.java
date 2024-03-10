package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.cadastro.UserService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.cadastro.UserClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.UserInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.UserOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserClient userClient;

    @Override
    public UserOutputDTO createUser(UserInputDTO userInputDTO) {
        log.info("createUser :: Registrando um usuário com os dados: {}", userInputDTO.toString());
        try {
            return userClient.createUser(userInputDTO);
        } catch (Exception e) {
            log.error("createUser :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<UserOutputDTO> getAllUser() {
        log.info("getAllUser :: Listando todos os usuários");
        List<UserOutputDTO> users = userClient.getAllUser();
        if (users.isEmpty()) {
            log.error("getAllUser :: Ocorreu um erro ao listar os usuários: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return users;
    }

    @Override
    public UserOutputDTO getUserById(String id) {
        log.info("getUserById :: Buscando usuário pelo id: {}", id);
        UserOutputDTO user = userClient.getUserById(id);
        if (Objects.isNull(user)) {
            log.error("getUserById :: Ocorreu um erro ao buscar o usuário: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return user;
    }

    @Override
    public UserOutputDTO getUserByName(String name) {
        log.info("getUserByName :: Buscando usuário pelo nome: {}", name);
        UserOutputDTO user = userClient.getUserByName(name);
        if (Objects.isNull(user)) {
            log.error("getUserByName :: Ocorreu um erro ao buscar o usuário: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return user;
    }

    @Override
    public UserOutputDTO updateUser(String id, UserInputDTO userInputDTO) {
        log.info("updateUser :: Atualizando usuário com os dados: {}", userInputDTO.toString());
        try {
            return userClient.updateUser(id, userInputDTO);
        } catch (Exception e) {
            log.error("updateUser :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deleteUser(String id) {
        log.info("deleteUser :: Deletando usuário pelo id: {}", id);
        try {
            userClient.deleteUser(id);
        } catch (Exception e) {
            log.error("deleteUser :: Ocorreu um erro ao deletar o usuário: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }

}
