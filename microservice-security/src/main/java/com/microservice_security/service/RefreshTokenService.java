package com.microservice_security.service;

import com.microservice_security.entity.RefreshToken;
import com.microservice_security.exceptions.ExpiredRefreshTokenException;
import com.microservice_security.repository.RefreshTokenRepository;
import com.microservice_security.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserCredentialRepository userRepository;

    public RefreshToken createRefreshToken(String username){
        RefreshToken refreshToken = RefreshToken.builder()
                .userCredential(userRepository.findByUsername(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(1209600000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }



    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new ExpiredRefreshTokenException("El token de actualización " + token.getToken() +  " esta vencido. Inicia sesión de nuevo");
        }
        return token;
    }



    public Optional<RefreshToken> findByUsername(String username){
        return refreshTokenRepository.findByUsername(username);
    }

    public void DeleteRefreshToken(RefreshToken refreshToken){
        refreshTokenRepository.delete(refreshToken);
    }

}
