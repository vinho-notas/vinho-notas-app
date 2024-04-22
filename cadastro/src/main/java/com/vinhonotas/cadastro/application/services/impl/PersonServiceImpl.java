package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.PersonConverter;
import com.vinhonotas.cadastro.application.services.PersonService;
import com.vinhonotas.cadastro.application.services.UserService;
import com.vinhonotas.cadastro.domain.entities.*;
import com.vinhonotas.cadastro.domain.entities.exceptions.*;
import com.vinhonotas.cadastro.infrastructure.PersonRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.EditPersonInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final StateServiceImpl stateService;
    private final CountryServiceImpl countryService;
    private final UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonEntity create(PersonInputDTO personInputDTO) {
        log.info("create :: Registrando uma pessoa com os dados: {}", personInputDTO.toString());
        existsPersonByDocument(personInputDTO);
        existsStateByUf(personInputDTO);
        validateAge(personInputDTO.getBirthDate());
        existsCountryByCountryName(personInputDTO);
        try {
            PersonEntity personEntity = personConverter.convertToEntity(personInputDTO);
            log.info("Salvando pessoa no banco com os dados: {}", personEntity.toString());
            return personRepository.save(personEntity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.ERROR_WHEN_SAVING_PERSON, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_PERSON);
        }
    }

    private void validateAge(LocalDate birthDate) {
        if (birthDate.isAfter(LocalDate.now().minusYears(18))) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.PERSON_UNDERAGE);
            throw new PersonUnderageException(MessagesConstants.PERSON_UNDERAGE);
        }
    }

    private void existsCountryByCountryName(PersonInputDTO personInputDTO) {
        CountryEntity country = countryService.getByName(personInputDTO.getAddress().getCountry());
        if (Objects.isNull(country)) {
            throw new CountryNotFoundException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME +
                    personInputDTO.getAddress().getCountry());
        }
        log.info("Salvando um país com os dados: {}", country);
        personInputDTO.getAddress().setCountry(country.getCountryName());
    }

    private void existsStateByUf(PersonInputDTO personInputDTO) {
        StateEntity state = stateService.getByUf(personInputDTO.getAddress().getUf());
        if (Objects.isNull(state)) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.STATE_NOT_FOUND);
            throw new StateNotFoundException(MessagesConstants.STATE_NOT_FOUND);
        } else {
            log.info("Salvando um estado com os dados: {}", state);
            personInputDTO.getAddress().setUf(state.getUf());
        }
    }

    private void existsPersonByDocument(PersonInputDTO personInputDTO) {
        PersonEntity person = personRepository.findByDocument(personInputDTO.getDocument());
        if (Objects.nonNull(person)) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.PERSON_ALREADY_EXISTS + person);
            throw new PersonAlreadyExistsException(MessagesConstants.PERSON_ALREADY_EXISTS);
        }
    }

    @Override
    public List<PersonEntity> getAll() {
        log.info("getAll :: Listando todas as pessoas");
        List<PersonEntity> personList = personRepository.findAll();
        if (personList.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao buscar as pessoas: {}", MessagesConstants.PERSONS_NOT_FOUND);
            throw new PersonNotFoundException(MessagesConstants.PERSONS_NOT_FOUND);
        }
        return personList;
    }

    @Override
    public PersonEntity getById(UUID id) {
        log.info("getById :: Buscando pessoa pelo id: {}", id.toString());
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(MessagesConstants.PERSON_NOT_FOUND));
    }

    @Override
    public PersonEntity getByName(String name) {
        log.info("getByName :: Buscando pessoa pelo nome: {}", name);
        PersonEntity person = personRepository.findByName(name);
        if (Objects.isNull(person)) {
            log.error("getByName :: Ocorreu um erro: {}", MessagesConstants.PERSON_NOT_FOUND_WITH_NAME + name);
            throw new PersonNotFoundException(MessagesConstants.PERSON_NOT_FOUND_WITH_NAME + name);
        }
        return person;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonEntity update(UUID id, EditPersonInputDTO editPersonInputDTO) {
        log.info("update :: Atualizando pessoa com os dados: {}", editPersonInputDTO.toString());
        try {
            PersonEntity existingPerson = getById(id);
            updatePersonData(editPersonInputDTO, existingPerson);
            updateAuditingInfo(existingPerson);

            PersonEntity updatedPerson = personRepository.save(existingPerson);
            log.info("Pessoa atualizada com sucesso: {}", updatedPerson);

            return updatedPerson;
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro ao atualizar a pessoa: {}", e.getMessage());
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
            throw new PersonNotFoundException(MessagesConstants.PERSON_NOT_FOUND);
        }

        log.info("Buscando um usuário com o personId: {}", person.get().getId());
        UserEntity user = userService.getByPersonId(person.get().getId());
        if (Objects.nonNull(user)) {
            log.info("Deletando a seguinte pessoa: {}", person);
            userService.delete(user.getId());
        }

        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro: {}", MessagesConstants.ERROR_DELETE_PERSON_DATA, e);
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_PERSON_DATA);
        }
    }

    private static void updateAuditingInfo(PersonEntity existingPerson) {
        existingPerson.setDthalt(LocalDateTime.now());
        existingPerson.setUseralt("usuario");
    }

    private static void updatePersonData(EditPersonInputDTO editPersonInputDTO, PersonEntity existingPerson) {
        existingPerson.setName(editPersonInputDTO.getName());
        existingPerson.setDocument(editPersonInputDTO.getDocument());
        existingPerson.setBirthDate(editPersonInputDTO.getBirthDate());
    }


}
