package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.UserOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public UserOutputDTO convertToOutputDTO(UserEntity userEntity) {
        return UserOutputDTO.builder()
                .id(userEntity.getId())
                .person(userEntity.getPerson())
                .enumProfile(userEntity.getEnumProfile())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    public UserOutputDTO convertToOutputDTOUpdate(UserEntity update, UUID uuid, UserOutputDTO userOutputDTO) {
        return UserOutputDTO.builder()
                .id(uuid)
                .person(update.getPerson() != null ? update.getPerson() : userOutputDTO.getPerson())
                .enumProfile(update.getEnumProfile() != null ? update.getEnumProfile() : userOutputDTO.getEnumProfile())
                .email(update.getEmail() != null ? update.getEmail() : userOutputDTO.getEmail())
                .password(update.getPassword() != null ? update.getPassword() : userOutputDTO.getPassword())
                .build();
    }

    public List<UserOutputDTO> convertToOutputDTOList(List<UserEntity> all) {
        return all.stream()
                .map(this::convertToOutputDTO)
                .toList();
    }
}