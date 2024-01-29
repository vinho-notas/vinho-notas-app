package com.vinhonotas.degustacao.domain.entities;

import com.vinhonotas.degustacao.domain.enums.EnumTastingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Tbtastingcard", schema = "degustacao")
public class TastingCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @Column(name = "tastingdata")
    private LocalDate tastingData;
    @Column(name = "tastingtype")
    @Enumerated(EnumType.STRING)
    private EnumTastingType tastingType;
    @ManyToOne
    @Column(name = "tasting")
    @JoinColumn(name = "tasting_id", nullable = false)
    private TastingEntity tasting;

}
