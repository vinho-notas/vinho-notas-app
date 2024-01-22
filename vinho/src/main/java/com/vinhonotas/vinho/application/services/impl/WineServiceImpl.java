package com.vinhonotas.vinho.application.services.impl;

import com.vinhonotas.vinho.application.converters.WineConverter;
import com.vinhonotas.vinho.application.services.WineService;
import com.vinhonotas.vinho.application.services.exceptions.BadRequestException;
import com.vinhonotas.vinho.domain.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.WineRepository;
import com.vinhonotas.vinho.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.vinho.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;
    private final WineConverter wineConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WineEntity create(WineInputDTO wineInputDTO) {
        try {
        return wineRepository.save(wineConverter.toEntity(wineInputDTO));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_CREATE_WINE);
        }
    }

    @Override
    public List<WineEntity> getAll() {
        return null;
    }

    @Override
    public WineEntity getById(UUID id) {
        return null;
    }

    @Override
    public WineEntity update(UUID id, WineInputDTO wineInputDTO) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
