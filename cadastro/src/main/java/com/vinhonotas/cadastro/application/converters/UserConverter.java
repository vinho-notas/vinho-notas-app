package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import com.vinhonotas.cadastro.infrastructure.PersonRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.UserOutputDTO;
import com.vinhonotas.cadastro.utils.EnumConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final PersonConverter personConverter;
    private final PersonRepository personRepository;

    public UserEntity convertToEntity(UserInputDTO userInputDTO) {
        PersonEntity personEntity = personRepository.findById(UUID.fromString(userInputDTO.getPersonId())).orElseThrow();
        return UserEntity.builder()
                .person(personEntity)
                .enumProfile(EnumConverter.fromString(userInputDTO.getEnumProfile(), EnumProfile.class))
                .email(userInputDTO.getEmail())
                .password(userInputDTO.getPassword())
                .userreg(userInputDTO.getUserreg())
                .dthreg(LocalDateTime.now())
                .useralt(userInputDTO.getUseralt())
                .dthalt(userInputDTO.getDthalt())
                .build();
    }

    public UserEntity converteToEntityUpdate(UserEntity entity, UUID id, UserInputDTO userInputDTO) {
        PersonEntity personEntity = personRepository.findById(UUID.fromString(userInputDTO.getPersonId())).orElseThrow();
        return UserEntity.builder()
                .id(id)
                .person(entity.getPerson() != null ? entity.getPerson() : personEntity)
                .enumProfile(userInputDTO.getEnumProfile() != null ? EnumConverter.fromString(userInputDTO
                        .getEnumProfile(), EnumProfile.class) : entity.getEnumProfile())
                .email(userInputDTO.getEmail() != null ? userInputDTO.getEmail() : entity.getEmail())
                .password(userInputDTO.getPassword() != null ? userInputDTO.getPassword() : entity.getPassword())
                .dthreg(userInputDTO.getDthreg() != null ? userInputDTO.getDthreg() : entity.getDthreg())
                .useralt(userInputDTO.getUseralt() != null ? userInputDTO.getUseralt() : entity.getUseralt())
                .dthalt(LocalDateTime.now())
                .build();
    }

    public UserOutputDTO convertToOutputDTO(UserEntity userEntity) {
        return UserOutputDTO.builder()
                .id(userEntity.getId())
                .person(personConverter.convertToOutputDTO(userEntity.getPerson()))
                .enumProfile(EnumConverter.toString(userEntity.getEnumProfile()))
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    public UserOutputDTO convertToOutputDTOUpdate(UserEntity update, UUID uuid, UserOutputDTO userOutputDTO) {
        return UserOutputDTO.builder()
                .id(uuid)
                .person(personConverter.convertToOutputDTO(update.getPerson()) != null ?
                        personConverter.convertToOutputDTO(update.getPerson()) : userOutputDTO.getPerson())
                .enumProfile(update.getEnumProfile() != null ? EnumConverter.toString(update.getEnumProfile()) :
                        userOutputDTO.getEnumProfile())
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
