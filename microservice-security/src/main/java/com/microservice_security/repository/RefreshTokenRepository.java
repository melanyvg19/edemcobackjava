package com.microservice_security.repository;

import com.microservice_security.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Query("SELECT r FROM RefreshToken r INNER JOIN UserCredential u ON r.userCredential.idUser = u.idUser WHERE u.username = :username")
    Optional<RefreshToken> findByUsername(String username);

}
