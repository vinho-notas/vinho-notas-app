package com.vinhonotas.cadastro.domain.entities;

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
@Table(name = "state")
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @Column(name = "state")
    private String stateName;
    @Column(name = "uf")
    private String uf;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
}
