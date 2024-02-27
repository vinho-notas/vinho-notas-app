package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.PersonOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonConverter {

    private final AddressConverter addressConverter;

    public PersonEntity toEntity(PersonInputDTO personInputDTO) {
        return PersonEntity.builder()
                .name(personInputDTO.getName())
                .document(personInputDTO.getDocument())
                .birthDate(personInputDTO.getBirthDate())
                .address(addressConverter.convertToEntity(personInputDTO.getAddress()))
                .build();
    }

    public PersonEntity toEntityUpdate(PersonEntity entity, UUID id, PersonInputDTO personInputDTO) {
        return PersonEntity.builder()
                .id(id)
                .name(personInputDTO.getName() != null ? personInputDTO.getName() : entity.getName())
                .document(personInputDTO.getDocument() != null ? personInputDTO.getDocument() : entity.getDocument())
                .birthDate(personInputDTO.getBirthDate() != null ? personInputDTO.getBirthDate() : entity.getBirthDate())
                .address(personInputDTO.getAddress() != null ? addressConverter.convertToEntity(personInputDTO.getAddress()) : entity.getAddress())
                .build();
    }

    public PersonOutputDTO convertToOutputDTO(PersonEntity personEntity) {
        return PersonOutputDTO.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .document(personEntity.getDocument())
                .birthDate(personEntity.getBirthDate())
                .address(personEntity.getAddress())
                .build();
    }

    public List<PersonOutputDTO> convertToOutputDTOList(List<PersonEntity> all) {
        return all.stream()
                .map(this::convertToOutputDTO)
                .toList();
    }

    public PersonOutputDTO convertToOutputDTOUpdate(PersonEntity update, UUID uuid, PersonOutputDTO personOutputDTO) {
        return PersonOutputDTO.builder()
                .id(uuid)
                .name(update.getName() != null ? update.getName() : personOutputDTO.getName())
                .document(update.getDocument() != null ? update.getDocument() : personOutputDTO.getDocument())
                .birthDate(update.getBirthDate() != null ? update.getBirthDate() : personOutputDTO.getBirthDate())
                .address(update.getAddress() != null ? update.getAddress() : personOutputDTO.getAddress())
                .build();
    }
}
