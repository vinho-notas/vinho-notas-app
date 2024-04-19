package com.vinhonotas.bff.application.services.harmonizacao.impl;

import com.vinhonotas.bff.client.harmonizacao.PairingClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.harmonizacao.PairingInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.harmonizacao.PairingResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PairingServiceImplTest {

    private final String WINE_NAME = "Vinho Miolo Lote 43";
    private final String INFORMATION = "As características do vinho Vinho Miolo Lote 43 podem variar de acordo com a " +
            "safra e o processo de produção, mas geralmente são:" +
            "\n- Tipo de vinho: Tinto" +
            "\n- País de origem: Brasil" +
            "\n- Região: Vale dos Vinhedos, Rio Grande do Sul" +
            "\n- Uvas utilizadas: Cabernet Sauvignon e Merlot" +
            "\n- Teor alcoólico: Normalmente em torno de 13,5%" +
            "\n- Notas aromáticas: Frutas vermelhas, especiarias, notas de tabaco e baunilha" +
            "\n- Cor: Vermelho intenso" +
            "\n- Corpo: Encorpado" +
            "\n- Taninos: Macios e aveludados" +
            "\n- Acidez: Equilibrada";

    private final String PAIRING = "O Vinho Miolo Lote 43 harmoniza bem com:" +
            "\n- Carnes vermelhas grelhadas" +
            "\n- Massas com molhos vermelhos" +
            "\n- Queijos maduros" +
            "\n- Risotos de funghi" +
            "\n- Comida mexicana";

    private final String MENU_PAIRING = "Para harmonizar com o Vinho Miolo Lote 43, sugerimos o seguinte menu:" +
            "\n- Entrada: Carpaccio de carne com rúcula e lascas de queijo parmesão" +
            "\n- Prato Principal: Risoto de cogumelos selvagens" +
            "\n- Sobremesa: Tiramisù";

    @InjectMocks
    private PairingServiceImpl pairingService;

    @Mock
    private PairingClient pairingClient;

    private PairingInputDTO wine;

    @BeforeEach
    void setUp() {
        wine = new PairingInputDTO(WINE_NAME);
    }

    @Test
    @DisplayName("Deve retornar informações do vinho")
    void testGetWineInformation() {
        when(pairingClient.getWineInformation(wine)).thenReturn(new PairingResponseDTO(INFORMATION));

        PairingResponseDTO wineInformation = pairingService.getWineInformation(wine);
        assertNotNull(wineInformation.response());
        verify(pairingClient).getWineInformation(wine);
    }

    @Test
    @DisplayName("Deve retornar harmonizações do vinho")
    void testGetWinePairing() {
        when(pairingClient.getWinePairing(wine)).thenReturn(new PairingResponseDTO(PAIRING));

        PairingResponseDTO winePairing = pairingService.getWinePairing(wine);
        assertNotNull(winePairing.response());
        verify(pairingClient).getWinePairing(wine);
    }

    @Test
    @DisplayName("Deve retornar menu harmonizado com o vinho")
    void testGetMenuPairing() {
        when(pairingClient.getMenuPairing(wine)).thenReturn(new PairingResponseDTO(MENU_PAIRING));

        PairingResponseDTO menuPairing = pairingService.getMenuPairing(wine);
        assertNotNull(menuPairing.response());
        verify(pairingClient).getMenuPairing(wine);
    }

}
