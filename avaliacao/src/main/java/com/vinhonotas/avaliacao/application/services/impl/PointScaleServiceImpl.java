package com.vinhonotas.avaliacao.application.services.impl;

import com.vinhonotas.avaliacao.application.converters.PointScaleConverter;
import com.vinhonotas.avaliacao.application.services.PointScaleService;
import com.vinhonotas.avaliacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import com.vinhonotas.avaliacao.infraestructure.PointScaleRepository;
import com.vinhonotas.avaliacao.interfaces.dtos.inputs.PointScaleInputDTO;
import com.vinhonotas.avaliacao.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PointScaleServiceImpl implements PointScaleService {

    private final PointScaleRepository pointScaleRepository;
    private final PointScaleConverter pointScaleConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PointScaleEntity create(PointScaleInputDTO pointScaleInputDTO) {
        try {
            return pointScaleRepository.save(pointScaleConverter.toEntity(pointScaleInputDTO));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_CREATE_POINT_SCALE);
        }
    }

    @Override
    public List<PointScaleEntity> getAll() {
        List<PointScaleEntity> list = pointScaleRepository.findAll();
        if (list.isEmpty()) {
            throw new BadRequestException(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND);
        }
        return list;
    }

    @Override
    public PointScaleEntity getById(UUID id) {
        return pointScaleRepository.findById(id).orElseThrow(() -> new BadRequestException(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PointScaleEntity update(UUID id, PointScaleInputDTO pointScaleInputDTO) {
        try {
            PointScaleEntity pointScale = this.getById(id);
            return pointScaleRepository.save(pointScaleConverter.toEntityUpdate(pointScaleInputDTO, id, pointScale));
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_POINT_SCALE);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        Optional<PointScaleEntity> pointScale = pointScaleRepository.findById(id);
        if (pointScale.isEmpty()) {
            throw new BadRequestException(MessagesConstants.ERROR_POINT_SCALE_NOT_FOUND);
        }
        try {
            pointScaleRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_DELETE_POINT_SCALE);
        }

    }
}
