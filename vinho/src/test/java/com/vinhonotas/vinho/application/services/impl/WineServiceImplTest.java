package com.vinhonotas.vinho.application.services.impl;

import com.vinhonotas.vinho.application.converters.WineConverter;
import com.vinhonotas.vinho.infraestructure.WineRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WineServiceImplTest {

    @InjectMocks
    private WineServiceImpl wineServiceImpl;

    @Mock
    private WineRepository wineRepository;
    @Mock
    private WineConverter wineConverter;

}