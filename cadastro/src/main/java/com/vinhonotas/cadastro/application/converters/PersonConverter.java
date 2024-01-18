package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PersonConverter {

    public PersonEntity toEntity(PersonInputDTO personInputDTO) {
        return PersonEntity.builder()
                .name(personInputDTO.getName())
                .document(personInputDTO.getDocument())
                .birthDate(personInputDTO.getBirthDate())
                .address(personInputDTO.getAddress())
                .build();
    }

    public PersonEntity toEntityUpdate(PersonEntity entity, UUID id, PersonInputDTO personInputDTO) {
        return PersonEntity.builder()
                .id(id)
                .name(personInputDTO.getName() != null ? personInputDTO.getName() : entity.getName())
                .document(personInputDTO.getDocument() != null ? personInputDTO.getDocument() : entity.getDocument())
                .birthDate(personInputDTO.getBirthDate() != null ? personInputDTO.getBirthDate() : entity.getBirthDate())
                .address(personInputDTO.getAddress() != null ? personInputDTO.getAddress() : entity.getAddress())
                .build();
    }
}
