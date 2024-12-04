package com.microservice_remitentes.service;

import com.microservice_remitentes.entities.Email;
import com.microservice_remitentes.exceptions.EmailConflictException;
import com.microservice_remitentes.exceptions.EmailNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IEmailService {

    Optional<List<Email>> findAll();

    Optional<Long> createEmail(Email email) throws EmailConflictException;

    Optional<Email> updateEmail(Email email) throws EmailNotFoundException;

    Optional<String> deleteEmail(Long idEmail) throws EmailNotFoundException;

    Optional<List<Email>> findByIdPlanta(String idPlanta) throws EmailNotFoundException;

}
