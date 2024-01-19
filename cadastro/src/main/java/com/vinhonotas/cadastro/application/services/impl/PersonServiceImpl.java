package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.PersonConverter;
import com.vinhonotas.cadastro.application.services.PersonService;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.infrastructure.PersonRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonEntity create(PersonInputDTO personInputDTO) {
        try {
            PersonEntity personEntity = personConverter.toEntity(personInputDTO);
            return personRepository.save(personEntity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_PERSON);
        }
    }

    @Override
    public List<PersonEntity> getAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonEntity getById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.PERSON_NOT_FOUND));
    }

    @Override
    public PersonEntity getByName(String name) {
        try {
            return personRepository.findByName(name);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.PERSON_NOT_FOUND_WITH_NAME + name);
        }
    }

    @Override
    public PersonEntity update(UUID id, PersonInputDTO personInputDTO) {
        try {
            PersonEntity personEntity = this.getById(id);
            personRepository.save(personConverter.toEntityUpdate(personEntity, id, personInputDTO));
            return personRepository.findByName(personEntity.getName());
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_PERSON_DATA);
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_PERSON_DATA);
        }
    }
}
