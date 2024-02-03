package com.vinhonotas.avaliacao.domain.entities;

import com.vinhonotas.avaliacao.domain.enums.EnumPointScale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Tbpointscale", schema = "avaliacao")
public class PointScaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @Column(name = "whattasted")
    private String whatTasted;
    @Column(name = "whentasted")
    private String whenTasted;
    @Column(name = "whatsaw")
    private String whatSaw;
    @Column(name = "whataromas")
    private String whatAromas;
    @Column(name = "whatflavors")
    private String whatFlavors;
    @Column(name = "whatopinion")
    private String whatOpinion;
    @Column(name = "pointscale")
    @Enumerated(EnumType.STRING)
    private EnumPointScale pointScale;
}
