package com.vinhonotas.cadastro.domain.entities;

import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Tbsystemuser", schema = "cadastro")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @Column(name = "enumprofile")
    @Enumerated(EnumType.STRING)
    private EnumProfile enumProfile;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "dthreg")
    private LocalDateTime dthreg;

    @Column(name = "userreg")
    private String userreg;

    @Column(name = "dthalt")
    private LocalDateTime dthalt;

    @Column(name = "useralt")
    private String useralt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.enumProfile == EnumProfile.OENOPHILE) {
            return List.of(new SimpleGrantedAuthority("ROLE_OENOPHILE"));
        } else if (this.enumProfile == EnumProfile.SOMMELIER) {
            return List.of(new SimpleGrantedAuthority("ROLE_SOMMELIER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_PARTNER"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
