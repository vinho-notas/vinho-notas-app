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
import java.util.Optional;
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
        List<WineEntity> wineList = wineRepository.findAll();
        if (wineList.isEmpty()) {
            throw new BadRequestException(MessagesConstants.ERROR_WINE_NOT_FOUND);
        }
        return wineList;
    }

    @Override
    public WineEntity getById(UUID id) {
       return wineRepository.findById(id)
               .orElseThrow(() -> new BadRequestException(MessagesConstants.ERROR_WINE_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WineEntity update(UUID id, WineInputDTO wineInputDTO) {
        try {
            WineEntity wineSaved = this.getById(id);
            return wineRepository.save(wineConverter.toEntityUpdate(wineSaved, id, wineInputDTO));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_WINE_DATA);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<WineEntity> wine = wineRepository.findById(id);
        if (wine.isEmpty()) {
            throw new BadRequestException(MessagesConstants.ERROR_WINE_NOT_FOUND);
        }
        try {
            wineRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_WINE);
        }
    }
}
