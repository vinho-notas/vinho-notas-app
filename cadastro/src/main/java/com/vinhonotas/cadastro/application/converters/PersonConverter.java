package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.PersonOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonConverter {

    private final AddressConverter addressConverter;

    public PersonEntity convertToEntity(PersonInputDTO personInputDTO) {
        return PersonEntity.builder()
                .id(personInputDTO.getId() != null ? UUID.fromString(personInputDTO.getId()): null)
                .name(personInputDTO.getName())
                .document(personInputDTO.getDocument())
                .birthDate(personInputDTO.getBirthDate())
                .address(addressConverter.convertToEntity(personInputDTO.getAddress()))
                .userreg(personInputDTO.getUserreg())
                .dthreg(LocalDateTime.now())
                .useralt(personInputDTO.getUseralt())
                .dthalt(personInputDTO.getDthalt())
                .build();
    }

    public PersonEntity convertToEntityUpdate(PersonEntity entity, UUID id, PersonInputDTO personInputDTO) {
        return PersonEntity.builder()
                .id(id)
                .name(personInputDTO.getName() != null ? personInputDTO.getName() : entity.getName())
                .document(personInputDTO.getDocument() != null ? personInputDTO.getDocument() : entity.getDocument())
                .birthDate(personInputDTO.getBirthDate() != null ? personInputDTO.getBirthDate() : entity.getBirthDate())
                .address(personInputDTO.getAddress() != null ? addressConverter.convertToEntity(personInputDTO
                        .getAddress()) : entity.getAddress())
                .userreg(personInputDTO.getUserreg() != null ? personInputDTO.getUserreg() : entity.getUserreg())
                .dthreg(personInputDTO.getDthreg() != null ? personInputDTO.getDthreg() : entity.getDthreg())
                .useralt(personInputDTO.getUseralt() != null ? personInputDTO.getUseralt() : entity.getUseralt())
                .dthalt(LocalDateTime.now())
                .build();
    }

    public PersonOutputDTO convertToOutputDTO(PersonEntity personEntity) {
        return PersonOutputDTO.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .document(personEntity.getDocument())
                .birthDate(personEntity.getBirthDate())
                .address(addressConverter.convertToOutputDTO(personEntity.getAddress()))
                .build();
    }

    public List<PersonOutputDTO> convertToOutputDTOList(List<PersonEntity> all) {
        return all.stream()
                .map(this::convertToOutputDTO)
                .toList();
    }

    public PersonInputDTO convertToInputDTO(PersonEntity person) {
        return PersonInputDTO.builder()
                .id(person.getId().toString())
                .name(person.getName())
                .document(person.getDocument())
                .birthDate(person.getBirthDate())
                .address(addressConverter.convertToInputDTO(person.getAddress()))
                .userreg(person.getUserreg())
                .dthreg(person.getDthreg())
                .useralt(person.getUseralt())
                .dthalt(person.getDthalt())
                .build();
    }
}
