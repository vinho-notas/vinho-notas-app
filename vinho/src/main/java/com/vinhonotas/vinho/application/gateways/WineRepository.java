package com.vinhonotas.vinho.application.gateways;

import com.vinhonotas.vinho.domain.entities.wine.WineDomain;

public interface WineRepository {

    WineDomain createWine(WineDomain wineDomain);

}
