package com.microservice_remitentes.controller;

import com.microservice_remitentes.entities.Email;
import com.microservice_remitentes.exceptions.EmailConflictException;
import com.microservice_remitentes.exceptions.EmailNotFoundException;
import com.microservice_remitentes.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(emailService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmail(@RequestBody Email email) throws EmailConflictException {
        return ResponseEntity.ok(emailService.createEmail(email));
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmail(@RequestParam("idEmail")Long idEmail) throws EmailNotFoundException {
        return ResponseEntity.ok(emailService.deleteEmail(idEmail));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmail(@RequestBody Email email) throws EmailNotFoundException {
        return ResponseEntity.ok(emailService.updateEmail(email));
    }

    @GetMapping("/find")
    public ResponseEntity<?> findAll(@RequestParam("idPlanta") String idPlanta) throws EmailNotFoundException {
        return ResponseEntity.ok(emailService.findByIdPlanta(idPlanta));
    }

}
