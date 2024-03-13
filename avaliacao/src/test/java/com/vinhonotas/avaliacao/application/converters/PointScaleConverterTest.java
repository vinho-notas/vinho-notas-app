package com.vinhonotas.avaliacao.application.converters;

import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import com.vinhonotas.avaliacao.domain.enums.EnumPointScale;
import com.vinhonotas.avaliacao.interfaces.dtos.inputs.PointScaleInputDTO;
import com.vinhonotas.avaliacao.interfaces.dtos.outputs.PointScaleOutputDTO;
import com.vinhonotas.avaliacao.utils.EnumConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PointScaleConverterTest {

    @InjectMocks
    private PointScaleConverter pointScaleConverter;
    
    private PointScaleInputDTO pointScaleInputDTO;
    private PointScaleOutputDTO pointScaleOutputDTO;
    private PointScaleEntity pointScaleEntity;

    @BeforeEach
    void setUp() {
        pointScaleEntity = createPointScaleEntity();
        pointScaleInputDTO = createPointScaleInputDTO();
        pointScaleOutputDTO = createPointScaleOutputDTO();        
    }


    @Test
    @DisplayName("Testa o método toEntity")
    void testToEntity() {
        PointScaleEntity pointScaleEntity = assertDoesNotThrow(() -> pointScaleConverter.toEntity(pointScaleInputDTO));

        assertNotNull(pointScaleEntity);
        assertEquals(pointScaleEntity.getWhatTasted(), pointScaleInputDTO.getWhatTasted());
        assertEquals(pointScaleEntity.getWhenTasted(), pointScaleInputDTO.getWhenTasted());
        assertEquals(pointScaleEntity.getWhatSaw(), pointScaleInputDTO.getWhatSaw());
        assertEquals(pointScaleEntity.getWhatAromas(), pointScaleInputDTO.getWhatAromas());
        assertEquals(pointScaleEntity.getWhatFlavors(), pointScaleInputDTO.getWhatFlavors());
        assertEquals(pointScaleEntity.getWhatOpinion(), pointScaleInputDTO.getWhatOpinion());
        assertEquals(EnumConverter.toString(pointScaleEntity.getPointScale()), pointScaleInputDTO.getPointScale());
    }

    @Test
    @DisplayName("Testa o método toEntityUpdate")
    void testToEntityUpdate() {
        pointScaleInputDTO.setPointScale(EnumPointScale.MEDIOCRE.getCode());
        PointScaleEntity pointScaleUpdated = assertDoesNotThrow(() -> pointScaleConverter.toEntityUpdate(
                pointScaleInputDTO, UUID.randomUUID(), pointScaleEntity));

        assertNotNull(pointScaleEntity);
        assertEquals(pointScaleEntity.getWhatTasted(), pointScaleUpdated.getWhatTasted());
        assertEquals(pointScaleEntity.getWhenTasted(), pointScaleUpdated.getWhenTasted());
        assertEquals(pointScaleEntity.getWhatSaw(), pointScaleUpdated.getWhatSaw());
        assertEquals(pointScaleEntity.getWhatAromas(), pointScaleUpdated.getWhatAromas());
        assertEquals(pointScaleEntity.getWhatFlavors(), pointScaleUpdated.getWhatFlavors());
        assertEquals(pointScaleEntity.getWhatOpinion(), pointScaleUpdated.getWhatOpinion());
        assertEquals(EnumPointScale.MEDIOCRE, pointScaleUpdated.getPointScale());
    }

    @Test
    @DisplayName("Testa o método toOutputDTO")
    void testToOutputDTO() {
        PointScaleOutputDTO dtoUpdated = assertDoesNotThrow(() -> pointScaleConverter.toOutputDTO(pointScaleEntity));

        assertNotNull(dtoUpdated);
        assertEquals(pointScaleEntity.getWhatTasted(), dtoUpdated.getWhatTasted());
        assertEquals(pointScaleEntity.getWhenTasted(), dtoUpdated.getWhenTasted());
        assertEquals(pointScaleEntity.getWhatSaw(), dtoUpdated.getWhatSaw());
        assertEquals(pointScaleEntity.getWhatAromas(), dtoUpdated.getWhatAromas());
        assertEquals(pointScaleEntity.getWhatFlavors(), dtoUpdated.getWhatFlavors());
        assertEquals(pointScaleEntity.getWhatOpinion(), dtoUpdated.getWhatOpinion());
        assertEquals(EnumConverter.toString(pointScaleEntity.getPointScale()), dtoUpdated.getPointScale());
    }

    @Test
    @DisplayName("Testa o método toOutputDTOList")
    void testToOutputDTOList() {
        List<PointScaleOutputDTO> outputList = assertDoesNotThrow(() -> pointScaleConverter.toOutputDTOList(List.of(pointScaleEntity, pointScaleEntity)));

        assertNotNull(outputList);
        assertEquals(2, outputList.size());
        assertFalse(outputList.isEmpty());
        assertEquals(pointScaleEntity.getWhenTasted(), outputList.get(0).getWhenTasted());
        assertEquals(pointScaleEntity.getWhatSaw(), outputList.get(0).getWhatSaw());
        assertEquals(pointScaleEntity.getWhatAromas(), outputList.get(0).getWhatAromas());
        assertEquals(pointScaleEntity.getWhatFlavors(), outputList.get(0).getWhatFlavors());
        assertEquals(pointScaleEntity.getWhatOpinion(), outputList.get(0).getWhatOpinion());
        assertEquals(EnumConverter.toString(pointScaleEntity.getPointScale()), outputList.get(0).getPointScale());
    }

    @Test
    @DisplayName("Testa o método toOutputDTOUpdate")
    void testToOutputDTOUpdate() {
        PointScaleOutputDTO dtoUpdated = assertDoesNotThrow(() -> pointScaleConverter.toOutputDTOUpdate(pointScaleEntity, UUID.randomUUID(), pointScaleOutputDTO));

        assertNotNull(dtoUpdated);
        assertEquals(pointScaleEntity.getWhatTasted(), dtoUpdated.getWhatTasted());
        assertEquals(pointScaleEntity.getWhenTasted(), dtoUpdated.getWhenTasted());
        assertEquals(pointScaleEntity.getWhatSaw(), dtoUpdated.getWhatSaw());
        assertEquals(pointScaleEntity.getWhatAromas(), dtoUpdated.getWhatAromas());
        assertEquals(pointScaleEntity.getWhatFlavors(), dtoUpdated.getWhatFlavors());
        assertEquals(pointScaleEntity.getWhatOpinion(), dtoUpdated.getWhatOpinion());
        assertEquals(EnumConverter.toString(pointScaleEntity.getPointScale()), dtoUpdated.getPointScale());
    }

    private PointScaleOutputDTO createPointScaleOutputDTO() {
        return PointScaleOutputDTO.builder()
                .id(UUID.fromString("ea1cd995-e8d4-4cb7-b446-ca1a233aacba"))
                .whatTasted("Faustino Rivero Ulecia Reserva Rioja DOCa, safra 2018, vinho tinto, seco, " +
                        "produzido e engarrafado por vinícola Loma Negra, em Vale Central, Chile.")
                .whenTasted("Em 28/10/2023, às 21:30h.")
                .whatSaw("Coloração amarelo dourado.")
                .whatAromas("Aroma de pimentão vermelho maduro.")
                .whatFlavors("Na boca boa acidez, lembrando frutas cítricas.")
                .whatOpinion("Muito suculento com final longo.")
                .pointScale(EnumPointScale.OUTSTANDING.getCode())
                .build();
    }

    private PointScaleInputDTO createPointScaleInputDTO() {
        return PointScaleInputDTO.builder()
                .whatTasted("Faustino Rivero Ulecia Reserva Rioja DOCa, safra 2018, vinho tinto, seco, " +
                        "produzido e engarrafado por vinícola Loma Negra, em Vale Central, Chile.")
                .whenTasted("Em 28/10/2023, às 21:30h.")
                .whatSaw("Coloração amarelo dourado.")
                .whatAromas("Aroma de pimentão vermelho maduro.")
                .whatFlavors("Na boca boa acidez, lembrando frutas cítricas.")
                .whatOpinion("Muito suculento com final longo.")
                .pointScale(EnumPointScale.OUTSTANDING.getCode())
                .build();
    }

    private PointScaleEntity createPointScaleEntity() {
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