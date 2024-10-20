package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.application.gateways.UpdateWineRepository;
import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.domain.entities.exceptions.WineNotFoundException;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
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
    public WineEntity updateWine(String id, WineDomain wineDomain) {
        try {
            WineEntity wineRetrieved = wineRepository.findById(UUID.fromString(id)).orElseThrow(
                    () -> new WineNotFoundException(MessagesConstants.ERROR_WINE_NOT_FOUND));

            updatePurchaseInfo(wineRetrieved, wineDomain);
            updateWineDetails(wineRetrieved, wineDomain);
            updateWineOrigin(wineRetrieved, wineDomain);

            return wineRepository.save(wineRetrieved);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_WINE_DATA);
        }
    }

    private void updatePurchaseInfo(WineEntity wineRetrieved, WineDomain wineDomain) {
        var purchaseInfo = wineDomain.getPurchaseInfo();
        updateField(wineRetrieved::setPrice, purchaseInfo.getPrice(), wineRetrieved.getPrice());
        updateField(wineRetrieved::setPurchaseLocation, purchaseInfo.getPurchaseLocation(), wineRetrieved.getPurchaseLocation());
        updateField(wineRetrieved::setPurchaseDate, purchaseInfo.getPurchaseDate(), wineRetrieved.getPurchaseDate());
    }


    private void updateWineDetails(WineEntity wineRetrieved, WineDomain wineDomain) {
        var wineDetails = wineDomain.getWineDetails();
        updateField(wineRetrieved::setWineType, wineDetails.getWineType(), wineRetrieved.getWineType());
        updateField(wineRetrieved::setWineClassification, wineDetails.getWineClassification(), wineRetrieved.getWineClassification());
        updateField(wineRetrieved::setAlcoholContent, wineDetails.getAlcoholContent(), wineRetrieved.getAlcoholContent());
        updateField(wineRetrieved::setVolumeMl, wineDetails.getVolumeMl(), wineRetrieved.getVolumeMl());
        updateField(wineRetrieved::setGrape, wineDetails.getGrape(), wineRetrieved.getGrape());
        updateField(wineRetrieved::setWinery, wineDetails.getWinery(), wineRetrieved.getWinery());
        updateField(wineRetrieved::setServiceTemperature, wineDetails.getServiceTemperature(), wineRetrieved.getServiceTemperature());
    }

    private void updateWineOrigin(WineEntity wineRetrieved, WineDomain wineDomain) {
        var wineOrigin = wineDomain.getWineOrigin();
        updateField(wineRetrieved::setHarvest, wineOrigin.getHarvest(), wineRetrieved.getHarvest());
        updateField(wineRetrieved::setCountry, wineOrigin.getCountry(), wineRetrieved.getCountry());
        updateField(wineRetrieved::setGuardTime, wineOrigin.getGuardTime(), wineRetrieved.getGuardTime());
        updateField(wineRetrieved::setRegion, wineOrigin.getRegion(), wineRetrieved.getRegion());
        updateField(wineRetrieved::setMaturation, wineOrigin.getMaturation(), wineRetrieved.getMaturation());
        updateField(wineRetrieved::setHarmonization, wineOrigin.getHarmonization(), wineRetrieved.getHarmonization());
    }

    private <T> void updateField(Consumer<T> setter, T newValue, T currentValue) {
        if (newValue != null && !newValue.equals(currentValue)) {
            setter.accept(newValue);
        }
    }

}
