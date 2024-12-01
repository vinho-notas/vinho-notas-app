package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.application.gateways.UpdateWineRepository;
import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.domain.entities.exceptions.WineNotFoundException;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import com.vinhonotas.vinho.utils.EnumConverter;
import com.vinhonotas.vinho.utils.MessagesConstants;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.function.Consumer;

@Repository
public class UpdateWineRepositoryJPA implements UpdateWineRepository {

    private final WineRepository wineRepository;

    public UpdateWineRepositoryJPA(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public WineEntity updateWine(String id, WineInputDTO wineInputDTO) {
            WineEntity wineRetrieved = wineRepository.findById(UUID.fromString(id)).orElseThrow(
                    () -> new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND));
        try {
            updatePurchaseInfo(wineRetrieved, wineInputDTO);
            updateWineDetails(wineRetrieved, wineInputDTO);
            updateWineOrigin(wineRetrieved, wineInputDTO);

            return wineRepository.save(wineRetrieved);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_WINE_DATA);
        }
    }

    private void updatePurchaseInfo(WineEntity wineRetrieved, WineInputDTO wineInputDTO) {
        if (wineInputDTO.purchaseInfo() == null) {
            return;
        }

        var purchaseInfo = wineInputDTO.purchaseInfo();
        updateField(wineRetrieved::setPrice, purchaseInfo.price(), wineRetrieved.getPrice());
        updateField(wineRetrieved::setPurchaseLocation, purchaseInfo.purchaseLocation(), wineRetrieved.getPurchaseLocation());
        updateField(wineRetrieved::setPurchaseDate, purchaseInfo.purchaseDate(), wineRetrieved.getPurchaseDate());
    }


    private void updateWineDetails(WineEntity wineRetrieved, WineInputDTO wineInputDTO) {
        if (wineInputDTO.wineDetails() == null) {
            return;
        }
        var wineDetails = wineInputDTO.wineDetails();
        updateField(wineRetrieved::setWineType, EnumConverter.fromString(wineDetails.wineType(), EnumWineType.class), wineRetrieved.getWineType());
        updateField(wineRetrieved::setWineClassification, EnumConverter.fromString(wineDetails.wineClassification(),
                EnumWineClassification.class), wineRetrieved.getWineClassification());
        updateField(wineRetrieved::setAlcoholContent, wineDetails.alcoholContent(), wineRetrieved.getAlcoholContent());
        updateField(wineRetrieved::setVolumeMl, wineDetails.volumeMl() != null ? Integer.valueOf(wineDetails.volumeMl()) : null,
                wineRetrieved.getVolumeMl());
        updateField(wineRetrieved::setGrape, wineDetails.grape(), wineRetrieved.getGrape());
        updateField(wineRetrieved::setWinery, wineDetails.winery(), wineRetrieved.getWinery());
        updateField(wineRetrieved::setServiceTemperature, wineDetails.serviceTemperature(), wineRetrieved.getServiceTemperature());
    }

    private void updateWineOrigin(WineEntity wineRetrieved, WineInputDTO wineInputDTO) {
        if (wineInputDTO.wineOrigin() == null) {
            return;
        }

        var wineOrigin = wineInputDTO.wineOrigin();
        updateField(wineRetrieved::setHarvest, wineOrigin.harvest(), wineRetrieved.getHarvest());
        updateField(wineRetrieved::setCountry, wineOrigin.country(), wineRetrieved.getCountry());
        updateField(wineRetrieved::setGuardTime, wineOrigin.guardTime(), wineRetrieved.getGuardTime());
        updateField(wineRetrieved::setRegion, wineOrigin.region(), wineRetrieved.getRegion());
        updateField(wineRetrieved::setMaturation, wineOrigin.maturation(), wineRetrieved.getMaturation());
        updateField(wineRetrieved::setHarmonization, wineOrigin.harmonization(), wineRetrieved.getHarmonization());
    }

    private <T> void updateField(Consumer<T> setter, T newValue, T currentValue) {
        if (newValue != null && !newValue.equals(currentValue)) {
            setter.accept(newValue);
        }
    }

}
