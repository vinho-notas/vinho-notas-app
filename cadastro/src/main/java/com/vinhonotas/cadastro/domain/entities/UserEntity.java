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
@Table(name = "wine_system_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;
    @Column(name = "enum_profile")
    private EnumProfile profile;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
