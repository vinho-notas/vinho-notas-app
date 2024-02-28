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
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonEntity create(PersonInputDTO personInputDTO) {
        PersonEntity person = personRepository.findByDocument(personInputDTO.getDocument());
        if (Objects.nonNull(person)) {
            throw new BadRequestException(MessagesConstants.PERSON_ALREADY_EXISTS);
        }
        try {
            PersonEntity personEntity = personConverter.convertToEntity(personInputDTO);
            return personRepository.save(personEntity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_PERSON);
        }
    }

    @Override
    public List<PersonEntity> getAll() {
        List<PersonEntity> personList = personRepository.findAll();
        if (personList.isEmpty()) {
            throw new BadRequestException(MessagesConstants.PERSONS_NOT_FOUND);
        }
        return personList;
    }

    @Override
    public PersonEntity getById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.PERSON_NOT_FOUND));
    }

    @Override
    public PersonEntity getByName(String name) {
        PersonEntity person = personRepository.findByName(name);
        if (Objects.isNull(person)) {
            throw new BadRequestException(MessagesConstants.PERSON_NOT_FOUND_WITH_NAME + name);
        }
        return person;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonEntity update(UUID id, PersonInputDTO personInputDTO) {
        try {
            PersonEntity personEntity = this.getById(id);
            personRepository.save(personConverter.convertToEntityUpdate(personEntity, id, personInputDTO));
            return personRepository.findByName(personEntity.getName());
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_PERSON_DATA);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<PersonEntity> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new BadRequestException(MessagesConstants.PERSON_NOT_FOUND);
        }
        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_PERSON_DATA);
        }
    }
}
