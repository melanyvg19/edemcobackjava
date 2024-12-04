package com.microservice_remitentes.service;

import com.microservice_remitentes.entities.Email;
import com.microservice_remitentes.exceptions.EmailConflictException;
import com.microservice_remitentes.exceptions.EmailNotFoundException;
import com.microservice_remitentes.persistence.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public Optional<List<Email>> findAll() {
        return Optional.of(emailRepository.findAll());
    }

    @Override
    public Optional<Long> createEmail(Email email) throws EmailConflictException {
        List<Email> emailList = emailRepository.findByIdPlantaAndEmail(email.getIdPlanta(), email.getEmail());
        if (!emailList.isEmpty()){
            throw new EmailConflictException("El email " + email.getEmail() + " ya existe para la planta " + email.getIdPlanta());
        }
        emailRepository.save(email);
        return Optional.ofNullable(email.getIdEmail());
    }

    @Override
    public Optional<Email> updateEmail(Email email) throws EmailNotFoundException {
        Email editEmail = emailRepository.findById(String.valueOf(email.getIdEmail())).orElseThrow(() -> new EmailNotFoundException("Email no encontrado"));
        editEmail.setEmail(email.getEmail());
        editEmail.setIdPlanta(email.getIdPlanta());
        emailRepository.save(editEmail);
        return Optional.of(editEmail);
    }

    @Override
    public Optional<String> deleteEmail(Long idEmail) throws EmailNotFoundException {
        emailRepository.findById(idEmail.toString()).orElseThrow(() -> new EmailNotFoundException("Email no encontrado"));
        emailRepository.deleteById(idEmail.toString());
        return Optional.of("Email eliminado con exito");
    }

    @Override
    public Optional<List<Email>> findByIdPlanta(String idPlanta) throws EmailNotFoundException {
        List<Email> emailList = emailRepository.findByIdPlanta(idPlanta);
        if (emailList.isEmpty()){
            throw new EmailNotFoundException("Email no encotnrado");
        }
        return Optional.ofNullable(emailRepository.findByIdPlanta(idPlanta));
    }
}
