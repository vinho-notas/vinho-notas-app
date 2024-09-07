package com.vinhonotas.vinho.application.converters;

import com.vinhonotas.vinho.v1.application.converters.WineConverter;
import com.vinhonotas.vinho.v1.domain.entities.WineEntity;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineType;
import com.vinhonotas.vinho.v1.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.vinho.v1.interfaces.dtos.outputs.WineOutputDTO;
import com.vinhonotas.vinho.v1.utils.EnumConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WineConverterTest {

    @InjectMocks
    private WineConverter wineConverter;

    private WineEntity wineEntity;
    private WineInputDTO wineInputDTO;
    private WineOutputDTO wineOutputDTO;

    @BeforeEach
    void setUp() {
        wineEntity = createWineEntity();
        wineInputDTO = createWineInputDTO();
        wineOutputDTO = createWineOutputDTO();
    }

    @Test
    @DisplayName("Deve converter um WineInputDTO para WineEntity")
    void testToEntity() {
        WineEntity wineEntityConverted = wineConverter.toEntity(wineInputDTO);
        assertAll(
                () -> assertNotNull(wineEntityConverted),
                () -> assertEquals(wineEntity.getName(), wineEntityConverted.getName()),
                () -> assertEquals(wineEntity.getPrice(), wineEntityConverted.getPrice()),
                () -> assertEquals(wineEntity.getPurchaseLocation(), wineEntityConverted.getPurchaseLocation()),
                () -> assertEquals(wineEntity.getPurchaseDate(), wineEntityConverted.getPurchaseDate()),
                () -> assertEquals(wineEntity.getWineType(), wineEntityConverted.getWineType()),
                () -> assertEquals(wineEntity.getWineClassification(), wineEntityConverted.getWineClassification()),
                () -> assertEquals(wineEntity.getAlcoholContent(), wineEntityConverted.getAlcoholContent()),
                () -> assertEquals(wineEntity.getVolumeMl(), wineEntityConverted.getVolumeMl()),
                () -> assertEquals(wineEntity.getGrape(), wineEntityConverted.getGrape()),
                () -> assertEquals(wineEntity.getWinery(), wineEntityConverted.getWinery()),
                () -> assertEquals(wineEntity.getServiceTemperature(), wineEntityConverted.getServiceTemperature()),
                () -> assertEquals(wineEntity.getHarvest(), wineEntityConverted.getHarvest()),
                () -> assertEquals(wineEntity.getCountry(), wineEntityConverted.getCountry()),
                () -> assertEquals(wineEntity.getGuardTime(), wineEntityConverted.getGuardTime()),
                () -> assertEquals(wineEntity.getRegion(), wineEntityConverted.getRegion()),
                () -> assertEquals(wineEntity.getMaturation(), wineEntityConverted.getMaturation()),
                () -> assertEquals(wineEntity.getHarmonization(), wineEntityConverted.getHarmonization())
        );
    }

    @Test
    @DisplayName("Deve converter um WineEntity para WineOutputDTO")
    void testToOutputDTO() {
        WineOutputDTO wineOutputDTOConverted = wineConverter.toOutputDTO(wineEntity);
        assertAll(
                () -> assertNotNull(wineOutputDTOConverted),
                () -> assertEquals(wineOutputDTO.getId(), wineOutputDTOConverted.getId()),
                () -> assertEquals(wineOutputDTO.getName(), wineOutputDTOConverted.getName()),
                () -> assertEquals(wineOutputDTO.getPrice(), wineOutputDTOConverted.getPrice()),
                () -> assertEquals(wineOutputDTO.getPurchaseLocation(), wineOutputDTOConverted.getPurchaseLocation()),
                () -> assertEquals(wineOutputDTO.getPurchaseDate(), wineOutputDTOConverted.getPurchaseDate()),
                () -> assertEquals(wineOutputDTO.getWineType(), wineOutputDTOConverted.getWineType()),
                () -> assertEquals(wineOutputDTO.getWineClassification(), wineOutputDTOConverted.getWineClassification()),
                () -> assertEquals(wineOutputDTO.getAlcoholContent(), wineOutputDTOConverted.getAlcoholContent()),
                () -> assertEquals(wineOutputDTO.getVolumeMl(), wineOutputDTOConverted.getVolumeMl()),
                () -> assertEquals(wineOutputDTO.getGrape(), wineOutputDTOConverted.getGrape()),
                () -> assertEquals(wineOutputDTO.getWinery(), wineOutputDTOConverted.getWinery()),
                () -> assertEquals(wineOutputDTO.getServiceTemperature(), wineOutputDTOConverted.getServiceTemperature()),
                () -> assertEquals(wineOutputDTO.getHarvest(), wineOutputDTOConverted.getHarvest()),
                () -> assertEquals(wineOutputDTO.getCountry(), wineOutputDTOConverted.getCountry()),
                () -> assertEquals(wineOutputDTO.getGuardTime(), wineOutputDTOConverted.getGuardTime()),
                () -> assertEquals(wineOutputDTO.getRegion(), wineOutputDTOConverted.getRegion()),
                () -> assertEquals(wineOutputDTO.getMaturation(), wineOutputDTOConverted.getMaturation()),
                () -> assertEquals(wineOutputDTO.getHarmonization(), wineOutputDTOConverted.getHarmonization())
        );
    }

    @Test
    @DisplayName("Deve converter um WineInputDTO para WineEntity para atualização")
    void testToEntityUpdate() {
        wineInputDTO.setPrice(BigDecimal.valueOf(800.00));

        WineEntity wineEntityConverted = wineConverter.toEntityUpdate(wineEntity, wineEntity.getId(), wineInputDTO);
        assertAll(
                () -> assertNotNull(wineEntityConverted),
                () -> assertEquals(wineEntity.getId(), wineEntityConverted.getId()),
                () -> assertEquals(wineEntity.getName(), wineEntityConverted.getName()),
                () -> assertEquals(BigDecimal.valueOf(800.00), wineEntityConverted.getPrice()),
                () -> assertEquals(wineEntity.getPurchaseLocation(), wineEntityConverted.getPurchaseLocation()),
                () -> assertEquals(wineEntity.getPurchaseDate(), wineEntityConverted.getPurchaseDate()),
                () -> assertEquals(wineEntity.getWineType(), wineEntityConverted.getWineType()),
                () -> assertEquals(wineEntity.getWineClassification(), wineEntityConverted.getWineClassification()),
                () -> assertEquals(wineEntity.getAlcoholContent(), wineEntityConverted.getAlcoholContent()),
                () -> assertEquals(wineEntity.getVolumeMl(), wineEntityConverted.getVolumeMl()),
                () -> assertEquals(wineEntity.getGrape(), wineEntityConverted.getGrape()),
                () -> assertEquals(wineEntity.getWinery(), wineEntityConverted.getWinery()),
                () -> assertEquals(wineEntity.getServiceTemperature(), wineEntityConverted.getServiceTemperature()),
                () -> assertEquals(wineEntity.getHarvest(), wineEntityConverted.getHarvest()),
                () -> assertEquals(wineEntity.getCountry(), wineEntityConverted.getCountry()),
                () -> assertEquals(wineEntity.getGuardTime(), wineEntityConverted.getGuardTime()),
                () -> assertEquals(wineEntity.getRegion(), wineEntityConverted.getRegion()),
                () -> assertEquals(wineEntity.getMaturation(), wineEntityConverted.getMaturation()),
                () -> assertEquals(wineEntity.getHarmonization(), wineEntityConverted.getHarmonization())
        );
    }

    @Test
    @DisplayName("Deve converter um WineEntity para WineOutputDTO")
    void testToOutputDTOUpdate() {
        wineEntity.setPrice(BigDecimal.valueOf(800.00));

        WineOutputDTO wineOutputDTOConverted = wineConverter.toOutputDTOUpdate(wineEntity, wineEntity.getId(), wineOutputDTO);
        assertAll(
                () -> assertNotNull(wineOutputDTOConverted),
                () -> assertEquals(wineEntity.getId(), wineOutputDTOConverted.getId()),
                () -> assertEquals(wineEntity.getName(), wineOutputDTOConverted.getName()),
                () -> assertEquals(BigDecimal.valueOf(800.00), wineOutputDTOConverted.getPrice()),
                () -> assertEquals(wineEntity.getPurchaseLocation(), wineOutputDTOConverted.getPurchaseLocation()),
                () -> assertEquals(wineEntity.getPurchaseDate(), wineOutputDTOConverted.getPurchaseDate()),
                () -> assertEquals(EnumConverter.toString(wineEntity.getWineType()), wineOutputDTOConverted.getWineType()),
                () -> assertEquals(EnumConverter.toString(wineEntity.getWineClassification()), wineOutputDTOConverted.getWineClassification()),
                () -> assertEquals(wineEntity.getAlcoholContent(), wineOutputDTOConverted.getAlcoholContent()),
                () -> assertEquals(String.valueOf(wineEntity.getVolumeMl()), wineOutputDTOConverted.getVolumeMl()),
                () -> assertEquals(wineEntity.getGrape(), wineOutputDTOConverted.getGrape()),
                () -> assertEquals(wineEntity.getWinery(), wineOutputDTOConverted.getWinery()),
                () -> assertEquals(wineEntity.getServiceTemperature(), wineOutputDTOConverted.getServiceTemperature()),
                () -> assertEquals(wineEntity.getHarvest(), wineOutputDTOConverted.getHarvest()),
                () -> assertEquals(wineEntity.getCountry(), wineOutputDTOConverted.getCountry()),
                () -> assertEquals(wineEntity.getGuardTime(), wineOutputDTOConverted.getGuardTime()),
                () -> assertEquals(wineEntity.getRegion(), wineOutputDTOConverted.getRegion()),
                () -> assertEquals(wineEntity.getMaturation(), wineOutputDTOConverted.getMaturation()),
                () -> assertEquals(wineEntity.getHarmonization(), wineOutputDTOConverted.getHarmonization())
        );
    }

    @Test
    @DisplayName("Deve converter uma lista de WineEntity em uma lista de WineOutputDTO")
    void testToOutputDTOList() {
        List<WineOutputDTO> wineOutputDTOConverted = wineConverter.toOutputDTOList(List.of(wineEntity));
        assertAll(
                () -> assertFalse(wineOutputDTOConverted.isEmpty()),
                () -> assertEquals(1, wineOutputDTOConverted.size()),
                () -> assertNotNull(wineOutputDTOConverted),
                () -> assertEquals(wineOutputDTO.getId(), wineOutputDTOConverted.get(0).getId()),
                () -> assertEquals(wineOutputDTO.getName(), wineOutputDTOConverted.get(0).getName()),
                () -> assertEquals(wineOutputDTO.getPrice(), wineOutputDTOConverted.get(0).getPrice()),
                () -> assertEquals(wineOutputDTO.getPurchaseLocation(), wineOutputDTOConverted.get(0).getPurchaseLocation()),
                () -> assertEquals(wineOutputDTO.getPurchaseDate(), wineOutputDTOConverted.get(0).getPurchaseDate()),
                () -> assertEquals(wineOutputDTO.getWineType(), wineOutputDTOConverted.get(0).getWineType()),
                () -> assertEquals(wineOutputDTO.getWineClassification(), wineOutputDTOConverted.get(0).getWineClassification()),
                () -> assertEquals(wineOutputDTO.getAlcoholContent(), wineOutputDTOConverted.get(0).getAlcoholContent()),
                () -> assertEquals(wineOutputDTO.getVolumeMl(), wineOutputDTOConverted.get(0).getVolumeMl()),
                () -> assertEquals(wineOutputDTO.getGrape(), wineOutputDTOConverted.get(0).getGrape()),
                () -> assertEquals(wineOutputDTO.getWinery(), wineOutputDTOConverted.get(0).getWinery()),
                () -> assertEquals(wineOutputDTO.getServiceTemperature(), wineOutputDTOConverted.get(0).getServiceTemperature()),
                () -> assertEquals(wineOutputDTO.getHarvest(), wineOutputDTOConverted.get(0).getHarvest()),
                () -> assertEquals(wineOutputDTO.getCountry(), wineOutputDTOConverted.get(0).getCountry()),
                () -> assertEquals(wineOutputDTO.getGuardTime(), wineOutputDTOConverted.get(0).getGuardTime()),
                () -> assertEquals(wineOutputDTO.getRegion(), wineOutputDTOConverted.get(0).getRegion()),
                () -> assertEquals(wineOutputDTO.getMaturation(), wineOutputDTOConverted.get(0).getMaturation()),
                () -> assertEquals(wineOutputDTO.getHarmonization(), wineOutputDTOConverted.get(0).getHarmonization())
        );
    }

    private WineOutputDTO createWineOutputDTO() {
        return WineOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE.getCode())
                .wineClassification(EnumWineClassification.DRYWINE.getCode())
                .alcoholContent("12.5")
                .volumeMl("750")
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature("17.0")
                .harvest("2020")
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

    private WineInputDTO createWineInputDTO() {
        return WineInputDTO.builder()
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE.getCode())
                .wineClassification(EnumWineClassification.DRYWINE.getCode())
                .alcoholContent("12.5")
                .volumeMl("750")
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature("17.0")
                .harvest("2020")
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

    private WineEntity createWineEntity() {
        return WineEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .alcoholContent("12.5")
                .volumeMl(750)
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature("17.0")
                .harvest("2020")
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

}