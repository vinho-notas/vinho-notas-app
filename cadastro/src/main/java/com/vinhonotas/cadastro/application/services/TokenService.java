package com.vinhonotas.cadastro.application.services;

import com.vinhonotas.cadastro.domain.entities.UserEntity;

public interface TokenService {

    String generateToken(UserEntity user);
    String validateToken(String token);

}
