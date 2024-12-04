package com.microservice_security.service;

import com.microservice_security.dto.TokenDTO;
import com.microservice_security.entity.UserCredential;
import com.microservice_security.exceptions.BadUserCredentialsException;
import com.microservice_security.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public TokenDTO saveUser(UserCredential userCredential) throws BadUserCredentialsException {
            if (userCredentialRepository.findByUsername(userCredential.getUsername()).isPresent()){
                throw new BadUserCredentialsException("Ya existe un usuario con este correo: "+ userCredential.getUsername());
            }

            String decodedPassword = userCredential.getPassword();

            userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
            userCredentialRepository.save(userCredential);

            return TokenDTO.builder()
                    .accessToken(jwtService.generateToken(userCredential.getUsername(),decodedPassword))
                    .refreshToken(refreshTokenService.createRefreshToken(userCredential.getUsername()).getToken())
                    .build();
    }

    public String generateToken(String username, String password){
        return jwtService.generateToken(username,password);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
