package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.UserConverter;
import com.vinhonotas.cadastro.application.services.UserService;
import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.exceptions.UserAlreadyExistsException;
import com.vinhonotas.cadastro.domain.entities.exceptions.UserNotFoundException;
import com.vinhonotas.cadastro.domain.entities.exceptions.UserProfileException;
import com.vinhonotas.cadastro.infrastructure.PersonRepository;
import com.vinhonotas.cadastro.infrastructure.UserRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.EditUserInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PersonRepository personRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserEntity create(UserInputDTO userInputDTO) {
        log.info("create :: Registrando um usuário com os dados: {}", userInputDTO.toString());
        existsUser(userInputDTO);
        verifyProfile(userInputDTO.getEnumProfile());
        try {
            UserEntity userEntity = userConverter.convertToEntity(userInputDTO);
            return userRepository.save(userEntity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.ERROR_WHEN_SAVING_USER, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_USER);
        }
    }

    private void verifyProfile(String enumProfile) {
        if (!enumProfile.equals("Enófilo")) {
            log.error("verifyProfile :: Ocorreu um erro: {}", MessagesConstants.INVALID_PROFILE);
            throw new UserProfileException(MessagesConstants.INVALID_PROFILE);
        }
    }

    private void existsUser(UserInputDTO userInputDTO) {
        log.info("existsUser :: Verificando se o usuário já existe com o dados: {}", userInputDTO.toString());
        PersonEntity person = personRepository.findById(UUID.fromString(userInputDTO.getPersonId())).orElseThrow();
        log.info("Pessoa encontrada: {}", person);
        UserEntity user = userRepository.findByPersonDocument(person.getDocument());

        if (Objects.nonNull(user)) {
            log.error("existsUser :: Já existe um usuário com os dados: {}", user);
            throw new UserAlreadyExistsException(MessagesConstants.USER_ALREADY_EXISTS);
        }
    }

    @Override
    public List<UserEntity> getAll() {
        log.info("getAll :: Listando todos os usuários");
        List<UserEntity> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao buscar os usuários: {}", MessagesConstants.USERS_NOT_FOUND);
            throw new UserNotFoundException(MessagesConstants.USERS_NOT_FOUND);
        }
        return userList;
    }

    @Override
    public UserEntity getById(UUID id) {
        log.info("getById :: Buscando usuário pelo id: {}", id.toString());
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(MessagesConstants.USER_NOT_FOUND));
    }

    @Override
    public UserEntity getByName(String name) {
        log.info("getByName :: Buscando usuário pelo nome: {}", name);
        UserEntity user = userRepository.findByPersonName(name);
        if (Objects.isNull(user)) {
            log.error("getByName :: Ocorreu um erro ao buscar o usuário: {}", MessagesConstants.USER_NOT_FOUND_WITH_NAME + name);
            throw new UserNotFoundException(MessagesConstants.USER_NOT_FOUND_WITH_NAME + name);
        }
            return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserEntity update(UUID id, EditUserInputDTO editUserInputDTO) {
        log.info("update :: Atualizando usuário com os dados: {}", editUserInputDTO.toString());
        try {
            UserEntity userEntity = this.getById(id);
            log.info("Usuário encontrado: {}", userEntity.toString());

            return userRepository.save(userConverter.converteToEntityUpdate(userEntity, id, editUserInputDTO));
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {}", MessagesConstants.ERROR_UPDATE_USER_DATA, e);
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_USER_DATA);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando usuário com o id: {}", id.toString());
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.error("delete :: Ocorreu um erro ao deletar o usuário: {}", MessagesConstants.USER_NOT_FOUND);
            throw new UserNotFoundException(MessagesConstants.USER_NOT_FOUND);
        }
        try {
            log.info("Deletando usuário com os seguintes dados: {}", user.toString());
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro: {}", MessagesConstants.ERROR_DELETE_USER_DATA, e);
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_USER_DATA);
        }
    }

    @Override
    public UserEntity getByPersonId(UUID id) {
        log.info("getByPersonId :: Buscando usuário pelo id da pessoa: {}", id.toString());
        return userRepository.findByPersonId(id);
    }

}
