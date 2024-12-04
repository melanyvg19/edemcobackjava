package com.microservice_remitentes.persistence;

import com.microservice_remitentes.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email,String> {

    @Query("SELECT e FROM Email e WHERE e.idPlanta = :idPlanta")
    List<Email> findByIdPlanta(String idPlanta);

    @Query("SELECT e FROM Email e WHERE e.idPlanta = :idPlanta AND e.email = :email")
    List<Email> findByIdPlantaAndEmail(String idPlanta, String email);
}
