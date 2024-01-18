package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.converters.StateConverter;
import com.vinhonotas.cadastro.application.services.StateService;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import com.vinhonotas.cadastro.infrastructure.StateRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.StateInputDTO;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final StateConverter stateConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StateEntity create(StateInputDTO stateInputDTO) {
        try {
            StateEntity stateEntity = stateConverter.toEntity(stateInputDTO);
            return stateRepository.save(stateEntity);
        } catch (Exception e) {
            throw new IllegalArgumentException(MessagesConstants.ERROR_WHEN_SAVING_STATE);
        }
    }

    @Override
    public List<StateEntity> getAll() {
        return stateRepository.findAll();
    }

    @Override
    public StateEntity getById(UUID id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MessagesConstants.STATE_NOT_FOUND));
    }

    @Override
    public StateEntity getByName(String name) {
        try {
            return stateRepository.findByStateName(name);
        } catch (Exception e) {
            throw new IllegalArgumentException(MessagesConstants.STATE_NOT_FOUND_WITH_NAME + name);
        }
    }

    @Override
    public List<StateEntity> getByUf(String uf) {
        try {
            return stateRepository.findByUf(uf);
        } catch (Exception e) {
            throw new IllegalArgumentException(MessagesConstants.STATE_NOT_FOUND_WITH_UF + uf);
        }
    }

    @Override
    public StateEntity update(UUID id, StateInputDTO stateInputDTO) {
        try {
            StateEntity entity = this.getById(id);
            stateRepository.save(stateConverter.toEntityUpdate(entity, id, stateInputDTO));
            return stateRepository.findByStateName(entity.getStateName());
        } catch (Exception e) {
            throw new IllegalArgumentException(MessagesConstants.ERROR_UPDATE_STATE_DATA);
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            stateRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(MessagesConstants.ERROR_DELETE_STATE_DATA);
        }
    }
}
