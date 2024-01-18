package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConverter {

    public UserEntity toEntity(UserInputDTO userInputDTO) {
        return UserEntity.builder()
                .person(userInputDTO.getPerson())
                .enumProfile(userInputDTO.getEnumProfile())
                .email(userInputDTO.getEmail())
                .password(userInputDTO.getPassword())
                .build();
    }

    public UserEntity toEntityUpdate(UserEntity entity, UUID id, UserInputDTO userInputDTO) {
        return UserEntity.builder()
                .id(id)
                .person(userInputDTO.getPerson() != null ? userInputDTO.getPerson() : entity.getPerson())
                .enumProfile(userInputDTO.getEnumProfile() != null ? userInputDTO.getEnumProfile() : entity.getEnumProfile())
                .email(userInputDTO.getEmail() != null ? userInputDTO.getEmail() : entity.getEmail())
                .password(userInputDTO.getPassword() != null ? userInputDTO.getPassword() : entity.getPassword())
                .build();
    }
}
