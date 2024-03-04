package com.vinhonotas.bff.application.services.vinho;

import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.vinho.WineOutputDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface WineService {

    WineOutputDTO createWine(WineInputDTO wineInputDTO);

    List<WineOutputDTO> getAllWines();

    WineOutputDTO getWineById(String id);

    WineOutputDTO updateWine(String id, @Valid @RequestBody WineInputDTO wineInputDTO);

    void deleteWine(String id);

}
