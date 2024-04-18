package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.converters.StateConverter;
import com.vinhonotas.cadastro.application.services.StateService;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.exceptions.CountryNotFoundException;
import com.vinhonotas.cadastro.domain.entities.exceptions.StateAlreadyExistsException;
import com.vinhonotas.cadastro.domain.entities.exceptions.StateNotFoundException;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.infrastructure.StateRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
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
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final StateConverter stateConverter;
    private final CountryConverter countryConverter;
    private final CountryRepository countryRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StateEntity create(StateInputDTO stateInputDTO) {
        log.info("create :: Registrando um estado com os dados: {}", stateInputDTO.toString());
        existsStateByName(stateInputDTO);
        try {
            existsCountryByName(stateInputDTO);
            StateEntity stateEntity = stateConverter.convertToEntity(stateInputDTO);
            return stateRepository.save(stateEntity);
        } catch (Exception e) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.ERROR_WHEN_SAVING_STATE, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_STATE);
        }
    }

    private void existsCountryByName(StateInputDTO stateInputDTO) {
        CountryEntity country = countryRepository.findByCountryName(stateInputDTO.getCountry().getCountryName());
        if (Objects.nonNull(country)) {
            stateInputDTO.setCountry(countryConverter.convertToInputDTO(country));
        } else {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME + stateInputDTO.getCountry().getCountryName());
            throw new CountryNotFoundException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME + stateInputDTO.getCountry().getCountryName());
        }
    }

    private void existsStateByName(StateInputDTO stateInputDTO) {
        StateEntity state = stateRepository.findByStateName(stateInputDTO.getStateName());
        if (Objects.nonNull(state)) {
            log.error("create :: Ocorreu um erro: {}", MessagesConstants.STATE_ALREADY_EXISTS);
            throw new StateAlreadyExistsException(MessagesConstants.STATE_ALREADY_EXISTS);
        }
    }

    @Override
    public List<StateEntity> getAll() {
        log.info("getAll :: Listando todos os estados");
        List<StateEntity> entityList = stateRepository.findAll();
        if (entityList.isEmpty()) {
            log.error("getAll :: Ocorreu um erro ao buscar os estados: {}", MessagesConstants.STATES_NOT_FOUND);
            throw new StateNotFoundException(MessagesConstants.STATES_NOT_FOUND);
        }
        return entityList;
    }

    @Override
    public StateEntity getById(UUID id) {
        log.info("getById :: Buscando estado pelo id: {}", id.toString());
        return stateRepository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(MessagesConstants.STATE_NOT_FOUND));
    }

    @Override
    public StateEntity getByName(String name) {
        log.info("getByName :: Buscando estado pelo nome: {}", name);
        StateEntity state = stateRepository.findByStateName(name);
        if (Objects.isNull(state)) {
            log.error("getByName :: Ocorreu um erro ao buscar o estado: {}", MessagesConstants.STATE_NOT_FOUND_WITH_NAME + name);
            throw new StateNotFoundException(MessagesConstants.STATE_NOT_FOUND_WITH_NAME + name);
        }
        return state;
    }

    @Override
    public StateEntity getByUf(String uf) {
        log.info("getByUf :: Buscando estado pela sigla: {}", uf);
        StateEntity state = stateRepository.findByUf(uf);
        if (Objects.isNull(state)) {
            log.error("getByUf :: Ocorreu um erro ao buscar o estado: {}", MessagesConstants.STATE_NOT_FOUND_WITH_UF + uf);
            throw new StateNotFoundException(MessagesConstants.STATE_NOT_FOUND_WITH_UF + uf);
        }
        return state;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StateEntity update(UUID id, StateInputDTO stateInputDTO) {
        log.info("update :: Atualizando estado com os dados: {}", stateInputDTO.toString());
        try {
            StateEntity entity = this.getById(id);
            stateRepository.save(stateConverter.convertToEntityUpdate(entity, id, stateInputDTO));
            return stateRepository.findByStateName(entity.getStateName());
        } catch (Exception e) {
            log.error("update :: Ocorreu um erro: {}", MessagesConstants.ERROR_UPDATE_STATE_DATA, e);
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_STATE_DATA);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        log.info("delete :: Deletando estado com o id: {}", id.toString());
        Optional<StateEntity> entity = stateRepository.findById(id);
        if (entity.isEmpty()) {
            log.error("delete :: Ocorreu um erro ao deletar o estado: {}", MessagesConstants.STATE_NOT_FOUND);
            throw new StateNotFoundException(MessagesConstants.STATE_NOT_FOUND);
        }
        try {
            stateRepository.deleteById(id);
        } catch (Exception e) {
            log.error("delete :: Ocorreu um erro: {}", MessagesConstants.ERROR_DELETE_STATE_DATA, e);
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_STATE_DATA);
        }
    }

}
