package com.vinhonotas.bff.application.services.cadastro;

import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.StateOutputDTO;

import java.util.List;

public interface StateService {

    List<StateOutputDTO> getAllStates();
    StateOutputDTO getStateById(String id);
    StateOutputDTO getStateByName(String name);
    StateOutputDTO getStateByUf(String uf);

}
