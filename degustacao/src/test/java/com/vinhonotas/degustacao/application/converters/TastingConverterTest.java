package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import com.vinhonotas.degustacao.domain.enums.EnumTastingType;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingOutputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TastingConverterTest {

    @InjectMocks
    private TastingConverter tastingConverter;

    private TastingEntity tastingEntity;
    private TastingInputDTO tastingInputDTO;
    private TastingOutputDTO tastingOutputDTO;

    @BeforeEach
    void setUp() {
        tastingEntity = createTastingEntity();
        tastingInputDTO = createTastingInputDTO();
        tastingOutputDTO = createTastingOutputDTO();
    }

    @Test
    @DisplayName("Deve converter para uma entidade")
    void toEntity() {
        TastingEntity tasting = assertDoesNotThrow(() -> tastingConverter.toEntity(tastingInputDTO));

        assertNotNull(tasting);
        assertEquals(tastingInputDTO.getTastingData(), tasting.getTastingData());
        assertEquals(tastingInputDTO.getTastingType(), tasting.getTastingType());
        assertEquals(tastingInputDTO.getTastingCards(), tasting.getTastingCards());
    }

    @Test
    @DisplayName("Deve converter para uma entidade de atualização")
    void toEntityUpdate() {
        tastingInputDTO.setTastingData(LocalDate.now().minusDays(1));
        tastingInputDTO.setTastingType(EnumTastingType.VERTICAL);
        TastingEntity tasting = assertDoesNotThrow(() -> tastingConverter.toEntityUpdate(tastingInputDTO, tastingEntity.getId(), tastingEntity));

        assertNotNull(tasting);
        assertEquals(LocalDate.now().minusDays(1), tasting.getTastingData());
        assertEquals(EnumTastingType.VERTICAL, tasting.getTastingType());
        assertEquals(tastingInputDTO.getTastingCards(), tasting.getTastingCards());
    }

    @Test
    @DisplayName("Deve converter para um DTO de saída")
    void toOutputDTO() {
        TastingOutputDTO tasting = assertDoesNotThrow(() -> tastingConverter.toOutputDTO(tastingEntity));

        assertNotNull(tasting);
        assertEquals(tastingEntity.getId(), tasting.getId());
        assertEquals(tastingEntity.getTastingData(), tasting.getTastingData());
        assertEquals(tastingEntity.getTastingType(), tasting.getTastingType());
        assertEquals(tastingEntity.getTastingCards(), tasting.getTastingCards());
    }

    @Test
    @DisplayName("Deve converter para um DTO de saída de atualização")
    void toOutputDTOUpdate() {
        tastingOutputDTO.setTastingData(LocalDate.now().minusDays(1));
        tastingOutputDTO.setTastingType(EnumTastingType.VERTICAL);
        TastingOutputDTO tasting = assertDoesNotThrow(() -> tastingConverter.toOutputDTOUpdate(tastingEntity, tastingEntity.getId(), tastingOutputDTO));

        assertNotNull(tasting);
        assertEquals(tastingEntity.getId(), tasting.getId());
        assertEquals(LocalDate.now().minusDays(1), tasting.getTastingData());
        assertEquals(EnumTastingType.VERTICAL, tasting.getTastingType());
    }

    @Test
    @DisplayName("Deve converter para uma lista de DTO de saída")
    void toOutputDTOList() {
        List<TastingOutputDTO> list = assertDoesNotThrow(() -> tastingConverter.toOutputDTOList(List.of(tastingEntity)));

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(tastingEntity.getId(), list.get(0).getId());
        assertEquals(tastingEntity.getTastingData(), list.get(0).getTastingData());
        assertEquals(tastingEntity.getTastingType(), list.get(0).getTastingType());
        assertEquals(tastingEntity.getTastingCards(), list.get(0).getTastingCards());
    }

    private TastingOutputDTO createTastingOutputDTO() {
        return TastingOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE)
                .tastingCards(Set.of(Mockito.mock(TastingCardEntity.class)))
                .build();
    }

    private TastingInputDTO createTastingInputDTO() {
        return TastingInputDTO.builder()
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE)
                .tastingCards(Set.of(Mockito.mock(TastingCardEntity.class)))
                .build();
    }

    private TastingEntity createTastingEntity() {
        return TastingEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE)
                .tastingCards(Set.of(Mockito.mock(TastingCardEntity.class)))
                .build();
    }
}