package com.vinhonotas.avaliacao.infraestructure;

import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import com.vinhonotas.avaliacao.domain.enums.EnumPointScale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class PointScaleRepositoryTest {

    @Autowired
    private PointScaleRepository pointScaleRepository;

    private PointScaleEntity pointScaleOne;
    private PointScaleEntity pointScaleTwo;
    private PointScaleEntity pointScaleThree;

    @BeforeEach
    void setUp() {
        pointScaleOne = createPointScaleOne();
        pointScaleTwo = createPointScaleTwo();
        pointScaleThree = createPointScaleThree();
    }

    @Test
    @DisplayName("Deve criar uma avaliação de um vinho")
    void testCreatePointScale() {
        PointScaleEntity pointScale = assertDoesNotThrow(() -> pointScaleRepository.save(pointScaleOne));

        assertNotNull(pointScale);
        assertEquals(pointScaleOne.getId(), pointScale.getId());
        assertEquals(pointScaleOne.getWhatTasted(), pointScale.getWhatTasted());
        assertEquals(pointScaleOne.getWhenTasted(), pointScale.getWhenTasted());
        assertEquals(pointScaleOne.getWhatSaw(), pointScale.getWhatSaw());
        assertEquals(pointScaleOne.getWhatAromas(), pointScale.getWhatAromas());
        assertEquals(pointScaleOne.getWhatFlavors(), pointScale.getWhatFlavors());
        assertEquals(pointScaleOne.getWhatOpinion(), pointScale.getWhatOpinion());
        assertEquals(pointScaleOne.getPointScale(), pointScale.getPointScale());
    }

    @Test
    @DisplayName("Deve retornar uma lista contendo todos os vinhos avaliados")
    void testReadAllPointScale() {
        pointScaleRepository.save(pointScaleOne);
        pointScaleRepository.save(pointScaleTwo);
        pointScaleRepository.save(pointScaleThree);

        List<PointScaleEntity> list = assertDoesNotThrow(() -> pointScaleRepository.findAll());
        assertFalse(list.isEmpty());
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(pointScaleOne.getId(), list.get(0).getId());
        assertEquals(pointScaleTwo.getId(), list.get(1).getId());
        assertEquals(pointScaleThree.getId(), list.get(2).getId());
    }

    @Test
    @DisplayName("Deve retornar um vinho avaliado quando informado seu id")
    void testReadByIdPointScale() {
        pointScaleRepository.save(pointScaleOne);
        pointScaleRepository.save(pointScaleTwo);
        pointScaleRepository.save(pointScaleThree);

        Optional<PointScaleEntity> pointScale = assertDoesNotThrow(() -> pointScaleRepository.findById(pointScaleOne.getId()));
        assertNotNull(pointScale.get());
        assertEquals(pointScaleOne.getId(), pointScale.get().getId());
        assertEquals(pointScaleOne.getWhatTasted(), pointScale.get().getWhatTasted());
        assertEquals(pointScaleOne.getWhenTasted(), pointScale.get().getWhenTasted());
        assertEquals(pointScaleOne.getWhatSaw(), pointScale.get().getWhatSaw());
        assertEquals(pointScaleOne.getWhatAromas(), pointScale.get().getWhatAromas());
        assertEquals(pointScaleOne.getWhatFlavors(), pointScale.get().getWhatFlavors());
        assertEquals(pointScaleOne.getWhatOpinion(), pointScale.get().getWhatOpinion());
        assertEquals(pointScaleOne.getPointScale(), pointScale.get().getPointScale());
    }

    @Test
    @DisplayName("Deve alterar um vinho avaliado quando informado seu id")
    void testUpdatePointScale() {
        PointScaleEntity pointScaleSaved = assertDoesNotThrow(() -> pointScaleRepository.save(pointScaleTwo));
        pointScaleSaved.setPointScale(EnumPointScale.NOTRECOMMENDED);

        PointScaleEntity pointScaleUpdated = assertDoesNotThrow(() -> pointScaleRepository.save(pointScaleSaved));
        assertNotNull(pointScaleUpdated);
        assertEquals(pointScaleSaved.getId(), pointScaleUpdated.getId());
        assertEquals(EnumPointScale.NOTRECOMMENDED, pointScaleUpdated.getPointScale());
    }

    @Test
    @DisplayName("Deve deletar um vinho avaliado quando informado seu id")
    void testDeletePointScale() {
        pointScaleRepository.save(pointScaleThree);

        assertDoesNotThrow(() -> pointScaleRepository.deleteById(pointScaleThree.getId()));
        Optional<PointScaleEntity> pointScale = assertDoesNotThrow(() -> pointScaleRepository.findById(pointScaleThree.getId()));
        assertTrue(pointScale.isEmpty());
    }

    private PointScaleEntity createPointScaleOne() {
        return PointScaleEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .whatTasted("Portada Winemaker's Selection, safra 2020, vinho tinto, seco, 12,5% de álcool, " +
                        "produzido e engarrafado por DFJ Vinhos, em Lisboa, Portugal.")
                .whenTasted("Em 15/09/2023, às 20:00h.")
                .whatSaw("Vinho de cor vermelho rubi, com reflexos violáceos, límpido, brilhante, com lágrimas finas, " +
                        "rápidas e abundantes.")
                .whatAromas("Aromas de frutas vermelhas maduras, como cereja e framboesa, com notas de especiarias, " +
                        "como pimenta preta, e de ervas, como tomilho.")
                .whatFlavors("Em boca, o vinho é seco, com acidez média, taninos médios, álcool médio, corpo médio, " +
                        "intensidade de sabor média, com sabores de frutas vermelhas maduras, como cereja e framboesa, " +
                        "com notas de especiarias, como pimenta preta, e de ervas, como tomilho, e final médio.")
                .whatOpinion("Vinho de boa qualidade, com boa complexidade, equilibrado, com boa intensidade de sabor, " +
                        "com boa persistência, com boa tipicidade, com boa harmonização, com boa relação qualidade/preço, " +
                        "com potencial de guarda de 3 anos, mas que pode ser consumido desde já.")
                .pointScale(EnumPointScale.VERYGOOD)
                .build();
    }

    private PointScaleEntity createPointScaleTwo() {
        return PointScaleEntity.builder()
                .id(UUID.fromString("468fc21e-f713-4974-9358-4a9547708ae4"))
                .whatTasted("Faustino Rivero Ulecia Reserva Rioja DOCa, safra 2018, vinho tinto, seco, 14% de álcool, " +
                        "produzido e engarrafado por Marqués del Atrio, em Rioja, Espanha.")
                .whenTasted("Em 28/10/2023, às 21:30h.")
                .whatSaw("Vermelho escuro , opaco e brilhante.")
                .whatAromas("Compota de frutas , tostado e chocolate..")
                .whatFlavors("Taninos suaves e bastante acidez. Corpo médio e persistente.")
                .whatOpinion("Mais um ótimo tinto da Rioja que degusto, ótimos aromas de frutas negras com toque de " +
                        "pimenta e tabaco, ótimo corpo e estrutura com tanicidade eacidez bem equilibradas, vinho bem agradável")
                .pointScale(EnumPointScale.GOOD)
                .build();
    }

    private PointScaleEntity createPointScaleThree() {
        return PointScaleEntity.builder()
                .id(UUID.fromString("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"))
                .whatTasted("Faustino Rivero Ulecia Reserva Rioja DOCa, safra 2018, vinho tinto, seco, " +
                        "produzido e engarrafado por vinícola Loma Negra, em Vale Central, Chile.")
                .whenTasted("Em 28/10/2023, às 21:30h.")
                .whatSaw("Coloração amarelo dourado.")
                .whatAromas("Aroma de pimentão vermelho maduro.")
                .whatFlavors("Na boca boa acidez, lembrando frutas cítricas.")
                .whatOpinion("Muito suculento com final longo.")
                .pointScale(EnumPointScale.OUTSTANDING)
                .build();
    }

}
