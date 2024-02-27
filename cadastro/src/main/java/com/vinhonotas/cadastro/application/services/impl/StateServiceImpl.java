package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.CountryConverter;
import com.vinhonotas.cadastro.application.converters.StateConverter;
import com.vinhonotas.cadastro.application.services.StateService;
import com.vinhonotas.cadastro.application.services.exceptions.BadRequestException;
import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.infrastructure.CountryRepository;
import com.vinhonotas.cadastro.infrastructure.StateRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
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
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final StateConverter stateConverter;
    private final CountryConverter countryConverter;
    private final CountryRepository countryRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StateEntity create(StateInputDTO stateInputDTO) {
        StateEntity state = stateRepository.findByStateName(stateInputDTO.getStateName());
        if (Objects.nonNull(state)) {
            throw new BadRequestException(MessagesConstants.STATE_ALREADY_EXISTS);
        }
        try {
            CountryEntity country = countryRepository.findByCountryName(stateInputDTO.getCountry().getCountryName());
            if (Objects.nonNull(country)) {
                stateInputDTO.setCountry(countryConverter.convertToInputDTO(country));
            } else {
                throw new BadRequestException(MessagesConstants.COUNTRY_NOT_FOUND_WITH_NAME + stateInputDTO.getCountry().getCountryName());
            }
            StateEntity stateEntity = stateConverter.toEntity(stateInputDTO);
            return stateRepository.save(stateEntity);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_STATE);
        }
    }

    @Override
    public List<StateEntity> getAll() {
        List<StateEntity> entityList = stateRepository.findAll();
        if (entityList.isEmpty()) {
            throw new BadRequestException(MessagesConstants.STATES_NOT_FOUND);
        }
        return entityList;
    }

    @Override
    public StateEntity getById(UUID id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessagesConstants.STATE_NOT_FOUND));
    }

    @Override
    public StateEntity getByName(String name) {
        StateEntity state = stateRepository.findByStateName(name);
        if (Objects.isNull(state)) {
            throw new BadRequestException(MessagesConstants.STATE_NOT_FOUND_WITH_NAME + name);
        }
        return state;
    }

    @Override
    public StateEntity getByUf(String uf) {
        StateEntity state = stateRepository.findByUf(uf);
        if (Objects.isNull(state)) {
            throw new BadRequestException(MessagesConstants.STATE_NOT_FOUND_WITH_UF + uf);
        }
        return state;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StateEntity update(UUID id, StateInputDTO stateInputDTO) {
        try {
            StateEntity entity = this.getById(id);
            stateRepository.save(stateConverter.toEntityUpdate(entity, id, stateInputDTO));
            return stateRepository.findByStateName(entity.getStateName());
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_STATE_DATA);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<StateEntity> entity = stateRepository.findById(id);
        if (entity.isEmpty()) {
            throw new BadRequestException(MessagesConstants.STATE_NOT_FOUND);
        }
        try {
            stateRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_STATE_DATA);
        }
    }
}
