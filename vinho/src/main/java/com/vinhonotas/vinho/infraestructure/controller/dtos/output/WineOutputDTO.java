package com.vinhonotas.vinho.infraestructure.controller.dtos.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class WineOutputDTO {

    @Schema(description = "Identificador do vinho", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "SKU do vinho", example = "SKU-123456")
    private String sku;
    @Schema(description = "Nome do vinho", example = "Miolo Reserva Cabernet Sauvignon")
    private String name;
    @Schema(description = "Preço da compra", example = "100.00")
    private BigDecimal price;
    @Schema(description = "Local da compra", example = "Supermercado")
    private String purchaseLocation;
    @Schema(description = "Data da compra", example = "2021-10-10")
    private LocalDate purchaseDate;
    @Schema(description = "Tipo de vinho", example = "Vinho Tinto")
    private String wineType;
    @Schema(description = "Classificação do vinho", example = "Vinho Seco")
    private String wineClassification;
    @Schema(description = "Teor alcoólico", example = "13.5")
    private String alcoholContent;
    @Schema(description = "Volume em ml", example = "750")
    private String volumeMl;
    @Schema(description = "Uva", example = "Cabernet Sauvignon")
    private String grape;
    @Schema(description = "Produtor", example = "Miolo")
    private String winery;
    @Schema(description = "Temperatura de serviço", example = "18.0")
    private String serviceTemperature;
    @Schema(description = "Safra", example = "2019")
    private String harvest;
    @Schema(description = "País de origem", example = "Brasil")
    private String country;
    @Schema(description = "Guarda", example = "5 anos")
    private String guardTime;
    @Schema(description = "Região de origem", example = "Vale dos Vinhedos")
    private String region;
    @Schema(description = "Maturação", example = "12 meses")
    private String maturation;
    @Schema(description = "Harmonização", example = "Carnes vermelhas")
    private String harmonization;

}
