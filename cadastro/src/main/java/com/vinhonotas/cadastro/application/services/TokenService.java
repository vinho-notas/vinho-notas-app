package com.vinhonotas.cadastro.application.services;

import com.vinhonotas.cadastro.domain.entities.UserEntity;

public interface TokenService {

    public String generateToken(UserEntity user);
    public String validateToken(String token);

}
