package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.UserConverter;
import com.vinhonotas.cadastro.application.services.UserService;
import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.infrastructure.UserRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserEntity create(UserInputDTO userInputDTO) {
        try {
            UserEntity userEntity = userConverter.toEntity(userInputDTO);
            return userRepository.save(userEntity);
        } catch (Exception e) {
            throw new IllegalArgumentException(MessagesConstants.ERROR_WHEN_SAVING_USER);
        }
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MessagesConstants.USER_NOT_FOUND));
    }

    @Override
    public UserEntity getByName(String name) {
        try {
            return userRepository.findByPersonName(name);
        } catch (Exception e) {
            throw new IllegalArgumentException(MessagesConstants.USER_NOT_FOUND_WITH_NAME + name);
        }
    }

    @Override
    public UserEntity update(UUID id, UserInputDTO userInputDTO) {
        try {
            UserEntity userEntity = this.getById(id);
            userRepository.save(userConverter.toEntityUpdate(userEntity, id, userInputDTO));
            return userRepository.findByPersonName(userEntity.getPerson().getName());
        } catch (Exception e) {
            throw new IllegalArgumentException(MessagesConstants.ERROR_UPDATE_USER_DATA);
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(MessagesConstants.ERROR_DELETE_USER_DATA);
        }
    }
}
