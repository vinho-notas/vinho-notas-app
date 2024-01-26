package com.vinhonotas.cadastro.domain.entities;

import com.vinhonotas.cadastro.domain.enums.EnumProfile;
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
@Table(name = "Tbsystemuser", schema = "cadastro")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;
    @Column(name = "enumprofile")
    @Enumerated(EnumType.STRING)
    private EnumProfile enumProfile;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}