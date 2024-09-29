package com.vinhonotas.vinho.configuration;

import com.vinhonotas.vinho.application.gateways.CreateWineRepository;
import com.vinhonotas.vinho.application.usecases.impl.CreateWineImpl;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineDomainMapper;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineEntityMapper;
import com.vinhonotas.vinho.infraestructure.gateways.repositories.WineRepositoryJPA;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WineConfig {

    @Bean
    CreateWineImpl createWine(@Qualifier("createWineRepository") CreateWineRepository createWineRepository) {
        return new CreateWineImpl(createWineRepository);
    }

    @Bean
    WineRepositoryJPA createWineRepository(WineRepository wineRepository, WineEntityMapper wineEntityMapper) {
        return new WineRepositoryJPA(wineRepository, wineEntityMapper);
    }

    @Bean
    WineEntityMapper createWineEntityMapper() {
        return new WineEntityMapper();
    }

    @Bean
    WineDomainMapper createWineDomainMapper() {
        return new WineDomainMapper();
    }

}
