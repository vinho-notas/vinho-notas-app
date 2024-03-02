package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.PersonConverter;
import com.vinhonotas.cadastro.application.services.PersonService;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.infrastructure.PersonRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
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
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonEntity create(PersonInputDTO personInputDTO) {
        log.info("create :: Registrando uma pessoa com os dados: {}", personInputDTO.toString());
        PersonEntity person = personRepository.findByDocument(personInputDTO.getDocument());
        if (Objects.nonNull(person)) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.PERSON_ALREADY_EXISTS);
            throw new BadRequestException(MessagesConstants.PERSON_ALREADY_EXISTS);
        }
        try {
            PersonEntity personEntity = personConverter.convertToEntity(personInputDTO);
            return personRepository.save(personEntity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.ERROR_WHEN_SAVING_PERSON, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_PERSON);
        }
    }

    @Override
    public List<PersonEntity> getAll() {
        log.info("getAll :: Listando todas as pessoas");
        List<PersonEntity> personList = personRepository.findAll();
        if (personList.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao buscar as pessoas: {}", MessagesConstants.PERSONS_NOT_FOUND);
            throw new BadRequestException(MessagesConstants.PERSONS_NOT_FOUND);
        }
        return personList;
    }

    @Override
    public PersonEntity getById(UUID id) {
        log.info("getById :: Buscando pessoa pelo id: {}", id.toString());
        return personRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.PERSON_NOT_FOUND));
    }

    @Override
    public PersonEntity getByName(String name) {
        log.info("getByName :: Buscando pessoa pelo nome: {}", name);
        PersonEntity person = personRepository.findByName(name);
        if (Objects.isNull(person)) {
            log.error("getByName :: Ocorreu um erro: {}", MessagesConstants.PERSON_NOT_FOUND_WITH_NAME + name);
            throw new BadRequestException(MessagesConstants.PERSON_NOT_FOUND_WITH_NAME + name);
        }
        return person;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonEntity update(UUID id, PersonInputDTO personInputDTO) {
        log.info("update :: Atualizando pessoa com os dados: {}", personInputDTO.toString());
        try {
            PersonEntity personEntity = this.getById(id);
            personRepository.save(personConverter.convertToEntityUpdate(personEntity, id, personInputDTO));
            return personRepository.findByName(personEntity.getName());
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {}", MessagesConstants.ERROR_UPDATE_PERSON_DATA, e);
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_PERSON_DATA);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando pessoa com o id: {}", id.toString());
        Optional<PersonEntity> person = personRepository.findById(id);
        if (person.isEmpty()) {
            log.error("delete :: Ocorreu um erro: {}", MessagesConstants.PERSON_NOT_FOUND);
            throw new BadRequestException(MessagesConstants.PERSON_NOT_FOUND);
        }
        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro: {}", MessagesConstants.ERROR_DELETE_PERSON_DATA, e);
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_PERSON_DATA);
        }
    }

}
