package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.application.services.AuthorizationService;
import com.vinhonotas.cadastro.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        return repository.findByEmail(userName);
    }

}
